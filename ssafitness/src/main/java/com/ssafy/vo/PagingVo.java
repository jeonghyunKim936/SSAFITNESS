package com.ssafy.vo;

import lombok.Data;

@Data
public class PagingVo {
    /** 한 페이지당 게시글 수 **/
    private int pageSize = 8;
     
    /** 한 블럭(range)당 페이지 수 **/
    private int rangeSize = 10;
     
    /** 현재 페이지 **/
    private int curPage = 1;
     
    /** 현재 블럭(range) **/
    private int curRange = 1;
     
    /** 총 게시글 수 **/
    private int listCnt;
     
    /** 총 페이지 수 **/
    private int pageCnt;
     
    /** 총 블럭(range) 수 **/
    private int rangeCnt;
     
    /** 시작 페이지 **/
    private int startPage = 1;
     
    /** 끝 페이지 **/
    private int endPage = 1;
     
    /** 시작 index **/
    private int startIndex = 0;
     
    /** 이전 페이지 **/
    private int prevPage;
     
    /** 다음 페이지 **/
    private int nextPage;
     
    public PagingVo(int listCnt, int curPage){
         
        /**
         * 페이징 처리 순서
         * 1. 총 페이지수
         * 2. 총 블럭(range)수
         * 3. range setting
         */
         
        // 총 게시물 수와 현재 페이지를 Controller로 부터 받아온다.
        /** 현재페이지 **/
        setCurPage(curPage);
        /** 총 게시물 수 **/
        setListCnt(listCnt);
         
        /** 1. 총 페이지 수 **/
        setPageCnt(listCnt);
        /** 2. 총 블럭(range)수 **/
        setRangeCnt(pageCnt);
        /** 3. 블럭(range) setting **/
        rangeSetting(curPage);
         
        /** DB 질의를 위한 startIndex 설정 **/
        setStartIndex(curPage);
    }
  
    public void setPageCnt(int listCnt) {
        this.pageCnt = (int) Math.ceil(listCnt*1.0/pageSize);
    }
    public void setRangeCnt(int pageCnt) {
        this.rangeCnt = (int) Math.ceil(pageCnt*1.0/rangeSize);
    }
    public void rangeSetting(int curPage){
        
    	// 현재 range 값 찾기
        setCurRange(curPage);
        // 시작 및 끝 페이지
        this.startPage = (curRange - 1) * rangeSize + 1;
        this.endPage = startPage + rangeSize - 1;
        // 끝 페이지가  총페이지를 넘어버리는 경우 총페이지 까지만
        if(endPage > pageCnt){
            this.endPage = pageCnt;
        }
         
        this.prevPage = curPage - 1;
        this.nextPage = curPage + 1;
    }
    public void setCurRange(int curPage) {
        this.curRange = (int)((curPage-1)/rangeSize) + 1;
    }
    public void setStartIndex(int curPage) {
        this.startIndex = (curPage-1) * pageSize;
    }
}

package com.ssafy.mapper;


import java.util.List;

import com.ssafy.vo.Guide;
import com.ssafy.vo.PtReply;

public interface PtReplyMapper {
	public void insert(PtReply newPtReply);
	public List<PtReply> replyList(int pt_id);
	public void modify(int reply_id, String content);
	public void delete(int reply_id);
	public void insertMovie(PtReply newPtReply);
	public List<PtReply> bestList(int pt_id);
	public List<String> replyLikeList(int reply_id);
	public List<String> replyLikeInsert(int reply_id, String nickname);
	public List<String> replyLikeDelete(int reply_id, String nickname);
	public void replyUpdatePlus(int reply_id);
	public void replyUpdateMinus(int reply_id);
	public void updateCheckedLike(int reply_id, int checkedLike);
}

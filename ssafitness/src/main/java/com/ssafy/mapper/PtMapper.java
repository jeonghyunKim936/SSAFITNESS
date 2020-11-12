package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.Pt;

public interface PtMapper {

	public void insertPt(Pt pt);
	public String findMovieById(String pt_id);
	public void deletePt(String pt_id);
	public Pt searchById(String pt_id);
	public List<Pt> searchPt(int startIndex, int cntPerPage);
	public int getPtListCount();
	public void updatePt(Pt pt);

}

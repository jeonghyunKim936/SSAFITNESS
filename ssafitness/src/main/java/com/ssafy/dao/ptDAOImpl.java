package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.PtMapper;
import com.ssafy.vo.Pt;

@Repository
public class ptDAOImpl implements PtDAO{
	
	@Autowired
	PtMapper mapper;
	
	@Override
	public void insertPt(Pt pt) {
		mapper.insertPt(pt);
		return;
	}

	@Override
	public String findMovieById(String pt_id) {
		return mapper.findMovieById(pt_id);
	}

	@Override
	public void deletePt(String pt_id) {
		mapper.deletePt(pt_id);
		return;
		
	}

	@Override
	public Pt searchById(String pt_id) {
		return mapper.searchById(pt_id);
	}

	@Override
	public List<Pt> searchPt(int startIndex, int cntPerPage) {
		return mapper.searchPt(startIndex, cntPerPage);
	}

	@Override
	public int getPtListCount() {
		return mapper.getPtListCount();
	}

	@Override
	public void updatePt(Pt pt) {
		mapper.updatePt(pt);
		return;
		
	}
	
}

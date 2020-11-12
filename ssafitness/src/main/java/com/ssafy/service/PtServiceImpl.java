package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.PtDAO;
import com.ssafy.vo.Pt;

@Service
public class PtServiceImpl implements PtService{
	
	@Autowired
	PtDAO dao;
	
	@Override
	public void insertPt(Pt pt) {
		dao.insertPt(pt);
		return;
	}

	@Override
	public String findMovieById(String pt_id) {
		return dao.findMovieById(pt_id);
	}

	@Override
	public void deletePt(String pt_id) {
		dao.deletePt(pt_id);
		return;
		
	}

	@Override
	public Pt searchById(String pt_id) {
		return dao.searchById(pt_id);
	}

	@Override
	public List<Pt> searchPt(int startIndex, int cntPerPage) {
		return dao.searchPt(startIndex, cntPerPage);
	}


	@Override
	public int getPtListCount() {
		return dao.getPtListCount();
	}

	@Override
	public void updatePt(Pt pt) {
		dao.updatePt(pt);
		return;
	}

	
}

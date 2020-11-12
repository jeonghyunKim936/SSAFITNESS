package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.GuideDAO;
import com.ssafy.vo.Guide;

@Service
public class GuideServiceImpl implements GuideService{
	
	@Autowired
	GuideDAO dao;

	@Override
	public List<Guide> guideList() {
		return dao.guideList();
	}

	@Override
	public List<Guide> partList(String part) {
		return dao.partList(part);
	}

	
	
	
	
}

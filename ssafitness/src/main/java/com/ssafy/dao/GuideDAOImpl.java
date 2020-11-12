package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.GuideMapper;
import com.ssafy.vo.Guide;

@Repository
public class GuideDAOImpl implements GuideDAO{
	
	@Autowired
	GuideMapper mapper;

	@Override	
	public List<Guide> guideList() {
		// TODO Auto-generated method stub
		return mapper.guideList();
	}

	@Override
	public List<Guide> partList(String part) {
		return mapper.partList(part);
	}
	
	
}

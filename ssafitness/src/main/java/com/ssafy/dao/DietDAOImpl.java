package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.DietMapper;
import com.ssafy.vo.Diet;

@Repository
public class DietDAOImpl implements DietDAO{
	
	@Autowired
	DietMapper mapper;

	@Override
	public List<Diet> dietList() {
		return mapper.dietList();
	}
	
	
}

package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.DietDAO;
import com.ssafy.vo.Diet;

@Service
public class DietServiceImpl implements DietService{
	
	@Autowired
	DietDAO dao;

	@Override
	public List<Diet> dietList() {
		
		return dao.dietList();
	}

	
	
	
	
}

package com.ssafy.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.DiaryDAO;
import com.ssafy.vo.Diary;
import com.ssafy.vo.Exercise;

@Service
public class DiaryServiceImpl implements DiaryService{
	
	@Autowired
	DiaryDAO dao;	

	@Override
	public List<Diary> monthList(int year, int month, String nickname) {
		return dao.monthList(year,month,nickname);
	}


	@Override
	public Diary select(int year, int month, int day,String nickname) {
		// TODO Auto-generated method stub
		return dao.select(year,month,day,nickname);
	}

	@Override
	public void insert(Diary newDiary) {
		dao.insert(newDiary);
		
	}



	@Override
	public void modify(Diary modifyDiary) {
		dao.modify(modifyDiary);
		
	}


	@Override
	public void delete(HashMap map) {
		dao.delete(map);
		
	}


	@Override
	public void insertExercise(Exercise newExercise) {
		dao.insertExercise(newExercise);
		
	}


	@Override
	public Integer takeDiaryId(int year, int month, int day, String nickname) {
		return dao.takeDiaryId(year, month, day, nickname);
	}


	@Override
	public void deleteExercise(int diary_year, int diary_month, int diary_day) {
		dao.deleteExercise(diary_year,diary_month,diary_day);
		
	}


	@Override
	public List<Exercise> selectExercise(int num) {
		return dao.selectExercise(num);
	}


	@Override
	public List<Diary> ListLatest(int year, int month, String nickname) {
		return dao.ListLatest(year,month,nickname);
	}




	
	
	
	
}

package com.ssafy.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.DiaryMapper;
import com.ssafy.vo.Diary;
import com.ssafy.vo.Exercise;

@Repository
public class DiaryDAOImpl implements DiaryDAO{
	
	@Autowired
	DiaryMapper mapper;

	@Override
	public List<Diary> monthList(int year, int month, String nickname) {
		return mapper.monthList(year,month,nickname);
	}
	
	@Override
	public Diary select(int year, int month, int day, String nickname) {
		return mapper.select(year,month,day,nickname);
	}

	@Override
	public void insert(Diary newDiary) {
		mapper.insert(newDiary);
		
	}

	@Override
	public void modify(Diary modifyDiary) {
		mapper.modify(modifyDiary);
		
	}

	@Override
	public void delete(HashMap map) {
		mapper.delete(map);
		
	}

	@Override
	public void insertExercise(Exercise newExercise) {
		mapper.insertExercise(newExercise);
		
	}

	@Override
	public Integer takeDiaryId(int year, int month, int day, String nickname) {
		return mapper.takeDiaryId(year,month,day, nickname);
	}

	@Override
	public void deleteExercise(int diary_year, int diary_month, int diary_day) {
		mapper.deleteExercise(diary_year,diary_month,diary_day);
		
	}

	@Override
	public List<Exercise> selectExercise(int num) {
		return mapper.selectExercise(num);
	}

	@Override
	public List<Diary> ListLatest(int year, int month, String nickname) {
		return mapper.ListLatest(year,month,nickname);
	}

	
	
	
}

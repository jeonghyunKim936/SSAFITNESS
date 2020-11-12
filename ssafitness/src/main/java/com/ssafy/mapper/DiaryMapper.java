package com.ssafy.mapper;


import java.util.HashMap;
import java.util.List;

import com.ssafy.vo.Diary;
import com.ssafy.vo.Exercise;

public interface DiaryMapper {
	public List<Diary> monthList(int year, int month, String nickname);

	public void insert(Diary newDiary);

	public void modify(Diary modifyDiary);

	public Diary select(int year, int month, int day, String nickname);

	public void delete(HashMap map);

	public void insertExercise(Exercise newExercise);

	public Integer takeDiaryId(int year, int month, int day, String nickname);

	public void deleteExercise(int diary_year, int diary_month, int diary_day);

	public List<Exercise> selectExercise(int num);

	public List<Diary> ListLatest(int year, int month, String nickname);
	
}

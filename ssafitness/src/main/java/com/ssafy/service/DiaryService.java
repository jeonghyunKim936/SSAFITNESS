package com.ssafy.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.vo.Diary;
import com.ssafy.vo.Exercise;
import com.ssafy.vo.Guide;

public interface DiaryService {
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

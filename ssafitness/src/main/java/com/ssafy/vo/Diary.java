package com.ssafy.vo;

import java.util.Date;

public class Diary {
	private int diary_id;
	private int diary_year;
	private int diary_month;
	private int diary_day;
	private String exercise_start;
	private String exercise_end;
	private int diary_like;
	private String diary_comment;
	private String nickname;
	private String weight;
	
	public Diary() {
		super();
	}
	
	

	public Diary(String exercise_start, String exercise_end, int diary_like, String diary_comment) {
		super();
		this.exercise_start = exercise_start;
		this.exercise_end = exercise_end;
		this.diary_like = diary_like;
		this.diary_comment = diary_comment;
	}



	public Diary(int diary_year, int diary_month, int diary_day, String exercise_start, String exercise_end,
			int diary_like, String diary_comment, String nickname, String weight) {
		super();
		this.diary_year = diary_year;
		this.diary_month = diary_month;
		this.diary_day = diary_day;
		this.exercise_start = exercise_start;
		this.exercise_end = exercise_end;
		this.diary_like = diary_like;
		this.diary_comment = diary_comment;
		this.nickname = nickname;
		this.weight = weight;
	}



	public Diary(int diary_id, int diary_year, int diary_month, int diary_day, String exercise_start,
			String exercise_end, int diary_like, String diary_comment, String nickname, String weight) {
		super();
		this.diary_id = diary_id;
		this.diary_year = diary_year;
		this.diary_month = diary_month;
		this.diary_day = diary_day;
		this.exercise_start = exercise_start;
		this.exercise_end = exercise_end;
		this.diary_like = diary_like;
		this.diary_comment = diary_comment;
		this.nickname = nickname;
		this.weight = weight;
	}

	public int getDiary_id() {
		return diary_id;
	}

	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}

	public int getDiary_year() {
		return diary_year;
	}

	public void setDiary_year(int diary_year) {
		this.diary_year = diary_year;
	}

	public int getDiary_month() {
		return diary_month;
	}

	public void setDiary_month(int diary_month) {
		this.diary_month = diary_month;
	}

	public int getDiary_day() {
		return diary_day;
	}

	public void setDiary_day(int diary_day) {
		this.diary_day = diary_day;
	}

	public String getExercise_start() {
		return exercise_start;
	}

	public void setExercise_start(String exercise_start) {
		this.exercise_start = exercise_start;
	}

	public String getExercise_end() {
		return exercise_end;
	}

	public void setExercise_end(String exercise_end) {
		this.exercise_end = exercise_end;
	}

	public int getDiary_like() {
		return diary_like;
	}

	public void setDiary_like(int diary_like) {
		this.diary_like = diary_like;
	}

	public String getDiary_comment() {
		return diary_comment;
	}

	public void setDiary_comment(String diary_comment) {
		this.diary_comment = diary_comment;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getWeight() {
		return weight;
	}



	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	
}

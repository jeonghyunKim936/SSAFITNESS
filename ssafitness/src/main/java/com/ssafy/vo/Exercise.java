package com.ssafy.vo;

public class Exercise {
	private int exercise_id;
	private String exercise_type;
	private String exercise_name;
	private int exercise_weight;
	private int exercise_raps;
	private int exercise_set;
	private int diary_id;
	
	public Exercise() {
		super();
	}

	
	public Exercise(int exercise_id, String exercise_type, String exercise_name, int exercise_weight, int exercise_raps,
			int exercise_set, int diary_id) {
		super();
		this.exercise_id = exercise_id;
		this.exercise_type = exercise_type;
		this.exercise_name = exercise_name;
		this.exercise_weight = exercise_weight;
		this.exercise_raps = exercise_raps;
		this.exercise_set = exercise_set;
		this.diary_id = diary_id;
	}



	public Exercise(String exercise_type, String exercise_name, int exercise_weight, int exercise_raps,
			int exercise_set, int diary_id) {
		super();
		this.exercise_type = exercise_type;
		this.exercise_name = exercise_name;
		this.exercise_weight = exercise_weight;
		this.exercise_raps = exercise_raps;
		this.exercise_set = exercise_set;
		this.diary_id = diary_id;
	}


	public int getExercise_id() {
		return exercise_id;
	}

	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}

	public String getExercise_type() {
		return exercise_type;
	}

	public void setExercise_type(String exercise_type) {
		this.exercise_type = exercise_type;
	}

	public String getExercise_name() {
		return exercise_name;
	}

	public void setExercise_name(String exercise_name) {
		this.exercise_name = exercise_name;
	}

	public int getExercise_weight() {
		return exercise_weight;
	}

	public void setExercise_weight(int exercise_weight) {
		this.exercise_weight = exercise_weight;
	}

	public int getExercise_raps() {
		return exercise_raps;
	}

	public void setExercise_raps(int exercise_raps) {
		this.exercise_raps = exercise_raps;
	}

	public int getExercise_set() {
		return exercise_set;
	}

	public void setExercise_set(int exercise_set) {
		this.exercise_set = exercise_set;
	}

	public int getDiary_id() {
		return diary_id;
	}

	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	
	

	
	
	
}

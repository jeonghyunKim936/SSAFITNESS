package com.ssafy.vo;

public class Diet {
	private int food_id;
	private String food_name;
	private int food_kcal;
	private int food_carbon;
	private int food_protein;
	private int food_fat;
	private int food_natrium;
	private int food_cnt;

	public Diet() {
		super();
	}

	public Diet(int food_id, String food_name, int food_kcal, int food_carbon, int food_protein, int food_fat,
			int food_natrium, int food_cnt) {
		super();
		this.food_id = food_id;
		this.food_name = food_name;
		this.food_kcal = food_kcal;
		this.food_carbon = food_carbon;
		this.food_protein = food_protein;
		this.food_fat = food_fat;
		this.food_natrium = food_natrium;
		this.food_cnt = food_cnt;
	}

	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public int getFood_kcal() {
		return food_kcal;
	}

	public void setFood_kcal(int food_kcal) {
		this.food_kcal = food_kcal;
	}

	public float getFood_carbon() {
		return food_carbon;
	}

	public void setFood_carbon(int food_carbon) {
		this.food_carbon = food_carbon;
	}

	public float getFood_protein() {
		return food_protein;
	}

	public void setFood_protein(int food_protein) {
		this.food_protein = food_protein;
	}

	public float getFood_fat() {
		return food_fat;
	}

	public void setFood_fat(int food_fat) {
		this.food_fat = food_fat;
	}

	public float getFood_natrium() {
		return food_natrium;
	}

	public void setFood_natrium(int food_natrium) {
		this.food_natrium = food_natrium;
	}
	
	public int getFood_cnt() {
		return food_cnt;
	}
	
	public void setFood_cnt(int food_cnt) {
		this.food_cnt = food_cnt;
	}
	

}
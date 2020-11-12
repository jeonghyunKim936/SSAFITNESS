package com.ssafy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
	
	private String user_id;
	private String nickname;
	private String email;
	private String password;
	private String height;
	private String weight;
	private String gender;
	private String point;
	private String grade;
	
}

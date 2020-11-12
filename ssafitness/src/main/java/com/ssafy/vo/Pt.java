package com.ssafy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pt {
	String pt_id;
	String nickname;
	String pt_title;
	String pt_content;
	String pt_type;
	String pt_like;
	String pt_movie;
	String pt_date;
}

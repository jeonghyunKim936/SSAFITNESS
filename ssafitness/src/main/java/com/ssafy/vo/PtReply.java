package com.ssafy.vo;

import java.sql.Date;

public class PtReply {
	private int reply_id;
	private int pt_id;
	private String nickname;
	private String content;
	private int like;
	private Date date;
	private String reply_movie;
	private int checkedLike;
	private String grade;
	

	public PtReply() {
		super();
	}
	
	public PtReply(int reply_id, int pt_id, String nickname, String content, int like, Date date, String reply_movie,
			int checkedLike, String grade) {
		super();
		this.reply_id = reply_id;
		this.pt_id = pt_id;
		this.nickname = nickname;
		this.content = content;
		this.like = like;
		this.date = date;
		this.reply_movie = reply_movie;
		this.checkedLike = checkedLike;
		this.grade = grade;
	}

	
	public PtReply(int pt_id, String nickname, String content, int like) {
		super();
		this.pt_id = pt_id;
		this.nickname = nickname;
		this.content = content;
		this.like = like;
	}
	
	public PtReply(int pt_id, String nickname, String content, int like, String reply_movie) {
		super();
		this.pt_id = pt_id;
		this.nickname = nickname;
		this.content = content;
		this.like = like;
		this.reply_movie = reply_movie;
	}
	
	
	public PtReply(int reply_id, int pt_id, String nickname, String content, int like, Date date, String reply_movie) {
		super();
		this.reply_id = reply_id;
		this.pt_id = pt_id;
		this.nickname = nickname;
		this.content = content;
		this.like = like;
		this.date = date;
		this.reply_movie = reply_movie;
	}


	public PtReply(int reply_id, int pt_id, String nickname, String content, int like, Date date, String reply_movie,
			int checkedLike) {
		super();
		this.reply_id = reply_id;
		this.pt_id = pt_id;
		this.nickname = nickname;
		this.content = content;
		this.like = like;
		this.date = date;
		this.reply_movie = reply_movie;
		this.checkedLike = checkedLike;
	}

	public int getCheckedLike() {
		return checkedLike;
	}

	public void setCheckedLike(int checkedLike) {
		this.checkedLike = checkedLike;
	}

	public String getReply_movie() {
		return reply_movie;
	}

	public void setReply_movie(String reply_movie) {
		this.reply_movie = reply_movie;
	}

	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getPt_id() {
		return pt_id;
	}
	public void setPt_id(int pt_id) {
		this.pt_id = pt_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

}

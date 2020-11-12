package com.ssafy.vo;

import java.sql.Date;

public class QnA {
	private int qnaNo;
	private String qnaName;
	private String qnaTitle;
	private String qnaContent;
	private String qnaDate;

	public QnA() {
		super();
	}

	public QnA(int qnaNo, String qnaName, String qnaTitle, String qnaContent, String qnaDate) {
		super();
		this.qnaNo = qnaNo;
		this.qnaName = qnaName;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaDate = qnaDate;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaName() {
		return qnaName;
	}

	public void setQnaName(String qnaName) {
		this.qnaName = qnaName;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public String getQnaDate() {
		return qnaDate;
	}

	public void setQnaDate(String qnaDate) {
		this.qnaDate = qnaDate;
	}
}

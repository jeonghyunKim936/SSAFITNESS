package com.ssafy.vo;

public class NoticeReply {
	private int noticeNo;
	private int replyNo;
	private String nickname;
	private String replyContent;
	private String replyDate;

	public NoticeReply() {
		super();
	}

	public NoticeReply(int noticeNo, int replyNo, String nickname, String replyContent, String replyDate) {
		super();
		this.noticeNo = noticeNo;
		this.replyNo = replyNo;
		this.nickname = nickname;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
}

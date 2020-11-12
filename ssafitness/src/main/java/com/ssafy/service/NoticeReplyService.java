package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.NoticeReply;

public interface NoticeReplyService {
	public List<NoticeReply> replyList(int noticeNo);

	public void add(NoticeReply noticeReply);

	public void delete(int replyNo);

	public void modify(int replyNo, String replyContent);
}

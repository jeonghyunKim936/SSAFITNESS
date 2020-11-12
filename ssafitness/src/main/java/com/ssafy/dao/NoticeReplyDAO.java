package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.NoticeReply;

public interface NoticeReplyDAO {
	public List<NoticeReply> replyList(int noticeNo);

	public void add(NoticeReply noticeReply);

	public void delete(int replyNo);

	public void modify(int replyNo, String replyContent);
}

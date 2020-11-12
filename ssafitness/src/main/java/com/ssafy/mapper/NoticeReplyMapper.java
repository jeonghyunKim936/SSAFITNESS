package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.NoticeReply;

public interface NoticeReplyMapper {
	public List<NoticeReply> replyList(int noticeNo);

	public void add(NoticeReply noticeReply);

	public void delete(int replyNo);

	public void modify(int replyNo, String replyContent);
}

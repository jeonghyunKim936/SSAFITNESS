package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.NoticeReplyDAO;
import com.ssafy.vo.NoticeReply;

@Service
public class NoticeReplyServiceImpl implements NoticeReplyService {

	@Autowired
	NoticeReplyDAO dao;

	@Override
	public List<NoticeReply> replyList(int noticeNo) {
		return dao.replyList(noticeNo);
	}

	@Override
	public void add(NoticeReply noticeReply) {
		dao.add(noticeReply);
	}

	@Override
	public void delete(int replyNo) {
		dao.delete(replyNo);
	}

	@Override
	public void modify(int replyNo, String replyContent) {
		dao.modify(replyNo, replyContent);
	}
}

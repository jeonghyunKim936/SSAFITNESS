package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.NoticeReplyMapper;
import com.ssafy.vo.NoticeReply;

@Repository
public class NoticeReplyDAOImpl implements NoticeReplyDAO {

	@Autowired
	NoticeReplyMapper mapper;

	@Override
	public List<NoticeReply> replyList(int noticeNo) {
		return mapper.replyList(noticeNo);
	}

	@Override
	public void add(NoticeReply noticeReply) {
		mapper.add(noticeReply);
	}

	@Override
	public void delete(int replyNo) {
		mapper.delete(replyNo);
	}

	@Override
	public void modify(int replyNo, String replyContent) {
		mapper.modify(replyNo, replyContent);
	}

}

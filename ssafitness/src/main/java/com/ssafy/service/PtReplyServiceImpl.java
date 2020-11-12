package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.PtReplyDAO;
import com.ssafy.vo.PtReply;

@Service
public class PtReplyServiceImpl implements PtReplyService {

	@Autowired
	PtReplyDAO dao;


	@Override
	public void insert(PtReply newPtReply) {
		dao.insert(newPtReply);
		
	}

	@Override
	public List<PtReply> replyList(int pt_id) {
		return dao.replyList(pt_id);
	}

	

	@Override
	public void modify(int reply_id, String content) {
		dao.modify(reply_id,content);
		
	}

	@Override
	public void delete(int reply_id) {
		dao.delete(reply_id);
		
	}

	@Override
	public void insertMovie(PtReply newPtReply) {
		dao.insertMovie(newPtReply);
		
	}

	@Override
	public List<PtReply> bestList(int pt_id) {
		return dao.bestList(pt_id);
	}

	@Override
	public List<String> replyLikeList(int reply_id) {
		return dao.replyLikeList(reply_id);
	}

	@Override
	public List<String> replyLikeInsert(int reply_id, String nickname) {
		return dao.replyLikeInsert(reply_id,nickname);
	}

	@Override
	public List<String> replyLikeDelete(int reply_id, String nickname) {
		return dao.replyLikeDelete(reply_id,nickname);
	}

	@Override
	public void replyUpdatePlus(int reply_id) {
		dao.replyUpdatePlus(reply_id);
		
	}

	@Override
	public void replyUpdateMinus(int reply_id) {
		dao.replyUpdateMinus(reply_id);
		
	}

	@Override
	public void updateCheckedLike(int reply_id, int checkedLike) {
		dao.updateCheckedLike(reply_id,checkedLike);
		
	}

	

	
	
	
}

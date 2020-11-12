package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.PtReplyMapper;
import com.ssafy.vo.PtReply;

@Repository
public class PtReplyDAOImpl implements PtReplyDAO{
	
	@Autowired
	PtReplyMapper mapper;

	
	@Override
	public void insert(PtReply newPtReply) {
		mapper.insert(newPtReply);
		
	}

	@Override
	public List<PtReply> replyList(int pt_id) {
		return mapper.replyList(pt_id);
	}


	@Override
	public void modify(int reply_id, String content) {
		mapper.modify(reply_id,content);
		
	}

	@Override
	public void delete(int reply_id) {
		mapper.delete(reply_id);
		
	}

	@Override
	public void insertMovie(PtReply newPtReply) {
		mapper.insertMovie(newPtReply);
		
	}

	@Override
	public List<PtReply> bestList(int pt_id) {
		return mapper.bestList(pt_id);
	}

	@Override
	public List<String> replyLikeList(int reply_id) {
		return mapper.replyLikeList(reply_id);
	}

	@Override
	public List<String> replyLikeInsert(int reply_id, String nickname) {
		return mapper.replyLikeInsert(reply_id,nickname);
	}

	@Override
	public List<String> replyLikeDelete(int reply_id, String nickname) {
		return mapper.replyLikeDelete(reply_id,nickname);
	}

	@Override
	public void replyUpdatePlus(int reply_id) {
		mapper.replyUpdatePlus(reply_id);
		
	}

	@Override
	public void replyUpdateMinus(int reply_id) {
		mapper.replyUpdateMinus(reply_id);
		
	}

	@Override
	public void updateCheckedLike(int reply_id, int checkedLike) {
		mapper.updateCheckedLike(reply_id,checkedLike);
		
	}

	
	
	
}

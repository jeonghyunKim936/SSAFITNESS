package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.QnAMapper;
import com.ssafy.vo.QnA;

@Repository
public class QnADAOImpl implements QnADAO {

	@Autowired
	QnAMapper mapper;

	@Override
	public List<QnA> qnaList() {
		return mapper.qnaList();
	}

	@Override
	public List<QnA> search(String type, String keyword) {
		return mapper.search(type, keyword);
	}

	@Override
	public void add(QnA qna) {
		mapper.add(qna);
	}

	@Override
	public void delete(int qnaNo) {
		mapper.delete(qnaNo);
	}

	@Override
	public void modify(QnA qna) {
		mapper.modify(qna);
	}

	@Override
	public QnA selectOne(int qnaNo) {
		return mapper.selectOne(qnaNo);
	}
}

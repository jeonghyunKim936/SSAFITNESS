package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.QnADAO;
import com.ssafy.vo.QnA;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	QnADAO dao;

	@Override
	public List<QnA> qnaList() {
		return dao.qnaList();
	}

	@Override
	public List<QnA> search(String type, String keyword) {
		return dao.search(type, keyword);
	}

	@Override
	public void add(QnA qna) {
		dao.add(qna);
	}

	@Override
	public void delete(int qnaNo) {
		dao.delete(qnaNo);
	}

	@Override
	public void modify(QnA qna) {
		dao.modify(qna);
	}

	@Override
	public QnA selectOne(int qnaNo) {
		return dao.selectOne(qnaNo);
	}
}

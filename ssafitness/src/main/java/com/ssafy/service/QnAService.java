package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.QnA;

public interface QnAService {
	public List<QnA> qnaList();

	public List<QnA> search(String type, String keyword);

	public void add(QnA qna);

	public void delete(int qnaNo);

	public void modify(QnA qna);

	public QnA selectOne(int qnaNo);
}

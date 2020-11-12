package com.ssafy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.service.QnAService;
import com.ssafy.vo.QnA;

@CrossOrigin(origins = { "*" })
@Controller
public class QnAController {
	@Autowired
	QnAService service;

	@GetMapping("/qna/list")
	public ResponseEntity<List<QnA>> qnaList() throws Exception {
		return new ResponseEntity<List<QnA>>(service.qnaList(), HttpStatus.OK);
	}

	@GetMapping("/qna/search/{type}/{keyword}")
	public ResponseEntity<List<QnA>> searhTitle(@PathVariable String type, @PathVariable String keyword)
			throws Exception {
		List<QnA> qna = service.search(type, keyword);
		return new ResponseEntity<List<QnA>>(service.search(type, keyword), HttpStatus.OK);
	}

	@PostMapping("/qna/add")
	public ResponseEntity<String> add(@RequestBody QnA qna) {
		ResponseEntity response = null;
		try {
			service.add(qna);
			response = new ResponseEntity<>("등록 성공", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>("등록 성공", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@DeleteMapping("/qna/delete/{qnaNo}")
	public ResponseEntity<String> delete(@PathVariable int qnaNo) {
		ResponseEntity response = null;
		try {
			service.delete(qnaNo);
			response = new ResponseEntity<>("삭제 성공", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>("삭제 실패", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PutMapping("/qna/modify")
	public ResponseEntity<String> modify(@RequestBody QnA qna) {
		ResponseEntity response = null;
		try {
			service.modify(qna);
			response = new ResponseEntity<>("수정 성공", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>("수정 실패", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/qna/selectOne/{qnaNo}")
	public ResponseEntity<QnA> seletOneQnA(@PathVariable int qnaNo) {
		ResponseEntity response = null;
		response = new ResponseEntity<QnA>(service.selectOne(qnaNo), HttpStatus.OK);
		return response;
	}
}

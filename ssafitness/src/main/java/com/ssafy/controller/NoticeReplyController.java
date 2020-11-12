package com.ssafy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.NoticeReplyService;
import com.ssafy.vo.NoticeReply;

@CrossOrigin(origins = { "*" })
@RestController
public class NoticeReplyController {
	@Autowired
	NoticeReplyService service;

	@GetMapping("noticeReply/{noticeNo}")
	public ResponseEntity<List<NoticeReply>> replyList(@PathVariable int noticeNo) throws Exception {
		return new ResponseEntity<List<NoticeReply>>(service.replyList(noticeNo), HttpStatus.OK);
	}

	@PostMapping("/noticeReply/add")
	public ResponseEntity<String> add(@RequestBody NoticeReply noticeReply) {
		ResponseEntity response = null;
		try {
			service.add(noticeReply);
			response = new ResponseEntity<>("등록 성공", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>("등록 성공", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@DeleteMapping("/noticeReply/delete/{replyNo}")
	public ResponseEntity<String> delete(@PathVariable int replyNo) {
		ResponseEntity response = null;
		try {
			service.delete(replyNo);
			response = new ResponseEntity<>("삭제 성공", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>("삭제 실패", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PutMapping("/noticeReply/modify/{replyNo}")
	public ResponseEntity<String> modify(@PathVariable int replyNo, @RequestParam("replyContent") String replyContent) {
		ResponseEntity response = null;
		try {
			service.modify(replyNo, replyContent);
			response = new ResponseEntity<>("수정 성공", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>("수정 실패", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}

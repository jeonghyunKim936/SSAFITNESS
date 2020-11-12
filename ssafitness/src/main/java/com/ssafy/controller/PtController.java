package com.ssafy.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.service.AmazonClient;
import com.ssafy.service.PtService;
import com.ssafy.vo.PagingVo;
import com.ssafy.vo.Pt;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/pt")
public class PtController {

	private AmazonClient amazonClient;

	@Autowired
	PtController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}
	@Autowired
	PtService service;
	
	@PostMapping("/uploadFile")
	@ApiOperation(value = "해당 유저의 DB 등록 및 s3에 영상 저장")
	public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("type") String type, @RequestParam("nickname") String nickname) throws Exception,IOException{
		
		System.out.println(title);
		System.out.println(content);
		System.out.println(type);
		System.out.println(nickname);
		// 파일을 업로드 하고 객체 URL값을 받아온다
		String movieURL = this.amazonClient.uploadFile(file);
		ResponseEntity response = null;
		// example 
		Pt pt = new Pt(null,nickname,title,content,type,"0",movieURL,null);
		service.insertPt(pt);
		response = new ResponseEntity<>("pt insert success" , HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/deleteFile/{pt_id}")
	@ApiOperation(value = "해당 아이디의 영상을 s3에서 삭제, db삭제")
	public ResponseEntity<String> deleteFile(@PathVariable String pt_id) {
		ResponseEntity response = null;
		// 영상 URL을 찾아서
		String movieURL = service.findMovieById(pt_id);
		// 삭제한다
		this.amazonClient.deleteFileFromS3Bucket(movieURL);
		// 해당 DB를 삭제한다
		service.deletePt(pt_id);
		response = new ResponseEntity<>("pt delete success" , HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/{pt_id}")
	@ApiOperation(value = "해당 pt아이디의 상세 정보를  반환한다")
	public ResponseEntity<Pt> searchByID(@PathVariable String pt_id) {
		Pt pt = service.searchById(pt_id);
		ResponseEntity response = new ResponseEntity<>(pt, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "모든 pt 정보를  반환한다")
	public ResponseEntity<Model> searchPt(@RequestParam("currentPageNum") int currentPageNum, Model model) {
		
		int listCnt = service.getPtListCount();
		
		PagingVo paging = new PagingVo(listCnt, currentPageNum);
		int startIndex = paging.getStartIndex();
		int CntPerPage = paging.getPageSize();
		int pageCnt = paging.getPageCnt();
		
		List<Pt> ptList = service.searchPt(startIndex, CntPerPage);
		model.addAttribute("articles", ptList);
		model.addAttribute("pageSize", pageCnt);
		
		ResponseEntity response = new ResponseEntity<>(model, HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/updateFile")
	@ApiOperation(value = "해당 유저의 DB 수정 (영상정보 수정시에는 원래 영상 삭제 이후 추가)")
	public ResponseEntity<String> updateFile(@RequestPart(value = "file") MultipartFile file, @RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("type") String type,@RequestParam("pt_id") String pt_id) throws Exception,IOException{
		// 해당 아이디의 영상을 삭제한다
		String movieURL = service.findMovieById(pt_id);
		this.amazonClient.deleteFileFromS3Bucket(movieURL);		
		movieURL = this.amazonClient.uploadFile(file);
		ResponseEntity response = null;
		// example 
		Pt pt = new Pt(pt_id,"a",title,content,type,"0",movieURL,null);
		
		service.updatePt(pt);
		response = new ResponseEntity<>("pt insert success" , HttpStatus.OK);
		return response;
	}
	
	
}
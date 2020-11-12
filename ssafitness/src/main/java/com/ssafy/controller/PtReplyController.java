package com.ssafy.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.service.AmazonClient;
import com.ssafy.service.PtReplyService;
import com.ssafy.vo.PtReply;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/pt")
public class PtReplyController {
	private AmazonClient amazonClient;
	String movieURL = "";

	@Autowired
	PtReplyController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	@Autowired
	PtReplyService service;

	@GetMapping("/{pt_id}/reply_best")
	public ResponseEntity<List<PtReply>> bestList(@PathVariable int pt_id) throws Exception {
		return new ResponseEntity<List<PtReply>>(service.bestList(pt_id), HttpStatus.OK);
	}

	// 댓글 리스트 가져오기
	@GetMapping("/{pt_id}/reply_list")
	public List<PtReply>[] replyList(@PathVariable int pt_id, @RequestParam("nickname") String nickname)
			throws Exception {

		System.out.println("nickname : " + nickname);
		System.out.println("댓글리스트로 넘어오니?");
		List<PtReply> temp[] = new List[2];
		temp[0] = service.bestList(pt_id);
		temp[1] = service.replyList(pt_id);
		int checkedLike = 0;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < temp[i].size(); j++) {
				int reply_id = temp[i].get(j).getReply_id();
				System.out.println("reply_id : "+reply_id);
				List<String> test= service.replyLikeList(reply_id);
				//System.out.println(service.replyLikeList(reply_id));
				System.out.println(test);
				System.out.println(test.contains(nickname));
				if (test.contains(nickname)) {
					System.out.println("이 댓글 좋아요 리스트에 닉네임이 포함되어 있어");
					checkedLike = 1;
					service.updateCheckedLike(reply_id,checkedLike);
				} else {
					System.out.println("이 댓글 좋아요 리스트에 닉네임이 없어");
					checkedLike = 0;
					service.updateCheckedLike(reply_id,checkedLike);
				}
			}
		}
		temp[0] = service.bestList(pt_id);
		temp[1] = service.replyList(pt_id);
		//System.out.println(checkedLike);
		return temp;

	// return new ResponseEntity<List<PtReply>>(service.replyList(pt_id),
	// HttpStatus.OK);
	}

	// 댓글 좋아요 누른 닉네임 목록
	@GetMapping("/{pt_id}/{reply_id}/reply_like_list")
	public boolean reply_like_list(@PathVariable int reply_id, @RequestParam("nickname") String nickname)
			throws Exception {
		System.out.println("좋아요 리스트로 들어옴");
		if (service.replyLikeList(reply_id).contains(nickname)) {
			System.out.println(true);
			return true;
		} else {
			System.out.println(false);
			return false;
		}
		// return service.replyLikeList(reply_id);
	}

	// 댓글 좋아요누른 닉네임 인서트
	@GetMapping("/{pt_id}/{reply_id}/reply_like_insert")
	public int reply_like_insert(@PathVariable int reply_id, @RequestParam("nickname") String nickname)
			throws Exception {

		// int reply_id = replyData.getReply_id();
		// String nickname = replyData.getNickname();

		service.replyLikeInsert(reply_id, nickname);
		service.replyUpdatePlus(reply_id);
		return service.replyLikeList(reply_id).size();
	}

	@DeleteMapping("/{pt_id}/{reply_id}/reply_like_delete")
	public int reply_like_delete(@PathVariable int reply_id, @RequestParam("nickname") String nickname)
			throws Exception {

		// int reply_id = replyData.getReply_id();
		// String nickname = replyData.getNickname();
		service.replyLikeDelete(reply_id, nickname);
		service.replyUpdateMinus(reply_id);
		return service.replyLikeList(reply_id).size();
	}

	// 댓글입력 버튼 누르면 삽입되고 전체 리스트를 가져옴
	@PostMapping("/{pt_id}/reply_insert")
	public List<PtReply>[] insert(@RequestBody PtReply replyData) throws Exception {
		System.out.println("매핑 테스트");
		System.out.println(replyData.getContent());
		int pt_id = replyData.getPt_id();
		String nickname = replyData.getNickname();
		String content = replyData.getContent();
		int like = replyData.getLike();

		PtReply newPtReply = new PtReply(pt_id, nickname, content, like);

		service.insert(newPtReply);

		List<PtReply> temp[] = new List[2];
		temp[0] = service.bestList(pt_id);
		temp[1] = service.replyList(pt_id);
		return temp;

	}

	@PostMapping("/{pt_id}/uploadFile")
	public List<PtReply>[] uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam("pt_id") int pt_id,
			@RequestParam("nickname") String nickname, @RequestParam("content") String content,
			@RequestParam("like") int like) throws Exception, IOException {

		// 파일을 업로드 하고 객체 URL값을 받아온다
		String reply_movie = this.amazonClient.uploadFile(file);

		PtReply newPtReply = new PtReply(pt_id, nickname, content, like, reply_movie);
		service.insertMovie(newPtReply);
		List<PtReply> temp[] = new List[2];
		temp[0] = service.bestList(pt_id);
		temp[1] = service.replyList(pt_id);
		return temp;

	}

	@PutMapping("/{pt_id}/reply_update")
	public List<PtReply>[] modify(@RequestBody PtReply reply) throws Exception {

		int reply_id = reply.getReply_id();
		String content = reply.getContent();
		int pt_id = reply.getPt_id();

		service.modify(reply_id, content);
		List<PtReply> temp[] = new List[2];
		temp[0] = service.bestList(pt_id);
		temp[1] = service.replyList(pt_id);
		return temp;

	}

	@DeleteMapping("/{pt_id}/{reply_id}")
	public List<PtReply>[] delete(@PathVariable int reply_id, @PathVariable int pt_id) throws Exception {
		service.delete(reply_id);
		List<PtReply> temp[] = new List[2];
		temp[0] = service.bestList(pt_id);
		temp[1] = service.replyList(pt_id);
		return temp;
	}

}
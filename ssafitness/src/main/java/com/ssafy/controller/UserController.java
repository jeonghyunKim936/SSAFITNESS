package com.ssafy.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.JwtService;
import com.ssafy.service.UserService;
import com.ssafy.vo.User;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/account")
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	JwtService jwtService;
	
	// (영문(대소문자 구분), 숫자, 특수문자 조합, 8~12자리)
	String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{8,12}$";

	
	@GetMapping("/login")
	@ApiOperation(value = "로그인 성공/실패 여부를 반환한다")
	public ResponseEntity<Map<String,Object>> login(@RequestParam("email") String email,
            @RequestParam("password")  String password , HttpServletResponse res) {
		Map<String,Object> resultMap = new HashMap<>();
		// 해당 이메일 회원 확인
		User user = service.search(email);
		ResponseEntity response = null;
		
		if(user == null) {	// 이메일이 없다면... 아이디 없음
			
			response = new ResponseEntity<>("ID not exist", HttpStatus.NOT_FOUND);
		}else {
			if(!user.getPassword().equals(password)) {	// 비밀번호가 틀리다면 ..
				response = new ResponseEntity<>("Wrong Password", HttpStatus.NOT_FOUND);
			}else {			// 이상 없음
				// 로그인에 성공햇다면 토큰 생성
				String token = jwtService.create(user);
				// 토큰 정보는 request의 헤더로 보내고 나머지는  Map에 담아주자
				res.setHeader("jwt-auth-token", token);
				resultMap.put("status", true);
				resultMap.put("data", user);
				response = new ResponseEntity<>(resultMap, HttpStatus.OK);
			}
		}
	

		return response;
	}
	
	@PostMapping("/signup")
	@ApiOperation(value = "회원가입 후 성공/실패 여부를 반환한다")
	public ResponseEntity<String> signup(@RequestBody User request){
		String nickname = request.getNickname();
		String email = request.getEmail();
		String password = request.getPassword();
		String height = request.getHeight();
		String weight = request.getWeight();
		String gender = request.getGender();
		
		ResponseEntity response = null;
		// 패턴검사
		Matcher match = Pattern.compile(pwPattern).matcher(password);
		
		if(service.search(email) != null) {		// 이메일중복
			response = new ResponseEntity<>("이메일이 중복됩니다! 바꿔주세요" , HttpStatus.NOT_FOUND);
			
		}else if(service.searchByNickname(nickname) != null) {	// 닉네임 중복
			response = new ResponseEntity<>("닉네임이 중복됩니다! 바꿔주세요!" , HttpStatus.NOT_FOUND);
		}else if(!match.find()) {	// 패턴 불일치
			response = new ResponseEntity<>("비밀번호 패턴에 맞춰주세요!\n영문(대소문자 구분), 숫자, 특수문자 조합, 8~12자리" , HttpStatus.NOT_FOUND);
		}else {		// 이상없음
			User user = new User(null,nickname,email,password,height,weight,gender,"0","public");
			service.signUp(user);
			response = new ResponseEntity<>("signup success" , HttpStatus.OK);
		}
		
		return response;
	}
	
	@GetMapping("/user/{user_id}")
	@ApiOperation(value = "해당 아이디의 회원정보를 반환한다")
	public ResponseEntity<User> searchByID(@PathVariable String user_id) {
		User user = service.searchByID(user_id);
		ResponseEntity response = new ResponseEntity<>(user , HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/user")
	@ApiOperation(value = "회원수정 후 성공/실패 여부를 반환다")
	public ResponseEntity<String> updateUser(@RequestBody User request){
		// 불변 (수정못하게 막기)
		String user_id = request.getUser_id();
		//System.out.println(user_id);
		String email = request.getEmail();
		//String point = request.getPoint();
		
		// 수정 가능하나 유니크 해야됨
		String nickname = request.getNickname();
		
		
		// 수정 가능하나 패턴에 맞아야함한
		String password = request.getPassword();
		
		// 수정 가능
		String height = request.getHeight();
		String weight = request.getWeight();
		String gender = request.getGender();
		
		
		ResponseEntity response = null;
		Matcher match = Pattern.compile(pwPattern).matcher(password);
		
		User user = service.searchByID(user_id);
		
		// 원래 닉네임 그대로면 true, 아니면 false
		boolean presentNick = user.getNickname().equals(nickname);
		user = service.searchByNickname(nickname);
		// 중복 된 닉네임이 아니다 true , 중복된 닉네임 false
		boolean overlap = user !=null;
		
		// 닉네임 중복이라면 오류(현재 닉네임 그대로가 아니면서 닉네임이 중복되는경우)	
		if(!presentNick && overlap) {
			response = new ResponseEntity<>("닉네임이 중복됩니다! 바꿔주세요!" , HttpStatus.NOT_FOUND);
		}else if(!match.find()) {	// 패턴 불일치
			response = new ResponseEntity<>("비밀번호 패턴에 맞춰주세요!\n영문(대소문자 구분), 숫자, 특수문자 조합, 8~12자리" , HttpStatus.NOT_FOUND);
		}else {
			user = new User(user_id,nickname,email,password,height,weight,gender,"0","public");
			service.updateUser(user);
			response = new ResponseEntity<>("User update success" , HttpStatus.OK);
		}
		return response;
	}
	
	@DeleteMapping("/user/{user_id}")
	@ApiOperation(value = "회원 삭제 후 성공/실패 여부를 반환다")
	//@RequestParam("passsword")  String password
	public ResponseEntity<String> deleteUser(@PathVariable String user_id){
		
		User user = service.searchByID(user_id);
		ResponseEntity response = null;
		
//		if(!user.getPassword().equals(password)) {	// 비밀번호가 틀리다면 ...
//			response = new ResponseEntity<>("Wrong Password", HttpStatus.NOT_FOUND);
//		}else {
//			service.deleteUser(user_id);
//			response = new ResponseEntity<>("User delete success" , HttpStatus.OK);
//		}
		service.deleteUser(user.getUser_id());
		response = new ResponseEntity<>("User delete success" , HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/info")
	public ResponseEntity<Map<String,Object>> getInfo(HttpServletRequest req, @RequestParam("nickname") String nickname,HttpServletResponse res){
		
		Map<String,Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User user = service.searchByNickname(nickname);
			String token = jwtService.create(user);
			//System.out.println(token);
			// 토큰 정보는 request의 헤더로 보내고 나머지는  Map에 담아주자
			res.setHeader("jwt-auth-token", token);
			// resultMap.put("User", jwtService.get(req.getHeader("jwt-auth-token")).get("User"));
			resultMap.put("status", true);
			resultMap.put("data", user);
			status = HttpStatus.OK;
		}catch(RuntimeException e) {
			resultMap.put("message", "실패");
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	
}

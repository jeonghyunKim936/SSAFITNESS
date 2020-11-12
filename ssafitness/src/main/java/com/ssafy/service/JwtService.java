package com.ssafy.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.vo.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtService {

	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public String create(final User user) {
		log.trace("time: {}", expireMin);
		final JwtBuilder builder = Jwts.builder();
		// JWT tokern = header + payload + signagure
		// Header 설정
		builder.setHeaderParam("typ", "JWT"); // 토근의 타입으로 고정값
		
		//payload 설정 - claim 정보 포합
		builder.setSubject("로그인토큰")	// 토큰 제목 설정
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*expireMin))
				.claim("User",user);
		// signature - secret Key를 이용한 암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		final String jwt = builder.compact();
		log.debug("토큰 발행: {}",jwt);
		return jwt;
	}
	
	/**
	 * 
	 * @param jwt
	 */
	public void checkVaild(final String jwt) {
		log.trace("토큰 점검: {}",jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	/**
	 * 
	 * @param jwt
	 * @return
	 */
	public Map<String, Object> get(final String jwt){
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		}catch(final Exception e){
			throw new RuntimeException();
		}
		log.trace("claims:{}",claims);
		return claims.getBody();
	}
}

package com.ssafy;

import java.util.Arrays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.interceptor.JwtInterceptor;

@SpringBootApplication
@MapperScan(basePackages = { "com.ssafy.mapper" })
public class SsafitnessApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SsafitnessApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/account/**")
				.excludePathPatterns(Arrays.asList("/account/signup/**"))
				.excludePathPatterns(Arrays.asList("/account/login/**"))
				.excludePathPatterns(Arrays.asList("/account/user/**"));
	}

	/**
	 * Interceptoer를 이용해서 처리하므로 전역의 Cross origin 처리를 해준다
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
}
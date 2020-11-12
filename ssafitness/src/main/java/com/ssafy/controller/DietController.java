package com.ssafy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.DietService;
import com.ssafy.vo.Diet;

@RestController
@RequestMapping("/diet")
@CrossOrigin(origins = { "http://localhost:3000" })
public class DietController {
	@Autowired
	DietService service;

	@GetMapping("/list")
	public ResponseEntity<List<Diet>> dietList() throws Exception {
		return new ResponseEntity<List<Diet>>(service.dietList(), HttpStatus.OK);
	}

}

package com.ssafy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssafy.service.GuideService;
import com.ssafy.vo.Guide;

@CrossOrigin(origins = { "http://localhost:3000" })
@Controller
public class GuideController {
   @Autowired
   GuideService service;
   
   @GetMapping("/guide/list")
   public ResponseEntity<List<Guide>> guideList() throws Exception {
      return new ResponseEntity<List<Guide>>(service.guideList(), HttpStatus.OK);
   }
   
   @GetMapping("/guide/{part}")
   public ResponseEntity<List<Guide>> partList(@PathVariable String part) throws Exception {
         
      return new ResponseEntity<List<Guide>>(service.partList(part), HttpStatus.OK);
   }
}
package com.ssafy.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.DiaryService;
import com.ssafy.vo.Diary;
import com.ssafy.vo.Exercise;

@RestController
@RequestMapping("/diary")
@CrossOrigin(origins = { "http://localhost:3000" })
public class DiaryController {
	@Autowired
	DiaryService service;

	// 최근 20개 보내기
	@GetMapping("/{year}/{month}/ListLatest")
	public ResponseEntity<List<Diary>> ListLatest(@PathVariable int year, @PathVariable int month,
			@RequestParam("nickname") String nickname) throws Exception {
		//System.out.println(nickname + "??");
		return new ResponseEntity<List<Diary>>(service.ListLatest(year, month, nickname), HttpStatus.OK);
	}

	// 월별로 뛰우기
	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<Diary>> monthList(@PathVariable int year, @PathVariable int month,
			@RequestParam("nickname") String nickname) throws Exception {
		//System.out.println(nickname + "??");
		return new ResponseEntity<List<Diary>>(service.monthList(year, month, nickname), HttpStatus.OK);
	}

	// 일별로 뛰우기
	@GetMapping("/{year}/{month}/{day}")
	public ResponseEntity<Diary> select(@PathVariable int year, @PathVariable int month, @PathVariable int day,
			@RequestParam("nickname") String nickname) throws Exception {
		return new ResponseEntity<Diary>(service.select(year, month, day, nickname), HttpStatus.OK);
	}

	// 그 다이어리 id받아오기
	@GetMapping("/takeDiaryId/{year}/{month}/{day}")
	public int takeDiaryId(@PathVariable int year, @PathVariable int month, @PathVariable int day,
			@RequestParam("nickname") String nickname) {
		Integer num = service.takeDiaryId(year, month, day, nickname);
		//System.out.println(num);
		return num;
	}

	// 다이어리안에 exerciseList받아오기
	@GetMapping("/exerciseList/{year}/{month}/{day}")
	public ResponseEntity<List<Exercise>> selectExercise(@PathVariable int year, @PathVariable int month,
			@PathVariable int day, @RequestParam("nickname") String nickname) throws Exception {
		Integer num = service.takeDiaryId(year, month, day, nickname);
		if(num == null) {
			return null;
		}
		return new ResponseEntity<List<Exercise>>(service.selectExercise(num), HttpStatus.OK);
	}

	// 하루치 삭제하기
	@DeleteMapping("/{year}/{month}/{day}")
	public void delete(@PathVariable int year, @PathVariable int month, @PathVariable int day,
			@RequestParam("nickname") String nickname) throws Exception {
		HashMap map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		map.put("nickname", nickname);
		service.delete(map);
	}

	@PostMapping
	public void insert(@RequestBody Diary diary) throws Exception {
		//System.out.println("매핑 테스트");
		int diary_year = diary.getDiary_year();
		int diary_month = diary.getDiary_month();
		int diary_day = diary.getDiary_day();
		String exercise_start = diary.getExercise_start();
		String exercise_end = diary.getExercise_end();
		int diary_like = diary.getDiary_like();
		String diary_comment = diary.getDiary_comment();
		String nickname = diary.getNickname();
		//System.out.println(diary.getWeight());
		String weight = diary.getWeight();

		Diary newDiary = new Diary(diary_year, diary_month, diary_day, exercise_start, exercise_end, diary_like,
				diary_comment, nickname, weight);

		service.deleteExercise(diary_year, diary_month, diary_day);
		delete(diary_year, diary_month, diary_day, nickname);
		//System.out.println("nick2: " + diary.getNickname());
		service.insert(newDiary);

	}

	@PostMapping("/exercise/{year}/{month}/{day}/{nickname}")
	public void insertExercise(@RequestBody List<Exercise> exercise, @PathVariable int year, @PathVariable int month,
			@PathVariable int day, @PathVariable String nickname) throws Exception {

		int num = service.takeDiaryId(year, month, day, nickname);
		for (int i = 0; i < exercise.size(); i++) {
			String exercise_type = exercise.get(i).getExercise_type();
			String exercise_name = exercise.get(i).getExercise_name();
			int exercise_weight = exercise.get(i).getExercise_weight();
			int exercise_raps = exercise.get(i).getExercise_raps();
			int exercise_set = exercise.get(i).getExercise_set();
			// String diary_id = exercise.get(i).getDiary_id();

			Exercise newExercise = new Exercise(exercise_type, exercise_name, exercise_weight, exercise_raps,
					exercise_set, num);
			service.insertExercise(newExercise);
		}
	}

	@PutMapping
	public void modify(@RequestBody Diary diary) throws Exception {

		int diary_year = diary.getDiary_year();
		int diary_month = diary.getDiary_month();
		int diary_day = diary.getDiary_day();
		String exercise_start = diary.getExercise_start();
		String exercise_end = diary.getExercise_end();
		int diary_like = diary.getDiary_like();
		String diary_comment = diary.getDiary_comment();
		String nickname = diary.getNickname();
		String weight = diary.getWeight();

		Diary modifyDiary = new Diary(diary_year, diary_month, diary_day, exercise_start, exercise_end, diary_like,
				diary_comment, nickname, weight);

		service.modify(modifyDiary);
	}

}

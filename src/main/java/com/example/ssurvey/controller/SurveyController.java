package com.example.ssurvey.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.Survey;
import com.example.ssurvey.domain.SurveyA;
import com.example.ssurvey.domain.SurveyQ;
import com.example.ssurvey.service.SurveyService;



@RestController
public class SurveyController {

	@Autowired
	private SurveyService surveyService;
	
  
	@GetMapping("/survey")
	public ResponseEntity<List<Survey>> getAllSurveys() {
	    List<Survey> surveys = surveyService.getAllSurveys();
	    return new ResponseEntity<>(surveys, HttpStatus.OK);
	}
	
	
	@GetMapping("/surveyQ/{surveyNo}")
	public ResponseEntity<?> getSurveyQ(@PathVariable Integer surveyNo) {
		
		List<SurveyQ> surveyQs = surveyService.getSurveyQ(surveyNo);
		
		
		return new ResponseEntity<>(surveyQs, HttpStatus.OK);
		
	}
	 
	 
	
	
	
	
	@PostMapping("/addSurvey")
	public ResponseEntity<?> addSurvey(@RequestBody List<SurveyQ> surveyQ, String surTitle, String surveyCategory, String username) {
		
		surveyService.addSurvey(surTitle, surveyCategory, username);
		
		surveyService.addSurveyQ(surveyQ);
		

		return new ResponseEntity<>("설문 등록 완료", HttpStatus.OK);
	}
	
	@PostMapping("/surveyA")
	public ResponseEntity<?> addAnswer(@RequestBody List<SurveyA> surveyA, String username) {
		
		surveyService.addAnswer(surveyA, username);
		
		return new ResponseEntity<>("설문 답변 완료", HttpStatus.OK);
		
	}
	
	
	// 결과보기 눌렀을때 요청할 get endpoint 입니다. 
//	@GetMapping("/surveyQ/{surveyNo}")
//	public ResponseEntity<?> getSurveyQ(@PathVariable Integer surveyNo) {
//		
//		List<SurveyQ> surveyQs = surveyService.getSurveyQ(surveyNo);
		// 이 위에 형식으로 동일하게 받아와야함
	
//	for(SurveyQ onesurveyQ : surveyQs) {
//		Integer sqNo = onesurveyQ.getSqNo();
//		List<String> options =  onesurveyQ.getOption(); // 객관식에서 사용됨
//		List<String> textList = surveyService.textAnswer(sqNo);	// 단답, 장문에서 사용됨
//			
//		System.out.println(textList);
//		
//		
//		if(onesurveyQ.getSqType() == "객관식") {
//			
//			int[] countAnswers = new int[options.size()];
//
//			for (int i = 0; i < options.size(); i++) {
//			    String answer = options.get(i);
//			    
//			    // surveyService.countAnswer 메서드를 호출하여 count 값을 얻음
//			    int countAnswer = surveyService.countAnswer(sqNo, answer);
//			    
//			    // 얻은 count 값을 배열에 저장
//			    countAnswers[i] = countAnswer;
//			}
//		
//		}else {
//			
//	
//		}
//		
//	}
	
}

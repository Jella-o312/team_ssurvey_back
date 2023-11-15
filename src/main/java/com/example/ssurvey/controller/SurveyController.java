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
	
	
	@GetMapping("/surveyResult/{surveyNo}")
	public ResponseEntity<?> getSurveyResult(@PathVariable Integer surveyNo) {
	    List<SurveyQ> surveyQs = surveyService.getSurveyQ(surveyNo);

	    List<List<String>> allresult = new ArrayList<>();

	    for (SurveyQ onesurveyQ : surveyQs) {
	        Integer sqNo = onesurveyQ.getSqNo();
	        List<String> options = onesurveyQ.getOption();
	        List<String> textList = surveyService.textAnswer(sqNo);

	        if ("객관식".equals(onesurveyQ.getSqType())) {
	            List<String> countAnswers = new ArrayList<>();

	            for (String answer : options) {
	                // surveyService.countAnswer 메서드를 호출하여 count 값을 얻음
	                int countAnswer = surveyService.countAnswer(sqNo, answer);

	                // 얻은 count 값을 리스트에 추가
	                countAnswers.add(String.valueOf(countAnswer));
	            }

	            allresult.add(countAnswers);
	            
	        } else {
	            allresult.add(textList);
	        }
	    }

//	    System.out.println(allresult);

	    return new ResponseEntity<>(allresult, HttpStatus.OK);
	}
	
	

	
	
	
}

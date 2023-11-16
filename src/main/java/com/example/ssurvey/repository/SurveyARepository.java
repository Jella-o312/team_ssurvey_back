package com.example.ssurvey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ssurvey.domain.SurveyA;

@Repository
public interface SurveyARepository extends JpaRepository<SurveyA, Integer> {

	public List<SurveyA> findAllBySqNo(Integer surveyNo);

	    // 특정 sqNo와 answer 조건을 만족하는 레코드 개수를 세는 쿼리 메소드
    public int countBySqNoAndAnswer(Integer sqNo, String answer);
	
    @Query("SELECT s.answer FROM SurveyA s WHERE s.sqNo = :sqNo")
    List<String> findAnswersBySqNo(@Param("sqNo") Integer sqNo);
    
    
}

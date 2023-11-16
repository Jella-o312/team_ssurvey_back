package com.example.ssurvey.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
	      name = "FreeBoardReply_SEQ_GENERATOR", 
	      sequenceName = "FreeBoardReply_SEQ",
	      allocationSize = 1)
public class FreeBoardReply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FreeBoardReply_SEQ_GENERATOR")
	private Integer fbrNo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userNo")
	private User user;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "fbNo")
	private FreeBoard freeBoard;
	
	@Column(length = 500)
	private String fbrContent;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private String fbrCreateDate;
	

	@PrePersist
    protected void onCreate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fbrCreateDate = dateFormat.format(new Date());
    }


	public FreeBoardReply orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 생성자 자동완성
@NoArgsConstructor  // 생성자 자동완성
@Data // getter setter to String 자동완성
public class JoDTO {
	private int jno;
	protected String jname;
	private String captain;
	protected String project;
	private String slogan;
	
	private String cname; // 필요시 사용 - setter/getter, toString, default 생성자 등 만들 필요 없음(lombok)
}
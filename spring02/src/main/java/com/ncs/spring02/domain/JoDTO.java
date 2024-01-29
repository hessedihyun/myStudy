package com.ncs.spring02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 생성자 자동완성
@NoArgsConstructor  // 생성자 자동완성
@Data // getter setter to String 자동완성
public class JoDTO {
	private int jno;
	private String jname;
	private String captain;
	private String project;
	private String slogan;
	private String cname; // 필요시 사용 - setter/getter, toString, default 생성자 등 만들 필요 없음(lombok)
}
package com.ncs.spring02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//** DTO
//=> private 멤버변수
//=> getter/setter
//=> toString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	// 1) priavte 멤버 변수
	private String id; // Primary_Key
	private String password; // not null
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String birthday;  // date 타입이면 복잡해져서 String으로 선언
	private String rid; // 추천인
}

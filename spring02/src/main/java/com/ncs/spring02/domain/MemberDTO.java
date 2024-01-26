package com.ncs.spring02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//** DTO
//=> private 멤버변수
//=> getter/setter
//=> toString
@Data
@AllArgsConstructor // 매개 인수가 있는 모든 것
@NoArgsConstructor // 매개인수가 없어도

// 선택적으로 => @Data 즉, 다음 에너테이션을 모두 한번에 처리한다.
// => @Getter
// => @Setter
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
	
	// 2) getter/setter
	// 3) toString
}

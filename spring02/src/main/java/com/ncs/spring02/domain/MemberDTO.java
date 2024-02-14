package com.ncs.spring02.domain;

import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//** DTO
//=> private 멤버변수
//=> getter/setter
//=> toString


// 선택적으로 => @Data 즉, 다음 에너테이션을 모두 한번에 처리한다.
// => @Getter
// => @Setter
@Data
@AllArgsConstructor // 매개 인수가 있는 모든 것
@NoArgsConstructor // 매개인수가 없어도
public class MemberDTO {
	// 1) private 멤버 변수
	private String id; // Primary_Key
	private String password; // not null
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String birthday;  // date 타입이면 복잡해져서 String으로 선언
	private String rid; // 추천인
	private String uploadfile; // Table 보관용(file_name)
	private MultipartFile uploadfilef;
	// MultipartFile(인터페이스) : 업로드하는 파일을 그대로 손쉽게 처리할 수 있는 객체
	// => form의 Upload_File의 정보를 전달받기 위한 컬럼
	// MultipartFile(i) -> CommonsMultipartFile
	// pom.xml에 dependency 추가
	// => 구현체 commonsMultipartFile 생성(servlet ~ .xml)
	private String [] grouping;
}

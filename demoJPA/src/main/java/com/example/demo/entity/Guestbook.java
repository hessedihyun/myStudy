package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
// @Table(name = "guestbook") : 클래스명과 동일한 경우 생략가능
@Getter
@ToString
@Builder // Q. 왜 넣어준건가? -> 
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook extends BaseEntity {
	// BaseEntity(추상클래스) 상속 받음
	
		// @Id : javax.persistence ; DB에 Primary Key를 알려주는 어노테이션
		// => 테이블의 기본(Primary) Key와 매핑함. 
	    // ** @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    // => id로 설정된 기본키의 값을 자동으로 생성할때 추가적으로 사용
	    // => strategy 속성: 키 생성전략
	    //      - GenerationType.AUTO: default, JPA구현체 (Hibernate 등)가 생성방식 결정  
	    //      - GenerationType.IDENTITY: DB가 생성방식 결정 (MySql, Maria 는 auto_increment)  
	    //      - GenerationType.SEQUENCE: DB의 sequence를 이용해 생성, @SequenceGenerator 와 같이 사용  
	    //      - GenerationType.TABLE: 키생성 전용 테이블을 생성해서 키생성, @TableGenerator 와 같이 사용
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gno; // Auto_increment (보통 long으로 한다)
	
	@Column(length=100,nullable=false)
	private String title;
	@Column(length=2000,nullable=false)
	private String content;
	@Column(length=50,nullable=false)
	private String writer;
	
	public void changeTitle(String title) {
		this.title = title;
	}
	public void changeContent(String content) {
		this.content = content;
	}
	
	
} // class

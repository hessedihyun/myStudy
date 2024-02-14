package com.ncs.spring02.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;
import mapperInterface.MemberMapper;

//** Mybatis 적용 **************************
//=> CRUD 처리를 Mapper 를 이용
//=> DAO 대신 Mapper interface ->  ~Mapper.xml

//** Mybatis interface 방식으로 적용
//=> MemberDAO 대신 MemberMapper 사용
//=> MemberMapper 의 인스턴스를 스프링이 생성해주고 이를 주입받아 실행함
//   (스프링이 생성해주는 동일한 타입의 클래스는 JUnit Test 로 확인가능, 추후 실습) 
//=> 단, 설정화일에 <mybatis-spring:scan base-package="mapperInterface"/> 반드시 추가해야함
//  MemberDAO의 Sql구문 처리를 mapperInterface 사용으로 MemberMapper 가 역할을 대신함

//=> SQL 구문 : xml로 작성 -> 이 파일을 Mapper라 함 
//=> Mapper 작성규칙
// -> mapperInterface 와 패키지명, 파일명이 동일해야함
// -> 즉, Java interface, Mapper, Mapper의 namespace 값(패키지명, 파일명)이 모두 동일해야함.
// -> 그리고 해당 메서드는 Mapper의 xml 구문의 id 속성 값으로 찾음

@Service
public class MemberServiceImpl implements MemberService {
	// ** 전역변수 정의
	// MemberDAO dao = new MemberDAO();
    // @Autowired
    // MemberDAO dao;
	
	// ** Mybatis 적용
	// => mapper 구현객체는 스프링이 실행시 자동으로 만들어 주입해준다.
	// => 그러므로 개발자는 interface와 xml만 구현하고 Service랑 연결해주면 됨
	@Autowired
	MemberMapper mapper;
	
	// ** selectList
	@Override
	public List<MemberDTO> selectList() {
		return mapper.selectList();
	}
	// ** selectOne
	@Override
	public MemberDTO selectOne(String id) {
		return mapper.selectOne(id);
	}
	// ** insert
	@Override
	public int insert(MemberDTO dto) {
		return mapper.insert(dto);
	}
	// ** update
	@Override
	public int update(MemberDTO dto) {
		return mapper.update(dto);
	}
	// ** delete
	@Override
	public int delete(String id) {
		return mapper.delete(id);
	}
	// ** pwUpdate
	public int pwUpdate(MemberDTO dto) {
		return mapper.pwUpdate(dto);
	}
	
} // class

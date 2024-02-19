package springMybatis;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

import mapperInterface.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_interfaceMapper {
	
	// ** interface Mapper 설정
	// => Controller -> Service -> (DAO) -> interface Mapper : xml의 sql 구문을 이용해서 DB처리
	
	@Autowired
	MemberMapper mapper;
	
	@Autowired
	MemberDAO dao;
	
	@Autowired
	MemberDTO dto;
	
	// => 성공: MemberMapper mapper = new MemberMapper구현객체 ;
    //    -> 구현객체 생성 부터는 Spring과 Mybatis가 규칙에 의해 처리해줌 
	//    -> 규칙: 패키지 명과 클래스명을 interface , mapper xml, xml의 namespace 모두 동일하게 해줌.
	//           이를 위한 경로 설정 
	//           <mybatis-spring:scan base-package="mapperInterface"/>
	
	// @Test
	// ** mapper 동작 Test
	public void mapperTest() {
		System.out.println("** mapper => "+ mapper);
		assertNotNull(mapper);
		System.out.println("** Membermapper interface 구현객체 => "+ mapper.getClass().getName());
		System.out.println("** dto 인스턴스의 동작하는 클래스명 ===> " + dto.getClass().getName());
	}
	
	// ** mapper의 메서드 Test
	// => Mybatis 사용 시 주의사항
	// 	-> 참조형 매개변수 사용 시 주소를 공유하지 않음
	// 	   selectDTO(MemberDTO dto) { } 형식
	// 	-> 매개변수는 (Type은 무관하지만) 1개만 사용 가능
	//	   그러므로 주로 객체형으로 사용하지만,
	//     복수의 매개변수를 사용하려면 @Param을 이용할 수 있음
	//  -> xml 없이 @으로 Sql 구현 가능
	
	
	
	// 1) selectOne
	// @Test
	public void selectOne() {
		String id = "black"; // 있는 id OR 없는 id
		
		dto= mapper.selectOne(id);
		System.out.println("** selectOne dto => "+ dto);
		
		assertNotNull(dto);
	}
	
	// 주소 공유 X  : 2) selectDTO(memberDTO dto) 형식
	// 2) selectDTO(MemberDTO dto) 형식
	// => MemberDAO 와 Mybatis 비교
	//     참조형 매개변수 사용시 Mybatis는 매개변수 주소를 공유하지않음 주의
	// @Test
	public void selectDTO() {
		
		// 2.1) MemberDAO 적용시 (값이 들어가 있음)
		MemberDTO dto1 = new MemberDTO();
		dto1.setId("merci");
		// dao.selectDTO(dto1);
		if(dao.selectDTO(dto1)!=null) {
			double test = dto1.getPoint()*1000/365;
			System.out.println("** test => " + test);
		}
		System.out.println("** MemberDAO의 selectDTO() => "+ dto1);
		
		// 2.2) Mybatis 적용시 (
		MemberDTO dto2= new MemberDTO();
		dto2.setId("merci");
		// mapper.selectDTO(dto2);
		// dto2 = mapper.selectDTO(dto2);
		if(mapper.selectDTO(dto2)!=null) {
			// dto2 = mapper.selectDTO(dto2);
			double test2 = dto2.getPoint()*1000/365;
			System.out.println("** test => " + test2);
		}
		System.out.println("** Mybatis의 selectDTO() 바로 mapper로 불러오기=> " + mapper.selectDTO(dto2));
		System.out.println("** Mybatis의 selectDTO() 메소드 호출 후 dto로 불러오기=> " + dto2);
		
	} // selectDTO
	
	// 3) 복수의 매개변수 사용 Test
	// => Mybatis 에서 2개 이상의 매개변수 처리
	// => Mapper interface 에서 @Param 적용 가능
	// => selectParam(id,jno)
	@Test
	public void paramTest() {
		
		dto = mapper.selectParam("merci", 3);
		System.out.println("** Mybatis의 @Param Test =>" + dto);
		assertNotNull(dto);
	}
	
	
} // class

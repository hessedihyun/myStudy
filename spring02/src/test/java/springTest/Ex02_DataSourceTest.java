package springTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;

//*** DataSourceTest
//=> pom.xml 에 <dependency> spring-jdbc 추가
//=> 인터페이스 DataSource 구현객체 DriverManagerDataSource 를 bean 등록하고 (servlet~.xml 또는 root~.xml 에)
//=> DB Connection 생성 확인

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_DataSourceTest {
	// "나도 직접 DataSource 만들어서(주입받기) DAO 테스트해보고 싶어!"
	
	@Autowired // 주입을 받아보자 (이미 root-context.xml에 bean 등록되어 있음)
	DataSource dataSource;
	// ** 계층도 확인 ( Ctrl+T )
    // => DataSource (interface)
    //       -> AbstractDataSource
    //       -> AbstractDriverBasedDataSource
    //       -> DriverManagerDataSource ( root~.xml에 Bean 설정 )
    //          org.springframework.jdbc.datasource.DriverManagerDataSource
	
	// 인터페이스 : 추상메서드, 상수 만 올 수 있다. -> 모든 구현 요소를 의무 구현!
	// <> 추상메서드가 아닌 body가 있는 메서드도 인터페이스에 넣을 수 있는 경우 : ???
	// 추상클래스 : 멤버 필드, 메서드 + 추상메서드(1개라도 들어가면) -> 모든 구현을 의무적으로 받을(구현) 필요X
	// 구현(구상)클래스: static, default
	
	@Autowired
	MemberDTO dto;
	
	// @Test
	// 1) DBConnection 확인
	public void connectionTest() {
		try {
			assertNotNull(dataSource.getConnection());
			System.out.println("** DB Connection 성공 => " + dataSource.getConnection());
		} catch (Exception e) {
			System.out.println("** DB Connection 실패 => " + e.toString());
		}
	} // connectionTest(종류)
	
	// 2) SQL 구문 실행 Test
	public int delete(String id) {
		String sql = "delete from member where id=?";
		try {
			Connection cn = dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Test Exception => " + e.toString());
			return 0;
		}
	} // delete
	
	
	public int insert(MemberDTO dto) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection cn = dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());
			pst.setString(10, dto.getUploadfile());
			
			return pst.executeUpdate(); // 성공
		} catch (Exception e) {
			System.out.println("** insert Test Exception => " + e.toString());
			return 0;
		}
	} // insert

	@Before
	public void insertTest() {
		dto.setId("junit");
		dto.setPassword("12345!");
		dto.setName("유니트");
		dto.setAge(33);
		dto.setJno(7);
		dto.setInfo("JUnit TEST");
		dto.setPoint(200.456);
		dto.setBirthday("2000-02-02");
		dto.setRid("admin2");
		dto.setUploadfile("aaa.gif");
		
		assertEquals(insert(dto), 1);
		System.out.println("** insert dto => " + dto);
	} // insertTest
	
	@Test
	public void deleteTest() {
		String id="junit"; // 있는 id, 없는 id 비교 검사(Test)
		
		System.out.println("** delete(id) => " + delete(id));
		System.out.println("** delete id => "+ id);
		assertEquals(delete(id), 1);
	} // deleteTest
	
} // class

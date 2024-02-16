package javaTest;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;

public class Ex02_DBConnection {
	
	// 1) static, return 값이 있는 경우 (@Test 불허)
	// => Test 메서드를 따로 작성해서 Test
	
	public static Connection getConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url="jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"; 
	
				Connection cn = DriverManager.getConnection(url,"root","mysql");
				System.out.println("** DB Connection 성공 **\n");			
				return cn; // 리턴타입 getConnection() 
	 
			} catch (Exception e) {
				System.out.println("** DB Connection Exception => " + e.toString());
				return null;
			}	// try
	} // getConnection
	
	@Test
	public void connectionTest() {
		System.out.println("** DB_Connection => " + getConnection()); // 주소값 반환
		assertNotNull(getConnection());
		
		// Q. 콘솔로 찍어보면 null이 아닌 걸 아는데, 왜 assertNotNull 메서드로 확인해주나요?
		// assertNotNull이 null인지 아닌지 확인하는데 명시적이고 직관적이다. 하지만 값을 구하는 데에 있어서는 차이가 없다.
		// @Test 단위로 메서드를 점검해볼 수 있다는 데에 의의가 있다.
		
	} // connectionTest
} // class

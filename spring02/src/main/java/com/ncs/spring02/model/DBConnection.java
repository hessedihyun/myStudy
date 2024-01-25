package com.ncs.spring02.model;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

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

} // method
}   
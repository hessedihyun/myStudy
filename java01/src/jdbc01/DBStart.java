package jdbc01;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBStart {
	
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	public static void selectList() {
		
		sql = "select * from student";
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
						
			System.out.println("** Student List **");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("sno|name|age|jno|info|point");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
					
				} while(rs.next());
				
			} else {
				System.out.println("** selectList 결과가 1건도 없음 **");
			}
			
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
		} // try
	} // selectList
	
	// ** 조별 List1
	// => 매개변수를 활용한 조건을 추가 (Statement를 활용한)
	public static void joList(int jno) {
		
		sql = "select * from student where jno = " + jno;
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			System.out.println("** jo List => " +jno);
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
				} while(rs.next());
			} else {
				System.out.println("** joList 결과가 1건도 없음 **");				
			}
		} catch (Exception e) {
			System.out.println("** joList Exception => " + e.toString());
		} // try
	} // joList1
	
	// ** 조별 List2
	// => 매개변수를 활용한 조건을 추가 (PreparedStatement를 활용한)
	public static void joListPS(int jno) {
		
		sql = "select * from student where jno = ?";
		try {
			pst = cn.prepareStatement(sql); 
			pst.setInt(1, jno);
			rs=pst.executeQuery();
			System.out.println("** jo List => " +jno);
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
				} while(rs.next());
			} else {
				System.out.println("** joList 결과가 1건도 없음 **");				
			}
		} catch (Exception e) {
			System.out.println("** joList Exception => " + e.toString());
		} // try
	} // joListPS

	public static void insert(String name, int age, int jno, String info) {
		sql="insert into student(name, age, jno, info) values(?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setInt(3, jno);
			pst.setString(4, info);
//			int cnt = pst.executeUpdate(); // insert, update, delete 때 쓰고, 실행된 개수를
//			if (cnt == 1) System.out.println(" ** insert 성공 => " + cnt);
//			else System.out.println(" ** insert 실패 => " + cnt);
			
			if (pst.executeUpdate()>0) System.out.println(" ** insert 성공 ** ");
			else System.out.println("** insert 실패 **");
			
		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
		}// try-catch
	}
	
	public static void main(String[] args) {
		System.out.println("** Connection 확인 =>" + cn);
			joList(2);
	} // main

} // class

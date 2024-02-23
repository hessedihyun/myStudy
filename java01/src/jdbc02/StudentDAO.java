package jdbc02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc01.DBConnection;

public class StudentDAO {
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** Join Test
	// => sno, name, age, jno, jname, project, captain, 조장이름
	// => JoDTO 작성, joinList() 메서드 작성 (Controller, Service, DAO)	
	// ** joinList
	public List<StudentDTO> joinList() {
		sql="select s1.sno, s1.name, s1.age, s1.jno, jname, project, captain," 
				+"(select name from student where sno=captain) cname"
				+" from student s1 Left Outer Join  jo j ON s1.jno=j.jno";
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();	
			if (rs.next()) {
				do {
					StudentDTO dto = new StudentDTO();
					dto.setSno(rs.getInt(1));
					dto.setName(rs.getString(2));
					dto.setAge(rs.getInt(3));
					dto.setJno(rs.getInt(4));
					dto.setJname(rs.getString(5));
					dto.setProject(rs.getString(6));
					dto.setCaptain(rs.getInt(7));
					dto.setCname(rs.getString(8));
					list.add(dto);
				}while(rs.next());
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** joinList Exception => "+e.toString());
			return null;
		}
	} //joinList	
	
	// ** selectList
		public List<StudentDTO> selectList() {
			sql="select * from student";
			List<StudentDTO> list = new ArrayList<StudentDTO>();
			try {
				pst=cn.prepareStatement(sql);
				rs=pst.executeQuery();	
				if (rs.next()) {
					do {
						StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2),
								rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), null);
						list.add(dto);
					}while(rs.next());
					return list;
				}else {
					return null;
				}
			} catch (Exception e) {
				System.out.println("** selectList Exception => "+e.toString());
				return null;
			}
		} //selectList
	// ** selectOne
	public StudentDTO selectOne(int sno) {
		sql = "select * from student where sno = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, sno);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				do {
					StudentDTO dto = new StudentDTO();
		        	dto.setSno(rs.getInt(1));
		        	dto.setName(rs.getString(2));
		        	dto.setAge(rs.getInt(3));
		        	dto.setJno(rs.getInt(4));
		        	dto.setInfo(rs.getString(5));
		        	dto.setPoint(rs.getDouble(6));
		        	return dto;
				} while(rs.next());
			} else {
				return null;
			}
			} catch (Exception e){
			System.out.println("** selectOne Exception => **" + e.toString());
			return null;
		}
	} // selectOne
	// ** selectOne2
	public void selectOne2(StudentDTO dto) {
		sql = "select * from student where sno = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSno());
			rs=pst.executeQuery();
			
			if(rs.next()) {
					dto.setName(rs.getString(2));
					dto.setAge(rs.getInt(3));
					dto.setJno(rs.getInt(4));
					dto.setInfo(rs.getString(5));
					dto.setPoint(rs.getDouble(6));
			} else {
				System.out.println("** Student 없음");
			}
		} catch (Exception e){
		System.out.println("** selectOne Exception => **" + e.toString());
	}
	} // selectOne2
	// ** Insert
//	public int insert(StudentDTO dto) {
//		sql = "insert into student(name,age,jno,info) values(?,?,?,?)";
//		
//		try {
//			pst = cn.prepareStatement(sql);
//			pst.setString(1, dto.getName());
//			pst.setInt(2, dto.getAge());
//			pst.setInt(3, dto.getJno());
//			pst.setString(4, dto.getInfo());
//			
//			return pst.executeUpdate();
//		} catch (Exception e) {
//			System.out.println(" ** insert Exception =>" + e.toString());
//			return 0;
//		}
//	}
	// ** update
	public int update(StudentDTO dto) {
		sql = "update student set info=?, point = ? where sno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getInfo());
			pst.setDouble(2, dto.getPoint());
			pst.setInt(3, dto.getSno());
			
			return pst.executeUpdate();
		} catch(Exception e) {
			System.out.println("** update Exception => "+e.toString());
			return 0;
		}
	}
	// ** delete
	public int delete(int sno) {
		sql = "delete from student where sno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, sno);
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => "+e.toString());
			return 0;
		}
	}
	// ** 트렌직션
	   // ** Transaction Test
	   // => Connection 객체가 관리
	   // => 기본값은 AutoCommit  true 임.
	   // => setAutoCommit(false) -> commit 또는 rollback 
	   // => Test 사항
	   //   - 동일자료를 2번 입력 -> 2번째 입력에서 p.key 중복 오류발생 

	   // 1) Transaction 적용전
	   // => 동일자료를 2번 입력
	   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생 
	   //   - Rollback 불가능
	   //   - MySql Command 로 1번째 입력 확인 가능 
	      
	   // 2) Transaction 적용후 
	   // => 동일자료를 2번 입력 
	   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
	   //   - Rollback 가능 -> 둘다 취소됨
	
	public void transactionTest() {
		sql = "insert into student values(19,'김길동',99,9,'Transaction 적용전',123.45)";
		
		// 1) Transaction 적용 전
//		try {
//			pst=cn.prepareStatement(sql);
//			pst.executeUpdate(); // 1번째는 Table에 입력 완료
//			pst.executeUpdate(); // 2번째는 p.key 중복 오류 발생 -> catch 블럭으로
//		} catch(Exception e) {
//			System.out.println("**Transaction 적용전 => **"+e.toString());
//		}
		// 2) Transaction 적용 후
		try {
			cn.setAutoCommit(false); // Start Transaction이랑 동일
			pst=cn.prepareStatement(sql);
			pst.executeUpdate(); // 1번째는 입력완료 되었지만, (임시)Buffer에 보관-> 누가? DBMS (오라클 같은)
			pst.executeUpdate(); // 2번째는 p.key 중복오류 -> exception 발생 catch 블럭으로 -> Rollback / Commit 
			cn.commit();
		} catch(Exception e) {
			System.out.println("**Transaction 적용후 => **"+e.toString());
			// => Rollback;
			try {
				cn.rollback();
				// cn은 그 자체만으로 try-catch가 필요함.
				System.out.println("** 롤백 성공!! **");
			} catch (Exception e1) {
				System.out.println("**Transaction 적용후 => **"+e1.toString());
			}
		}
	} // transactionTest
	// Ex. 만약에 a와 b 사이 거래로 1만원을 a가 주고, b가 받는다. 둘이 동시에 같이 성공 또는 실패되어야 한다.이런 것들을 '트렉직션'으로 처리하는 것이다.
} // class

package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;

@Repository
public class JoDAO {
	private static Connection cn = DBConnection.getConnection();
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** memberInsert
	
	// ** Jo selectList
	public List<JoDTO> selectList() {
	 // sql="select * from Jo";
		sql ="select j.jno, j.jname, j.captain, m1.name as cname, j.project, j.slogan from member m1 join jo j on m1.id = j.captain";
		List<JoDTO> jlist = new ArrayList<JoDTO>();
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()) {
				do {
					JoDTO dto = new JoDTO();
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
					dto.setCaptain(rs.getString(3));
					dto.setCname(rs.getString(4));
					dto.setProject(rs.getString(5));
					dto.setSlogan(rs.getString(6));
					jlist.add(dto);
				}while(rs.next());
				return jlist;
			} else {
				return null;
			} // if-else
		} catch (Exception e) {
			System.out.println("** Jo selectList Exception =>" + e.toString());
			return null;
		} // try-catch
	} // jo selectList
	
	public JoDTO selectOne(int jno) {
		sql="select * from jo where jno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, jno);
			rs=pst.executeQuery();
			if(rs.next()) {
					JoDTO dto = new JoDTO();
					System.out.println("요요요기");
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
					dto.setCaptain(rs.getString(3));
					dto.setProject(rs.getString(4));
					dto.setSlogan(rs.getString(5));
					return dto;
			} else {
				return null;
			} // if-else
		} catch(Exception e) {
			System.out.println("** jo selectOne Exception =>" + e.toString());
			return null;
		} // try-catch
	} // jo selectOne
	
	// ** jo selectMember
	public List<MemberDTO> selectMember(int jno) {
		sql="select * from member where jno=?";
		List<MemberDTO> jmlist = new ArrayList<MemberDTO>();
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, jno);
			rs=pst.executeQuery();
			if(rs.next()) {
				do {
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));
					jmlist.add(dto);
				} while (rs.next());
				return jmlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** Jo selectList Exception =>" + e.toString());
			return null;
		} // try-catch
	} // jo selectMember
	
	// ** insert
	public int insert(JoDTO dto) {
		sql="insert into jo values(?,?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			pst.setString(2, dto.getJname());
			pst.setString(3, dto.getCaptain());
			pst.setString(4, dto.getProject());
			pst.setString(5, dto.getSlogan());
			return pst.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e){
			return -1; 
		} catch(Exception e) {
			System.out.println(" ** jo insert Exception => "+e.toString());
			return 0;
		} // try-catch
	} // jo insert
	
	// ** update
	public int update(JoDTO dto) {
		sql="update jo set jname=?, captain=?, project=?, slogan=? where jno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getJname());
			pst.setString(2, dto.getCaptain());
			pst.setString(3, dto.getProject());
			pst.setString(4, dto.getSlogan());
			pst.setInt(5, dto.getJno());
			return pst.executeUpdate();
		} catch(Exception e) {
			System.out.println("** update Exception => "+e.toString());
			return 0;
		} // try-catch
	} // jo update
	
	// ** delete
	public int delete(int jno) {
		sql="delete from jo where jno=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, jno);
			return pst.executeUpdate();
		} catch(Exception e) {
			System.out.println("** delete Exception => "+e.toString());
			return 0;
		} // try-catch
	} // jo delete
} // JoDAO class

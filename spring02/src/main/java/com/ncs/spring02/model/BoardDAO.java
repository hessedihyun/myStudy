package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;

//  ** 게시판
// => CRUD 구현

@Repository
public class BoardDAO {
	
	// ** 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** selectList
	public List<BoardDTO> selectList() {
		// sql="select * from board order by seq desc";
		sql="select * from board order by root desc, step asc";
		// => 답글 달기 추가 후 출력 순서 수정
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()) {
				do {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6)); // 조회수
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(8));
					dto.setIndent(rs.getInt(9));
					list.add(dto);
				} while (rs.next()); // do-while
				return list;
			} else {
				System.out.println("** Board selectList => 출력 자료가 없습니다. ");
				return null;
			} // if-else
		} catch (Exception e) {
			System.out.println("** Board selectList => " + e.toString());
			return null;
		} // try-catch
	} // board selectList
	
	// ** selectOne
	public BoardDTO selectOne(int seq) {
		sql="select * from board where seq=?";
		
		BoardDTO dto = new BoardDTO();
		
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, seq);
			rs=pst.executeQuery();
			if(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6)); // 조회수
				dto.setRoot(rs.getInt(7));
				dto.setStep(rs.getInt(8));
				dto.setIndent(rs.getInt(9));
				return dto;
				
			} else {
				return null;
			}} catch (Exception e) {
			System.out.println("** Board selectOne => " + e.toString());
			return null;
		} // try-catch
	} // board selectOne
	
	// ** insert (원글 입력)
	// => 입력 컬럼 : id, title, content
	// => default 값 : regdate, cnt, step, indent
	// => root : seq와 동일한 값
	// => Auto_Inc : seq(계산 : auto보다 max(seq)를 이용해서 직접 계산)
	public int insert(BoardDTO dto) {
		sql="insert into board values("
				+ "(select * from (select ifNull(max(seq),0)+1 from board) as temp),"
				+ "?, ?, ?, Current_TimeStamp, 0,"
				+ "(select * from (select ifNull(max(seq),0)+1 from board) as temp), 0, 0);";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board insert => " + e.toString());
			return 0;
		}
	} // insert
	
	   // ** replyInsert : 답글입력
	   // => seq: IFNULL 이용
	   // => 입력 컬럼: id, title, content, root, step, indent
       // => default 값 : regdate, cnt
	   // => JDBC subQuery 구문 적용시 주의사항
	   //     -> MySql: select 구문으로 한번더 씌워 주어야함 (insert 의 경우에도 동일) (오라클은 아님)   
	   // => stepUpdate 가 필요함
	   //    댓글 입력 성공후 실행
	   //     -> 현재 입력된 답글의 step 값은 수정되지 않도록 sql 구문의 조건 주의    
	   // => boardList의 출력순서 확인
	   //    ~~~ order by root desc, step asc
	public int rinsert(BoardDTO dto) {
		sql="insert into board(seq, id, title, content, root, step, indent) values("
				+ "(select * from (select ifNull(max(seq),0)+1 from board) as temp)"
				+ ",?,?,?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setInt(4, dto.getRoot());
			pst.setInt(5, dto.getStep());
			pst.setInt(6, dto.getIndent());
			pst.executeUpdate(); // 답글등록 성공 -> stepUpdate(별도의 메소드)
			System.out.println("** stepUpdate count => " + stepUpdate(dto));
			return 1;
		} catch (Exception e) {
			System.out.println("** Reply insert Exception => " + e.toString());
			return 0;
		} // try-catch
	} // rinsert
	// ** stepUpdate : step 값 증가
	// => 조건
	//    -> root 동일 and step >= and 새글 제외
	public int stepUpdate(BoardDTO dto) {
		sql="update board set step=step+1 where root=? and step>=? "
				+ "and seq <> (select * from (select ifNull(max(seq),0) from board) as temp)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getRoot());
			pst.setInt(2, dto.getStep());
			return pst.executeUpdate(); // 수정된 Data 갯수 return
		} catch (Exception e) {
			System.out.println("** stepUpdate Exception => " + e.toString());
			return 0;
		}
	} // stepUpdate
	// ** update
	public int update(BoardDTO dto) {
		sql="update board set title=?, content=?, cnt=? where seq=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,dto.getTitle());
			pst.setString(2,dto.getContent());
			pst.setInt(3, dto.getCnt());
			pst.setInt(4, dto.getSeq());
			System.out.println(dto);
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** board update Exception => "+e.toString());
			return 0;
		}
	} // update
	
	// ** delete
	// => seq로 삭제
	// => 답글 추가 후 : 원글과 답글 구분
	// 	  -> 원글 : ~ where root=? (모든 답글도 동시에 삭제)
	//    -> 답글 : ~ where seq=?
	public int delete(BoardDTO dto) {
		if(dto.getSeq()==dto.getRoot()) {
			// 원글삭제
			sql="delete from board where root=?";
		} else {
			sql="delete from board where seq=?";
		}
		
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** board delete Exception => "+e.toString());
			return 0;
		}
	} // board delete
} // class 

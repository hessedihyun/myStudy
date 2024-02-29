package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {

	// ** Join
	List<MemberDTO> findMemberJoin(); 
	List<MemberDTO> memberJoin();
	// ** Password Update
	// => @Query 적용
	void updatePassword1(String id, String password);
	void updatePassword2(String id, String password);
	// ** jno별 Member 출력
	// => JPAPepository Method Naming 규약
	List<Member> findByJno(int jno);
	
	// ** selectList
	List<Member> selectList();

	// ** selectOne
	Member selectOne(String id);

	// ** insert & update
	Member save(Member entity);
	

	// ** delete
	void deleteById(String id);

}
package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberService {

	// ** selectList
	List<MemberDTO> selectList();

	// ** selectOne
	MemberDTO selectOne(String id);

	// ** insert
	int insert(MemberDTO dto);

	// ** update
	int update(MemberDTO dto);

	// ** delete
	int delete(String id);

}
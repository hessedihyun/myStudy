package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import pageTest.SearchCriteria;

public interface MemberService {
	
	// ** memberInsert
	int memberInsert(String id, int jno);
	
	// ** Member Paging & Check_List
	public List<MemberDTO> mCheckList(SearchCriteria cri);
	public int mCheckRowsCount(SearchCriteria cri);
	
	public List<MemberDTO> aCheckList(SearchCriteria cri);
	public int aCheckRowsCount(SearchCriteria cri);
	
	public List<MemberDTO> mPageList(SearchCriteria cri);
	public int mTotalRowsCount(SearchCriteria cri);
		
	// ** selectList
	List<MemberDTO> selectList();

	// ** selectOne
	MemberDTO selectOne(String id);

	// ** insert
	int insert(MemberDTO dto);

	// ** update
	int update(MemberDTO dto);
	
	// ** pwUpdate
	int pwUpdate(MemberDTO dto);

	// ** delete
	int delete(String id);

}
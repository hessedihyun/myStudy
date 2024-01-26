package com.ncs.spring02.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.JoDAO;

@Component
public class JoService {
	
	@Autowired
	JoDAO dao;
	
	// ** jo selectList
	public List<JoDTO> selectList() {
		return dao.selectList();
	}
	// ** jo selectOne
	public JoDTO selectOne(int jno) {
		return dao.selectOne(jno);
	}
	// ** jo selectMember
	public List<MemberDTO> selectMember(int jno) {
		return dao.selectMember(jno);
	}
	
	// ** jo insert
	public int insert(JoDTO dto) {
		return dao.insert(dto);
	}
	// ** update
	public int update(JoDTO dto) {
		return dao.update(dto);
	}
	// ** delete
	public int delete(int jno) {
		return dao.delete(jno);
	}
} // JoService class

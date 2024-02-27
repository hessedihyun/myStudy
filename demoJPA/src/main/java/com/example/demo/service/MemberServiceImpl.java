package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	// ** 전역변수 정의
	private final MemberRepository repository;
	
	// ** 기본 C.R.U.D 메소드
	// ** selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	}
	// ** selectOne
	@Override
	public MemberDTO selectOne(String id) {
		return repository.selectOne(id);
	}
	// ** insert
	@Override
	public int insert(MemberDTO dto) {
		return repository.insert(dto);
	}
	// ** update
	@Override
	public int update(MemberDTO dto) {
		return repository.update(dto);
	}
	// ** delete
	@Override
	public int delete(String id) {
		return repository.delete(id);
	}
	// ** pwUpdate
	public int pwUpdate(MemberDTO dto) {
		return repository.pwUpdate(dto);
	}
	
} // class

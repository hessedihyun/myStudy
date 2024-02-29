package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final 초기값 없이, repository 정의
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository repository; // 수정 불가하도록 private final
	
	// ** Join
	@Override
	public List<MemberDTO> findMemberJoin() {
		return repository.findMemberJoin();
	}
	@Override
	public List<MemberDTO> memberJoin() {
		return repository.memberJoin();
	}
	// ** Password Update
	// => @Query 적용
	@Override
	public void updatePassword1(String id, String password) {
		repository.updatePassword1(id, password);
	}
	@Override
	public void updatePassword2(String id, String password) {
		repository.updatePassword2(id, password);
	}
	// ** jno별 Member 출력
	@Override
	public List<Member> findByJno(int jno) {
		return repository.findByJno(jno);
	}
	// ** selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	}
	// ** selectOne
	@Override
	public Member selectOne(String id) {
		Optional<Member> result = repository.findById(id);
		if(result.isPresent()) 
			return result.get(); // Optional 객체에 저장된 값을 제공
			else return null;
	}
	// ** insert & update
	@Override
	public Member save(Member entity) {
		// JPA Exception은 Controller에서 함 
		return repository.save(entity);
	}
	// ** delete
	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}
} // class

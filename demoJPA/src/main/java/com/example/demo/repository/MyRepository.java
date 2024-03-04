package com.example.demo.repository;

import java.util.List;
import com.example.demo.entity.Member;

public interface MyRepository {
	// 엔티티 매니저
	List<Member> emMemberList();
	Member emMemberDetail(String id);
}

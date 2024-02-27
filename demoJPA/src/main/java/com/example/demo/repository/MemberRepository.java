package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Member;

public interface MemberRepository 
					extends JpaRepository<Member, String>{ 
					// Entity가 누군지 알려줘야 해서 Generic임. <T, ID> 에서 T는 Entity. ID는 primary key의 type이다.
	
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

// Q클래스 수동 import
import static com.example.demo.entity.QMember.member;
import static com.example.demo.entity.QJo.jo;
//=> import static  
//   기본 import 구문은 '패키지 명시 없이 클래스를 사용'하게 해 주는데, 
//   import static 구문은 한 단계 더 들어가 '클래스 명시 없이 static변수나 static메서드를 사용'하게 해줌.

@RequiredArgsConstructor
@Repository
public class MemberDSLRepositoryImpl implements MemberDSLRepository {
		
		private final JPAQueryFactory jPAQueryFactory; // dependency 넣어줬기 때문에 생성 가능

		// 1) Entity return
		// => Q클래스로 SQL구문을 작성 -> 엔티티 return
		// => Parameter로 전달된 조원들만 출력하기
		@Override
		public List<Member> findMemberJnoDSL(int jno) {
			return jPAQueryFactory.selectFrom(member)
					.where(member.jno.eq(jno).and(member.point.goe(100))) // goe : 크거나 같다. (loe : 작거나 같다. // gt, lt 등 있음)
					.orderBy(member.age.desc())
					.fetch();
		}
		
		// 2) Join & DTO return
		// => QueryDSL에서 DTO 적용하기
		// => 메모장 QueryDSL사용법.txt 참고  
		//    4종류 방법중 1) Setter 접근 , 2) 필드 직접접근 적용
		   
		// 2.1) Setter 접근 
		// => MemberDTO의 setter를 호출해서, Dto의 멤버변수에 injection 해주는 방식.
		// => Projections.bean(~~~)로 접근
		// @Override
		public List<MemberDTO> findMemberJoinDSL2() {
			return jPAQueryFactory.select(
					Projections.bean(MemberDTO.class
										, member.id
										, member.name
										, member.jno
										, jo.jname
										, jo.project))
								.from(member)
								.leftJoin(jo)
								.on(member.jno.eq(jo.jno))
								.fetchJoin()
								.fetch();
		} // findMemberJoinDSL()
		
		// 2.2) 필드 직접 접근 
		// => 필드에 직접 접근해서 값을 injection하는 방식.
		// => Projections.fields(~~~)로 접근
		//    그러므로 DTO에 getter, setter 모두 필요 없고
		//    MemberDTO의 멤버변수에 값이 injection 된다.
		@Override
		public List<MemberDTO> findMemberJoinDSL() {
			return jPAQueryFactory.select(
				Projections.fields(MemberDTO.class 
						, member.id
						, member.name
						, member.jno
						, jo.jname
						, jo.project))
				.from(member)
				.leftJoin(jo)
				.on(member.jno.eq(jo.jno))
				.fetchJoin()
				.fetch();
		}
		
} // class

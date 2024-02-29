package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Guestbook;

//** interface JpaRepository<T, ID>
//=> interface 계층도
// Repository<T, ID> -> CrudRepository<T, ID> 
//-> PagingAndSortingRepository<T, ID> -> JpaRepository<T, ID>

//=> CrudRepository<T, ID> : 기본기능만 사용하는 경우 상속받음
//=> JpaRepository<T, ID> : JPA 관련기능 대부분 사용하는경우 상속받음

//=> 이들을 상속받는것만으로도 모든 처리가 됨.
//=> Test Code 로 확인

//** JPA 의 CRUD 메서드
//=> ~Repository 를 통해 JPA 의 EntityManager 에 접근됨.
//=> EntityManager : 영속 계층에 접근하여 엔티티에 대한 DB 작업을 제공함 .
//  (sql문을 실제로 만들고, DAO처럼 처리하는 핵심적인 기능을 하는 객체 ex) MyBatis에서는 sqlSessionFactoryBean이 한다.
//=> 주요 메서드 / JpaRepository에 있는 메소드들이다.(save, findAll 등...)
//    - Insert : save(엔티티 객체)
//    - Select : findAll(), findById(키), getOne(키) ..
//    - Update : save(엔티티 객체)
//    - Delete : deleteById(키), delete(엔티티 객체)
//=> Insert, Update 모두 save(엔티티 객체)를 사용하는 이유
//    - JpaRepository 의 save는 비교후 없으면 insert, 
//      있으면 update를 동작시키고, entity를 return.   
//    - deleteById(키) 삭제의 경우에도 select 후 없으면 (Exception 처리) ~~DataAccessException 발생시키고
//      있으면 삭제하고 void 로 정의되어 return값 없음. 

//=> JpaRepository의 save()메서드 동작원리
//    - 새로운 entity이면 persist()를 아니면 merge()를 호출
//      EntityManager의 persist()와 merge() 메소드임
//      persist() : 입력(insert), merge() : 수정(update)

public interface GuestbookRepository 
					extends JpaRepository<Guestbook, Long>{
	
	// (sqlSessionFactorybean 대신 이번엔) EntityManager를 주입받아서 처리한다.
	// EntityManager에 findAll() 등 다양한 메소드가 만들어져 있다. 모든 메소드를 총괄해서 관리한다.
			
}

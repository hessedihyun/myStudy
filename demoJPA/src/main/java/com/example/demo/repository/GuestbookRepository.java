package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Guestbook;

public interface GuestbookRepository 
					extends JpaRepository<Guestbook, Long>{
	
	// (sqlSessionFactorybean 대신 이번엔) EntityManager를 주입받아서 처리한다.
	// EntityManager에 findAll() 등 다양한 메소드가 만들어져 있다. 모든 메소드를 총괄해서 관리한다.
			
}

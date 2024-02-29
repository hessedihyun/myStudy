package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Jo;
import com.example.demo.entity.Member;

public interface JoService {
	
	// ** jo selectList
	List<Jo> selectList();

	// ** jo selectOne
	Jo selectOne(int jno);

	// ** jo insert & update
	Jo save(Jo entity);

	// ** delete
	void deleteById(int jno);

}
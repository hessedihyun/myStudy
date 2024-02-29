package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Board;

public interface BoardService {
	
	// ** selectList
	public List<Board> selectList();
	// ** selectOne
	public Board selectOne(int seq);
	// ** insert & update
	public Board save(Board entity);
	// ** rinsert(reply insert)
	public int rinsert(Board entity);
	// ** delete
	public void deleteById(int seq);
}

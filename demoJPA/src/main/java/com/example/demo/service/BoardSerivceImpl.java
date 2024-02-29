package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import mapperInterface.BoardMapper;
import pageTest.SearchCriteria;

@Service
@RequiredArgsConstructor
public class BoardSerivceImpl implements BoardService {
	
	private final BoardRepository repository;
	
	// ** 답글등록
	// => rinsert, stepUpdate
	@Override
	public int rinsert(Board entity) {
		return 0;
	}
	@Override
	public List<Board> selectList() {return repository.findAll();}
	@Override
	public Board selectOne(int seq) {
		Optional<Board> result = repository.findById(seq);
		if(result.isPresent()) return result.get();
		else return null;
	}
	@Override
	public Board save(Board entity) {
		return repository.save(entity);
	}
	@Override
	public void deleteById(int seq) {
		repository.deleteById(seq);
	}
} // class

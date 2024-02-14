package com.ncs.spring02.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.model.BoardDAO;

import mapperInterface.BoardMapper;
import pageTest.Criteria;

@Service
public class BoardSerivceImpl implements BoardService {
	
	// @Autowired
	// BoardDAO dao;
	
	
	@Autowired
	BoardMapper mapper;
	
	// ** Board_Paging
	public List<BoardDTO> bPageList(Criteria cri) {
		return mapper.bPageList(cri);
	}
	// ** totalCount
	public int totalRowsCount(Criteria cri) {
		return mapper.totalRowsCount(cri);
	}
	
	
	// ** 답글등록
	// => rinsert, stepUpdate
	@Override
	public int rinsert(BoardDTO dto) {
		if(mapper.rinsert(dto)>0) {
			// stepUpdate
			System.out.println("** stepUpdate Count => " + mapper.stepUpdate(dto));
			return 1;
		} else return 0;
	}
	@Override
	public List<BoardDTO> selectList() {return mapper.selectList();}
	@Override
	public BoardDTO selectOne(int seq) {return mapper.selectOne(seq);}
	@Override
	public int insert(BoardDTO dto) {return mapper.insert(dto);}
	@Override
	public int update(BoardDTO dto) {return mapper.update(dto);}
	@Override
	public int delete(BoardDTO dto) {return mapper.delete(dto);}
} // class

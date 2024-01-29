package com.ncs.spring02.controller;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.service.BoardService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	// @Autowired //@AllArgs 있어서 주석처리
	BoardService service;
	
	// ** Board List
	@GetMapping("/boardList") // method까지 모두 mapping 시켜줌 > RequestMapping보다 효과적
	public void boardList(Model model) {
		model.addAttribute("blist", service.selectList());
	} // boardList
	
	// ** Board Detail
	@GetMapping("/detail")
	public String boardDetail(Model model, @RequestParam("jCode") String jCode, @RequestParam("seq") int seq) {
		String uri="board/boardDetail";
		if("U".equals(jCode)) uri="board/boardUpdate";
		model.addAttribute("bdetail", service.selectOne(seq));
		return uri;
	}
	
	@RequestMapping("/boardInsert")
	public void boardInsert(Model model) {
	} // boardInsert로 가기
	
	@GetMapping("/insert")
	public String insert(Model model, String title, String content, BoardDTO dto) {
		String uri = "redirect:boardList";
		int rs = service.insert(dto);
		if(rs>0) {
			model.addAttribute("message", "새로운 글을 작성하셨습니다.");
		} else {
			model.addAttribute("message", "새로운 글 작성에 실패하였습니다. 다시 작성해주세요");
			uri="board/boardInsert";
		}
		return uri;
	}
	
} // class

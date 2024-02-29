package com.example.demo.controller;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import com.example.demo.service.JoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
	
	BoardService bservice;
	JoService jservice;
	
	// ** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("blist", bservice.selectList());
	} // boardList

	// ** Board Detail
	@GetMapping("/detail")
	public String boardDetail(HttpSession session, Model model, 
							  @RequestParam("jCode") String jCode, 
							  @RequestParam("seq") int seq) {
		String uri="board/boardDetail";
		Board entity = bservice.selectOne(seq);
		
		if("U".equals(jCode))	uri="board/boardUpdate";
		else if( !entity.getId().equals((String)session.getAttribute("loginID")) ) {
			// => 조회수 증가 조건에 만족
			entity.setCnt(entity.getCnt()+1);
			try {
				log.info("** boardDetail 수정 성공 =>" + bservice.save(entity));
				model.addAttribute("bdetail", entity);
			} catch (Exception e) {
				log.info("** boardDetail 수정 실패" + e.toString());
				model.addAttribute("message", "board detail 수정이 실패했습니다.");
			}
		}
		return uri;
	} // detail

// *********************************************** //

	@RequestMapping("/boardInsert")
	public void boardInsert() {
	} // boardInsert로 가기
	
	@PostMapping("/insert")
	public String insert(Model model, String title, String content, Board entity) {
		String uri = "redirect:boardList";
		try {
			log.info("** Board 원글 insert 성공 => "+bservice.save(entity));
			model.addAttribute("message", "새로운 글을 작성하셨습니다.");
		} catch (Exception e) {
			log.info("** Board 원글 insert 실패 => "+e.toString());
			model.addAttribute("message", "새로운 글 작성에 실패하였습니다. 다시 작성해주세요");
			uri="board/boardInsert";
		}
		return uri;
	} // board insert
	
	@PostMapping("/update")
	public String update(Model model, Board entity, RedirectAttributes rttr) {
		String uri="redirect:detail?jCode=D&seq="+ entity.getSeq();
		model.addAttribute("bdetail", entity);
		try {
			log.info("** board 원글 update 성공 => " + bservice.save(entity));
			rttr.addFlashAttribute("message", "글 수정이 완료되었습니다.");
		} catch (Exception e) {
			log.info("** board 원글 update 실패 => " + e.toString());
			rttr.addFlashAttribute("message", "글 수정에 실패하였습니다. 다시 작성해주세요");
			uri="board/boardUpdate";
		}
		return uri;
	} // board update
	
	// ** Board delete
	@GetMapping("/delete")
	public String delete(Board entity, Model model, int seq) {
		String uri="redirect:boardList";
		try {
			log.info("** Board delete 성공 : 삭제 seq 번호 => " + seq);
			model.addAttribute("message", entity.getSeq()+"번 글 삭제가 정상적으로 완료되었습니다.");
		} catch (Exception e) {
			log.info("** Board delete 실패 => " + e.toString());
			model.addAttribute("message", entity.getSeq()+"번 글 삭제 오류 발생! 다시 시도해주세요.");
			uri="board/boardDetail";
		}
		return uri;
	} // board delete

	// ** Reply Insert
	@GetMapping("/replyInsert")
	public void replyInsert(Board entity) {
	} // replyInsert.jsp로 가기
	
	@PostMapping("/replyInsert")
	public String replyInsert(Board entity, Model model, RedirectAttributes rttr) {
		String uri="redirect:boardList";

		entity.setStep(entity.getStep()+1);
		entity.setIndent(entity.getIndent()+1);
		
		if(bservice.rinsert(entity)>0) {
			rttr.addFlashAttribute("message", "답글 등록이 성공적으로 완료되었습니다.");
		} else {
			model.addAttribute("message", "답글 등록이 실패하였습니다.");
			uri="board/replyInsert";
		}
		return uri;
	} // replyInsert
} // class

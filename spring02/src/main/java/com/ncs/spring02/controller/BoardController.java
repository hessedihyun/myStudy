package com.ncs.spring02.controller;
import javax.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
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
	// => 글 요청 처리중, 글을 읽기 전
	// => 조회수 증가 처리
	//	  -> loginID(로그인계정) != bdetail.id(board의 아이디)
	
	@GetMapping("/detail")
	public String boardDetail(HttpSession session, Model model, 
							  @RequestParam("jCode") String jCode, 
							  @RequestParam("seq") int seq) {
		String uri="board/boardDetail";
		// => 조회수 증가
		// => selectOne 의 결과를 보관
		// -> update 요청이 아니고, loginID와 글쓴이의 id가 다른 겨우
		BoardDTO dto = service.selectOne(seq);
		
		if("U".equals(jCode))	uri="board/boardUpdate";
		else if( !dto.getId().equals((String)session.getAttribute("loginID")) ) {
			// => 조회수 증가 조건에 만족
			dto.setCnt(dto.getCnt()+1);
			service.update(dto);
		}
		model.addAttribute("bdetail", dto);
		return uri;
	} // detail
	
	@RequestMapping("/boardInsert")
	public void boardInsert() {
	} // boardInsert로 가기
	
	@PostMapping("/insert")
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
	} // board insert
	
	@PostMapping("/update")
	public String update(Model model, BoardDTO dto, RedirectAttributes rttr) {
		String uri="redirect:detail?jCode=D&seq="+dto.getSeq();
		model.addAttribute("bdetail", dto);
		if(service.update(dto)>0) {
			rttr.addFlashAttribute("message", "글 수정이 완료되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "글 수정에 실패하였습니다. 다시 작성해주세요");
			uri="board/boardUpdate";
		}
		return uri;
	} // board update
	@GetMapping("/delete")
	public String delete(int seq, Model model) {
		String uri="redirect:boardList";
		if(service.delete(seq)>0) {
			model.addAttribute("message", seq+"번 글 삭제가 정상적으로 완료되었습니다.");
		} else {
			model.addAttribute("message", seq+"번 글 삭제 오류 발생! 다시 시도해주세요.");
			uri="board/boardDetail";
		}
		return uri;
	} // board delete
	// ** Reply Insert
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) {
	  // => 답글처리를 위해 부모글의 root, step, indent를 전달 받으면,
      //    이 인자에 담겨진 이 값은 requestScope과 동일
      // => 그러므로 response가 전송 전까지는 서버에서 사용가능!
      //    (※단, 객체명의 첫 알파벳(문자)를 소문자로 써야 접근 및 사용가능!) ex) ${boardDTO.~~}
	} // replyInsert.jsp로 가기
	
	
	// => 메서드명과 요청명이 위의 메서드와 동일하지만,
	//    Post요청이고, 인자가 다르기 때문에 해당 코드가 허용됨
	@PostMapping("/replyInsert")
	public String replyInsert(BoardDTO dto, Model model, RedirectAttributes rttr) {
		// ** 답글 등록
		// => 성공 시 : boardList 입력 완료 확인
		// => 실패 시 : replyInsert 재입력 유도
		String uri="redirect:boardList";
		// => DTO 값 확인
		// 	  -> id, title, content : 사용가능
		//    -> root, step, indent : 사용가능
		//    -> 부모글의 step, indent : 1씩 증가
		// => SQL 처리
		//    -> replyInsert 처리, step값 업데이트
		dto.setStep(dto.getStep()+1);
		dto.setIndent(dto.getIndent()+1);
		if(service.rinsert(dto)>0) {
			rttr.addFlashAttribute("message", "답글 등록이 성공적으로 완료되었습니다.");
		} else {
			model.addAttribute("message", "답글 등록이 실패하였습니다.");
			uri="board/replyInsert";
		}
		return uri;
	}
} // class

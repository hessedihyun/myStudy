package com.example.demo.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.BoardDTO;
import com.example.demo.service.BoardService;
import com.example.demo.service.JoService;

import lombok.AllArgsConstructor;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	// @Autowired //@AllArgs 있어서 주석처리
	BoardService service;
	JoService jservice;
	
	// ** Board_Paging
	// => ver01 : Criteria 사용
	// public void bPageList(Model model, Criteria cri, PageMaker pageMaker)
	// => ver02 : SearchCriteria 사용 (검색기능 추가)
	@GetMapping("/bPageList")
	public void bPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		// 1) Criteria 처리
		// => ver01: currPage, rowsPerPage 값들은 Parameter로 전달되어 자동으로 cri에 set되어 있음
		// => ver02: ver01 + searchType, keyword도 동일하게 cri에 set
		cri.setSnoEno();
		
		// 2) Service
		// => 출력 대상인 Rows를 select
		// => ver01, 02 모두 같은 service 메서드 사용,
		// 	  mapper interface에서 사용하는 Sql 구문만 교체
		//	  즉, BoardMapper.xml에 새로운 sql구문 2개 추가, BoardMapper.java interface 수정
		model.addAttribute("jlist",jservice.selectList());
		model.addAttribute("blist", service.bPageList(cri));
		// model.addAttribute("myInfo", jservice.selectList());
		
		// 3) View처리 : PageMaker를 이용하기
		// => cri, totalRowsCount (Read from DB)
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
	} // bPageList.jsp로 이동하기
	
	@GetMapping("/bCheckList")
	public String bCheckList(HttpServletRequest request, Model model, 
			SearchCriteria cri, PageMaker pageMaker) {
		
		
		String uri="board/bPageList";
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		System.out.println("=> RequestURI: " + request.getRequestURI());
		// => RequestURI: /spring02/board/bPageList
		System.out.println("=> mappingName : " + mappingName);
		
		// 1) Criteria 처리
		cri.setSnoEno();
		
		// 2) Service
		// => check의 값을 선택하지 않은 경우 : check 값을 null로 확실하게 해줘야 함.
		//    mapper에서 명확하게 구분할 수 있도록 해야 정확한 처리 가능
		if(cri.getCheck()!=null && cri.getCheck().length<1)
			cri.setCheck(null);
			
		model.addAttribute("blist", service.bCheckList(cri));
		
		// 3) View처리 : PageMaker를 이용하기
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.bCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return uri;
		
	} // bCheckList
	
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
	public String delete(BoardDTO dto, Model model) {
		String uri="redirect:boardList";
		if(service.delete(dto)>0) {
			model.addAttribute("message", dto.getSeq()+"번 글 삭제가 정상적으로 완료되었습니다.");
		} else {
			model.addAttribute("message", dto.getSeq()+"번 글 삭제 오류 발생! 다시 시도해주세요.");
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

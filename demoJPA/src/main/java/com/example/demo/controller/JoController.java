package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping(value = "/jo")
public class JoController {

	JoService jservice;
	MemberService mservice;

// ************************************************* //
	@GetMapping("/joList")
	public void jlist(Model model) {
		model.addAttribute("jlist", jservice.selectList());
	} // jList로 가기

	@GetMapping("/joInsert")
	public void joInsert() {
	} // joInsert로 가기
	
	@GetMapping("/joUpdate")
	public void joUpdate(Model model, String jno) {
		model.addAttribute("jdetail", jservice.selectOne(Integer.parseInt(jno)));
	} // joUpdate로 가기
	
// ************************************************* //

	@GetMapping("/detail")
	public String jdetail(Model model, String jno, HttpServletRequest request, Jo entity, @RequestParam("jCode") String jCode) {
		String uri = "jo/joDetail";
		
		if("U".equals(jCode)) 
			uri = "jo/joUpdate";
		
		model.addAttribute("jdetail", jservice.selectOne(entity.getJno()));
		model.addAttribute("jmlist", mservice.findByJno(entity.getJno()));
		
		return uri;
	} // jdetail

	@PostMapping(value= "/insert")
	public String insert(Model model, Jo entity) {
		String uri = "redirect:joList";
		try {
			log.info("** Jo insert 성공 => " + jservice.save(entity));
			model.addAttribute("message", "새로운 조를 만드셨습니다.");
		} catch (Exception e) {
			model.addAttribute("message", "조등록 오류! 다시 만들어주세요");
			uri = "jo/joInsert";
		}
		return uri;
	} // insert
	
	@PostMapping(value= "/update")
	public String update(Model model, Jo entity) {
		String uri = "jo/joDetail";
		// String uri = "jo/joDetail";
		try {
			log.info("** Jo update 성공 => " + jservice.save(entity));
			model.addAttribute("message", "조 수정을 성공하셨습니다.");
			model.addAttribute("jdetail", jservice.selectOne(entity.getJno()));
			model.addAttribute("jmlist", mservice.findByJno(entity.getJno()));
		} catch (Exception e) {
			model.addAttribute("message", "조 수정 오류! 다시 시도해주세요");
			uri = "jo/joUpdate";
		}
		return uri;
	} // update
	
	@GetMapping("/delete")
	public String delete(Model model, String jno, RedirectAttributes rttr) {
		String uri = "redirect:joList";
		try {
			jservice.deleteById(Integer.parseInt(jno));
			log.info("** jo Delete 성공 : 삭제 아이디 => " + jno);
		} catch (Exception e) {
			log.info("** jo Delete 실패 => " + e.toString());
			rttr.addFlashAttribute("message", "조 삭제가 실패하였습니다. 다시 시도해주세요.");
			uri = "jo/joDetail";
		}
		return uri;
	} // jo delete

} // JoController class

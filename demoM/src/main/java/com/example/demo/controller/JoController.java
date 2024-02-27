package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;



@Controller
@RequestMapping(value = "/jo")
public class JoController {

	@Autowired(required = false)
	JoService service;

	@Autowired(required = false)
	MemberService mservice;

	@RequestMapping(value = "/joList", method = RequestMethod.GET)
	public void jlist(Model model) {
		model.addAttribute("jlist", service.selectList());
	} // jList로 가기

	@RequestMapping(value = "/joDetail", method = RequestMethod.GET)
	public String jdetail(Model model, String jno) {
		String uri = "jo/joDetail";
		model.addAttribute("jdetail", service.selectOne(Integer.parseInt(jno)));
		model.addAttribute("jmlist", service.selectMember(Integer.parseInt(jno)));
		model.addAttribute("jno", jno);
		/* model.addAttribute("userDetail", mservice.selectList(jno)); */
		return uri;
	} // jdetail

	@RequestMapping(value = "/joInsert", method = RequestMethod.GET)
	public void joInsert() {
	} // joInsert로 가기

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Model model, JoDTO dto) {
		String uri = "redirect:joList";
		System.out.println("여기까지!!!!!");
		int rs = service.insert(dto);
		if (rs > 0) {
			// 조만들기 성공
			model.addAttribute("message", "새로운 조를 만드셨습니다.");
		} else if (rs == -1) {
			// 조만들기 실패(중복된 조)
			model.addAttribute("message", "조번호가 중복됩니다. 다시 만드세요.");
			uri = "jo/joInsert";
		} else {
			model.addAttribute("message", "조등록 오류! 다시 만들어주세요");
			uri = "jo/joInsert";
		} // if-else
		return uri;
	} // insert

	@RequestMapping(value = "/joUpdate", method = RequestMethod.GET)
	public void joUpdate(Model model, String jno) {
		model.addAttribute("jdetail", service.selectOne(Integer.parseInt(jno)));
	} // joUpdate로 가기

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, JoDTO dto) {
		String uri = "jo/joDetail";
		model.addAttribute("jdetail", dto);

		if (service.update(dto) > 0) {
			model.addAttribute("message", "조 관련 내용 수정이 성공적으로 처리되었습니다.");
		} else {
			model.addAttribute("message", "조 관련 내용 수정이 실패했습니다.");
			uri = "jo/joUpdate";
		}
		return uri;
	} // jo update

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, String jno, RedirectAttributes rttr) {
		String uri = "redirect:joList";
		if (service.delete(Integer.parseInt(jno)) > 0) {
			rttr.addFlashAttribute("message", "조 삭제를 성공하였습니다. 확인해보세요.");
		} else {
			rttr.addFlashAttribute("message", "조 삭제가 실패하였습니다. 다시 시도해주세요.");
			uri = "jo/joDetail";
		}
		return uri;
	} // jo delete

	// ** jmInsert 출력 *************************
	@RequestMapping(value = "/jmInsert", method = RequestMethod.GET)
	public void jmInsert(Model model, String currjno) {
		model.addAttribute("memberInfo", mservice.selectList());
		model.addAttribute("currjno", currjno);
	} // jmInsert로 이동하기

	// Post 방식 jmInsert
	 @PostMapping("/jmInsert")
	 public String memberInsert(Model model, String currjno, 
			 String[] grouping, RedirectAttributes rttr) { 
		 
		 String uri="redirect: joDetail?jno="+currjno;
		 int jno = Integer.parseInt(currjno);
		 
		 System.out.println("★Jno => "+jno);
		 System.out.println("★grouping.length => "+grouping.length);
		 
		 if(grouping.length>0) {
			 for(int i=0; i<grouping.length; i++) {
				 String id = grouping[i];
				 System.out.println("★Id => "+ id);
				 mservice.memberInsert(id,jno);
			 }
			 rttr.addFlashAttribute("message", "멤버 등록을 성공하였습니다. 확인해보세요.");
		 } else {
			 rttr.addFlashAttribute("message", "멤버 등록을 실패하였습니다. 다시 시도해주세요.");
		 }
	 return uri; 
}
	 

//	@RequestMapping(value="/jmList", method=RequestMethod.GET)
//	public String jmList(Model model, String jno) {
//		model.addAttribute("jdetail",service.selectOne(Integer.parseInt(jno)));
//		if(service.selectMember(Integer.parseInt(jno))!=null) {
//			model.addAttribute("jmlist", service.selectMember(Integer.parseInt(jno)));
//		} else {
//			model.addAttribute("message", "해당 조에 아직 멤버가 존재하지 않습니다.");
//		}
//		String uri="jo/joDetail";
//		return uri;
//	} // jmlist

} // JoController class

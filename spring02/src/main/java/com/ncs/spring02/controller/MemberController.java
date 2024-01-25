package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;
//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//       DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.    
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//         DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

@Controller
@RequestMapping(value= "/member")
public class MemberController {
	
	@Autowired(required=false)
	MemberService service;
	
	// ** Login Form 출력
	// => VERSION1 : return String
	//	@RequestMapping(value = {"/loginForm"}, method = RequestMethod.GET)
	//   public String loginForm(Model model) {
	//   return "member/loginForm";
	//   } // loginForm - old version
	
	// => VERSION2 : return void
	// => viewName 생략 : 요청명과 동일한 viewName을 찾음 // servlet-context.xml에서 prefix때문에 views까지 자동 찾아감
	// => /WEB-INF/views/member/loginForm.jsp
		@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
		public void loginForm(Model model) { //리턴타입 void인경우 자동으로 요청된 이름이랑 같은 위치로 잡아줌
		} // loginForm - new version
		
		@RequestMapping(value="/login", method = RequestMethod.POST)
		public String login(HttpSession session, Model model, MemberDTO dto) {
			// => 매핑메서드의 인자 객체(필드)와 동일한 컬럼명의 값은 자동으로 할당 - getter setter 명으로 찾아옴
			// 아래의 구문은 필요 없음(파라미터 가져오기)
			// String id = request.getParameter("id");
			// dto.setId(id);
			// 1. 요청 분석
		    // => request 로 전달되는 id, password 처리: 
		    //    매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		    //   ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
		    // => 전달된 password 보관
			String password = dto.getPassword(); // 자동으로 dto에 명칭이 같으면 id, password를 담아줌
			String uri = "redirect:/home"; // 성공시 uri
			// String uri = "redirect:/"; 해도 됨 (home controller에서 처리해줌)
			
			// 2. 서비스 & 결과 처리
			// => id 확인
			// => 존재하면 Password 확인
			// => 성공 : id, name, session에 보관, home으로
			// => 실패 : 재로그인 유도
			dto = service.selectOne(dto.getId());
			if (dto!=null && dto.getPassword().equals(password)) {
				// 성공
				session.setAttribute("loginID", dto.getId());
				session.setAttribute("loginName", dto.getName());
			} else {
				// 실패
				uri="member/loginForm";
				model.addAttribute("message", "id 또는 password 오류! 다시하세요!");
			}
			return uri;
		} // login
		
		// ** Logout
		@RequestMapping(value="/logout", method = RequestMethod.GET)
		public String logout(HttpSession session, Model model) {
			session.invalidate();
			model.addAttribute("message", "정상적으로 로그아웃 처리되었습니다.");
			return "redirect:/";
		} // logout
	
		// ** Member Detail
		// => 단일 Parameter 의 경우 @RequestParam("...") 활용
		// => String jCode = request.getParameter("jCode"); 와 동일
		// 	  단, 해당하는 Parameter가 존재하지 않으면, 400 오류 발생! -> 그러므로 detail 요청값도 detail?jCode=D라고 jCode를 붙여줌
		@RequestMapping(value = "/detail", method = RequestMethod.GET)
		public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
			// 1. 요청분석
			// => id: session 에서 get
			// => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별
			String id = (String)session.getAttribute("loginID");
			String uri="member/mdetail"; // detail기준
			
			// => update 요청 확인 후 uri 수정
			if("U".equals(jCode)) 
				uri = "member/updateForm";
			
			// 2. Service & 결과 처리
			model.addAttribute("myInfo", service.selectOne(id));
			return uri;
		} // detail
		
		// ** Member List 출력
		@RequestMapping(value = "/memberList", method = RequestMethod.GET)
		public void mlist(Model model) {
			model.addAttribute("list", service.selectList());
		} // mList
		
		// ** Join Form 출력 *********************************************
		@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
		public void joinFrom() {
		} // joinForm
		
		// ** Join
		@RequestMapping(value="/join", method = RequestMethod.POST)
		public String join(Model model, MemberDTO dto) {
			// 1. 요청 분석
			// => 이전 : 한글처리, request 값 -> dto에 set
			// => 스프링 : 한글은 filter, request 처리는 parameter(매개변수)로 자동화
			String uri = "member/loginForm"; // 성공시
			
			// 2. Service & 결과
			if(service.insert(dto)>0) {
				// 성공
				model.addAttribute("message", "회원가입 성공!! 로그인 후 사용해주세요.");
			} else {
				// 실패 : 재가입 유도
				uri = "member/joinForm";
				model.addAttribute("message", "회원가입 실패했습니다. 다시 해주세요.");
			}
			return uri;
		} // join
	
		// ** Update
		@RequestMapping(value="/update", method = RequestMethod.POST)
		public String update(HttpSession session, Model model, MemberDTO dto) {
			// 1. 요청 분석 
			// => 성공 : mdetail, 실패 : updateForm
			// => 두 경우 모두 출력하려면 dto 객체의 값("myInfo")이 필요하므로 보관.
			
			String uri = "member/mdetail";
			model.addAttribute("myInfo", dto);
			
			// 2. Service & 결과
			if(service.update(dto)>0) {
				model.addAttribute("message", "회원정보 수정 성공!");
				// => 이름(name)을 수정했을 경우를 대비해, loginName을 수정해준다.
				session.setAttribute("loginName", dto.getName());
			} else {
				// 실패 : 재수정 유도
				uri = "member/updateForm";
				model.addAttribute("message", "회원정보 수정 실패! 다시 하세요.");
			}
			return uri;
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String delete(HttpSession session, Model model) {
			// 1. 요청분석
			// => id: session 에서 get
			// => delete & session 처리
			String id = (String)session.getAttribute("loginID");
			String uri="redirect:/";
			
			// 2. Service & 결과 처리
			if(service.delete(id)>0) {
				// 탈퇴 성공
				model.addAttribute("message","탈퇴 성공하셨습니다. 1개월 후 재가입 가능합니다.");
				session.invalidate();
			} else {
				// 탈퇴 실패
				model.addAttribute("message", "탈퇴 실패하셨습니다. 관리자에게 연락하세요.");
			}
			return uri;
		} // delete
		
			//	예전꺼
			//	// ** Member Detail
			//	@RequestMapping(value ="/mdetailsp", method = RequestMethod.GET)
			//    public String mDetail(Model model) {
			//    	model.addAttribute("mdetail", service.selectOne("merci"));
			//        return "member/memberDetail";
			//    }
	
	
} // class

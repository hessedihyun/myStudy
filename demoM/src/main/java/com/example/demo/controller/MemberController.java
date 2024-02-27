package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//       DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.    
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//         DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

@Log4j2 // @Log4j -> Boot 에서는 2015년 이후 지원중단
@AllArgsConstructor // (@Log4j를 쓴김에 롬복썼으니) 개별적인 @Autowired가 생략 가능하다. -> 초기화하면서 생성한다.
@Controller
@RequestMapping(value= "/member")
public class MemberController {
	
	MemberService service;
	JoService jservice;
	PasswordEncoder passwordEncoder; // DemoConfig에서 설정함
	
	
	// ** Ajax Member_Paging
	// => ver01: "/axmcri"만 구현( Search 기능만 구현)
	// => ver02: "/axmcheck" 요청도 처리할 수 있도록 구현
	// 			-> mappingName에 "check"가 포함되어 있다면, service를 아래 메서드를 호출하도록
	//             service.mCheckList(cri), mCheckRowsCount(cri)
	@GetMapping({"/axmcri","/axmcheck"})
	public String axmcri(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		// 1) Criteria 처리
		cri.setSnoEno();

		// 2) 요청 확인 & Service 처리
		String mappingName = 
				request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		pageMaker.setMappingName(mappingName);
		pageMaker.setCri(cri);
		if(mappingName.contains("check")) {
			// => Check 조건처리
			model.addAttribute("list", service.mCheckList(cri));
			pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		} else { 
			// => Search 조건처리
			model.addAttribute("list", service.mPageList(cri));
			pageMaker.setTotalRowsCount(service.mTotalRowsCount(cri));
		}
		
		// 3) View 처리
		model.addAttribute("pageMaker", pageMaker);
		
		return "axTest/axmPageList";
	} // axmcri
		
	@GetMapping(value = "/aximlist")
	public String axMemberList(Model model) {
		String uri="axTest/axMemberList";
		model.addAttribute("list", service.selectList());
		return uri;
	} // axMemberList 페이지로 이동하기
	
	@GetMapping("/log4jTest")
	public String log4jTest() {
		String name="merci";
		
		log.error("** Lombok @Log4j Test 中 Error : name="+name);
		log.warn("** Lombok @Log4j Test 中 Warn : name="+name);
		log.info("** Lombok @Log4j Test 中 Info : name="+name);
		log.debug("** Lombok @Log4j Test 中 Debug : name="+name);
		log.trace("** Lombok @Log4j Test 中 Trace : name="+name);
		
		return "redirect:/";
	}
	
	@GetMapping("/mPageList")
	public void mPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		// 1) Criteria 처리
		cri.setSnoEno();
		
		// 2) Service	
		model.addAttribute("list", service.mPageList(cri));
		
		// 3) View 처리
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mTotalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	} // mPageList
	
	@GetMapping("/mCheckList")
	public String mCheckList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		String uri="member/mPageList";
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		// 1) Criteria 처리
		cri.setSnoEno();
		
		// 2) Service
		if(cri.getCheck()!=null && cri.getCheck().length <1)
			cri.setCheck(null);
		
		model.addAttribute("list", service.mCheckList(cri));
		
		// 3) View 처리
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return uri;
	} // mCheckList
	
	@GetMapping("/aCheckList")
	public String aCheckList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		String uri="member/mPageList";
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		// 1) Criteria 처리
		cri.setSnoEno();
		
		// 2) Service
		if(cri.getCheck()!=null && cri.getCheck().length <1)
			cri.setCheck(null);
		
		model.addAttribute("list", service.mCheckList(cri));
		
		// 3) View 처리
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return uri;
	} // mCheckList
	
	// ** ID 중복확인
		@GetMapping("/idDupCheck")
		public void idDupCheck(@RequestParam("id") String id, Model model) {
			if(service.selectOne(id) !=null) {
				// => 사용 불가능
				model.addAttribute("idUse", "F");
			} else {
				model.addAttribute("idUse", "T");
				// => 사용 가능
			}
		} // idDupCheck
	
		@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
		public void loginForm(Model model) { //리턴타입 void인경우 자동으로 요청된 이름이랑 같은 위치로 잡아줌
		} // loginForm - new version
		
		@GetMapping("/searchPW")
		public void searchPW(Model model) { //리턴타입 void인경우 자동으로 요청된 이름이랑 같은 위치로 잡아줌
		} // searchPW - new version
		
		@RequestMapping(value="/login", method = RequestMethod.POST)
		public String login(HttpSession session, Model model, MemberDTO dto) {
		
			String password = dto.getPassword(); // 자동으로 dto에 명칭이 같으면 id, password를 담아줌
			String uri = "redirect:/home"; // 성공시 uri

			dto = service.selectOne(dto.getId());

			if (dto!=null && passwordEncoder.matches(password, dto.getPassword())) {
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
		
		// ** searchPW
		@PostMapping("/searchPW")
		public String searchPW2(Model model) {
			String uri = "member/pwUpdate";
			return uri;
		}
		
		// ** Logout
		@RequestMapping(value="/logout", method = RequestMethod.GET)
		public String logout(HttpSession session, Model model) {
			session.invalidate();
			model.addAttribute("message", "정상적으로 로그아웃 처리되었습니다.");
			return "redirect:/";
		} // logout
	
		// ** Member Detail
		@RequestMapping(value = "/detail", method = RequestMethod.GET)
		public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
			// 1. 요청분석
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
		public void joinForm(Model model) {
			model.addAttribute("myInfo", jservice.selectList());
		} // joinForm
		
		// ** Join
		@RequestMapping(value="/join", method = RequestMethod.POST)
		public String join(HttpServletRequest request, Model model, MemberDTO dto) throws IOException {
			// 1. 요청 분석
			
			// 수동
			dto.setId(request.getParameter("id"));
			dto.setPassword(request.getParameter("password"));

			String uri = "member/loginForm"; // 성공시
			
			System.out.println("*11*"+dto);
			// ** upload file 처리
			//    Spring boot realPath => E:\Merci\MyWork\demoM\src\main\webapp\
			
			String realPath = request.getRealPath("/");
			System.out.println("** realPath => "+ realPath);
			
			// 1.2) realPath를 이용해서 물리적저장위치(file1) 확인
			if(!realPath.contains("-tomcat-")) // 개발중
			     realPath += "resources\\uploadImages\\";
			else realPath = "E:\\Merci\\IDSet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";
			
			// 1.3) 폴더 만들기(없을수도 있음을 가정. File)
			File file = new File(realPath);
			if(!file.exists()) {
				// => 저장폴더가 존재하지 않는 경우 만들어줌
				file.mkdir();
			}
			
			// ** File Copy 하기 (IO Stream)
			file = new File(realPath +"basicman1.jpg"); // uploadImages 폴더에 화일존재 확인을 위함
			if(!file.isFile()) { // 존재하지 않는 경우
				String basicImagePath = "E:\\Merci\\MyWork\\demoM\\src\\main\\webapp\\resources\\images\\basicman1.jpg";
				FileInputStream fi= new FileInputStream(new File(basicImagePath));
				FileOutputStream fo = new FileOutputStream(file);
				FileCopyUtils.copy(fi, fo);
			}
			// ------------------------------------------

			// 1.4) 저장경로 완성
				// => 기본 이미지 저장
				String file1="", file2="basicman1.jpg";
				
				MultipartFile uploadfilef = dto.getUploadfilef();
				if( uploadfilef!=null && !uploadfilef.isEmpty()) {
					// => image_File을 선택함
					// 1.4.1) 물리적 위치 저장 (file1)
					file1=realPath+uploadfilef.getOriginalFilename(); // 저장경로(realPath + 화일명) 완성
					uploadfilef.transferTo(new File(file1)); // 해당 경로에 저장(붙여넣기)
					
					// 1.4.2) Table 저장경로 완성 (file2)
					file2 = uploadfilef.getOriginalFilename();
				}
				dto.setUploadfile(file2);
				
				System.out.println("****"+dto);
				// ** PasswordEncoder 적용
				dto.setPassword(passwordEncoder.encode(dto.getPassword()));
			    
				// service.insert(dto); // Transaction_Test, insert1 (abb1, abb2 까지는 들어갔지만, abb3는 들어가지 않음)
				
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
		
		// ** Password 수정 (BCryptPasswordEncoder 추가 후)
		@GetMapping("/pwUpdate")
		public void pwUpdate() {
		}
		// ** PasswordUpdate
		@PostMapping("/pwUpdate")
		public String pwUpdate(HttpSession session, MemberDTO dto, Model model) {
			// 1) 요청을 분석
			// id : session 에서
			// => password : 암호화
			dto.setId((String)session.getAttribute("loginID"));
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));
			
			String uri="member/loginForm"; // 성공시
			
			// 2) Service
			if(service.pwUpdate(dto)>0) {
				// => 성공
				session.invalidate();
				model.addAttribute("message", "Password 수정 완료 재로그인 진행해주세요");
			} else {
				// => 실패
				uri="member/pwUpdate";
				model.addAttribute("message", "Password 수정 실패");
			}
			return uri;
		} // pwUpdate
		
		// ** Update
		@RequestMapping(value="/update", method = RequestMethod.POST)
		public String update(HttpServletRequest request, HttpSession session, 
							 Model model, MemberDTO dto) throws IOException {
			// 1. 요청 분석 
			
			String uri = "member/mdetail";
			model.addAttribute("myInfo", dto);
			System.out.println("22"+dto);
			// ** uploadFile 처리
			MultipartFile uploadfilef = dto.getUploadfilef();
			if( uploadfilef!=null && !uploadfilef.isEmpty()) {
				// => newImage를 선택함
				// 1) 물리적 위치 저장 (file1)
				String realPath = request.getRealPath("/");
				String file1;
				// 2) realPath를 이용해서 물리적저장위치(file1) 확인
				if(  realPath.contains(".eclipse.")) // 개발중
				     realPath = "E:\\Merci\\MyWork\\demoM\\src\\main\\webapp\\resources\\uploadImages\\";
				else realPath = "E:\\Merci\\IDSet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";
				
				// 3) oldFile 삭제
				// => oldFile Name : dto.getUploadfile()
				// => 삭제 경로 : realPath+dto.getUploadfile()
				File delFile = new File(realPath+dto.getUploadfile());
				if(delFile.isFile()) delFile.delete(); // file 존재시 삭제
				
				// 4) newFile 저장
				file1=realPath+uploadfilef.getOriginalFilename(); // 저장경로(realPath + 화일명) 완성
				uploadfilef.transferTo(new File(file1)); // 해당 경로에 저장(붙여넣기)
				
				// 5) Table 저장경로 완성 (file2)
				dto.setUploadfile(uploadfilef.getOriginalFilename());
			}
			
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
		// ** delete
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
			// 1. 요청분석
			// => id: session 에서 get
			// => delete & session 처리
			String id = (String)session.getAttribute("loginID");
			String uri="redirect:/";
			
			// 2. Service & 결과 처리
			if(service.delete(id)>0) {
				// 탈퇴 성공
				session.invalidate();
				rttr.addFlashAttribute("message", "탈퇴 성공하셨습니다!! 1개월 후 재가입 가능합니다.");
			} else {
				// 탈퇴 실패
				rttr.addFlashAttribute("message", "탈퇴 실패하셨습니다~! 관리자에게 연락하세요.");
			}
			return uri;
		} // delete
	
} // class

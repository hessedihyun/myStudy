package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//       DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.    
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//         DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping(value= "/member")
public class MemberController {
	
	MemberService mservice;
	PasswordEncoder passwordEncoder;
	
// ***************************************************** //
	@GetMapping("/loginForm")
	public void loginForm() {
	} // loginForm
	
	@PostMapping("/login")
	public String login(HttpSession session, Model model, Member entity) {
		String password = entity.getPassword();
		String uri = "redirect:/home";
		entity = mservice.selectOne(entity.getId());
		
		if (entity!=null && passwordEncoder.matches(password, entity.getPassword())) {
			// 성공
			session.setAttribute("loginID", entity.getId());
			session.setAttribute("loginName", entity.getName());
		} else {
			// 실패
			uri="member/loginForm";
			model.addAttribute("message", "id 또는 password 오류! 다시하세요!");
		}
		return uri;
	} // login
	
	// ** logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	} // logout
	
// ***************************************************** //
	
	@GetMapping("/detail")
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		String id = (String)session.getAttribute("loginID");
		String uri="member/mdetail";
		
		if("U".equals(jCode)) 
			uri = "member/updateForm";
		model.addAttribute("myInfo", mservice.selectOne(id));
		return uri;
	} // detail
	
	// ** Password 수정 (BCryptPasswordEncoder 추가 후)
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
	} // pwUpdate.jsp로 이동
	
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, Member entity, Model model) {
		// 1) 요청을 분석
		// id : session 에서
		// => password : 암호화
		entity.setId((String)session.getAttribute("loginID"));
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		String uri="member/loginForm"; // 성공시
		
		// 2) Service
		try {
			// mservice.updatePassword1(entity.getId(), entity.getPassword());
			mservice.updatePassword2(entity.getId(), entity.getPassword());
			log.info("** pwUpdate 성공 id => " + entity.getId());
			session.invalidate();
			model.addAttribute("message", "Password 수정 완료 재로그인 진행해주세요");
		} catch (Exception e) {
			log.info("** pwUpdate Exception => " + e.toString());
			uri="member/pwUpdate";
			model.addAttribute("message", "Password 수정 실패");
		}
		return uri;
	} // pwUpdate
	
	// 2) Member_Jo Join List
	// => ver01) @Query("...") 에 JPQL, LEFT_JOIN 구문, MemberDTO return
	// => MemberDTO 는 JoDTO 상속
   @GetMapping("/mjoinList")
   public void mjoinList(Model model) {
      model.addAttribute("banana", mservice.findMemberJoin());
     //  model.addAttribute("banana", mservice.memberJoin());
   }
	
	
	// ** Update
	@PostMapping("/update")
	public String update(HttpServletRequest request, HttpSession session, 
						 Model model, Member entity) throws IOException {
		// 
		String uri = "member/mdetail";
		model.addAttribute("myInfo", entity);
		MultipartFile uploadfilef = entity.getUploadfilef();
		if( uploadfilef!=null && !uploadfilef.isEmpty()) {

			// => newImage를 선택함
			// 1) 물리적 위치 저장 (file1)
			String realPath = request.getRealPath("/");
			String file1;
			// 1.2) realPath를 이용해서 물리적저장위치(file1) 확인
			     realPath = "resources\\uploadImages\\";
			
			// 3) oldFile 삭제
			// => oldFile Name : dto.getUploadfile()
			// => 삭제 경로 : realPath+dto.getUploadfile()
			File delFile = new File(realPath+entity.getUploadfile());
			if(delFile.isFile()) delFile.delete(); // file 존재시 삭제
			
			// 4) newFile 저장
			file1=realPath+uploadfilef.getOriginalFilename(); // 저장경로(realPath + 화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당 경로에 저장(붙여넣기)
			
			// 5) Table 저장경로 완성 (file2)
			entity.setUploadfile(uploadfilef.getOriginalFilename());
		}
		
		// 2. Service & 결과
		try {
			log.info("** member Update 성공 => " + mservice.save(entity));
			model.addAttribute("message", "회원정보 수정 성공!");
			session.setAttribute("loginName", entity.getName());
		} catch (Exception e) {
			log.info("** member Update 실패 => " + e.toString());
			uri = "member/updateForm";
			model.addAttribute("message", "회원정보 수정 실패! 다시 하세요.");
		}
		return uri;
	} // update
	
	// ** Member List 출력
	@GetMapping("/memberList")
	public void mlist(Model model) {
		model.addAttribute("list", mservice.selectList());
	} // mList

// ***************************************************** //
	
	// ** Join Form 출력
	@GetMapping("/joinForm")
	public void joinForm(Model model) {
	// model.addAttribute("myInfo", jservice.selectList());
	} // joinForm
	
	// ** Join
	@PostMapping("/join")
	public String join(HttpServletRequest request, Model model, Member entity) throws IOException {
		entity.setId(request.getParameter("id"));
		entity.setPassword(request.getParameter("password"));
		String uri = "member/loginForm"; // 성공시
		
	    // *** Upload File 처리 **************************
        // 1) 물리적 실제저장위치 확인
        // 1.1) 현재 웹어플리케이션의 실행위치 확인
        // => SpringBoot 의 realPath 값은 
        // => 이클립스 개발환경 (배포전) : C:\MTest\MyWork\demoM\src\main\webapp\
        // => 톰캣 서버 배포후 :  C:\MTest\IDESet\apache-tomcat-9.0.85\webapps\demoJPA\
        // => 그러므로 물리적저장위치 (file1) 값은 동일하게 "resources\\uploadImages\\" 만 연결하면됨.
		
		String realPath = request.getRealPath("/");
		System.out.println("** realPath => "+ realPath);
		// realPath = E:\\Merci\\Myspace\\myStudy\\demoJPA\\src\\main\\wepapp\\
		
        realPath += "resources\\uploadImages\\";
		
        // 1.2) 폴더 만들기 (저장폴더가 존재하지 않는경우 만들어줌)
		File file = new File(realPath);
		if(!file.exists()) {
			// => 저장폴더가 존재하지 않는 경우 만들어줌
			file.mkdir();
		}
		
		// ** File Copy 하기 (IO Stream)
		file = new File(realPath +"basicman1.jpg"); // uploadImages 폴더에 화일존재 확인을 위함
		if(!file.isFile()) { // 존재하지 않는 경우
			String basicImagePath = "E:\\Merci\\MyWork\\demoJPA\\src\\main\\webapp\\resources\\images\\basicman1.jpg";
			FileInputStream fi= new FileInputStream(new File(basicImagePath));
			FileOutputStream fo = new FileOutputStream(file);
			FileCopyUtils.copy(fi, fo);
		}

		// 1.3) 저장경로 완성
		// => 기본 이미지 저장
		String file1="", file2="basicman1.jpg";
		
		MultipartFile uploadfilef = entity.getUploadfilef();
		if( uploadfilef!=null && !uploadfilef.isEmpty()) {
			// => image_File을 선택함
			// 1.3.1) 물리적 위치 저장 (file1)
			file1=realPath+uploadfilef.getOriginalFilename(); // 저장경로(realPath + 화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당 경로에 저장(붙여넣기)
			
			// 1.3.2) Table 저장경로 완성 (file2)
			file2 = uploadfilef.getOriginalFilename();
		}
		entity.setUploadfile(file2);
			
		// ** PasswordEncoder 적용
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		
		try {
			log.info("** member insert 성공 => " + mservice.save(entity));
			model.addAttribute("message", "회원가입 성공!! 로그인 후 사용해주세요.");
		} catch (Exception e) {
			log.info("** member insert Exception => " + e.toString());
			model.addAttribute("message", "회원가입 실패했습니다. 다시 해주세요.");
			uri = "member/joinForm";
		}
		return uri;
	} // join
	
	// ** ID 중복확인
	@GetMapping("/idDupCheck")
	public void idDupCheck(@RequestParam("id") String id, Model model) {
		if(mservice.selectOne(id) !=null) {
			model.addAttribute("idUse", "F");
		} else {
			model.addAttribute("idUse", "T");
		}
	} // idDupCheck
	
	@GetMapping("/searchPW")
	public void searchPW(Model model) { //리턴타입 void인경우 자동으로 요청된 이름이랑 같은 위치로 잡아줌
	} // searchPW - new version

	// ** searchPW
	@PostMapping("/searchPW")
	public String searchPW2(Model model) {
		String uri = "member/pwUpdate";
		return uri;
	}
	
// ***************************************************** //
	// ** delete
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model) {
		String id = (String)session.getAttribute("loginID");
		String uri="redirect:/";
		
		try {
			mservice.deleteById(id);
			log.info("** member Delete 성공 : 삭제 아이디 => " + id);
			session.invalidate();
		} catch (Exception e) {
			log.info("** member Delete 실패 => " + e.toString());
		}
		return uri;
	} // delete
} // class

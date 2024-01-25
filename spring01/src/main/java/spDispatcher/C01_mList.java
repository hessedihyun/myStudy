package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

public class C01_mList implements Controller {
		@Autowired
		MemberService service; // Controller와 MemberService는 의존관계이다. Controller를 TVUser라고 하면, Service는 TV라고 하자. Service는 Dao(Speaker)와 의존관계
		public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) { 
			// Member List
			ModelAndView mv = new ModelAndView();
			mv.addObject("list", service.selectList());
			mv.setViewName("member/memberList");
			return mv;
	}
} // class

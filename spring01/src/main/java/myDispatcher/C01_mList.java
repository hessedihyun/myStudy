package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class C01_mList implements MyController {
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) { 
	// Member List
		MemberService service = new MemberService();
		request.setAttribute("list", service.selectList());
		return "member/memberList";
	}
} // class

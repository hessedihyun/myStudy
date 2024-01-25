package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class C02_mDetail implements MyController {
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// Member Detail
		MemberService service = new MemberService();
		request.setAttribute("myInfo", service.selectOne("merci"));
		return "member/mdetail";
	}
} // class 

//package servlet03_flow;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/logout")
//public class EX04_Logout extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    public EX04_Logout() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		HttpSession session = request.getSession();
//		session.invalidate();
//		System.out.println("Session 무효화 성공");
//		
//		response.setContentType("text/html; charset=UTF-8");
//	    PrintWriter out = response.getWriter();
//		out.print("<h3>** 로그아웃되었습니다!!! o((>ω< ))o **</h3>");
//		out.print("<br><br><h2><a href='javascript:history.go(-1)'>메인홈으로 가기</a></h2><br>");
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}

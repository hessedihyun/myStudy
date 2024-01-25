//package servlet03_flow;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import mvcTest.StudentDTO;
//import mvcTest.StudentService;
//
//@WebServlet("/login")
//public class EX04_Login extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public EX04_Login() {
//        super();
//    }
//	// 일반적으로 메일은 post 요청합니다.
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 1. 요청분석
//		// => 한글, request 의 Parameter 처리
//		request.setCharacterEncoding("UTF-8");
//		
////		int sno = 0;
////		if(request.getParameter("sno")!=null && request.getParameter("sno").length()>0) {
////			sno = Integer.parseInt(request.getParameter("sno"));
////		}
////		
//		String sno = request.getParameter("sno"); // 29-33행 있으면 34행도 없어도 됨, 아래 형변환 필요 없음
//		String name = request.getParameter("name");
//		String uri="index.html";
//		
//		// 2. Service 처리
//		// => Service의 selectOne()을 이용 : sno 확인
//		// 	  확인 결과 성공이면, name을 (그 다음) 확인
//		// => 성공 : index.html 로 , 실패 : flowEx04_LoginForm.jsp 로 이동
//		// 3. View 출력 (Response) : Forward
//		// => 성공 : index.html
//		// => 실패 : flowEx04_LoginForm.jsp (재로그인 유도)
//		StudentService service = new StudentService();
//		StudentDTO dto = new StudentDTO();
//		
//		dto = service.selectOne(Integer.parseInt(sno));
//		response.setContentType("text/html; charset=UTF-8");
//		
////		if(dto!=null && dto.getName().equals(name)) {
////			request.getSession().setAttribute("loginName", name);
////			request.getSession().setAttribute("loginID", sno);
////			System.out.println("**로그인성공**");
////			System.out.println("**로그인 student => " + dto);
////			response.sendRedirect(uri);
////			// 포워드를 하게 되면 안되는 이유 : 
////		} else {
////			System.out.println("**로그인실패**");
////			uri = "servletTestForm/flowEx04_LoginForm.jsp";
////			request.getRequestDispatcher(uri).forward(request, response);
////		}
//		
//		
//		if(dto.getSno() == Integer.parseInt(sno) && dto.getName().equals(name)) {
//			response.sendRedirect(uri);
//		} else {
//			uri = "/web01/servletTestForm/flowEx04_LoginForm.jsp";
//			response.sendRedirect(uri);
//		}
//	} // doGet
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	} // doPost
//
//}

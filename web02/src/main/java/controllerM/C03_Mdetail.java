package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mdetail")
public class C03_Mdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public C03_Mdetail() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 분석
				// => id 처리 , session에서 getAttribute
				String id = (String)(request.getSession().getAttribute("loginID"));
				String uri = "member/mdetail.jsp"; // 성공시
				System.out.println("id 받아오기 성공");
				// => Update 요청 시에는 uri 값을 updateForm.jsp 로 보내기
				if ("U".equals(request.getParameter("jCode"))) {
					uri = "member/updateForm.jsp";
				};
				
		// 2. 서비스 처리
				// => Service, DTO 객체 생성
				// => 결과를 View가 출력 가능하도록 Attribute에 저장
				MemberService service = new MemberService();
				
		// 3. View (Response) : Forward
				request.setAttribute("myInfo",service.selectOne(id));
//				request.getSession().setAttribute("info", service.selectOne(id).getId());
				request.getRequestDispatcher(uri).forward(request, response);
//				response.sendRedirect(uri);
	} // doPost
}

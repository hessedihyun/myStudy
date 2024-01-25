package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.MemberService;
// 프론트 컨트롤러 패턴으로 바꾸는 시도를 해볼 예정이다. (web03) -> 자동화 해놓은 프레임워크 : 스프링
@WebServlet("/mlist")
public class C01_mList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C01_mList() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석
		MemberService service = new MemberService();
		
		// 2) Service 처리, 처리결과 보관
		request.setAttribute("list",service.selectList());
		
		// 3) View 처리
		request.getRequestDispatcher("member/memberList.jsp").forward(request, response);
		
	} //doGet
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost
}

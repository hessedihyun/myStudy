package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;
@WebServlet("/mjoin")
public class C04_Mjoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public C04_Mjoin() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 분석
		// => request의 Parameter 처리
		// => 성공 : 로그인 유도 (loginForm.jsp)
		// => 실패 : 재가입 유도
		String uri="member/loginForm.jsp";
		
		request.setCharacterEncoding("UTF-8"); // post 요청에서는 필수!
		MemberDTO dto = new MemberDTO();
			dto.setId(request.getParameter("id"));
			dto.setPassword(request.getParameter("password"));
			dto.setName(request.getParameter("name"));
			dto.setAge(Integer.parseInt(request.getParameter("age")));
			dto.setJno(Integer.parseInt(request.getParameter("jno")));
			dto.setInfo(request.getParameter("info"));
			dto.setPoint(Double.parseDouble(request.getParameter("point")));
			dto.setBirthday(request.getParameter("birthday"));
			dto.setRid(request.getParameter("rid"));
		
		// 2. 서비스 처리
		// => Service 객체 생성
		MemberService service = new MemberService();
			if (service.insert(dto)>0) {
				// => 성공
				request.setAttribute("message", "회원가입에 성공하셨습니다. 로그인해주세요.");
			} else {
				request.setAttribute("message", "회원가입에 실패하셨습니다. 다시 가입해주세요.");
				uri="member/joinForm.jsp";
			} // if-else
		
		// 3. View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
		
	} // doGet
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost
}

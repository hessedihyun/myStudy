package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;
@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public C02_Login() {
        super(); 
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request의 Parameter 처리 => id, password 보관 처리
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String uri = "home.jsp";
		// 2. 서비스 처리
		// => Service, DTO 객체 생성 
		// => id 확인 : selectOne() 
		// => id 확인되면 password 일치 확인
		// => 성공 : id, name을 session에 보관 후 home.jsp로 이동
		// => 실패 : loginForm으로 재로그인 유도
		MemberService service = new MemberService();
		MemberDTO dto = service.selectOne(id);
		if(dto!=null && dto.getPassword().equals(password)) { 
			request.getSession().setAttribute("loginID", id);
			request.getSession().setAttribute("loginName",dto.getName());
			System.out.println("** 로그인성공 **");
		// 3. View (Response) : Forward
			response.sendRedirect(uri);
		} else {
			System.out.println("** 로그인 실패 **");
			request.setAttribute("message", "로그인에 실패하셨습니다. 다시 로그인을 시도해주시길 바랍니다.");
			uri = "member/loginForm.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		} // else
	} // doPost

} // class

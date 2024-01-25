package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
@WebServlet("/mdelete")
public class C06_Mdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public C06_Mdelete() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = (String)request.getSession().getAttribute("loginID");
		String uri = "home.jsp";
		
		MemberService service = new MemberService();
		if(service.delete(id)>0) {
			request.setAttribute("message", id+"님 탈퇴 성공했습니다!! 1개월 후 재가입 가능합니다.");
			request.getSession().invalidate();
			System.out.println("성공");
		} else {
			request.setAttribute("message", "탈퇴 실패!");
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

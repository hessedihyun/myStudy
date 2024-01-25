package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/logout")
public class C02_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public C02_Logout() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// a 테그로 들어오는 방식은 모두 get 방식이다.
		request.setCharacterEncoding("UTF-8");
		String uri = "home.jsp";
		
		request.getSession().invalidate();
		System.out.println("** 로그아웃 되었습니다 **");
		response.sendRedirect(uri);
	}
}

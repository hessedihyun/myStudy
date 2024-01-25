package mvcTest;

import java.io.IOException;
import mvcTest.StudentService;
import mvcTest.StudentDTO;
import mvcTest.StudentDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JoinBymyself")
public class JoinBymyself extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinBymyself() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		StudentService service = new StudentService();
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int jno = Integer.parseInt(request.getParameter("jno"));
		String info = request.getParameter("info");

		StudentDTO dto = new StudentDTO();
		dto.setName(name);
		dto.setAge(age);
		dto.setJno(jno);
		dto.setInfo(info);
		
		service.insert(dto);
		String uri="join_bymyself.jsp";
		String cong = name+"님 회원가입이 완료되었습니다. 축하드립니다. 당신의 번호는 OO입니다.";
		if(service.insert(dto)>0) {
			request.setAttribute("message", cong);
		} else {
			request.setAttribute("message", "회원가입 실패!");
		}
		uri="home.jsp";
		request.getRequestDispatcher(uri).forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

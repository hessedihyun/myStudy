package mvcTest;

import java.io.IOException;
import mvcTest.StudentDTO;
import mvcTest.StudentService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex02_MVC02Detail() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 갖고 오기 (Service 처리)
		StudentService service = new StudentService();
		int mysno = (Integer)(request.getSession().getAttribute("loginID"));
//		int mysno = Integer.parseInt((String)(request.getSession().getAttribute("loginID")));
		
		// StudentDTO myInfo = service.selectOne((Integer)(request.getSession().getAttribute("loginID")));
		StudentDTO myInfo = service.selectOne(mysno);
		
		request.setAttribute("myInformation", myInfo);
		//Forward
		String uri="mvcTestJsp/ex03_MVC02Detail.jsp";
		request.getRequestDispatcher(uri).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

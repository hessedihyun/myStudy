package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/update")
public class C05_Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public C05_Update() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 분석
		// => request의 한글(post요청 시 필수) & Parameter
		// => 성공 : mdetail로 (memberDetail.jsp)
		// => 실패 : 재수정 유도 (updateForm.jsp)
		// => 출력객체 (myInfo) 필요함
		//    -> redirect 또는 전달된 값들을 myInfo에 저장
		
		String uri="member/mdetail.jsp";
		request.setCharacterEncoding("UTF-8");
		
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
		
		request.setAttribute("myInfo", dto);
		
		MemberService service = new MemberService();
		if(service.update(dto)>0) {
			// 성공 : session에 보관한 Name 수정
			request.getSession().setAttribute("loginName", dto.getName());
			request.setAttribute("message", "회원정보 수정 성공!");
		} else {
			request.setAttribute("message", "회원정보 수정 실패!");
			uri = "member/updateForm.jsp";
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

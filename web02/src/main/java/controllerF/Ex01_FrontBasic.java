package controllerF;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

//** FrontController 패턴 1.
//** Factory 패턴 적용 (2단계-> 권장)
//=> 대표 컨트롤러 1개만 서블릿으로 작성 
//   나머지 컨트롤러는 일반 클래스로 작성
//=> 모든 요청을 이 대표컨트롤러(FrontController)가 받도록 함.
//=> 각각의 서비스를 일반클래스(컨트롤러)로 작성 Factory로부터 제공받음 

//** 요청에 대한 서비스를 if문으로 서블릿내에서 모두 처리 (1단계 -> 비권장)
//=> 코드가독성, 모듈의 재사용성 떨어짐 


@WebServlet(urlPatterns = {"*.do"})
public class Ex01_FrontBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ex01_FrontBasic() {
        super();
    } // 생성자(디폴트)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석
		// => 한글 처리 (요청명 확인 후 한글 처리해줘도 됨)
		// => url 분석후 요청명 확인(/test.do)
		request.setCharacterEncoding("UTF-8");
		String uri= request.getRequestURI();
			// ~URI => /web02/test.do
			// ~URL => http://localhost:8080/web02/test.do
		uri=uri.substring(uri.lastIndexOf("/"));
			// ~uri => /test.do
		
			// (확인 과정)
		System.out.println("** ~URL => " + request.getRequestURL());
		System.out.println("** ~URI => " + request.getRequestURI());
		System.out.println("** ~uri => " + uri);
		
		// 2) Service 실행
		MemberService service = new MemberService();
		MemberDTO dto = new MemberDTO();
		
		if("/mlist.do".equals(uri)) {
			// 생성하고 실행하기 위해서는 요청별로 Class를 찾아서 생성시켜주는 역할을 하는 인스턴스가 있으면 편하겠다. 
			// 이런 환경에서는 요청에 mapping되는 class를 찾아서 생성시켜주는 역할이 필요하고, 그런 역할을 하는 class를 자바에서는 Factory라고 한다.
			// 자바에서 '공장'은 class를 만들어내는 걸 의미한다. bin Factory (bin을 생성해주는 Factory. bin이 클래스라는 의미와 가깝다. 클래스 인스턴스들을 bin이라고 한다.)
			request.setAttribute("list", service.selectList());
			uri="member/memberList.jsp";
		} else if("/mdetail.do".equals(uri)) {
			// ** Member Detail : Login 후 Test
			request.setAttribute("myInfo", service.selectOne((String)request.getSession().getAttribute("loginID")));
			uri="member/mdetail.jsp";
		} else {
			request.setAttribute("message", "요청(uri)에 해당하는 서비스는 없습니다.");
			uri="home.jsp";
		}
		// 3) View 처리
		request.getRequestDispatcher(uri).forward(request, response);
		
	} //doGet
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost
} // class 

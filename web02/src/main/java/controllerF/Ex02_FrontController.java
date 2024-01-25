package controllerF;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** FrontController 패턴 2.
//=> Factory 패턴 적용
// - ServiceFactory
// - 개별컨트롤러(일반클래스) : 일관성을 위해 강제성 부여 ( interface 사용 )

@WebServlet(urlPatterns = {"*.fo"})
// => web.xml에서 처리 가능
public class Ex02_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ex02_FrontController() {
        super();
    }
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
				
		// 2) Service 실행 (** 여기서부터 달라짐)
			// => ServiceFactory에 요청
			// => uri를 전달하면 해당 서비스컨트롤러(일반클래스로 만들어진) 를 생성해서 인스턴스를 제공
			// => SerivceFactory 생성 : 허용하지 않음 (이유 : 싱글턴 패턴을 적용했기 때문)
			// Ex03_ServiceFactory sf = new Ex03_ServiceFactory(); // 오류
			// => 오류 : ~~ Ex03_ServiceFactory() is not visible (private이니까)
			// => 싱글턴 패턴 Test
			   Ex03_ServiceFactory sf = Ex03_ServiceFactory.getInstance();
			// Ex03_ServiceFactory sf1 = Ex03_ServiceFactory.getInstance();
			// Ex03_ServiceFactory sf2 = Ex03_ServiceFactory.getInstance();
			// System.out.printf("**싱글턴 패턴 Test : sf=%s, sf1=%s, sf2=%s \n", sf,sf1,sf2);
			
			   Ex04_Controller controller = sf.getController(uri);
			   uri = controller.doUser(request, response);
			
		// 3) View 처리
			   request.getRequestDispatcher(uri).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

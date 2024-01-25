package myDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*** Spring MVC2_ver01
//=> MyDispatcherServlet (FrontController 역할)
//  HandlerMapping(공장), ViewResolver 를 활용해서 (**ModelAndView 나중에)
//  요청분석, Service, View 를 처리

// => Url Mapping 은 web.xml에서 처리한다. 
public class MyDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// ** 전역변수 정의
	private MyHandlerMapping hmappings;
	private MyViewResolver vresolver;


	// ** 멤버변수 초기화: 생성자에서
    public MyDispatcher() {
        super();
        hmappings = MyHandlerMapping.getInstance();
        vresolver = new MyViewResolver();
        vresolver.setPrefix("/WEB-INF/views/"); // 경로
        vresolver.setSuffix(".jsp"); // 확장자
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석 (한글처리, url 분석 및 요청명 확인)
			request.setCharacterEncoding("UTF-8");
			String uri= request.getRequestURI();
			uri=uri.substring(uri.lastIndexOf("/"));
			
		// 2) Service 실행
		// => MyHandlerMapping(공장)에 요청해서, 해당하는 서비스컨트롤러의 인스턴스를 제공받음
		// => 해당 서비스 실행
			MyController controller = hmappings.getController(uri);
			System.out.println(uri);
			if( controller!=null ) {
				uri = controller.handleRequest(request, response);
			} else {
				uri = "home";
				request.setAttribute("message", "없는 요청입니다.");
			}
			
		// 3) View 처리
			uri = vresolver.getViewName(uri); // 최종 viewName이 완성됨.
			request.getRequestDispatcher(uri).forward(request, response);
	} // doGet
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost

}

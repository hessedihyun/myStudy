package servlet03_flow;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** PageFlow
// 서버 내에서의 이동 flow지만 서버 밖에서의 이동은 '요청'이라고 한다.(이미 배움)
//=> 서버내에서 웹페이지(Html, Jsp) 또는 Servlet 간의 이동
//=> 서버외 : 클라이언트의 요청으로 이동 ( a Tag , submit 등 )    
//=> 경우
// 주로 요청을 받는 역할을 서블릿이 하기 때문에, servlet 위주로 배우는 것이다.
//  servlet -> servlet
//  servlet <-> jsp , html
//  jsp -> jsp   
//** Forward 와 Redirect
//** Forward : 웹브라우져의 주소창이 안바뀜
//  => 현재의 요청에 대해 서버내에서 page만 이동함.
//** Redirect: 웹브라우져의 주소창이 바뀜
//  => 현재의 요청에 대해 응답 -> 재요청 -> 처리

@WebServlet("/flow01")
public class Ex01_Flow01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public Ex01_Flow01() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Forward
		// => flow01 -> hello (요청은 flow01인데, flow01은 안나타남. hello가 나타난 셈)
		
		// => 출력문이 출력되지 않음을 확인
		response.setContentType("text/html; charset=UTF-8");
		// forward 할 때에는 전달받은 때에 한글처리해줘야 한다.
		PrintWriter out = response.getWriter();
		out.print("<h2>** Forward Test**</h2>");
		// => Console 로 확인
		System.out.println("** Foward Test => flow01");
		// 1.1) Forward : Servlet -> Servlet
		String uri= "hello";
		
		// 1.2) Forward : Servlet -> Jsp, Html
//		uri="servletTestForm/form04_Select.jsp";
	    // => 웹브라우져의 주소창은 바뀌지않으며, 요청명과 다른 결과가 실행됨
	    //    요청명은 flow01 이지만 실행결과는 hello
		
		// RequestDispatcher dis = request.getRequestDispatcher(uri);
		// dis.forward(request, response);
		// request.getRequestDispatcher(uri).forward(request, response);
	
		// 2. Redirect (재요청 처리)
		//    첫번째 요청 flow01 에서 hellos 라는 요청을 다시 보내줄것을 웹브라우져에게 response 함.
		//    재요청으로 hellos 가 서버로 전달되어 처리하는 방식   
		// => 그러므로 웹브라우져에 표시된 요청명이 hellos 로 변경됨
		// 2.1) Servlet -> Servlet
		// 2.2) Servlet -> JSP
		
		response.sendRedirect(uri);
		
		
	} // doPost
	
} // class
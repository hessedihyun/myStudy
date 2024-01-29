package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/radio")
public class Ex02_RadioText extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ex02_RadioText() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 1) 요청 분석 : 
	// => request 처리 : 한글, Parameter
	
	request.setCharacterEncoding("UTF-8");
	String gender = request.getParameter("gender");
	String mail = request.getParameter("mailcheck");
	String content = request.getParameter("content");
	
	// 2) 서비스 처리
	if(mail.equals("Yes")) mail = "수신동의";
	else mail = "수신거절";
	// 이 다음부터는 view 처리는 다른 서블릿 또는 jsp, html 등에 넘길수도 있음 (이런 경우를 '페이지의 흐름(page flow)'이라고 한다.)
	// 이동을 할 땐 두가지 방식이 있다. forward, redirect 방식이 있다. 
	
	// 3) 결과 처리 (VIEW)
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.print(" ● gender : " + gender + "<br>");
	out.print(" ● mailchecked : " + mail + "<br>");
	out.print(" ● 가입인사 : " + content + "<br>");
	out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시입력하기</a></h2><br>");
	// history 자바스크립트 내장된 객체의 go(-1) 이전 페이지를 의미함 / 가장 최상위 : window - document, history(객체)뒤로가기 앞으로가기를 담당해주는 객체. 기억해놓고 있음
	// 웹브라우저에 이런 앞뒤 history를 기억하는 버퍼가 있는 것이다. (버퍼 또는 객체) go(-1) 뒤로 가기, go(+1) 앞으로 가기
	
	
	// 2) Service & 결과 처리
	// => response 한글처리, 출력객체 생성 & response에 담기
	}

}
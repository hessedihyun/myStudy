package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/check")
public class Ex03_Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ex03_Check() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 1) 요청 분석 : 
	// => request 처리 : 한글, Parameter
		request.setCharacterEncoding("UTF-8");
		
		String[] gift = request.getParameterValues("gift");
	
    // => CheckBox 처리
    //   -> 하나의 Name 에 복수개의 Value 들이 있음
    //   -> request.getParameterValues("gift") 를 이용해서 배열로 처리    
	
	
		// 2) 서비스 & 결과 처리 => response 한글처리, 출력객체생성 & response에 담기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		// => 선택여부 확인
		if(gift != null && gift.length>0) {
			// => 선택함
			out.print("내가 선택한 결과는 ");
		for(String s : gift) {
			out.print(s+", ");
		}
			out.print("등 이다.");
		} else {
			// => 선택하지 않음
			out.print("선택사항이 없습니다");
		}
		out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시입력하기</a></h2><br>");
	// 2) Service & 결과 처리
	// => response 한글처리, 출력객체 생성 & response에 담기

	}

}

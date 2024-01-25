package servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** Get
//- request 의 header 영역의 url 뒤에 쿼리스트링으로 전달,
//- 일반적으로 256 byte 이내로 크기제한이 있는 것으로 알려져 있으나,
// 이 용량은 브라우져 또는 장비에 따라 다를수 있음
//- 결론은 이미지 등 무거운 자료의 전송은 대부분 불가능 하므로 이때는 post로 해야함. 

//** Post
//- request 의 body 영역에 담겨져 전달됨
//- 크기제한 없음, 보안성 유리

 @WebServlet("/getpost")
public class Ex03_GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ex03_GetPost() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 1) request 의 Parameter 처리
    // => 한글처리, getParameter 전에 해야함
    //   - Tomcat(WAS) 은 Get 방식요청에서는 "UTF-8" 을 default 로 적용함 
    //   ( html 문서에서 "UTF-8" 작성되었고 , Get 방식으로 전송되면 생략가능
    //     단, post 방식에서는 반드시 처리해야함 )
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id"); // 파라미터의 name 값을 불러와 변수 id에 넣어줌 
	// 2) name이 id인 input Tag의 value 값을 return
	String name = request.getParameter("name");
	
	// => 해당하는 파라미터가 없을 경우 : null을 return
	// => parameter는 존재하지만 값이 없는 경우, null 아님(값은 없음)
	// (http://localhost:8080/web01/getpost?id=banana&name=바나나&password=)
	String password = request.getParameter("password");
	if(password != null && password.length()>0) {		
	System.out.println("** password => " + password.toUpperCase());
	} else {
		System.out.println("** password is null **");
	}
	// => 한글처리 해야 함(출력객체 생성 전에 해야 함)
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.print("<html><body>");
	out.print("<h2 style='color:blue;'> ** Hello Servlet **</h2>");
	out.print("<h3> => 전달된 parameter 확인</h3>");
	out.print("<h3> => id: "+id+"</h3>");
	out.print("<h3> => Name: "+name+"</h3>");
	out.print("<h3> => 여기는 doGet 메서드입니다.</h3>");
	out.print("</body></html>");
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

} // class 

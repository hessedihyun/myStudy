package servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/list")
public class EX02_MVC01List extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public EX02_MVC01List() {
        super();
    }
    // ** MVC 패턴1 StudnetList 출력
    // => 요청 Service 처리 (JDBC를 통해서 DB 작업을 해야 함) // 현재 얘가 Controller임
	// => 요청 결과 출력
	StudentService service = new StudentService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// => 요청 Service 처리
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		// List<StudnetDTO> list = service.selectList();
		list = service.selectList();
		
		// => 결과 출력 : 출력 내용을 Response 객체의 Body 영역에 Write
		// - 한글 처리
		// - 출력 객체 생성 & 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<h2 style='color:blue;'>** Servlet_MVC1 StudentList **</h2>");
		if(list != null) {
			
			out.print("<table border='1'>");			
			out.print("<thead>");
			out.print("<tr>");
			out.print("<th>sno</th>");
			out.print("<th>이름</th>");
			out.print("<th>나이</th>");
			out.print("<th>조번호</th>");
			out.print("<th>인사말</th>");
			out.print("<th>포인트</th>");
			out.print("</tr>");
			out.print("</thead>");
			
			out.print("<tbody>");
			for(StudentDTO s:list) {
			out.print("<tr>");
			out.print("<td>" + s.getSno() + "</td>");
			out.print("<td>" + s.getName() + "</td>");
			out.print("<td>" + s.getAge() + "</td>");
			out.print("<td>" + s.getJno() + "</td>");
			out.print("<td>" + s.getInfo() + "</td>");
			out.print("<td>" + s.getPoint() + "</td>");
			out.print("</tr>");
			}
			out.print("</tbody>");
			out.print("<tfoot>");
			out.print("<tr>");
			out.print("<td colspan='4'>총 인원 수</td>");
			out.print("<td colspan='2'>" + "sum" + "</td>");
			out.print("</table>");
			out.print("</body></html>");
		} else {
			out.print("<h2>~~ 출력할 데이터가 없습니다. ~~</h2>");
		}
		
	} //doGet
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

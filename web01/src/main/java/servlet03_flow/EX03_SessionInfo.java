package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessioni")
public class EX03_SessionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public EX03_SessionInfo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Session 인스턴스 생성
		// => Session 객체는 클라이언트가 접속과 동시에 서버에서 자동 생성됨
		//    이 값을 코드내에서 사용하기위해 전달받음
		HttpSession session = request.getSession();
		
		// 2. Session Info 출력
		Date now = new Date(); // 원하는 포맷으로 출력하기 위해서 포맷터를 하나 지정해야 한다. 포맷터 짝꿍이 정해져 있음 SimpleDateFormat이다.
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print("<h2>** Session Info (✿◡‿◡)**</h2>");
		out.printf("<h3>** Session_ID : %s </h3>", session.getId());
		out.printf("<h3>** 현재시간: %s </h3>", formatter.format(now));
		// => session 생성 시간
		now.setTime(session.getCreationTime());
		out.printf("<h3>** CreationTime: %s </h3>", formatter.format(now));
		// => 마지막 접근 시간
		now.setTime(session.getLastAccessedTime());
		out.printf("<h3>** LastAccessedTime: %s </h3>", formatter.format(now));
		
		// ** Session_ID : 263096CDE148E681709D3207A82C82E8 -> 새로고침해도 바뀌지 않는다. 서버 다시 열고 닫으면 바뀐다.
		// ** CreationTime: 2024-01-12 16:30:28 -> 새로고침해도 바뀌지 않는다. 서버 다시 열고 닫으면 바뀐다.

		// 3. Session Time(제한 시간) 설정
		// => 메서드 : session.setMaxInactiveInterval(10); 단위가 초 단위라 10초를 의미한다. 1시간(60*60)
		// => 설정파일(web.xml) : 단위가 분 단위, 0 또는 음수 사용시 무한 시간 <session-config>테그 의 subTag <session-timeout>테그 (web.xml로 가서 작업하기)
		
		// session.setMaxInactiveInterval(10); // 10초
		// 2D1E896B9D9559F1188066A0DE31F0AD -> 10초 후 새로운 새션 (소멸함) 4D7536BFFB8E6F4EBEA82BAAA7F4BF88
		
		// 4. Session 무효화(종료)
		// => invalidate : 무효화
		//    세션객체 자체를 소멸시키는것이 아니라, 세션을 초기화하고 무효화시킴.
		//    session 이 null 이 아니고 session = ""
      
		// => 퀴리스트링으로 테스트 ( ~~/sessioni?jCode=D )
		// => 주의: jCode 라는 Parameter 가 없는 경우 null 을 return 
		//         -> NullPointerException 예방 하도록 작성
		if( "D".equals(request.getParameter("jCode"))) { // null 예방하는 방법
			session.invalidate();
			System.out.println("** Session 무효화 성공 **");
			out.print("<h3>** Session이 지금 막 종료되었습니다. o((>ω< ))o **</h3>");
			return;
		}
		out.print("<h3>** Session Info 정상종료 (◡‿◡✿)**</h3>");
	}
}
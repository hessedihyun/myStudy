<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** View Cookies **</title>
<!-- 클라이언트 단위로 관리되는 객체 : session, cookie (2가지) -->
<!-- session과 cookie : session 서버측 공간 활용, cookie 클라이언트 쪽 공간 활용 -->
<!-- cookie 어떻게 관리, 삭제하는지 등도 알아보자!  -->
<!-- session storage와 local storage는 완전 다른 session 얘기다. 웹브라우저에서 개발자모드에서 Application에서 Storage 종류 중 하나다. 웹브라우저는 왜 2가지를 제공할까?
* local storage : 창을 닫는다고 안 지워짐. 개발자가 명령을 해줘야 함. / session storage : 창을 닫으면 사라진다. 시점 단위의 작업.
* 쿠키도 웹브라우저에서 관리한다. 하지만 서버가 통제하는 경우다. 서버측에서 보내주는 정보를 웹브라우저가 자바스크립트(프로그래밍 언어)로 읽음 // 웹브라우저(하나의 어플리케이션) 기능 : 통신기능, html 엔진 기능, 자바스크립트 엔진 기능, 스토리지 관리, 개발자 모드 지원 등
* local storage와 cookie 차이 : cookie는 request에 늘 자동으로 담긴다.
* 스토리지는 url별로 관리한다. 지속적으로 보관하고 싶으면 local에, 임시적이면 session에 스토리지 활용
-->
</head>
<body>
<h2>** View Cookies **</h2>
<pre>
=> 웹 브라우져는 request의 header에 쿠키의 값을 담아보냄
=> request 객체에 담겨진 쿠키목록 확인
=> request.getCookies() : 배열타입이며 없으면 null
<hr><b>
=> Cookie List
<%
	Cookie[] ck = request.getCookies();
	if(ck!=null && ck.length>0) {
		for(Cookie c : ck) {
			out.print("<br>* Name:" + c.getName());
			out.print(", Value:" + c.getValue());
		} // for
	} else {
		out.print("<br>** Cookie NotFound **");
	}
%>
</b><hr>
=> <a href="ex02_makeCookies.jsp">MakeCookies</a>
=> <a href="ex03_upDelCookies.jsp">UpDelCookies</a>
</pre>
</body>
</html>
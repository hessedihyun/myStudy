<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** index **</title>
</head>
<body>
<h2>** Dynamic Web Project **</h2>

<%
 if (session.getAttribute("loginName")!=null) { %> <!--  jsp가 서블릿보다 편한 점 : request, out, response, session 등 이미 객체화되어 있음. -->
 		<h3><%=session.getAttribute("loginName")%>님 안녕하세요~~</h3>
<% } else { %>
	<h3>로그인 후 이용하세요~~</h3>
<% }
%>

<hr>
<form action="getpost" method="post">
	<input type="text" name="id" value="banana"/> &nbsp;
	<input type="text" name="name" value="바나나"/> &nbsp;
	<input type="text" name="password"/> &nbsp;
	<input type="submit" value="확인"/> &nbsp;
	<!-- getpost?id=banana 여기서 id는 파라미터의 name이다.  
	** id, class, name 속성
	 id나 name 중 하나만 필요한 것은 아니다. form을 디자인하면 보통 id, name 값 둘다 필요하다.
	 -->
	<input type="submit" value="Test"/>
	
</form>
<hr>
<!--  ** 경로 표기
	=> 절대경로 : /(슬러시)로 시작, 프로젝트명부터 전체경로 표기
	   ex) /web01/images/letsgo.png
	   -> (webapp 폴더는 생략)
	=> 상대경로 : 현재위치에서 시작. /로 시작하면 안됨
	   -> ./(점 슬러시)로 시작, ../(점점 슬러시)는 1단계 상위. 
	   ex) "./images/letsgo.png", "images/letsgo.png" 동일
-->
<!-- <img alt="" src="/web01/images/party_parrot.gif" width="300" height="200">   -->
<img alt="" src="images/down.gif" width="300" height="200">
<hr>
&nbsp;<a href="/web01/servletTestForm/flowEx04_LoginForm_ssam.jsp">LoginForm</a>&nbsp;
&nbsp;<a href="/web01/logoutSsam">Logout</a><br>
&nbsp;<a href="/web01/hello">Hello</a>&nbsp;
&nbsp;<a href="/web01/list">MVC01ListS</a>&nbsp;
&nbsp;<a href="/web01/life">LifeCycle</a><br>
&nbsp;<a href="/web01/servletTestForm/form01_Adder.html">Adder</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form02_Radio.jsp">Radio</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form03_Check.jsp">Check</a>&nbsp;
&nbsp;<a href="/web01/servletTestForm/form04_Select.jsp">Select</a><br>
&nbsp;<a href="/web01/flow01">Flow01</a>&nbsp;
&nbsp;<a href="/web01/sessioni">SessionInfo</a>&nbsp;
&nbsp;<a href="/web01/jsp01/ex01_HelloJsp.jsp">HelloJ</a>&nbsp;
&nbsp;<a href="/web01/jsp01/ex02_mvc01List.jsp">M01ListJ</a>&nbsp;
&nbsp;<a href="/web01/list2">M02List</a>&nbsp;
</body>
</html>
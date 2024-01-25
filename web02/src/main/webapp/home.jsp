<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** index **</title>
</head>
<body>
<h2>** web02_MVC02 **</h2>
<%
 if (session.getAttribute("loginID")!=null) { %>
 		<h3  style="color:MediumBlue;"><%=session.getAttribute("loginName")%>님 안녕하세요~~</h3>
 		
<% } else { %>
	<h3 style="color:DarkSeaGreen;">로그인 후 이용하세요~~</h3>
<% }
%>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<hr>
<img alt="" src="/web02/images/banana2.gif" width="300" height="200">
<hr>
* 다음 미션 *<br>
1. 회원가입(join)에서 아이디 중복을 확인해주기<br>
2. password는 버튼을 눌러 수정할 수 있는 형태로 바꿔주기
<hr>
<c:if test="${not empty sessionScope.loginID}">
	&nbsp;<a href="/web02/mdetail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
	&nbsp;<a href="/web02/logout">Logout</a><br>
	&nbsp;<a href="/web02/mdelete">탈퇴</a><br>
</c:if>
<c:if test="${empty sessionScope.loginID}">
	&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="/web02/member/joinForm.jsp">Join</a><br>
</c:if>
&nbsp;<a href="/web02/mlist">MList</a>&nbsp;
</body>
</html>
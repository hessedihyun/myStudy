<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> --%> <!-- session 써야하기 때문에 이 부분은 지우는 게 맞다. 공부 필기를 위해서 주석처리-->
<html>
<head>
	<title>Home</title>
</head>
<body>
<h2> ** Hello Spring!!! ** </h2>
<P>  The time on the server is ${serverTime}. </P>
<pre>
<b>** 이름이 바뀐 기능들</b>
- DispatcherServlet 는 Front Controller
- Handler Mapping 는 ServieFactory
- Controller 는 interface
<b>** 동일한 기능</b>
- View 
<b>** 새로 등장한 기능들</b>
- viewReslover : 응답할 view를 찾는 작업을 처리
- ModelAndView : 응답할 view와 view에게 전달한 값을 저장하는 용도의 객체 (Controller와 Dispatcher Servlet 사이)
</pre>
<hr>
<c:if test="${not empty sessionScope.loginName}">
	<h4>${sessionScope.loginName}님 안녕하세요? (✿◕‿◕✿)</h4><hr></c:if>
<c:if test="${empty sessionScope.loginName}">
	<h4>로그인 후 이용하세요!</h4><hr>
</c:if>
<c:if test="${not empty requestScope.message}">
	<h4>${requestScope.message}</h4>
</c:if>
<img alt="" src="resources/images/aaa.gif" width="300" height="200">
<hr>
<c:if test="${not empty sessionScope.loginName}">
	&nbsp;<a href="/web02/mdetail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
	&nbsp;<a href="/web02/logout">Logout</a><br>
	&nbsp;<a href="/web02/mdelete">탈퇴</a><br>
</c:if>
<c:if test="${empty sessionScope.loginName}">
	&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="/web02/member/joinForm.jsp">Join</a><br>
</c:if>
&nbsp;<a href="mlist">MList</a>&nbsp;
&nbsp;<a href="mdetail">MDetail</a>&nbsp;
&nbsp;<a href="mlistsp">MListSp</a>&nbsp;
&nbsp;<a href="mdetailsp">MDetailSp</a>&nbsp;

</body>
</html>

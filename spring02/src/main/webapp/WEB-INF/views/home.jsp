<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" 
		  href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2> ** Hello Spring_MVC02!!!** </h2>
<P>* Home_time : ${serverTime}. </P>
<hr>
<c:if test="${not empty sessionScope.loginName}">
	${sessionScope.loginName}님 안녕하세요!<br>
</c:if>
<c:if test="${empty sessionScope.loginID}">
	로그인 후 이용하세요.<br>
</c:if>
<c:if test="${not empty requestScope.message}">
	<hr>=> ${requestScope.message}<br>
</c:if>
<hr>
<img alt="mainImage" src="/spring02/resources/images/aaa.gif" width="300" height="200"> <!-- 절대경로: '/'슬러시로 시작 -->
<hr>
<!-- Login 전 -->
<c:if test="${empty sessionScope.loginID}">
	&nbsp;<a href="member/loginForm">LoginF</a>&nbsp;
	&nbsp;<a href="member/joinForm">JoinF</a>&nbsp;
</c:if>
<!-- Login 후 -->
<c:if test="${!empty sessionScope.loginID}">
	&nbsp;<a href="member/logout">Logout</a>&nbsp;
	&nbsp;<a href="member/detail?jCode=D">내정보</a>&nbsp;
	&nbsp;<a href="member/detail?jCode=U">내정보수정</a>&nbsp;
	&nbsp;<a href="member/delete">탈퇴</a>&nbsp;
</c:if>
<br><hr>
&nbsp;<a href="member/memberList">MList</a>&nbsp;
</body>
</html>
<!-- Spring은 원천적으로 절대경로로 사용하지 않음. member 안에 있는 jsp 파일명으로 href를 맞춰주면 된다. 편리함! -->

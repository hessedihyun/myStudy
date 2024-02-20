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
<header>
<img alt="headImage" src="/spring02/resources/images/park.jpg" width="100" height="100">
<h3>Hello! This is Merci's Spring_MVC02</h3>
</header>
<span id=span1>${serverTime}</span>
<div id="mainImg">
<img alt="mainImage" src="/spring02/resources/images/parkdance.gif" width="300" height="300"> <!-- 절대경로: '/'슬러시로 시작 -->
</div>
<div class="div1">
<c:if test="${empty sessionScope.loginID && empty requestScope.message}">
	로그인 후 이용하세요.
</c:if>
<c:if test="${not empty sessionScope.loginName}">
	${sessionScope.loginName}님 안녕하세요!<br>
</c:if>
</div>
<c:if test="${not empty requestScope.message}">
	=> ${requestScope.message}<br>
</c:if>
<br>
<div class="div2">
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
</div>
<hr>
<div class="div2">
&nbsp;<a href="member/memberList">MList</a>&nbsp;
&nbsp;<a href="member/mPageList">MPaging</a>&nbsp;
&nbsp;<a href="jo/joList">JList</a>&nbsp;
&nbsp;<a href="board/boardList">BList</a>&nbsp;
&nbsp;<a href="bcrypt">BCrypt</a><br>
&nbsp;<a href="board/bPageList">BPaging</a>&nbsp;
&nbsp;<a href="etest">Exception</a>&nbsp;
</div>


</body>
</html>
<!-- Spring은 원천적으로 절대경로로 사용하지 않음. member 안에 있는 jsp 파일명으로 href를 맞춰주면 된다. 편리함! -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** LoginForm **</title>
	<link rel="stylesheet" type="text/css"
		href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<div id="div3">
<h2 class="center"> Let's Login then Play!</h2>
<form action="login" method="post">
<table class="center">
	<tr height="40">
		<td><lable for="ids">ID</lable></td>
		<td><input type="text" name="id" id="ids" size="10" autofocus placeholder="아이디"/></td>
	</tr>
	<tr height="40">
		<td><lable for="password">Password</lable></td>
		<td><input type="password" name="password" id="password" size="10" autofocus placeholder="비밀번호"/></td>
	</tr>
</table>
<br>
<div class="center">
<button type="submit">로그인</button>&nbsp;&nbsp;
<button type="reset">취소</button>
</div>
</form>
<br><br>
<div class="center" style="font-size: 20px;">
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if>
</div>
<br>
<div class="center">
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
&nbsp;<a href="joinForm">회원가입</a>&nbsp;
</div>
</div>
</body>
</html>
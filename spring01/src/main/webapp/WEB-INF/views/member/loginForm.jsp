<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** LoginForm **</title>
</head>
<body>
<h2>** Spring1 MVC2 LoginForm **</h2>
<form action="/web02/login" method="post">
<table>
	<tr height="40">
		<td bgcolor="LightGoldenRodYellow"><lable for="ids">ID</lable></td>
		<td><input type="text" name="id" id="ids" size="10" autofocus placeholder="아이디"/></td>
	</tr>
	<tr height="40">
		<td bgcolor="Khaki"><lable for="password">Password</lable></td>
		<td><input type="password" name="password" id="password" size="10" autofocus placeholder="비밀번호"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="로그인"/>&nbsp;&nbsp;
			<input type="reset" value="취소"/>
		</td>
	</tr>
</table>
</form>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if><br><br>
&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
&nbsp;<a href="joinForm.jsp">회원가입</a>&nbsp;
</body>
</html>
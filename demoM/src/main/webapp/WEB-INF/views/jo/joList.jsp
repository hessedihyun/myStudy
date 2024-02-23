<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring02_MVC02 JoList **</title>
<link rel="stylesheet" type="text/css"
		href="/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring02_MVC02 JoList **</h2>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr bgcolor="DarkMagenta">
		<th>Jno</th>
		<th>Jname</th>
		<th>Captain</th>
		<th>조장이름</th>
		<th>Project</th>
		<th>Slogan</th>
	</tr>
	<tr>
	<c:if test="${not empty requestScope.jlist}">
		<c:forEach var="j" items="${requestScope.jlist}">
			<td><a href="joDetail?jno=${j.getJno()}">${j.getJno()}조</a></td>
			<td>${j.getJname()}</td>
			<td>${j.getCaptain()}</td>
			<td>${j.getCname()}</td>
			<td>${j.getProject()}</td>
			<td>${j.getSlogan()}</td>
			<tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.jlist}">
		<td colspan="5">출력할 jo 자료가 없습니다.</td>
	</c:if>
	</tr>
</table>
<hr>
&nbsp;<a href="joInsert">조등록</a>&nbsp;
<hr>
&nbsp;<a href="/home">[Home]</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
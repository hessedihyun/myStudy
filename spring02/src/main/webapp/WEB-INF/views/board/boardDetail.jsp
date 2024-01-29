<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardDetail **</title>
<link rel="stylesheet" type="text/css"
	  href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring MVC2 BoardDetail **</h2>
<table border="1" style="width:100%; text-align: center; text-wrap: wrap;">
	<tr>
		<th colspan="2" bgcolor="lightsalmon">Title</th>
		<td colspan="6"><h3>${requestScope.bdetail.title}</h3></td>
	</tr>
	<tr>
		<th bgcolor="lightsalmon">글번호</th>
		<td>${requestScope.bdetail.seq}</td>
		<th bgcolor="lightsalmon">글쓴이</th>
		<td>${requestScope.bdetail.id}</td>
		<th bgcolor="lightsalmon">날짜시간</th>
		<td>${requestScope.bdetail.regdate}</td>
		<th bgcolor="lightsalmon">조회수</th>
		<td>${requestScope.bdetail.cnt}</td>
	</tr>
	<tr>
		<td colspan="9">${requestScope.bdetail.content}</td>
	</tr>
</table>
<hr>
<c:if test="${not empty sessionScope.loginID}">
&nbsp;<a href="board/boardUpdate">글수정</a>&nbsp;
</c:if>
<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardUpdate **</title>
<link rel="stylesheet" type="text/css"
	  href="/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring MVC2 BoardUpdate **</h2>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<form action="update" method="post">
<table style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr>
		<th>글번호</th>
		<td><input type="text" value="${requestScope.bdetail.seq}" name="seq" id="seq" readonly="readonly"/></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input type="text" value="${requestScope.bdetail.title}" name="title" id="title" required="required"/></td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td><input type="text" value="${sessionScope.loginID}" name="id" id="id" readonly="readonly"/></td>
	</tr>
	<tr>
		<th><label for="ctt">내용</label></th>
		<td><input type="text" value="${requestScope.bdetail.content}" name="content" /></td>
	</tr>
	<tr>
		<th>조회수</th>
		<td><input type="text" name="cnt" id="cnt" value="${requestScope.bdetail.cnt}" readonly="readonly"/></td>
	</tr>
</table><br>
<div class="center">
<button type="submit">작성완료</button>&nbsp;&nbsp;
<button type="reset">재입력</button>
</div>
</form>
<hr>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
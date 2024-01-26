<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jo Update **</title>
<link rel="stylesheet" type="text/css"
		href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring_MVC02 Jo Update **</h2>
<form action="update" method="post">
<table border="1">
	<c:if test="${not empty requestScope.jdetail}">
		<tr>
			<th bgcolor="Lavender">JNO</th>
			<td><input type="text" name="jno" id="jno" value="${requestScope.jdetail.jno}" readonly/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">JNAME</th>
			<td><input type="text" name="jname" id="jname" value="${requestScope.jdetail.jname}"/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">CAPTAIN</th>
			<td><input type="text" name="captain" id="captain" value="${requestScope.jdetail.captain}"/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">project</th>
			<td><input type="text" name="project" id="project" value="${requestScope.jdetail.project}"/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">slogan</th>
			<td><input type="text" name="slogan" id="slogan" value="${requestScope.jdetail.slogan}"/></td>
		</tr>
	</c:if>
	<c:if test="${empty requestScope.jdetail}">
		=> 수정할 조 자료가 없습니다.
	</c:if>
</table>
<table>
	<tr>
		<td><input type="submit" value="수정제출" name="submit"/></td>
		<td><input type="reset" value="재입력하기"/></td>
	</tr>
</table>
</form>
<hr>
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
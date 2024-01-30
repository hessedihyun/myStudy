<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring01_MVC02 MemberList **</title>
<link rel="stylesheet" type="text/css"
	  href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring01_MVC02 MemberList **</h2>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr bgcolor="LightSalmon">
		<th>ID</th>
		<th>Password</th>
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
		<th>Birthday</th>
		<th>추천인</th>
	</tr>
	<c:if test="${not empty requestScope.list}">
		<c:forEach var="m" items="${requestScope.list}">
			<td>${m.getId()}</td>
			<td>${m.password}</td>
			<td>${m.name}</td>
			<td>${m.age}</td>
			<td>${m.jno}</td>
			<td>${m.info}</td>
			<td>${m.point}</td>
			<td>${m.birthday}</td>
			<td>${m.rid}</td>
			<tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.list}">
		<td colspan="9" style="text-align:center;">출력할 멤버 자료가 없습니다.</td>
	</c:if>
</table>
<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
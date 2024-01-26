<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JM List **</title>
<link rel="stylesheet" type="text/css"
		href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h3>** Spring02_MVC02 JMList **</h3>
<table border="1" style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr bgcolor="LightSalmon">
		<th>Jno</th>
		<th>ID</th>
		<th>Password</th>
		<th>Name</th>
		<th>Age</th>
		<th>Info</th>
		<th>Point</th>
		<th>Birthday</th>
		<th>추천인</th>
	</tr>
	<c:if test="${not empty requestScope.jmlist}">
		<c:forEach var="m" items="${requestScope.jmlist}">
			<td>${m.jno}</td>
			<td>${m.getId()}</td>
			<td>${m.password}</td>
			<td>${m.name}</td>
			<td>${m.age}</td>
			<td>${m.info}</td>
			<td>${m.point}</td>
			<td>${m.birthday}</td>
			<td>${m.rid}</td>
			<tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.jmlist}">
		<td colspan="9" style="text-align:center;">출력할 멤버 자료가 없습니다.</td>
	</c:if>
</table>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
</body>
</html>
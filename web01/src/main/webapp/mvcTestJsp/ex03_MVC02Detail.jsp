<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** My Info **</title>
</head>
<body>
<h2>** My Info **</h2>
<h3> This is ${requestScope.myInformation.name}'s Information</h3>
<pre>
=> If you want to revise the part,
   then <a href=""> Click this</a>!
</pre>
<br><b>
<table border="1">
	<tr>
		<th  style="background-color:salmon;">번호</th>
		<th  style="background-color:salmon;">이름</th>
		<th  style="background-color:salmon;">나이</th>
		<th  style="background-color:salmon;">조번호</th>
		<th  style="background-color:salmon;">자기소개</th>
		<th  style="background-color:salmon;">포인트</th>
	</tr>
	<c:if test="${not empty requestScope.myInformation}">
		<tr>
			<td style="text-align:center;">${requestScope.myInformation.sno}</td>
			<td>${requestScope.myInformation.name}</td>
			<td style="text-align:center;">${requestScope.myInformation.age}</td>
			<td style="text-align:center;">${requestScope.myInformation.jno}</td>
			<td>${requestScope.myInformation.info}</td>
			<td style="text-align:center;">${requestScope.myInformation.point}</td>
		</tr>
	</c:if>
	<c:if test="${empty requestScope.myInformation}">
		<tr>
			<td colspan="6" style="text-align:center;"><h3>출력할 자료가 없습니다~~</h3></td>
		</tr>
	</c:if>
</table>
</b>

</body>
</html>
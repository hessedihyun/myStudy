<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MVC02_List JSTL **</title>
</head>
<body>
<h2>** MVC02_List JSTL **</h2>
<!-- scope 이용해서 이미 ex01에 만든 List를 가져올 수 있기 때문에, 다시 List<StudentDTO> 객체를 생성할 필요는 없음 -->
<table border="1" style="width: 100%">
	<tr bgcolor="Aquamarine">
		<th>Sno</th>
		<th>Name</th>
		<th>age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
	</tr>
	<c:if test="${not empty requestScope.myList}">
		<c:forEach var="s" items="${requestScope.myList}">
			<tr>
			<!-- DTO에서 멤버변수(sno, name 등)가 private인데, 어떻게 호출됐을까? getter 함수가 발동! -->
			<!-- 카멜 표기를 하면, 내부적으로 혼란이 일어난다. 그래서 멤버변수를 카멜 표기화 하지 않아야 쓸 수 있다. -->
				<td style="text-align:center;">${s.sno}</td> <!-- getter를 알아서 호출해준다. -->
				<td>${s.name}</td>
				<td style="text-align:center;">${s.age}</td>
				<td style="text-align:center;">${s.jno}</td>
				<td>${s.info}</td>
				<td style="text-align:center;">${s.point}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.myList}">
		<tr>
			<td colspan="6" style="font: weight;"><h3 style="text-align:center;">출력할 자료가 없습니다.</h3></td>
		</tr>
	</c:if>
</table>
<hr>
<h3><a href="javascript:history.go(-1)">이전으로</a></h3>
</body>
</html>
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
<div class="table-title">
	<h2>Spring02 Homepage MemberList</h2>
</div>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table  class="table-fill" border="1" style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr bgcolor="LightSalmon">
		<th class="table-left">ID</th>
<!-- 		<th class="table-left">Password</th> -->
		<th class="table-left">Name</th>
		<th class="table-left">Age</th>
		<th class="table-left">Jno</th>
		<th class="table-left">Info</th>
		<th class="table-left">Point</th>
		<th class="table-left">Birthday</th>
		<th class="table-left">추천인</th>
		<th class="table-left">이미지</th>
	</tr>
	<tbody class="table-hover">
	<c:if test="${not empty requestScope.list}">
		<c:forEach var="m" items="${requestScope.list}">
			<td class="table-left">${m.getId()}</td>
			<%-- <td class="table-left">${m.password}</td> --%>
			<td class="table-left">${m.name}</td>
			<td class="table-left">${m.age}</td>
			<td class="table-left">${m.jno}</td>
			<td class="table-left">${m.info}</td>
			<td class="table-left">${m.point}</td>
			<td class="table-left">${m.birthday}</td>
			<td class="table-left">${m.rid}</td>
			<td class="table-left"><img alt="MYIMAGE" src="/spring02/resources/uploadImages/${m.uploadfile}" width="70" height="60"></td>
			<tr>
		</c:forEach>
	</c:if>
	</tbody>
	<c:if test="${empty requestScope.list}">
		<td colspan="9" style="text-align:center;">출력할 멤버 자료가 없습니다.</td>
	</c:if>
</table>
<hr>
<div class="back color-7">
	<div class="menu align-center expanded text-center SMN_effect-7">
		&nbsp;<a href="/spring02/home" data-hover="Home">Home</a>&nbsp;
		&nbsp;<a href="javascript:history.go(-1)" data-hover="이전으로">이전으로</a>&nbsp;
	</div>
</div>
</body>
</html>
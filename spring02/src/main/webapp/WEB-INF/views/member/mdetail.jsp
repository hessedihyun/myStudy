<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** My Info **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring_MVC02 Member Detail **</h2>
<h2>** My Info **</h2>
<h3> This is ${requestScope.myInfo.name}'s Information</h3>
<pre>
=> If you want to revise the part,
   then <a href="detail?jCode=U"> Click this</a>!
</pre>
<table border="1">
<c:if test="${!empty requestScope.myInfo}">
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">이름</th>
		<td>${requestScope.myInfo.name}</td>
	</tr>
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">아이디</th>
		<td>${requestScope.myInfo.id}</td>
	</tr>
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">나이</th>
		<td>${requestScope.myInfo.age}</td>
	</tr>
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">조번호</th>
		<td>${requestScope.myInfo.jno}</td>
	</tr>
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">소개</th>
		<td>${requestScope.myInfo.info}</td>
	</tr>
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">포인트</th>
		<td>${requestScope.myInfo.point}</td>
	</tr>
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">생년월일</th>
		<td>${requestScope.myInfo.birthday}</td>
	</tr>
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">추천인</th>
		<td>${requestScope.myInfo.rid}</td>
	</tr>
	<tr height="30">
		<th style="background-color:MidnightBlue; color:white; text-align:center;">이미지</th>
		<td><img alt="MYIMAGE" src="/spring02/resources/uploadImages/${requestScope.myInfo.uploadfile}" width="70" height="60"></td>
	</tr>
</c:if>
<c:if test="${empty requestScope.myInfo}">
	<tr>
		<h3>출력할 자료가 없습니다.</h3>
	</tr>
</c:if>
</table>
<br><hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if><br><br>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
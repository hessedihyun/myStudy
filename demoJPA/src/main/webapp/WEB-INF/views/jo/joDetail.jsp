<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jo Detail **</title>
<link rel="stylesheet" type="text/css"
		href="/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring_MVC02 Jo Detail **</h2>
<table border="1">
	<c:if test="${not empty requestScope.jdetail}">
		<tr>
			<th bgcolor="Lavender">JNO</th>
			<td>${requestScope.jdetail.jno}</td>
		</tr>
		<tr>
			<th bgcolor="Lavender">JNAME</th>
			<td>${requestScope.jdetail.jname}</td>
		</tr>
		<tr>
			<th bgcolor="Lavender">CAPTAIN</th>
			<td>${requestScope.jdetail.captain}</td>
		</tr>
		<tr>
			<th bgcolor="Lavender">CNAME</th>
			<td>${requestScope.jdetail.cname}</td>
		</tr>
		<tr>
			<th bgcolor="Lavender">project</th>
			<td>${requestScope.jdetail.project}</td>
		</tr>
		<tr>
			<th bgcolor="Lavender">slogan</th>
			<td>${requestScope.jdetail.slogan}</td>
		</tr>
	</c:if>
	<c:if test="${empty requestScope.jdetail}">
		=> 출력할 조 자료가 없습니다.
	</c:if>
</table>
<c:if test="${not empty requestScope.jmlist}">
<%@ include file="jmList.jsp" %>
</c:if>
<c:if test="${empty requestScope.jmlist}">
<hr>=> 해당 조에는 아직 멤버가 없습니다.<br>
</c:if>
<hr>
<%-- <c:if test="${empty requestScope.jmlist}">
&nbsp;<a href="jmList?jno=${requestScope.jdetail.jno}">조멤버</a>&nbsp;
</c:if> --%>
<%-- <c:if test="${not empty requestScope.jmlist}">
&nbsp;<a href="javascript:history.go(-1)">JMList닫기</a>&nbsp;
</c:if> --%>
&nbsp;<a href="joInsert">조등록</a>&nbsp;
&nbsp;<a href="joUpdate?jno=${requestScope.jdetail.jno}">조수정</a>&nbsp;
&nbsp;<a href="delete?jno=${requestScope.jdetail.jno}">조삭제</a>&nbsp;
&nbsp;<a href="jmInsert?currjno=${requestScope.jdetail.jno}">멤버등록</a>&nbsp;
&nbsp;<a href="joList">JLIST</a>&nbsp;
<hr>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
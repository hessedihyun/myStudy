<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring2 MVC02_Jo Insert **</title>
<link rel="stylesheet" type="text/css"
		href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** Spring2 MVC02_Jo Insert **</h2>
<pre>
If you want to make a new Project Team,
then you must be a 'master'

※ You cannot make the team(jo) which has the same jno that we already used.
</pre>
<form action="insert" method="post">
	<table border="1">
		<tr>
			<th bgcolor="Lavender">JNO</th>
			<td><input type="text" placeholder="조번호" name="jno" id="jno" required="required"/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">JNAME</th>
			<td><input type="text" placeholder="조이름" name="jname" id="jname" required="required"/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">CAPTAIN</th>
			<td><input type="text" placeholder="조장아이디" name="captain" id="captain" required="required"/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">CNAME</th>
			<td><input type="text" placeholder="조장이름" name="cname" id="cname" required="required"/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">project</th>
			<td><input type="text" placeholder="프로젝트명" name="project" id="project"/></td>
		</tr>
		<tr>
			<th bgcolor="Lavender">slogan</th>
			<td><input type="text" placeholder="슬로건" name="slogan" id="slogan"/></td>
		</tr>
</table>
<br>
<table>
	<tr>
		<td><input type="submit" value="조만들기" name="submit"/></td>
		<td><input type="reset" value="재입력하기"/></td>
	</tr>
</table>
</form>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br>
</c:if>
</body>
</html>
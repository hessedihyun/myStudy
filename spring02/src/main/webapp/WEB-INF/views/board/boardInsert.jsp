<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardInsert **</title>
</head>
<body>
<h2>** Spring MVC2 BoardInsert **</h2>
<form action="insert?seq=${seq}">
<table border="1" style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr>
		<th bgcolor="lightgreen">Title</th>
		<td><h3><input type="text" placeholder="글 제목" name="title" id="title" required="required"/></h3></td>
	</tr>
	<tr>
		<th bgcolor="lightsalmon">글쓴이</th>
		<td><input type="text" value="${sessionScope.loginID}" name="id" id="id" readonly="readonly"/></td>
	</tr>
	<tr height="40">
		<td><label for="ctt">Content</label></td>
		<td><textarea rows="5" cols="50" name="content" id="ctt"></textarea></td>
	</tr>
</table>
<table>
	<tr>
		<td><input type="submit" value="작성완료" name="submit"/></td>
		<td><input type="reset" value="재입력"/></td>
	</tr>
</table>
</form>
</body>
</html>
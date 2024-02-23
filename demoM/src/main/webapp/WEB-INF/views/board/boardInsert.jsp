<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardInsert **</title>
<link rel="stylesheet" type="text/css"
	  href="/resources/myLib/myStyle.css">
</head>
<body>
<h2 class="center">** 자유게시판 **</h2>
<form action="insert" method="post">
<table style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr>
		<th>글번호</th>
		<td>seq</td>
	</tr>
	<tr>
		<th>타이틀</th>
		<td><h3><input type="text" placeholder="글 제목" name="title" id="title" required="required"/></h3></td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td><input type="text" value="${sessionScope.loginID}" name="id" id="id" readonly="readonly"/></td>
	</tr>
	<tr>
		<th><h3><label for="ctt">내용</label></h3></th>
		<td><input type="text" placeholder="내용을 여기에 적어주세요. (1000자 내외)" name="content" id="ctt"/></td>
	</tr>
</table>
<br>
<div style="position:absolute; left:35%;">
<button type="submit">작성완료</button>&nbsp;&nbsp;
<button type="reset">재입력</button>
</div>
</form>
</body>
</html>
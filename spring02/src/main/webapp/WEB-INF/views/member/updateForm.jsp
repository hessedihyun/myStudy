<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Revise my Information details **</title>
<link rel="stylesheet" type="text/css"
	  href="/spring02/resources/myLib/myStyle.css">
<!-- disabled는 서버로 값이 나가지 않음. readonly는 서버로 값이 같이 나감. -->
</head>
<body>
<h2>** Revise my Information details **</h2>
<form action="update" method="post">
	<table>
		<tr>
			<th>ID</th>
			<td><input type="text" name="id" id="id" value="${requestScope.myInfo.id}" readonly /></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" name="password" id="password" value="${requestScope.myInfo.password}" readonly/></td>
			<!-- <button type="button" onclick="" >비밀번호수정</button> -->
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" id="name" value="${requestScope.myInfo.name}" name="name" autofocus="autofocus"/></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><input type="text" id="age" value="${requestScope.myInfo.age}" name="age"/></td>
		</tr>
		<tr>
			<th>조번호</th>
			<td><input type="text" value="${requestScope.myInfo.jno}" name="jno"/></td>
		</tr>
		<tr>
			<th>소개</th>
			<td><input type="text" id="info" value="${requestScope.myInfo.info}" name="info"/></td>
		</tr>
		<tr>
			<th>포인트</th>
			<td><input type="text" id="point" value="${requestScope.myInfo.point}" name="point"/></td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td><input type="text" id="birthday" value="${requestScope.myInfo.birthday}" name="birthday"/></td>
		</tr>
		<tr>
			<th>추천인</th>
			<td><input type="text" id="rid" value="${requestScope.myInfo.rid}" name="rid"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="수정하기" name="submit"/></td>
			<td><input type="reset" value="재입력하기"/></td>
		</tr>
	</table>
</form>
<hr>
<c:if test="${not empty requestScope.message}">
	=> ${requestScope.message}
</c:if>
<hr>
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
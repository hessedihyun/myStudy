<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
<h2> ** 비밀번호 찾기 ** </h2>
<h3> 아이디와 이름을 적어주세요 </h3>
<form action="searchPW" method="POST">
	<table>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" id="id" placeholder="아이디를 적어주세요"/></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" id="name" placeholder="등록한 이름을 적어주세요"/></td>
		</tr>
	</table>
	<div class="center">
		<button type="submit" id="submitTag">제출하기</button>&nbsp;&nbsp;
		<button type="reset">다시작성하기</button>
	</div>
</form>

</body>
</html>
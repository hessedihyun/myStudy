<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardDetail **</title>
<link rel="stylesheet" type="text/css"
	  href="/resources/myLib/myStyle.css">
	  <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">
</head>
<body>
<h2>** Spring MVC2 BoardDetail **</h2>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%; text-align: center; text-wrap: wrap;">
	<tr>
		<th colspan="2" bgcolor="lightsalmon">Title</th>
		<td colspan="6"><h3>${requestScope.bdetail.title}</h3></td>
	</tr>
	<tr>
		<th bgcolor="lightsalmon">글번호</th>
		<td>${requestScope.bdetail.seq}</td>
		<th bgcolor="lightsalmon">글쓴이</th>
		<td>${requestScope.bdetail.id}</td>
		<th bgcolor="lightsalmon">날짜시간</th>
		<td>${requestScope.bdetail.regdate}</td>
		<th bgcolor="lightsalmon">조회수</th>
		<td>${requestScope.bdetail.cnt}</td>
	</tr>
	<tr>
		<td colspan="9">${requestScope.bdetail.content}</td>
	</tr>
</table>
<!-- 로그인 한 경우에는 새 글 등록 -->
<br>
<c:if test="${not empty sessionScope.loginID}">
&nbsp;<a href="boardInsert">새글등록</a>&nbsp;
<!-- 댓글등록을 위해 부모글의 root, step, indent 값이 필요하기 때문에
    서버로 보내주어야함 (퀴리스트링으로 작성)    -->
&nbsp;<a href="replyInsert?root=${bdetail.root}&step=${bdetail.step}&indent=${bdetail.indent}">
         답글등록</a>&nbsp;
<br>
</c:if>
<c:if test="${sessionScope.loginID==requestScope.bdetail.id}">
<br>
&nbsp;<a href="detail?jCode=U&seq=${requestScope.bdetail.seq}">글수정</a>&nbsp;
&nbsp;<a href="delete?seq=${requestScope.bdetail.seq}&root=${bdetail.root}">글삭제</a>&nbsp;
<br>
</c:if>
<br>
&nbsp;<a href="boardList">글목록</a>&nbsp;
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardList **</title>
<link rel="stylesheet" type="text/css"
	  href="/spring02/resources/myLib/myStyle.css">
	  
</head>
<body>
<h2>** Spring MVC2 BoardList **</h2>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%; text-wrap: nowrap;">
	<tr bgcolor="Salmon">
		<th>Seq</th>
		<th>Title</th>
		<th>ID</th>
		<th>RegDate</th>
		<th>조회수</th>
	</tr>
	<c:if test="${not empty requestScope.blist}">
		<c:forEach var="b" items="${requestScope.blist}">
			<tr>
			<td>${b.seq}</td>
			<td>
				<!-- 답글 등록 후 Title 출력 전에 들여쓰기 추가 -->
				<c:if test="${b.indent>0}">
					<c:forEach begin="1" end="${b.indent}">
						<span>&nbsp;&nbsp;</span>
					</c:forEach>
					<span style="color:blue;">re..</span>
				</c:if>
				<!-- 로그인 한 경우에만 글 내용 볼 수 있도록 -->
				<c:if test="${not empty sessionScope.loginID}">
					<a href="detail?jCode=D&seq=${b.seq}">${b.title}</a>
				</c:if>
				<c:if test="${empty sessionScope.loginID}">
					${b.title}
				</c:if>
			</td>
			<td>${b.id}</td>
			<td>${b.regdate}</td>
			<td>${b.cnt}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.blist}">
		<td colspan="5" style="text-align:center;">출력할 게시판이 없습니다.</td>
	</c:if>
</table>
<hr>
<c:if test="${not empty sessionScope.loginID}">
&nbsp;<a href="boardInsert?">글등록</a>&nbsp;
<hr>
</c:if>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
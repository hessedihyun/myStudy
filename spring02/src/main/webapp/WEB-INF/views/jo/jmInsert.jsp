<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** 멤버등록 JMInsert **</title>
<link rel="stylesheet" type="text/css"
		href="/spring02/resources/myLib/myStyle.css">
</head>
<body>
<h2>** 멤버등록 JMInsert **</h2>
<br>
${currjno} !!!!
<form action="jminsert" method="post">
	<table>
		<tr>
			<c:forEach var="mf" items="${requestScope.memberInfo}">
				<c:if test="${mf.jno==0}">
					<input type="checkbox" name="grouping" value="${mf.id}">${mf.name}(id: ${mf.id}) <br>
				</c:if>
			</c:forEach>
			<input type="hidden" name="currjno" id="currjno" value="${currjno}" />
		</tr>
	</table>
<br>
<button type="submit" id="submitTag">등록</button>&nbsp;&nbsp;
</form>


</body>
</html>
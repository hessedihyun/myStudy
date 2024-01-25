<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL Loop(forEach) Test01 **</title>
<% 
	String[] menu = {"살치살스테이크","명란아보카도크림파스타","루꼴라치즈토마토샐러드","레드와인","버섯그라탕리조또"};
	pageContext.setAttribute("menuList", menu);
%>
</head>
<body>
<h2>** JSTL Loop(forEach) Test01 **</h2>
<pre>
1) forEach 기본형식
=> Java 의 forEach 와 유사
   for (String s:students) {  out.print(s); }
   
2) varStatus 속성 활용
=> index, count, first, last

3) varStatus 속성 연습
=> first, last : boolean Type
=> for, if(또는 choose) 구문 모두 중첩 가능 
=> 과제
   . first 는 굵은 파랑으로 출력
   . ul li 를 이용해서 출력하면서 menu 뒤에 ',' 표시
   . 단, 마지막에는 마침표를 표시하세요 ~~ 
=> 결과
</pre><hr>
<b>
Test 1) forEach 기본형식 (※ 자동완성 안시켜줌)<br>
<c:forEach var="menuItem" items="${pageScope.menuList}">
<span style="color:green;">${menuItem},&nbsp;</span>
</c:forEach><br>
<br>Test 2) varStatus 속성 활용
<table border="1" style="text-align:center; width: 90%;">
	<tr>
		<th style="background-color:pink;">menu</th>
		<th style="background-color:pink;">index</th>
		<th style="background-color:pink;">count</th>
		<th style="background-color:pink;">first</th>
		<th style="background-color:pink;">last</th>
	</tr>
	<c:forEach var="menuItem2" items="${menuList}" varStatus="vs">
		<tr>
			<td>${menuItem2}</td>
			<td>${vs.index}</td>
			<td>${vs.count}</td>
			<td>${vs.first}</td>
			<td>${vs.last}</td>
		</tr>
	</c:forEach>
</table>
<br>Test 3) 과제
<ul>
	<c:forEach var="menuItem3" items="${menuList}" varStatus="vs">
	 	<c:choose>
	 		<c:when test="${vs.first}">
	 			<li style="color:green; font:bold;">${menuItem3}<span>,</span></li>
	 		</c:when>
<%-- 	 		<c:when test="${vs.last}">
	 			<li>${menuItem3}.</li>
	 		</c:when>
	 		<c:otherwise>
			 	<li>${menuItem3},</li>	 	
	 		</c:otherwise> --%>
	 		<c:otherwise>
	 			<li>${menuItem3}${vs.last? '.':','}</li>
	 		</c:otherwise>
	 	</c:choose>
	</c:forEach>
</ul>
</b>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL forEach begin, end, step Test **</title>
</head>
<body>
<h2>** JSTL forEach begin, end, step Test **</h2>
<pre>
=> 구간반복: StartIndex(begin), LastIndex(end), 증감값(step) 적용하기
=> step 의 default 값은 1
=> 실습 1)
   1 ~ 10 까지를 다음처럼 출력하세요 ~~
   -> 1, 2, 3, .....10         
   -> java의 예 : for (int i=1; i<11; i++) { ......  }   
=> 결과
</pre>
<b>
<c:forEach var="i" begin="1" end="10" step="1" varStatus="vs">
	${i}${vs.last? "":", "}
</c:forEach>
</b>
<hr><pre>
=> 실습 2)
   1 ~ 10 중에서 짝수만, index, count 출력하기
   단, table 을 이용해서 출력하세요 ~~
   ex03_for01 의 table 과 비교해 보세요 ~~    
=> count : 반복횟수 
=> index : 배열 등 index가 존재하는 경우에는 index 값을 출력.
           index가 없는 경우, 반복자(iterator) 의 값.   
           step 을 지정하지 않으면 1씩 증가   
=> 아래 표에서 index가 0부터 시작하지 않는 이유 :
index는 배열을 전제한다. index가 없을 경우는 반복자의 값(i)를 갖고 온다.
   
</pre>
<b>
=> 결과1: step=1 <br>
<table border="1" style="text-align:center; width: 30%;">
	<tr>
		<th style="background-color:lightblue;">짝수</th>
		<th style="background-color:lightblue;">index</th>
		<th style="background-color:lightblue;">count</th>
	</tr>
	<c:forEach var="num" begin="1" end="10" step="1" varStatus="vs">
		<tr>
			<td>${num%2==0? num:""}</td>
			<td>${num%2==0? vs.index:""}</td>
			<td>${num%2==0? vs.count:""}</td>
		</tr>
	</c:forEach>
</table>
<br><br>
=> 결과1-2: step=1 <br>
<table border="1" style="text-align:center; width: 30%;">
	<tr>
		<th style="background-color:lightblue;">짝수</th>
		<th style="background-color:lightblue;">index</th>
		<th style="background-color:lightblue;">count</th>
	</tr>
	<c:forEach var="num" begin="1" end="10" step="1" varStatus="vs">
		<tr>
			<c:choose>
				<c:when test="${num%2==0}">
					<td>${num}</td>
					<td>${vs.index}</td>
					<td>${vs.count}</td>
				</c:when>
				<c:otherwise />
			</c:choose>
		</tr>
	</c:forEach>
</table>
</b><br><br>
<b>
=> 결과2: step=2(조건 : 2부터 2씩 증가) <br>
<table border="1" style="text-align:center; width: 30%;">
	<tr>
		<th style="background-color:lightblue;">짝수</th>
		<th style="background-color:lightblue;">index</th>
		<th style="background-color:lightblue;">count</th>
	</tr>
	<c:forEach var="even" begin="2" end="10" step="2" varStatus="vs">
		<tr>
			<td>${even}</td>
			<td>${vs.index}</td>
			<td>${vs.count}</td>
		</tr>
	</c:forEach>
</table>
</b><br><hr>
<pre>
=> 실습 3) 1~30 을 다음처럼 1행에 5개씩 출력하세요~<br> 
1,2,3,4,5
6,7,8,9,10
11,12,13,14,15
...
............30
</pre>
<b>
<span style="color:blue;">=> Java Code (JSP 스크립트 형식)</span>
<br>
<%
	for(int i=0; i<6; i++) {
		for(int j=1; j<6; j++) {
			out.print(5*i+j);
			if((5*i+j)%5!=0){
				out.print(", ");
			} else {
				out.print("");
			}
		} %>
		<br>
<% 	}
%>
<br>
<span style="color:blue;">=> JSTL 언어</span>
<c:forEach var="i" begin="0" end="5">
	<br>
	<c:forEach var="j" begin="1" end="5">
		<span>${5*i+j}${5*i+j%5==0? "":", "}</span>
	</c:forEach>
</c:forEach><br><br>
<span style="color:blue;">=> 두 번째 방법</span><br>
<c:forEach var="i" begin="1" end="30">
	${i}${i%5==0 ? "<br>":", "}
</c:forEach>
</b>
</body>
</html>
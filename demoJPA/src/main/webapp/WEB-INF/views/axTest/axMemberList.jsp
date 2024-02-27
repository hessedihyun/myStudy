<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring01_MVC02 MemberList **</title>
<link rel="stylesheet" type="text/css"
	  href="/resources/myLib/myStyle.css">
</head>
<body>
<div class="table-title">
	<h2>ax MemberList</h2>
</div>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table  class="table-fill" border="1" style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr bgcolor="hotpink">
		<th class="table-left">ID</th>
<!-- 		<th class="table-left">Password</th> -->
		<th class="table-left">Name</th>
		<th class="table-left">Age</th>
		<th class="table-left">Jno</th>
		<th class="table-left">Info</th>
		<th class="table-left">Point</th>
		<th class="table-left">Birthday</th>
		<th class="table-left">추천인</th>
		<th class="table-left">이미지</th>
		<th class="table-left">Delete</th>
	</tr>
	<tbody class="table-hover">
	<c:if test="${not empty requestScope.list}">
		<c:forEach var="m" items="${requestScope.list}">
			<!-- ** idbList(id별 boardList) ** 
					=> 선택된 id를 function에 전달 (매개변수를 활용)
					   idbList('merci')
					   => a Tag에 이벤트 적용시 책갈피 기능 활용가능
						-> href : 적용하지 않음(이동하지 않음) 
						-> href = "#id" : id 위치로 이동, "#": 최상단으로 이동
						-> href = "javascript: ;" : 이동하지 않음
			-->
			<tr>
			<td class="table-left">
				<%-- <span class="textlink" onclick="idbList('${m.id}')">${m.getId()}</span> --%>
				     <a href="#resultArea2" onclick="idbList('${m.id}')">${m.id}</a>
			</td>
			<%-- <td class="table-left">${m.password}</td> --%>
			<td class="table-left">${m.name}</td>
			<td align="center">${m.age}</td>
			<!-- ** Jo 정보 Div에 출력 -->
			<td align="center">
				<span class="textlink" onmouseover="showJoDetail(event, ${m.jno})" 
						    onmouseout="hideJoDetail()">${m.jno}</span>
	    	</td>
			<td class="table-left">${m.info}</td>
			<td class="table-left">${m.point}</td>
			<td class="table-left">${m.birthday}</td>
			<td class="table-left">${m.rid}</td>
			<td class="table-left"><img alt="MYIMAGE" src="/resources/uploadImages/${m.uploadfile}" width="70" height="60"></td>
			<!-- ** Delete 기능 추가 
				=> 선택된 id를 function 에 전달 (매개변수를 활용)
				=> 결과는 성공/실패 여부만 전달: RESTController
				=> 성공 : Deleted로 변경, onclick 이벤트 해제
			-->
			<td class="table-left"><span class="textlink" onclick="axiDelete(event, '${m.id}')" id="${m.id}">Delete</span></td>
			<tr>
		</c:forEach>
	</c:if>
	</tbody>
	<c:if test="${empty requestScope.list}">
		<td colspan="10" style="text-align:center;">출력할 멤버 자료가 없습니다.</td>
	</c:if>
</table>
<div id="content"></div>
<hr>
</body>
</html>
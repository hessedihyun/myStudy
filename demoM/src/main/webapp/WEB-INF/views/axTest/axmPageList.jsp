<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardPageList **</title>
<link rel="stylesheet" type="text/css"
	  href="/resources/myLib/myStyle.css">
<script type="text/javascript">
"use strict" 
function keywordClear() {
	if(document.getElementById('searchType').value=='all')
	   document.getElementById('keyword').value='';
} // keywordClear
function checkClear() {
	// document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용 안 됨 (배열의 형태)
	let ck = document.querySelectorAll('.clear');
	for(let i=0; i<ck.length; i++) {
		ck[i].checked=false;
	}
	return false; // reset 의 기본 이벤트를 제거해줘야 한다.
} // checkClear
function checkClearage() {
	// document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용 안 됨 (배열의 형태)
	let ck = document.querySelectorAll('.aclear');
	for(let i=0; i<ck.length; i++) {
		ck[i].checked=false;
	}
	return false; // reset 의 기본 이벤트를 제거해줘야 한다.
} // checkClearage
function checkAll() {
	// document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용 안 됨 (배열의 형태)
	let ck = document.querySelectorAll('.clear');
	for(let i=0; i<ck.length; i++) {
		ck[i].checked=true;
	}
	return false; // reset 의 기본 이벤트를 제거해줘야 한다.
} // checkAllage
function checkAllage() {
	// document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용 안 됨 (배열의 형태)
	let ck = document.querySelectorAll('.aclear');
	for(let i=0; i<ck.length; i++) {
		ck[i].checked=true;
	}
	return false; // reset 의 기본 이벤트를 제거해줘야 한다.
} // checkAll
</script>
</head>
<body>
<h2>** ax MemberPageList **</h2>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<hr>
<div id="searchBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${pageMaker.cri.searchType=='all'? "selected":""}>전체</option>
		<option value="id" ${pageMaker.cri.searchType=='id'? "selected":""}>ID</option>
		<option value="name" ${pageMaker.cri.searchType=='name'? "selected":""}>Name</option>
		<option value="info" ${pageMaker.cri.searchType=='info'? "selected":""}>Info</option>
		<option value="jno" ${pageMaker.cri.searchType=='jno'? "selected":""}>Jno</option>
		<option value="birthday" ${pageMaker.cri.searchType=='birthday'? "selected":""}>Birthday</option>
		<option value="rid" ${pageMaker.cri.searchType=='rid'? "rid":""}>Rid</option>
   <!-- <option value="">조회수</option> -->
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}">
	<button id="searchBtn" onclick="searchDB()" >Search</button>
	<hr>
	<!-- CheckBox Test -->
	<form action="mCheckList" method="get" id="mypagingform" enctype="multipart/form-data">
		<b>조별 : </b>
		<!-- check 의 선택한 값 유지를 위한 코드 -->
	      <c:set var="ck0" value="false" />
	      <c:set var="ck1" value="false" />
	      <c:set var="ck2" value="false" />
	      <c:set var="ck3" value="false" />
	      <c:set var="ck4" value="false" />
	      <c:set var="ck7" value="false" />
	      <c:set var="ck8" value="false" />
	      <c:set var="ck9" value="false" />
	      <c:set var="ck13" value="false" />
	      <c:set var="ck14" value="false" />
	      <c:set var="ck15" value="false" />
	      <c:forEach  var="jno" items="${pageMaker.cri.check}" >
	         <c:if test="${jno=='0'}"> <c:set var="ck0" value="true" /> </c:if>
	         <c:if test="${jno=='1'}"> <c:set var="ck1" value="true" /> </c:if>
	         <c:if test="${jno=='2'}"> <c:set var="ck2" value="true" /> </c:if>
	         <c:if test="${jno=='3'}"> <c:set var="ck3" value="true" /> </c:if>
	         <c:if test="${jno=='4'}"> <c:set var="ck4" value="true" /> </c:if>
	         <c:if test="${jno=='7'}"> <c:set var="ck7" value="true" /> </c:if>
	         <c:if test="${jno=='8'}"> <c:set var="ck8" value="true" /> </c:if>
	         <c:if test="${jno=='9'}"> <c:set var="ck9" value="true" /> </c:if>
	         <c:if test="${jno=='13'}"> <c:set var="ck13" value="true" /> </c:if>
	         <c:if test="${jno=='14'}"> <c:set var="ck14" value="true" /> </c:if>
	         <c:if test="${jno=='15'}"> <c:set var="ck15" value="true" /> </c:if>
		  </c:forEach>
      <!----------------------------------- -->
		
		<input type="checkbox" name="check" class="clear" value="0" ${ck0 ? 'checked':''}>미정&nbsp;
		<input type="checkbox" name="check" class="clear" value="1" ${ck1 ? 'checked':''}>Business&nbsp;
		<input type="checkbox" name="check" class="clear" value="2" ${ck2 ? 'checked':''}>static&nbsp;
		<input type="checkbox" name="check" class="clear" value="3" ${ck3 ? 'checked':''}>칭찬해조&nbsp;
		<input type="checkbox" name="check" class="clear" value="4" ${ck4 ? 'checked':''}>카톡으로얘기하조&nbsp;
		<input type="checkbox" name="check" class="clear" value="7" ${ck7 ? 'checked':''}>칠면조<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="check" class="clear" value="8" ${ck8 ? 'checked':''}>8조&nbsp;
		<input type="checkbox" name="check" class="clear" value="9" ${ck9 ? 'checked':''}>9조&nbsp;
		<input type="checkbox" name="check" class="clear" value="13" ${ck13 ? 'checked':''}>13조&nbsp;
		<input type="checkbox" name="check" class="clear" value="14" ${ck14 ? 'checked':''}>14조&nbsp;
		<input type="checkbox" name="check" class="clear" value="15" ${ck15 ? 'checked':''}>15조&nbsp;
		<input type="submit" value="Search">&nbsp;
		<input type="button" value="All" onclick="return checkAll()">&nbsp;
		<input type="reset" value="Clear" onclick="return checkClear()"><hr>
	</form>
	<!-- CheckBox Test 2 -->
	<%-- <form action="aCheckList" method="get">
		<b>나이대 : </b>
		<!--  check 의 선택한 값 유지를 위한 코드 -->
	      <c:set var="ck10" value="false" />
	      <c:set var="ck20" value="false" />
	      <c:set var="ck30" value="false" />
	      <c:forEach  var="age" items="${pageMaker.cri.check}" >
	         <c:if test="${age>=10 && age<20}"> <c:set var="ck10" value="true" /> </c:if>
	         <c:if test="${age>=20 && age<30}"> <c:set var="ck20" value="true" /> </c:if>
	         <c:if test="${age>=30 && age<40}"> <c:set var="ck30" value="true" /> </c:if>
		  </c:forEach>
      <!----------------------------------- -->
		
		<input type="checkbox" name="check" class="aclear" value="10" ${ck10 ? 'checked':''}>10대&nbsp;
		<input type="checkbox" name="check" class="aclear" value="20" ${ck20 ? 'checked':''}>20대&nbsp;
		<input type="checkbox" name="check" class="aclear" value="30" ${ck30 ? 'checked':''}>30대&nbsp;
		<input type="submit" value="Search">&nbsp;
		<input type="button" value="All" onclick="return checkAllage()">&nbsp;
		<input type="reset" value="Clear" onclick="return checkClearage()"><hr>
	</form> --%>
	
</div>
<table  class="table-fill" border="1" style="width:100%; text-align: center; text-wrap: nowrap;">
	<tr bgcolor="LightSalmon">
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
	</tr>
	<tbody class="table-hover">
	<c:if test="${not empty requestScope.list}">
		<c:forEach var="m" items="${requestScope.list}">
			<td class="table-left">${m.getId()}</td>
			<%-- <td class="table-left">${m.password}</td> --%>
			<td class="table-left">${m.name}</td>
			<td class="table-left">${m.age}</td>
			<td class="table-left">${m.jno}</td>
			<td class="table-left">${m.info}</td>
			<td class="table-left">${m.point}</td>
			<td class="table-left">${m.birthday}</td>
			<td class="table-left">${m.rid}</td>
			<td class="table-left"><img alt="MYIMAGE" src="/spring02/resources/uploadImages/${m.uploadfile}" width="70" height="60"></td>
			<tr>
		</c:forEach>
	</c:if>
	</tbody>
	<c:if test="${empty requestScope.list}">
		<td colspan="9" style="text-align:center;">출력할 멤버 자료가 없습니다.</td>
	</c:if>
</table>
<div align="center">
     <!-- 1) Prev, First -->
     <c:choose>
     	<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
		<!-- ver02: searchQuery 메서드 적용 -->     	
     		<a href="${pageMaker.searchQuery(1)}">FP</a>&nbsp;
     		<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp;
     	</c:when>
     	<c:otherwise>
     		<font color="Gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>&nbsp;
     	</c:otherwise>
     </c:choose>
     
     <!-- 2) PageNo -->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage}">
			<a href="${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
		</c:if>
	</c:forEach>

     <!-- 3) Next, LastPage -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;</a>
			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo)}">LP</a>
		</c:when>
		<c:otherwise>
			<font color="Gray">&nbsp;&GT;&nbsp;LP</font>
		</c:otherwise>
	</c:choose>
</div>
<hr>
<c:if test="${not empty sessionScope.loginID}">
&nbsp;<a href="member/joinForm">회원가입</a>&nbsp;
<hr>
</c:if>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
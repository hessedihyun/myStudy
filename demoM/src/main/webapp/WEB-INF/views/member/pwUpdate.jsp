<!-- member 파일 내 pwUpdate.jsp 추가 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pwUpdate</title>
<link rel="stylesheet" href="/resources/myLib/myStyle.css">
<script src="/resources/myLib/incheck.js"></script>
<script>
let pCheck =false;
let p2Check = false;

onload = function() {
	document.getElementById('password').focus();
	
	// => Password
	document.getElementById('password').addEventListener('keydown', 
	(e)=>{
		if(e.which==13) {
			e.prevetDefault();
			document.getElementById('password2').focus();
		} // if
	});
	// => 무결성 점검
	document.getElementById('password').addEventListener('focusout',
	() => {pCheck=pwCheck(); });
	
	// => password2
	document.getElementById('password2').addEventListener('keydown',
	(e)=> {
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('submitTag').focus();
		} // if
	});
	// => 무결성 점검
	document.getElementById('password2').addEventListener('focusout',
	()=>{p2Check=pw2Check();});
} // onload

function inCheck() {
	if(!pCheck) {document.getElementById('pMessage').innerHTML='필수입력, Password를 확인해주세요~!~!';}
	if(!p2Check) {docuemnt.getElementById('p2Message').innerHTML='필수입력, Password를 확인해주세요~!~!';}
	if(pCheck && p2Check) {
		// submit 확인진행
		if(confirm("수정 (YES:확인/NO:취소)")) {
			// submit 진행
			return true;
		} else {
			alert("수정 취소");
			return false;
		} // confirm
	} else {
		return false;
	} // check
} // inCheck
</script>
</head>
<body>
<h2>Password 수정</h2>
<div class="main">
<br><b>=> 새로운 Password를 입력해주세요</b><br>
<form action="pwUpdate" method="post">
	<table class="table-fill">
		<tr>
			<td><label>Password</label></td>
			<td><input type="password" id="password" name="password">
			<br><span id="pMessage" class="eMessage"></span></td>
		</tr>
		<tr>
			<td><label>재확인</label></td>
			<td><input type="password" id="password2" placeholder="반드시 입력 필수"/>
			<span id="p2Message" class="eMessage"></span></td>
		</tr>
		<tr>
			<td><input type="submit" value="수정" id="submitTag" onclick="return inCheck()"/>
			<input type="reset" value="취소" />
			</td>
		</tr>
	</table>
</form>
</div>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if>

<br>&nbsp;
<a href="/home" data-hover="Home">Home</a>&nbsp;&nbsp;
<a href="javascript:history.go(-1)" data-hover="이전으로">이전으로</a>&nbsp;&nbsp;
<a href="joinFrom" data-hover="회원가입">회원가입</a>&nbsp;
</body>
</html>
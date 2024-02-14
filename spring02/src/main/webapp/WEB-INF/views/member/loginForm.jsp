<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** LoginForm **</title>
	<link rel="stylesheet" type="text/css"
		href="/spring02/resources/myLib/myStyle.css">
	<script src="/spring02/resources/myLib/inCheck.js"></script>
<script>
'use strict'
	
let iCheck=false;
let pCheck=false;

onload=function() {
	document.getElementById('id').focus();
	document.getElementById('id').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('password').focus();
		} // if
	});
    document.getElementById('id').addEventListener('focusout',()=>{ iCheck=idCheck(); });
    
    
    
    //
    document.getElementById('password').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
  		    e.preventDefault();
  		    document.getElementById('submitTag').focus();
  			// => password 에서 입력후 Enter_Key 누르면 바로 submit 진행 되도록~~
            //     type="submit" 을 사용하는경우 정확하게 적용하기 어려워 적용하지 않음    
            //if (!iCheck) iCheck=idCheck();
            //else if (!pCheck) pCheck=pwCheck();
            //else document.getElementById('myForm').submit();
		} // if
	});
    document.getElementById('password').addEventListener('focusout',()=>{ pCheck=pwCheck(); });
} //onload
function inCheck() {
	
	// 각각 오류 메시지 입력
	if (!iCheck)  { document.getElementById('iMessage').innerHTML=' 필수입력, ID를 확인하세요. '; }
	if (!pCheck)  { document.getElementById('pMessage').innerHTML=' 필수입력, PW를 확인하세요. '; }

	if (iCheck && pCheck) {
		return true;
	} else {
		return false;
	}
} // incheck
</script>
</head>
<body>
<div id="div3">
<h2 class="center"> Let's Login then Play!</h2>
<form action="login" method="post" id="myForm">
<table class="center">
	<tr height="40">
		<td><lable for="id">ID</lable></td>
		<td><input type="text" name="id" id="id" size="10" autofocus placeholder="아이디"/>
		<br><span id="iMessage" class="eMessage"></span>
		</td>
	</tr>
	<tr height="40">
		<td><lable for="password">Password</lable></td>
		<td><input type="password" name="password" id="password" size="10" autofocus placeholder="비밀번호"/>
		<br><span id="pMessage" class="eMessage"></span>
		</td>
	</tr>
</table>
<br>
<div class="center">
<button type="submit" id="submitTag" onclick="return inCheck()" >로그인</button>&nbsp;&nbsp;
<button type="reset">취소</button>
</div>
</form>
<br><br>
<div class="center" style="font-size: 20px;">
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if>
</div>
<br>
<div class="center">
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
&nbsp;<a href="joinForm">회원가입</a>&nbsp;
&nbsp;<a href="searchPW">패스워드찾기</a>&nbsp;
</div>
</div>
</body>
</html>
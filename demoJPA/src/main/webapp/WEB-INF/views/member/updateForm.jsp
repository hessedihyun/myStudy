<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Revise my Information details **</title>
<link rel="stylesheet" type="text/css"
	  href="/resources/myLib/myStyle.css">
<!-- disabled는 서버로 값이 나가지 않음. readonly는 서버로 값이 같이 나감. -->
</head>
<body>
<h2>** Revise my Information details **</h2>
<form action="update" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>ID</th>
			<td><input type="text" name="id" id="id" value="${requestScope.myInfo.id}" readonly /></td>
		</tr>
		<%-- BCryptPasswordEncoder 적용 후 분리(비밀번호만 따로 수정할 수 있게!!!) 
		<tr>
			<th>Password</th>
			<td><input type="password" name="password" id="password" value="${requestScope.myInfo.password}" readonly/></td>
			<!-- <button type="button" onclick="" >비밀번호수정</button> -->
		</tr> --%>
		<tr>
			<th>이름</th>
			<td><input type="text" id="name" value="${requestScope.myInfo.name}" name="name" autofocus="autofocus"/></td>
		</tr>
		<tr>
			<th>나이</th>
			<td><input type="text" id="age" value="${requestScope.myInfo.age}" name="age"/></td>
		</tr>
		<tr>
			<th>조번호</th>
			<td><input type="text" value="${requestScope.myInfo.jno}" name="jno"/></td>
		</tr>
		<tr>
			<th>소개</th>
			<td><input type="text" id="info" value="${requestScope.myInfo.info}" name="info"/></td>
		</tr>
		<tr>
			<th>포인트</th>
			<td><input type="text" id="point" value="${requestScope.myInfo.point}" name="point"/></td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td><input type="text" id="birthday" value="${requestScope.myInfo.birthday}" name="birthday"/></td>
		</tr>
		<tr>
			<th>추천인</th>
			<td><input type="text" id="rid" value="${requestScope.myInfo.rid}" name="rid"/></td>
		</tr>
		<!-- Image Update 추가 
         => form Tag : method, enctype 확인
         => new Image 를 선택하는 경우 -> uploadfilef 사용
         => new Image 를 선택하지않는 경우 
            -> 본래 Image 를 사용 -> uploadfile 값이 필요함 (hidden으로 보관)
   		-->   
		<tr>
			<th>이미지</th>
			<td>
				<img alt="MYIMAGE" src="/resources/uploadImages/${requestScope.myInfo.uploadfile}" class="select_img" width="70" height="60"/>
				<input type="hidden" id="uploadfile" value="${requestScope.myInfo.uploadfile}" name="uploadfile"/>
				<br>
				<input type="file" id="uploadfilef" name="uploadfilef"/>
			</td>
		
			<script>
		        document.getElementById('uploadfilef').onchange=function(e){
			         if(this.files && this.files[0]) {
			            let reader = new FileReader;
			            reader.readAsDataURL(this.files[0]);
			             reader.onload = function(e) {
			                document.getElementsByClassName('select_img')[0].src=e.target.result;
			               } // onload_function
			          } // if   
		        }; //change  
	        </script>
		</tr>
		<tr>
			<td><input type="submit" value="수정하기" name="submit"/></td>
			<td><input type="reset" value="재입력하기"/></td>
		</tr>
	</table>
</form>
<br><hr>
&nbsp; <a href="pwUpdate" data-hover="Password 수정">Password 수정</a>&nbsp;
<c:if test="${not empty requestScope.message}">
	=> ${requestScope.message}
</c:if>
<hr>
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
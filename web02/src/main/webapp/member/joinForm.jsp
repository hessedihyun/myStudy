<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** joinForm **</title>
</head>
<body>
<h2>** 회원가입 **</h2>
<br>
<form action="/web02/mjoin" method="post">
	<table>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="id">ID</label></th>
			<td><input type="text" placeholder="영문,숫자 4~10글자" name="id" id="id" required="required"/></td>
		</tr>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="password">Password</label>
			<td><input type="password" placeholder="특수문자 필수" name="password" id="password" required="required"/></td>
		</tr>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="name">이름</label>
			<td><input type="text" placeholder="이름을 입력하세요" name="name" id="name" required="required"/></td>
		</tr>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="age">나이</label>
			<td><input type="text" placeholder="나이를 입력하세요" name="age" id="age" required="required"/></td>
		</tr>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="jno">조번호</label>
			<td>
				<select name="jno" id="jno">
					<option value="1" ${requestScope.myInfo.jno==1? "selected":""}>1조</option>
					<option value="2" ${requestScope.myInfo.jno==2? "selected":""}>2조</option>
					<option value="3" ${requestScope.myInfo.jno==3? "selected":""}>3조</option>
					<option value="4" ${requestScope.myInfo.jno==4? "selected":""}>4조</option>
					<option value="7" ${requestScope.myInfo.jno==7? "selected":""}>7조</option>
				</select>
			</td>
		</tr>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="info">소개</label>
			<td><input type="text" placeholder="소개를 입력하세요" name="info" id="info"/></td>
		</tr>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="point">포인트</label>
			<td><input type="text" vlaue="100.0"placeholder="실수 형태의 포인트" name="point" id="point"/></td>
		</tr>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="birthday">생년월일</label>
			<td><input type="date" placeholder="생년월일을 입력하세요" name="birthday" id="birthday"/></td>
		</tr>
		<tr height="40">
			<th bgcolor="LightSalmon"><label for="rid">추천인</label>
			<td><input type="text" placeholder="추천인을 입력하세요" name="rid" id="rid"/></td>
		</tr>
	</table>
	<br>
	<table>
		<tr>
			<td><input type="submit" value="가입하기" name="submit"/></td>
			<td><input type="reset" value="재입력하기"/></td>
		</tr>
	</table>
</form>
<br><hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if><br><br>
&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;	
</body>
</html>
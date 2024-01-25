<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
** Error Message Page **
=> exception 객체 
	- 전달받은 예외객체를 표현하는 객체
	- page 디렉티브의 isErrorPage="true" 일 때만 사용 가능

=> 서비스 처리과정에서 <%=exception.getClass().getSimpleName()%>
   오류가 발생했습니다.
   잠시 후 다시 해주세요 ~~ 
   
=> Exception Type : <%=exception.getClass().getName()%>
=> Exception toString : <%=exception.toString()%>
=> Exception getMessage : <%=exception.getMessage()%>
</pre>
</body>
</html>
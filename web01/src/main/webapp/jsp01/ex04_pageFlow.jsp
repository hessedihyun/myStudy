<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jsp Page Flow **</title>
</head>
<body>
<h2>** Jsp Page Flow **</h2>
<h3>1. Forward</h3>
=> JSP 표준 Action Tag 를 이용한 이동
<script type="text/javascript">
	alert("~~ Hello 로 이동 합니다 ~~");
	// => Forward Test 시에는
	// => 스크립스는 브라우져에서 실행되기 때문에 실행 되지않음
	// => 서버에서 forward 된 화면이 response 로 출력 되기때문 
</script>
<%-- <jsp:forward page="ex01_HelloJsp.jsp" /> --%>

<h3>2. Include</h3>
<hr>
-> 2.1) JSP Action Tag <br>
Jsp 문서의 완성된 웹페이지가 포함됨, 변수공유 불가능 (코드호환이 안됨)<br>
소스파일이 들어오는 게 아니라 완성된 웹문서가 들어오기 때문이다.<br>

<jsp:include page="ex01_HelloJsp.jsp" />
<%-- => 변수 공유 확인 : name = <%=name%> --%>

-> 2.2) Directive include Test <br>
Jsp 문서의 소스코드가 포함됨, 변수공유 가능 (코드호환이 됨)<br>
<%@ include file="ex01_HelloJsp.jsp" %>
<hr>
=> 변수 공유 확인 : name = <%=name%>
<!-- 액션테그랑  2.2)의 include 차이는 속성 이름이다. page와 file. 디렉티브(2.2)는 소스코드관계에서 포함이 가능하다. 디렉티브를 많이 씀 -->
<hr>
</body>
</html>
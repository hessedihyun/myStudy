<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- 페이지 디렉티브 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Hello Jsp **</title>
</head>
<body style="background-color:#FFFACD">
<h2>** Hello Jsp **</h2>
<h3>~~ 안녕하세요! 이지현입니다 ~~</h3>
<h3>* Jsp 장점 : View가 간편하다.</h3>
<h3>* Jsp 단점 : Java Code가 불편하다.</h3>
<hr>
<h3>=> Java Code</h3>
<pre>
1) Scriptlet : 자바코드
2) Expression : 표현식 (출력문)
3) Declaration : 선언부 (메서드 등)
</pre>
** Declaration : 선언부 <br><br>
<%! // Declaration : 선언부
	public int multiply(int a, int b) {
	return a*b;
	}
	String name="홍길동";
	int i=3;
	int j=5;
%>

<!-- Expression : 표현식 -->
** Expression : 표현식(출력문) <br> 
=> multiply(4,5)의 결과는 <%=multiply(4,5)%><br> <!-- // 세미콜론 절대로 안됨 -->
=> 변수출력 : name = <%=name%>, i = <%=i%>, j = <%=j%><br>
=> 연산적용 : i+j= <%=i+j%><br><br>

** Scriptlet : 자바 코드 <br>
<% // Declaration : 선언부
   // multiply(4,5)의 결과를 짝/홀 구분해서 출력하기
   if(multiply(i,j)%2==0) { %>
   => multiply(i,j)의 결과는 짝수 <%=multiply(i,j)%>입니다<br>
<%  } else { %>
   => multiply(i,j)의 결과는 홀수 <%=multiply(i,j)%>입니다<br>
<%  }
%>





</body>
</html>
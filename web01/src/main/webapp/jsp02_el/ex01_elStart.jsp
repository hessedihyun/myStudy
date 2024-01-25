<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** EL 기본사항 **</title>
</head>
<body>
<h2>** EL 기본사항 **</h2>
<% String name="홍길동"; %>
<pre>
=> EL : Expression Language, 표현언어
=> 편리한 값(Value) 의 출력과 사용

<b>
1. 값(변수의 값) 의 출력 비교</b>
=> Java 표현식: <%=name%>
=> Java out 객체: <% out.print("Java out 객체 => "+name);%>
=> EL 출력: ${"~~ Hello EL, 표현언어 ~~"}
	-> Java 변수 출력: \${"EL로 Java 변수 출력 => "+name} : 500 발생 (백슬래시 있어야 함)
	-> EL 내부에서는 Java 변수 사용 불가능
	-> JSTL과 병행해서 사용됨
<br>

<b>
2. EL TEST</b>
** EL 자료형 **
정수형: ${123}
실수형: ${10.123}
문자형: ${"안녕하세요! EL 자료 문자형!!"}
논리형: ${true}
null: ${null}

** EL 연산 **
=> 산술(사칙)연산
\${5+2} => ${5+2}
\${5-2} => ${5-2}
\${5*2} => ${5*2}
\${5/2} => ${5/2}
\${5%2} => ${5%2}
-> 나누기 결과는 실수형

=> 비교(관계)연산 : true or false
<!-- >,<,>=,<=,==,!= -->
-> gt: greater than / lt: less than
-> ge: greater equal / le: less equal
-> eq: equal, == / ne: not equal, !=

\${5>2} => ${5>2}
\${5gt2} => ${5 gt 2}
\${5<2} => ${5<2}
\${5lt2} => ${5 lt 2}

\${5>=2} => ${5>=2}
\${5ge2} => ${5 ge 2}
\${5<=2} => ${5<=2}
\${5le2} => ${5 le 2}

\${5==2} => ${5==2}
\${5eq2} => ${5 eq 2}
\${5!=2} => ${5!=2}
<%-- \${5ne2} => ${5 ne 2} --%>
<!-- 에디터상에서는 오류이지만 실행은 잘됨. 일단 에디터 오류를 막기 위해 주석처리해놓겠음-->

=> 논리(집합)연산: &&, ||
\${5>2 && 10>20} => ${5>2 && 10>20}
\${5>2 || 10>20} => ${5>2 || 10>20}

=> 조건(삼항)식
\${5>2 ? '오(5)':"이(2)"} => ${5>2 ? '오(5)':'이(2)'}
<!-- 자바 더블쿼터만 가능했는데, jsp EL테그 내부에서는 더블, 싱글쿼터 모두 가능하다. EL 테그도 결국 서블릿에서 처리해주는 것이다. -->

<b>
3. 기타 </b>
** Java 변수
=> Java 표현식: <%=name%>
=> \${name}: ${name} <!-- EL 테그에서는 직접적으로 Java 변수를 출력하지 않는다. -->
	-> 자바 변수는 출력하지 않음. JSTL로 정의한 변수는 출력
	-> name의 값이 없음을 확인
	-> 에러는 발생하지 않는다. JSTL로 정의한 변수가 있는지 현 파일에서 확인 -> Attribute에 있는지 확인 -> 출력
=> \${empty_name}: ${empty name} 
   
   <!-- 
    => empty : 검사할 객체가 비어있는지 확인 
            비어있으면 true 
            list, map 타입의 객체가 값이 있는지 없는지 구분해줌  
    => EL 에 자바변수는 직접 값을 전달하지 못함
     (jsp에서 자바코드가 완전 분리됨을 목표로 하기때문에 자바변수를 사용할 필요는 없으므로)  
    => EL 에 변수명이 오면 JSTL로 정의한 변수 또는 속성(Attribute) 의 이름으로 인식함.              
    -->

** request 객체의 Parameter 처리
=> request 객체의 Parameter를 전달하는 el의 내부객체 제공 : param (파람)
=> 퀴리스트링으로 id 지정 전.후 Test : ~/web01/jsp02_el/ex01_elStart.jsp?id=banana
=> Java 표현식 : <%=request.getParameter("id")%>
\${empty_param.id}: ${empty param.id}
\${param.id}: ${param.id}
\${param["id"]}: ${param["id"]}

** getAttribute 처리
</pre>
</body>
</html>
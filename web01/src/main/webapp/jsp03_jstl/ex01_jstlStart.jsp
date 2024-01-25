<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL Start **</title>
</head>
<body>
<h2>** JSTL Start **</h2>
<pre><b>
=> Jstl Library 를 정의 (현재문서_Page 가 인식할 수 있도록)
   디렉티브 taglib 에 uri=".." prefix=".."
<br>
1. 출력: out Tag
=> Java의 out 객체, 표현식, EL 역할
<c:out value="~~Hello JSTL!!! 안녕 ~~" />

2. 변수 정의
=> set
<c:set value="홍길동" var="name"/>
<c:set value="22" var="age"/>

3. 변수 출력 (out Tag, EL)
=> JSTL 의 out_Tag 
* name = <c:out value="${name}"/>
* age = <c:out value="${age}"/>

=> EL
* name = ${name}, \${name}의 형태
* age = ${age}, \${age}의 형태
* age*100 = ${age*100}, \${age*100}의 형태

=> Java 는 JSTL변수와 호환되는가? : 안된다. 컴파일 오류
<%-- * name = <%=name%> --%>

4. 연산 적용
<c:set value="${age+age}" var="add"/>
\${add} = ${add}
<c:set value="${name==age}" var="bool"/>
\${bool} = ${bool}
<c:set value="${age>add? age:add}" var="max"/>
\${max} = ${max}

5. 변수 삭제
=> remove
<c:remove var="add"/>
\${empty_add} = ${empty add}
\${empty_age} = ${empty age}

=> 정의하지 않은 변수 삭제 : 오류 발생하지 않음
<c:remove var="password"/>

6. 우선순위
=> JSTL 변수 VS pageScope 의 Attribute
=> 나중에 적용한 값이 우선 적용된다. (set 변수, attribute 중 나중에 정의된 값 우선)

<% // pageScope에 Attribut를 정의 후 Test
	pageContext.setAttribute("name", "그린컴");
%>
* Test1) name 정의 순서: set -> pageScope
\${name} = ${name}
* Test2) set 의 name을 재정의: set -> set(재정의)
<c:set value="이지현" var="name"/>
\${name} = ${name} : 나중값 우선
</b></pre>
</body>
</html>
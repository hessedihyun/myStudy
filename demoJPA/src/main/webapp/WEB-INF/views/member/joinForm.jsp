<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** joinForm **</title>
<link rel="stylesheet" type="text/css"
	  href="/resources/myLib/myStyle.css">
<script src="/resources/myLib/inCheck.js"></script>
<script>
"use strict"
// ** ID 중복확인
// => UI 개선사항
// => 중복확인 버튼 추가
//    처음 : 중복확인버튼_enable / submit_disable
// => 중복확인 완료후 submit 이 가능하도록
//    중복확인버튼_disable / submit_enable
// => 중복확인 기능 : function idDupCheck()
//    id입력값의 무결성점검 -> id 확인요청 -> 서버로 전송 -> id , selectOne 결과 -> response: 사용가능/불가능 
// => 서버측 : 컨트롤러에 idDupCheck 요청을 처리하는 매핑메서드, view_Page(팝업창) 작성   
function idDupCheck() {
	// 1) id 입력값의 무결성 점검
	if( !iCheck ) iCheck = idCheck();
	else {
	// 2) 서버로 id 확인 요청 -> 결과는 view_Page(팝업창) 으로
		let url="idDupCheck?id=" + document.getElementById('id').value;
		window.open(url,'_blank','width=400,height=300,resizable=yes,scrollbars=yes,toolbar=no,menubar=yes');
	}
}

// ** 화살표 함수
// => 익명함수를 간단하게 표기
//    function(){....}  
//    () => {....}  

// ** 입력값의 무결성 점검
// => ID 중복확인, 무결성 점검

// 1) 모든항목  focusout 이벤트핸들러
//    => 개별항목 점검확인하는 boolean Type 변수 (스위치변수) 
//    => 개별항목 점검 function() 작성
// 2) submit 진행전에 점검확인
//    => 모든항목의 점검이 완료된 경우에만  submit 진행
//    => function inCheck() 로 확인
//    => submit 버튼의 onclick 리스너에 등록
//       ( submit 의 default 이벤트 고려 )
// ----------------------------------------------------
// ** 실습 **
// ** 입력값의 무결성 확인
// => ID 중복확인, 입력값

// ** 입력값의 무결성 확인
// 1) 전역변수 정의
// => 무결성 확인 결과를 위한 switch(true/false)'flag' 변수
	let iCheck=false;
	let pCheck=false;
	let p2Check=false;
	let nCheck=false;
	let aCheck=false; // age 정수확인
	let oCheck=false; // point 실수확인
	let bCheck=false;

// 2) 개별적인 확인 코드
// => 이벤트 : focusout, keydown_EnterKey
// => 오류가 없으면 : switch변수값을 true, e메시지 삭제
// => 오류가 있으면 : switch변수값을 false, e메시지 출력
// => 순서: Tag인식 => Tag의 value값 가져오기 -> 무결성확인


onload=function() {
	// => window.onload ; window는 생략가능
	// => body의 Tag들을 인식가능한 상태일때 실행 되도록 하기 위함.
	// => ID
	// -> keydown_EnterKey 에 포커스이동 적용
    // -> 제어문자의 ascii 코드 값(참고)
    //    esc=27, EnterKey=13, Space_Bar=32
	document.getElementById('id').focus();
	document.getElementById('id').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			// => form에서는
			// => enter 누르면 자동 submit 발생이 되기 때문에 기존 액션(이벤트)를 제거함.
			document.getElementById('password').focus();
		} // if
	});
	// -> 무결성 점검
    document.getElementById('id').addEventListener('focusout',()=>{ iCheck=idCheck(); });
	// => Password
    document.getElementById('password').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('password2').focus();
		} // if
	});
	// -> 무결성 점검
    document.getElementById('password').addEventListener('focusout',()=>{ pCheck=pwCheck(); });
	// => Password2
    document.getElementById('password2').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('name').focus();
		} // if
	});
	// -> 무결성 점검
    document.getElementById('password2').addEventListener('focusout',()=>{ p2Check=pw2Check(); });
	// => name
    document.getElementById('name').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('age').focus();
		} // if
	});
	// -> 무결성 점검
    document.getElementById('name').addEventListener('focusout',()=>{ nCheck=nmCheck(); });
	// => age
    document.getElementById('age').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('jno').focus();
		} // if
	});
	// -> 무결성 점검
    document.getElementById('age').addEventListener('focusout',()=>{ aCheck=ageCheck(); });
	// => Jno : Focus 이동만 
    document.getElementById('jno').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('info').focus();
		} // if
	});
	// => info : Focus 이동만
    document.getElementById('info').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13) {
    				e.preventDefault();
    				document.getElementById('point').focus();
    			} // if
    		});
    // => point
    document.getElementById('point').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('birthday').focus();
		} // if
	});
	// -> 무결성 점검
    document.getElementById('point').addEventListener('focusout',()=>{ oCheck=poCheck(); });
   
	// => birthday
    document.getElementById('birthday').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('rid').focus();
		} // if
	});
	// -> 무결성 점검
    document.getElementById('birthday').addEventListener('focusout',()=>{ bCheck=bdCheck(); });
	
	// => rid : Focus 이동만
    document.getElementById('rid').addEventListener('keydown',
	(e)=>{
		if(e.which==13) {
			e.preventDefault();
			document.getElementById('submitTag').focus();
		} // if
	});
} // onload

// 3) submit 실행 여부 판단 & 실행
// => 모든항목의 무결성을 확인
// => 오류가 없으면 : return true
// => 오류가 1항목이라도 있으면 : return false

function inCheck() {
	
	// 각각 오류 메시지 입력
	if (!iCheck)  { document.getElementById('iMessage').innerHTML=' 필수입력, ID를 확인하세요. '; }
	if (!pCheck)  { document.getElementById('pMessage').innerHTML=' 필수입력, PW를 확인하세요. '; }
	if (!p2Check) { document.getElementById('p2Message').innerHTML=' 필수입력, PW를 확인하세요. '; }
	if (!nCheck)  { document.getElementById('nMessage').innerHTML=' 필수입력, NAME을 확인하세요. '; }
	if (!aCheck)  { document.getElementById('aMessage').innerHTML=' 필수입력, AGE를 확인하세요. '; }
	if (!oCheck)  { document.getElementById('oMessage').innerHTML=' 필수입력, POINT를 확인하세요. '; }
	if (!bCheck)  { document.getElementById('bMessage').innerHTML=' 필수입력, BIRTHDAY를 확인하세요. '; }

	if (iCheck && pCheck && p2Check && nCheck 
			&& aCheck && oCheck && bCheck) {
		// submit 확인
		if(confirm("정말 가입을 하시겠습니까? (Yes:확인/NO:취소)")) {
			// => submit 진행
			return true;
		
		} else {
			alert("회원가입이 취소되었습니다.");
			return false;
		} // confirm if-else
	} else {
		return false
	} // check조건 if-else
} // incheck
</script>
</head>
<body>
<h2>** 회원가입 **</h2>
<br>
<form action="join" id="myform" method="post" enctype="multipart/form-data"> <!-- enctype="multipart/form-data" -->
	<table> 
		<tr height="40">
			<th><label for="id">ID</label></th>
			<td><input type="text" placeholder="영문,숫자 4~10글자" name="id" id="id" required="required" size="20"/>
				<button type="button" onclick="idDupCheck()" id="idDup">ID중복확인</button>
				<br><span id="iMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<th><label for="password">Password</label>
			<td><input type="password" placeholder="특수문자 필수" name="password" id="password" required="required"/>
				<br><span id="pMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<th><label for="password2">PW 확인</label>
			<td><input type="password" id="password2" placeholder="PW 재입력 확인" required="required"/>
			<br><span id="p2Message" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<th><label for="name">이름</label>
			<td><input type="text" placeholder="이름을 입력하세요" name="name" id="name" required="required"/>
				<br><span id="nMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<th><label for="age">나이</label>
			<td><input type="text" placeholder="나이를 입력하세요" name="age" id="age" required="required"/>
				<br><span id="aMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<th><label for="jno">조이름</label>
			<td>
				<select name="jno" id="jno">
					<c:forEach items="${requestScope.myInfo}" var="info" >
						<option value="${info.jno}" ${info.jno==1? "selected":""}>${info.jname}</option>
					</c:forEach>
					<!-- <option value="0">미정</option> -->
					<%-- <option value="1" ${requestScope.myInfo.jno==1? "selected":""}>1조</option>
					<option value="2" ${requestScope.myInfo.jno==2? "selected":""}>2조</option>
					<option value="3" ${requestScope.myInfo.jno==3? "selected":""}>3조</option>
					<option value="4" ${requestScope.myInfo.jno==4? "selected":""}>4조</option>
					<option value="7" ${requestScope.myInfo.jno==7? "selected":""}>7조</option> --%>
				</select>
			</td>
		</tr>
		<tr height="40">
			<th><label for="info">소개</label>
			<td><input type="text" placeholder="소개를 입력하세요" name="info" id="info"/></td>
		</tr>
		<tr height="40">
			<th><label for="point">포인트</label>
			<td><input type="text" vlaue="100.0"placeholder="실수 형태의 포인트" name="point" id="point"/>
				<br><span id="oMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<th><label for="birthday">생년월일</label>
			<td><input type="date" placeholder="생년월일을 입력하세요" name="birthday" id="birthday"/>
				<br><span id="bMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<th><label for="rid">추천인</label>
			<td><input type="text" placeholder="추천인을 입력하세요" name="rid" id="rid"/></td>
		</tr>
		<tr height="40">
			<th><label for="uploadfilef">이미지첨부</label>
			<td>
				<img alt="MYIMAGE" src="" class="select_img" width=80 height=100/><br>
				<input type="file" name="uploadfilef" id="uploadfilef"/></td>
				<script>
			        document.getElementById('uploadfilef').onchange=function(e){
				         if(this.files && this.files[0]) {
				            let reader = new FileReader;
				            reader.readAsDataURL(this.files[0]);
				             reader.onload = function(e) {
				                // => jQuery를 사용하지 않는경우 
				                document.getElementsByClassName('select_img')[0].src=e.target.result;
				                
				               //$(".select_img").attr("src", e.target.result)
				               //            .width(70).height(90); 
				               } // onload_function
				          } // if   
			        }; //change  
		        </script>
		</tr>
	</table>
	<br>
	<div>
		<button type="submit" onclick="return inCheck()" id="submitTag" disabled="disabled">가입</button>&nbsp;&nbsp;
		<button type="reset" >취소</button>&nbsp;&nbsp;
		<!-- Axios Join Test -->
		<span class="textlink" onclick="axiJoin()">axiJoin</span>&nbsp;&nbsp;
	</div>
	<!-- => Tag의 onclick 이벤트를 작성하고, onclick 이벤트핸들러가 가지고있던
            기본동작인 submit 을 선택적으로 진행되도록 해준다. 
             - submit 진행 : default (또는 return true)
             - submit 정지 : submit 이벤트를 무효화 해야함 (return false 또는 이벤트.preventDefault())
	-->
	<!-- ** Button Test 
		 => default : form 내부에서는  submit 와  동일하게 작동됨 
            inCheck() 의 return 값에 따라 (true 면) submit 진행됨 
         => 단, type 속성을 선택하면 (button, reset, submit 등) 속성에 맞게 실행됨
            예) button 을 선택하면 submit 은 실행되지않음
         ** Enter_Key : form 내부에서는 누르면, submit이 진행됨.
             
	-->
</form>
<!-- ** FileUpLoad Form  **
		=> form과 table Tag 사용 시 주의사항 : form 내부에 table 사용해야 함
		-> form 단위 작업 시 인식 안됨
		-> JQ의 serialize, FormData의 append all 등 
		=> method="POST" : 255 byte 이상 대용량 전송 가능 하므로
		=> <form enctype="속성값">
		   <form> 태그의 데이터(input의 value)가 서버로 제출될 때 해당 데이터가 인코딩되는 방법을 명시함.
		=> enctype="multipart/form-data" : 화일 upload를 가능하게 해줌
	 ** multipart/form-data는 파일 업로드가 있는 입력 양식 요소에 사용되는 enctype 속성의 값 중 하나이고,
	    multipart는 폼데이터가 여러 부분으로 나뉘어 서버로 전송되는 것을 의미.
	    이 폼이 제출될 때, 이 형식을 서버에 알려주며, multipart/form-data로 지정이 되어 있어야 서버에서 정상적으로 데이터를 처리할 수 있다.
-->
<br>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}
</c:if>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;	
</body>
</html>
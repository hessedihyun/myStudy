// ** Ajax_REST API, Axios Test **
// => Axios 메서드형식 적용
// => 1. List 출력
//   - axiMList : MemberController, Page response
//   - idbList(id별 boardList) : RESTController, List_Data response 
// => 2. 반복문에 이벤트 적용하기
//   - Delete, JoDetail
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// => Axios 메서드형식 적용 (00_AJAX(공유).pptx 16p)
/*   - GET   : axios.get( url[, config] )
   - POST  : axios.post( url, data[, config] )
   - PUT   : axios.put( url, data[, config] )
   - PATCH : axios.patch( url[, data[, config]] )
   - DELETE: axios.delete( url[, config] )     */

//** 검색 & 페이징 포함한 요청의 Ajax 처리
// => Ajax 요청 function 작성, url 을 매개변수로 전달 : axiMListCri(url) 
// => Page 요청 : aTag -> span 으로 변경하고 function 으로 처리 
// => Check 검색은 submit 을 사용하기 때문에 적용하지 않음(주석처리)

// => Ajax 처리시에는 문서내부의 function이 인식 안되므로
//    searchDB(), keywordClear() 모두 axTest03.js 에 작성

// ** Ajax Member_PageList *********************
// => axiMList 에 Paging + 검색기능 추가
// => 검색조건 & Paging , Ajax 구현
//    -> 입력된 값들을 서버로 전송요청: axios
//   -> url 완성후 axios 호출

// => 1) 검색조건 입력 후 버튼클릭
//   -> jsp  문서내무의 script 구문을 외부문서로 작성 : EL Tag 적용안됨
//    ${pageMaker.makeQuery(1)} -> ?currPage=1&rowsPerPage=5 

'use strict'
// 1. List 출력 
// 1.1) Page response
// => response를 resultArea1에 출력하기
// => 속성명: /member/aximlist
// => response : axmemberList.jsp
function axiMList() {
	
	let url="/member/aximlist";
	axios.get(url
	).then(response => {
		console.log("** response: axMemberList 성공");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		alert(`** response: axMemberList 실패 => ${err.message}`);
	});
} // axiMList

/*function axiMpageList() {
	
	let url = "/member/axmpagelist";
	axios.get(url
	).then(response => {
		console.log("** response: axmPageList 성공");
		document.getElementById('resultArea3').innerHTML = response.data;
	}).catch(err => {
		alert(`** response : axmPageList 실패 => ${err.message}`)
	});
}*/
// => 1) searchDB
function searchDB() {
	let url='axmcri'
				+'?currPage=1&rowsPerPage=5'
				+'&searchType='+document.getElementById('searchType').value
				+'&keyword='+document.getElementById('keyword').value;
		axiMListCri(url); // axios 호출
} // searchDB()

// => 2) searchType을 '전체'로 변경하면 keyword는 clear
function keywordClear() {
	if(document.getElementById('searchType').value=='all')
	   document.getElementById('keyword').value='';
} // keywordClear

// => 3) axios Code
function axiMListCri(url) {
	url="/member/"+url;
	alert(`axiMListCri url=${url}`);
	axios.get(url
	).then(response => {
		console.log("** response 성공 **");
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err => {
		document.getElementById(`resultArea1`).innerHTML="** axiMListCri 실패 **"
	});
	
	document.getElementById('resultArea2').innerHTML="";
} // axiMList
function checkClear() {
	// document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용 안 됨 (배열의 형태)
	let ck = document.querySelectorAll('.clear');
	for(let i=0; i<ck.length; i++) {
		ck[i].checked=false;
	}
	return false; // reset 의 기본 이벤트를 제거해줘야 한다.
} // checkClear
function checkClearage() {
	// document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용 안 됨 (배열의 형태)
	let ck = document.querySelectorAll('.aclear');
	for(let i=0; i<ck.length; i++) {
		ck[i].checked=false;
	}
	return false; // reset 의 기본 이벤트를 제거해줘야 한다.
} // checkClearage
function checkAll() {
	// document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용 안 됨 (배열의 형태)
	let ck = document.querySelectorAll('.clear');
	for(let i=0; i<ck.length; i++) {
		ck[i].checked=true;
	}
	return false; // reset 의 기본 이벤트를 제거해줘야 한다.
} // checkAllage
function checkAllage() {
	// document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용 안 됨 (배열의 형태)
	let ck = document.querySelectorAll('.aclear');
	for(let i=0; i<ck.length; i++) {
		ck[i].checked=true;
	}
	return false; // reset 의 기본 이벤트를 제거해줘야 한다.
} // checkAllage


function axiMListCheck() {
	// => 첫 요청
	// url=/member/axmcheck?currPage=1&rowsPerpage=5&check=1&check=2&check=3
	
	let checkAll = document.querySelectorAll(".check");
	let checkData = "";
	
	
	/*for (let i=0; i<checkAll.length; i++) {
		if(checkAll[i].checked)
		checkData += "&check=" + checkAll[i].value;
	}*/
	// ** forEach() 적용하기
	checkAll.forEach(check => {
		if(check.checked)
		checkData += "&check=" + check.value;
	})
	
	
	let url = 'axmcheck'
			+ '?currPage=1&rowsPerPage=5'
			+ checkData;
	console.log("됩니다");
	axiMListCri(url); // axios 호출
} // axiMListCheck

// 1.2) idbList()(id별 boardList)
// => RESTController, PathVariable 처리, List_Data response
// => Server : service, Sql구문
// => request : id를 path로 전송하기 (ex. "/rest/idblist/merci")
// => Response
// 	  -> 성공 : 반복문, Table로 List 출력문 완성해서 resultArea2에 출력하기
//    -> 출력 자료의 유무 : Server에서 status로 (없으면 502로) 처리
// 	  -> 실패 : resultArea2 clear 해주고, alert로 실패 메시지 출력하기

function idbList(id){
	let url="/rest/idblist/" + id;
	axios.get(url
	).then(response => {
		alert("** 성공 => resultArea2에 List 작성 **")
		console.log(`** result List_Data => ${response.data} **`);
		let listData = response.data;
		let resultHTML = 
			`<table border="1" style="width:100%; text-wrap: nowrap;">
				<tr bgcolor="Salmon">
					<th>Seq</th>
					<th>Title</th>
					<th>ID</th>
					<th>RegDate</th>
					<th>조회수</th>
				</tr>`;
			// => 반복문 필요
			for (let b of listData) {
				resultHTML +=
				`<tr>
					<td>${b.seq}</td>
					<td>${b.title}</td>
					<td>${b.id}</td>
					<td>${b.regdate}</td>
					<td>${b.cnt}</td>
				</tr>`;
			 } // for
			resultHTML +=`</Table>`;
			document.getElementById('resultArea2').innerHTML=resultHTML;
			
			// ** for 간편출력 : of, in
		    // => in: undifined 는 통과하고, 배열(index Return), 객체(속성명 Return)
		    // => of: undifined 까지 모두출력 (순차출력과 동일), value 를 return, 
		    //        ES6 에 for ~ in 의 단점을 보완 개선하여 추가됨.
		    //        일반 객체에는 적용안되지만, (오류발생, 개발자모드로 확인가능)
		    //         Array, String, Map, Set, function의 매개변수 객체 와
		    //        이터러블 규약을 따르는 이터러블 객체 (Iterable Object) 는 적용됨
		    // => 이터러블 규약
		    //      내부에 Symbol.iterator (줄여서 @@iterator로 표현하기도함) 메서드가 구현되어 있어야 한다는 규약 

			
	}).catch(err => {
		// => response의 status 값이 502면 출력 자료 없음
		if(err.response.status=='502') {
			document.getElementById('resultArea2').innerHTML=err.response.data;		
		} else { 
			document.getElementById('resultArea2').innerHTML="";		
			alert("** 시스템 오류!! 잠시 후 다시하세요. => " + err.message);
		}
	});
} // idbList

// 2.2) axiDelete('${m.id}')
// => 요청명: "rest/axidelete/" PathVariable 적용
// => 결과는 성공/실패 여부만 전달: RESTController로
// => 성공 : Deleted로 변경,  onclick 이벤트 해제
function axiDelete(e,id) {
	let url="/rest/axidelete/"+id;
	axios.delete(url
	).then(response => {
		alert(response.data)
		// => 삭제성공
		// 	- Delete -> Deleted, Gray_color, Bold로
		//  - onclick 이벤트 제거
		//  - Style 제거
		/*document.getElementById(id).innerHTML="Deleted";
		document.getElementById(id).style.color="Gray";
		document.getElementById(id).style.fontWeight="bold";
		document.getElementById(id).classList.remove('textlink');
		document.getElementById(id).removeAttribute('onclick');*/
		
		// => e.target 적용
		e.target.innerHTML="Deleted";
		e.target.style.color="Gray";
		e.target.style.fontWeight="bold";
		e.target.classList.remove('textlink');
		e.target.removeAttribute('onclick');
	}).catch(err => {
		if(err.response.status=='502') alert(err.response.data)
		else alert("** 시스템 오류!! 잠시 후 다시하세요. => " + err.message);
	});
} // axiDelete

// 2.3) JoDetail
// 2.3.1) MouseOver : showJoDetail
// => jno mouseover : JoDetail content Div에 출력
// => request : axios, get, RESTController에 "/jodetail" 요청
// => response: JoDTO 객체
function showJoDetail(e,jno) {
   // ** 마우스포인터 위치 확인
   // => 이벤트객체 활용
   //     - event객체 (이벤트핸들러 첫번째 매개변수) 가 제공
   //     - event객체 프로퍼티: type, target, preventDefault() 등 (JS 9장_Event.pptx 28p)   
   //     - e.pageX, e.pageY : 전체 Page 기준 (정해진 고정값)
   //     - e.clientX, e.clientY : 보여지는 화면 기준-> page Scroll 시에 불편함
   console.log(`** e.pageX=${e.pageX}, e.pageY=${e.pageY}`);
   console.log(`** e.clientX=${e.clientX}, e.clientY=${e.clientY}`);

	let url="/rest/jodetail/"+jno;
	let mleft = e.pageX+20;
	let mtop = e.pageY;
	axios.get(url
	).then(response => {
		console.log(`** response 성공! => ${response.data}`);
		
	   // ** JSON.stringify 적용 비교
       let jj =JSON.stringify(response.data);   
       // => Object -> JSON : Data를 나열해줌 
       // => JS 객체포맷을 JSON 포맷화 하면 key:value 형태로 나열해줌
       //    (즉, 하나의 긴문자열, 문자Type 이됨)
       //    console.log 로 response.data 의 내용을 확인할때 사용하면 편리함.  
       console.log(`** response 성공: JSON포맷 => ${jj}`);
	   let jo = response.data;
	   console.log(`** Data jo.jno => ${jo.jno}`);
		
	   let resultHTML=
		`<table>
		 	<tr height="10"><th>JNO</th><td>${jo.jno}</td></tr>
		 	<tr height="10"><th>JoName</th><td>${jo.jname}</td></tr>
		 	<tr height="10"><th>CaptainID</th><td>${jo.captain}</td></tr>
		 	<tr height="10"><th>Project</th><td>${jo.project}</td></tr>
		 	<tr height="10"><th>Slogan</th><td>${jo.slogan}</td></tr>
	     </table>`;
		document.getElementById('content').innerHTML=resultHTML;
		document.getElementById('content').style.display='block';
		document.getElementById('content').style.left=mleft+"px";
		document.getElementById('content').style.top=mtop+"px";
	}).catch(err => {
		if(err.response.status=='502') alert(err.response.data)
		else alert(`** 시스템 오류, 잠시후 다시 하세요 => ${err.message}`);
	});
} // showJoDetail

// 2.3.2) MouseOut : hideJoDetail
// => 화면에 표시되어 있던 content Div가 사라짐
function hideJoDetail() {
	document.getElementById('content').style.display='none';
} // hideJoDetail
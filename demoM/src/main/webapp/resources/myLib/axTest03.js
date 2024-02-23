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

function axiMpageList() {
	
	let url = "/member/axmpagelist";
	axios.get(url
	).then(response => {
		console.log("** response: axmPageList 성공");
		document.getElementById('resultArea3').innerHTML = response.data;
	}).catch(err => {
		alert(`** response : axmPageList 실패 => ${err.message}`)
	});
}

function searchDB() {
	let url = '/rest/idbList' 
				+ '?currPage=1&rowsPerPage=5'
				+ '&searchType=' + document.getElementById('searchType').value
				+ '&keyword=' + document.getElementById('keyword').value;
	
	axios.get(url
	).then(response => {
		     
	}).catch(err => {
		
	})
				
} // searchDB

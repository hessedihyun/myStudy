/**
// ** Ajax_REST API Join Test **
// => axios
// => file_UpLoad 가 포함된 formData 처리
// => joinForm 요청: MemberController -> 웹 Page return
// => join 요청: RTestController -> 결과 Text return  
 */
// => Axios 메서드형식 적용 (00_AJAX(공유).pptx 16p)
/*   - GET   : axios.get( url[, config] )
   - POST  : axios.post( url, data[, config] )
   - PUT   : axios.put( url, data[, config] )
   - PATCH : axios.patch( url[, data[, config]] )
   - DELETE: axios.delete( url[, config] )     */

'use strict'
// 1) joinForm 요청
// => response : 웹 Page
function rsJoinf() {
	let url="/member/joinForm";
	axios.get(url
	).then(response => { // url(좌표)을 갖고서 MemberController에 JoinForm 갔다가 다시 돌아온 response 값
		console.log("** response: joinForm 성공");
		document.getElementById('resultArea2').innerHTML=response.data;
	}).catch(err => {
		alert(`** response: joinForm 실패 => ${err.message}`);
	});
} // rsJoinf

// ** form Tag의 input Data 처리방법
// => 직접 입력 : multipart 타입은 처리할 수 없음
//      data: { id:document.getElementById('id').value,
//              password:document.getElementById('password').value
//           } 
// => Form 의 serialize() : jQuery 만 적용됨
//       -> input Tag 의 name 과 id 가 같아야 함.   
//       -> multipart 타입은 전송안됨. 
//         처리하지 못하는 값(예-> file Type) 은 스스로 제외시킴 
// => 객체화 : multipart 타입은 처리할 수 없음
//       let myData = {
//            id:document.getElementById('id').value,
//            password:document.getElementById('password').value
//         }

// 2) join 처리
// => fileUpload 포함된 경우 => 색다른 방법 필요! (JS의 내장객체 FormData 객체에 담아서 전송)
// => fileUpload 포함되어 있지 않은 경우 => 기존에 하던 body 내용처럼 표기해도 됨
// => Data 전송 : JS의 FormData 사용, 요청_headers "Context-Type" 변경(multipart이기 때문에)

// => FormData 객체 활용 : JS의 내장객체
//      -> append 메서드 : multipart 타입 처리 불편
//      -> 생성자 매개변수 이용 : multipart 타입 포함 간편한 처리가능 

// => multipart 타입 포함
// => JS의 내장객체 FormData 사용
//      -> 요청_headers "Content-Type" 변경

function axiJoin() {
	// 2.1) Data 전송 준비
	let formData = new FormData(document.getElementById('myform')); 
		// FormData 객체는 form 안에 있는 input 테그 값들을 append하면서 객체 형태로 자동으로 담아준다. 
		// multipart 타입도 가능하기 때문에 이미지 보내기 위해서 FormData를 쓴 것.
	
	// 2.2) Axios 요청처리
	let url="/rest/rsjoin";
	axios.post(url, formData
				, { headers:{ 'Content-Type':'multipart/form_data' }
	}).then(response => {
		alert(`** join 성공 => ${response.data}`);
		rsLoginf();
		// location.reload();
	}).catch(err => {
		if(err.response.status=='502') alert("~~ 입력 오류! 다시 하세요 ~~")
		else alert(`~~ System 오류 ~~ => ${err.message}`)
	});
	document.getElementById('resultArea2').innerHTML='';
} // axiJoin
package aop04;

import org.aspectj.lang.JoinPoint;

// ** xml 방식의 공통적 관심 사항 구현 2.
//=> pointcut : 매개변수, return 값 있음  
//=> 개별 advice 메서드를 구현 
//  Before, After_returning, After_throwing, After 

//** JoinPoint (인터페이스)  
//=> 핵심적 관심사항으로 들어가는 모든 데이터 (before를 통해) 사항을
//  가지고 있으며 처리할 수 있도록 해줌

//1) 인자
//=> JoinPoint의 getArgs() 메서드
// 핵심관심사항의 인자(매개변수) 의 목록을 배열의 형태로 제공함.
// Before  메서드 에서 사용가능함.
//2) return 값 처리
//=> myAfter_returning 메서드에 매개변수(리턴값)로 전달되어 사용 가능. 

public class MyAspect {
	
	// ** Before
	// 1) 핵심적 관심사항의 매개변수 처리가능 
	// => 핵심적 관심사항을 실행할 필요가 없으므로 JoinPoint Type 을 사용함  
	
	public void myBefore(JoinPoint joinPoint) {
		System.out.println("프로젝트 과제를 합니다. => Before");
		
		// => pointcut의 인자값 확인가능
		Object[] args = joinPoint.getArgs(); // 핵심기능에 전달되어진 인자(여러 개 가능)를 확인해볼 수 있다. 지금은 int n 한 개
		for(Object o:args) {
			System.out.println(" ** myBefore, pointcut의 인자값 => " + o);
		} // for
	} // myBefore
	
	// ** After_returning : 핵심적 관심사항(기능) 정상종료
	// => 핵심적 관심사항 정상 종료후 결과 return 
	// => 이 결과를 매개변수로 전달 받으며 이에 대한 처리가 가능
	// => 전달받을 매개변수 : xml에서 mapping -> returning="result"   
	   
	// Test 1. 전달된 Return 값  사용가능함 
	// Test 2. class 의 main 실행시에는 전달된 return 값 출력됨 확인
	public void myAfter_returning(Object result) {
		System.out.println("~~ 200 OK : 회원가입, 글 등록이 잘 됩니다 => 핵심적 관심사항 정상 종료");
		System.out.println(" ** myAfter_returning, pointcut의 리턴값 => " + result);
		result += "return Value Change";
		// => result는 현 메소드의 지역변수이므로 pointcut의 return 값에는 영향 없음
	} // myAfter_returning
	
	// ** After-throwing : 핵심적 관심사항(기능) 비정상종료
	// => 핵심적 관심사항 실행도중 예외발생시 예외메시지 return 
	// => 매개변수로 예외 메시지 전달받으면 이에 대한 처리 가능
	// => 전달받을 매개변수 : xml에서 mapping -> throwing="e"
	public void myAfter_throwing(Exception e) {
		System.out.println("** 밤새워 수정합니다...zz => 예외발생으로 핵심적 관심사항 비정상 종료");
		System.out.println(" ** myAfter_throwing, 전달 받은 Exception Message => " + e.toString());
	} // myAfter_throwing

	// ** After : 정상/비정상 관계없이 무조건 시행
	public void myAfter() {
		System.out.println("** finally: 무조건 제출 합니다 ~~ => 무조건 종료 (After)");
	} // After
	
} // class
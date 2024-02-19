package aop02;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
	
	public void myAround(ProceedingJoinPoint joinpoint) {
		// ** Before
		System.out.println("프로젝트 과제를 합니다. => Before");
		try {
			// ** 핵심기능수행 (전달받아야 한다 from Boy, Girl 일명 "PointCut")
			joinpoint.proceed();
				 // => Throwable 로 예외처리를 해야함
		         //    Obejct - Throwable - Exception - RuntimeException (UnChecked), IOException (Checked)
		         //              - Error
				 // => Exception : 대응 가능한 오류
				 // => Error : 대응 불가능한 오류
			// ** 핵심적 관심사항의 정상종료기능 : After_returning
			System.out.println("~~ 200 OK : 회원가입, 글 등록이 잘 됩니다 => 핵심적 관심사항 정상 종료");
		} catch (Throwable e) {

			// ** 핵심적 관심사항의 비정상종료기능 : After_throwing
			System.out.println("** 밤새워 수정합니다...zz => 예외발생으로 핵심적 관심사항 비정상 종료");
			// => 발생된 예외를 Throwable로 처리(처리&종료) 했으므로 main까지 전달되지않음 (확인후 Test)
			// System.out.println("** Exception => " + e.toString()); // (X)
	        // => main으로 전달하려면 예외발생시켜주면됨.
	        // throw new Exception(e) ;  // Exception 은 Checked -> try~catch 처리 해야함 // (△)
			throw new RuntimeException(e); // main에서 e.toString()! (Unchecked Exception)
		} finally {
			System.out.println("** finally: 무조건 제출 합니다 ~~ => 무조건 종료 (After)");
		}
	} // doStudying
	
	
	
}

package aop04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


// ** aop04 : 
public class Test {

	public static void main(String[] args) {
		//  ** IOC/DI 적용
		// => 스프링컨테이너 생성 
		// => 필요한 Bean을 주입 받는다.
		
		AbstractApplicationContext sc =
				new GenericXmlApplicationContext("aop04.xml"); // resources에 만들면 간략해짐 (과거 : iocDI01_xml/app01.xml 이렇게 했었음)
		Programmer programmerB = (Programmer)sc.getBean("boy");
		Programmer programmerG = (Programmer)sc.getBean("girl");
		try {
			System.out.println("** Boy Test **");
			System.out.println("* Boy 결과 => " + programmerB.doStudying(20));
			System.out.println();
			System.out.println("** Girl Test **");
			System.out.println("* Girl 결과 => " + programmerG.doStudying(30));
		} catch (Exception e) {
			System.out.println("\n ** main Exception => " + e.toString());
		}
		sc.close();
		
	} // main
} // class

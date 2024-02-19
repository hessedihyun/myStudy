package aop01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		//  ** IOC/DI 적용
		// => 스프링컨테이너 생성 
		// => 필요한 Bean을 주입 받는다.
		
		AbstractApplicationContext sc =
				new GenericXmlApplicationContext("aop01.xml"); // resources에 만들면 간략해짐 (과거 : iocDI01_xml/app01.xml 이렇게 했었음)
		Programmer programmerB = (Programmer)sc.getBean("boy");
		Programmer programmerG = (Programmer)sc.getBean("girl");
		
		programmerB.doStudying();
		programmerG.doStudying();
		sc.close();
		
	} // main
} // class

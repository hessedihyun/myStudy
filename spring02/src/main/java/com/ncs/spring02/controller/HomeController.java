package com.ncs.spring02.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	} // home
	
	@GetMapping("/bcrypt")
	   public String bcrypt() {
	      
	      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	      String password = "12345!";
	      
	      // 1) encode
	      // => 동일한 원본(rawData) 에 대해 각기 다른 결과(digest)를 생성
	      String digest1 = passwordEncoder.encode(password);
	      String digest2 = passwordEncoder.encode(password);
	      String digest3 = passwordEncoder.encode(password);
	      String digest4 = passwordEncoder.encode("6789@");
	      String digest5 = passwordEncoder.encode("abcd#");
	      
	      System.out.println("** digest1 => "+digest1);
	      System.out.println("** digest2 => "+digest2);
	      System.out.println("** digest3 => "+digest3);
	      System.out.println("** digest4 => "+digest4);
	      System.out.println("** digest5 => "+digest5);
	      
	      // 2) matches(rawData, digest) 
	      System.out.println("** digest1 matches => "+ passwordEncoder.matches(password, digest1)); 
	      System.out.println("** digest2 matches => "+ passwordEncoder.matches(password, digest2)); 
	      System.out.println("** digest3 matches => "+ passwordEncoder.matches(password, digest3)); 
	      System.out.println("** digest4 matches => "+ passwordEncoder.matches(password, digest4)); 
	      System.out.println("** digest5 matches => "+ passwordEncoder.matches(password, digest5)); 
	      
	      return "redirect:home";
	   } //bcrypt
	
	   // ** Sring_Exception Test
	   //  * Web의 Exception 처리  
	   // => WebPage 별, ExceptionType 별, 응답상태코드 별 
	   // => web.xml
	   //   : Exception Type 은 전달되어 해당하는 jsp는 실행되지만, 
	   //   : page 디렉티브의 isErrorPage="true" 와 무관하게 ~.jsp 에 ${exception.message} 전달안됨. 
	   
	   //  * Spring exceptionResolver 적용  
	   // => servlet~~~.xml 에 설정
	   // => page 디렉티브의 isErrorPage="true" 와 무관하게 ~.jsp 에 ${exception.message} 전달됨. 
	   //    그러나 Spring exceptionResolver 가 오류 메시지를 받아 처리하므로 
	   //    console 에는 Exception Message가 출력(전달) 되지않는다.
	   //    ( DEBUG level Message 는 출력됨. )
	
	
	@GetMapping("/etest")
	public String etest(HttpServletRequest request) {
		
	      // 1) ArithmeticException 
	      // => ArithmeticException -> 500 
	      int i=100/10;
	      // 로그메시지 찍는 방법 중 한 가지 (info, error, warning 등) VS Sysout 하지 말고(이건 그때마다 in/out을 하는거라 컴퓨터 처리 지연)
	      logger.info("** ArithmeticException 정수 => "+i);
	      // => 실수형연산: by Zero Exception 발생하지않음
	      //    
	      double d = 100.0/0.0; // Infinity (무한수)
	      double p = 100.0%0.0; // NaN (Not a Number)
	      
	      if (Double.isInfinite(d)) d=1;
	      if (Double.isNaN(p)) p=0;
	      logger.info("** ArithmeticException 실수d => "+(d*100));
	      logger.info("** ArithmeticException 실수p => "+(p+100));
	      
	      // 2) NumberFormatException (Java, web.xml) or IllegalArgumentException (Spring)
	      String s="12345"; // "12345a" 와 비교
	      i+= Integer.parseInt(s);
	      logger.info("** IllegalArgumentException => "+i);
	      
	      // 3) NullPointerException
	      // => getParameter() : parameter 가 없으면 null return
	      s=request.getParameter("name");
	      
	      // if (s.equals("홍길동")) s="Yes";
	      // => NullPointerException 예방위해
	      if ("홍길동".equals(s)) s="Yes";
	      else s="No";
	      logger.info("** NullPointerException => "+s);
	      
	      // 4) ArrayIndexOutOfBoundsException
	      // => <props> 에 정의안됨 : defaultErrorView Test
	      String[] menu = {"오징어떡볶이","카레라이스","떡갈비"};
	      logger.info("** ArrayIndexOutOfBoundsException 500 => "+menu[2]);
	      
	      // 5) SQL Test : Transaction 또는 join 으로 
		
		
		
		return "redirect:home";
	} // etest
	
}

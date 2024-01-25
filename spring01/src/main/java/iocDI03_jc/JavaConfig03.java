package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java Bean Configuration class를 이용한 DI
//=> Test03 : 스피커 2개중 선택 
//=> 생성자 를 이용한 주입.. & JC에서 @, xml 병행사용

//*** JC 와 @ 병행사용
//*** JC , @, xml 병행사용
//=> JC 내에서 xml 로 생성된 객체를 직접 사용하는것은 허용 되지 않음. 
// 단, User 클래스에서는 사용가능

//** 실습
//=> SsTVsi , Speaker 는 xml 로 생성
//=> LgTVsi, AiTVsi 는 JC 의 @Bean 으로 생성
//=> LgTVsi (Speaker 생성자주입) 
// AiTVsi (Speaker @Autowired)

@ImportResource("iocDI03_jc/app10.xml") // xml 병행할 때
@Configuration // 이 파일의 정체 (위에 import가 생성됨)
public class JavaConfig03 {
	 // 1) 고전적 방법 (직접 new : 소스 재컴파일)
	 // => xml 병행 Test
	
	 // 2) IOC / DI : 생성자 주입
	 // => SpeakerB를 xml로 생성시 : 전달 불가능
	 // => SpeakerB를 @로 생성시 : 전달 불가능
	 // 스프링 컨테이너로 만들어지니까 반드시 @Bean이 필요 
	 @Bean 
	 public TV lgtv() { return new LgTVsi(spb(),"Orange", 1234000); }
	 public Speakeri spb() { return new SpeakerB(); } // xml로 하건 @로 하건 전달 불가능이기 때문에 하고 싶다면 @Autowired해야 함
	 
	 // 3) IOC / DI : JC에서 xml 병행 사용
	 // => AiTVsi를 jc에서 생성 후 Speaker 는 @Autowired
	 // => SpeakerA 를 jc로 생성 후 @Bean의 적용에 따른 다른 결과
	 // => SpkearA 를 xml, @로 생성 후 확인
	 // @Bean //(1차 방법 -> 40,41행 실행)
	 // public TV aitv() {return new AiTVsi();}
	 
	 
	 // => AiTVsi를 xml에서 생성 후 @Autowired Test
	 
	 
	 
} // class

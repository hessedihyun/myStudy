package jdbc02;
import java.util.List;

public class StudentController {
	StudentService service = new StudentService();
	public void printList(List<StudentDTO> list) {
		System.out.println("** Student List **");
		if (list != null) {
			for(StudentDTO s : list) {
				System.out.println(s);
			}
		} else {
			System.out.println("** selectList 결과가 1건도 없음 **");	
		}
	}
/////////////////////////////////////////////////////////
	public void printDetail(StudentDTO studentDTO) {
		System.out.println("** selectOne 성공 **");
		if (studentDTO!=null) {
			System.out.println(studentDTO);
		}else {
			System.out.println("** selectOne 해당 결과가 없음 **");
		}
	}
/////////////////////////////////////////////////////////	
	public static void main(String[] args) {
		StudentController sc = new StudentController();
		sc.printList(sc.service.selectList());
		sc.printDetail(sc.service.selectOne(3));
		
		StudentDTO dto = new StudentDTO();
		
//		dto.setName("바나나");
//		dto.setAge(30);
//		dto.setJno(9);
//		dto.setInfo("Insert test");
//		if(sc.service.insert(dto)>0) {
//			System.out.println("**insert 성공**");
//		} else System.out.println("**insert 실패**");
//		
      // ** 참조 자료형 TEST
		StudentDTO dto2 = new StudentDTO();
		dto2.setSno(3);
		sc.service.selectOne2(dto2);
		sc.printDetail(dto2);
	  // ** Insert
	  // => dto에 입력값 담기 -> Service(->DAO) -> 결과 출력
		
		
		
	 // ** Update (info, point를 수정하고 싶음 sno= 19일때)
//		dto.setSno(19);
//		dto.setInfo("수정 테스트");
//		dto.setPoint(123.456);
//		if(sc.service.update(dto)>0) {
//			System.out.println("** update 성공 & 확인 **");
//			sc.printDetail(sc.service.selectOne(19));
//		} else System.out.println("** update 실패 **");
		
	 // ** Delete
//		if ( sc.service.delete(21)>0 ) {
//			System.out.println("** delete 성공 & 확인 **");
//			sc.printDetail(sc.service.selectOne(21));
//		} else System.out.println("** delete 실패 **");	
	// ** Join Test
	// sc.printList(sc.service.joinList());
	// ** Transaction Test
	sc.service.transactionTest();	
	}
}

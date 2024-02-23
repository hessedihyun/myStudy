package buildTest;

import com.ncs.green.domain.UserDTO;
import com.ncs.green.service.DTOService;

public class BuildTest {

	public static void main(String[] args) {
		// 1) UserDTO 생성
		UserDTO dto = new UserDTO();
		dto.setId("banana");
		dto.setName("홍길동");
		dto.setLoginTime("2024/02/22 AM 10:05:25");
		
		// 2) 직접 출력
		System.out.println("** 직접 출력 => " + dto);
		
		// 3) DTOService로 출력
		DTOService service = new DTOService();
		service.setUserDTO(dto);
		System.out.println("** AntBuild Test **");
		System.out.println("** DTOService => " + service.getUserDTO());
	}

}

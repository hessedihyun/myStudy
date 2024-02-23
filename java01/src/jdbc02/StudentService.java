package jdbc02;
import java.util.List;

// ** Service
// => Controller 요청에 해당하는 DAO의 메서드를 실행하는 중간적인 역할
// => Controller와 DAO의 중간에 위치하면서 이 둘의 의존성을 낮춰준다.
// (의존성이란? 필요성. Controller에서는 StudentDTO를 사용하고 있는데, 이걸 의존관계라고 한다.)
// (의존성이 높을수록 다른 class를 수정할 때 영향을 받는 정도가 높다. 영향을 안 받아야 좋다.)
public class StudentService {
	// ** 전역 변수 정의
	StudentDAO dao = new StudentDAO();
	
	public List<StudentDTO> joinList() {
		return dao.joinList();
	}
	
	// ** selectList 
	public List<StudentDTO> selectList() {
		return dao.selectList();
	}
	// ** selectOne
	public StudentDTO selectOne(int sno) {
		return dao.selectOne(sno);
	}
	// ** selectOne2
	public void selectOne2(StudentDTO dto) {
		dao.selectOne2(dto);
	}
	// ** Insert
//	public int insert(StudentDTO dto) {
//		return dao.insert(dto);
//	}
	// ** update
	public int update(StudentDTO dto) {
		return dao.update(dto);
	}
	// ** delete
	public int delete(int sno) {
		return dao.delete(sno);
	}
	// ** Transaction 적용전
	public void transactionTest() {
		dao.transactionTest();
	}
} // class

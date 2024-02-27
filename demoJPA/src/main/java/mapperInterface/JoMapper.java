package mapperInterface;
import java.util.List;
import com.example.demo.domain.JoDTO;
import com.example.demo.domain.MemberDTO;


public interface JoMapper {
	// ** jo selectList
	List<JoDTO> selectList();

	// ** jo selectOne
	JoDTO selectOne(int jno);

	// ** jo selectMember
	List<MemberDTO> selectMember(int jno);

	// ** jo insert
	int insert(JoDTO dto);

	// ** update
	int update(JoDTO dto);

	// ** delete
	int delete(int jno);

}

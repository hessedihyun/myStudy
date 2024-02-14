package mapperInterface;
import java.util.List;
import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;

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

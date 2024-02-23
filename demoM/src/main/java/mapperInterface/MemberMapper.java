package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.example.demo.domain.MemberDTO;
import pageTest.SearchCriteria;

public interface MemberMapper {
	
	// ** JUnit Test
	// => selectDTO Test
	@Select("select * from member where id=#{id}")
	MemberDTO selectDTO(MemberDTO dto);
	
	// => selectParam Test
	@Select("select * from member where id=#{pid} and jno=#{pjno}")
	MemberDTO selectParam(@Param("pid") String id, @Param("pjno") int jno); // Integer 대문자 파람 해야 한다. (기본자료형 안되게 되어 있는데 업데이트 됐나...?)
	// => @Param 적용 Test
	//    -> 기본규칙: Mybatis 에서는 매개변수 Type은 무관하지만, 갯수는 1개만 허용
	//   -> @Param: mapper 에서 #{...} 적용, 복수갯수 사용 가능 (단, 기본자료형 사용불가_JUnit 에서는 가능 
	
	// ** memberInsert
	int memberInsert(@Param("ii") String id, @Param("jno") Integer jno);
	
	// ** Member Paging & Check_List
	public List<MemberDTO> aCheckList(SearchCriteria cri);
	public int aCheckRowsCount(SearchCriteria cri);
	
	public List<MemberDTO> mCheckList(SearchCriteria cri);
	public int mCheckRowsCount(SearchCriteria cri);
	
	public List<MemberDTO> mSearchList(SearchCriteria cri);
	public int mSearchRowsCount(SearchCriteria cri);
	
	public List<MemberDTO> mPageList(SearchCriteria cri);
	public int totalRowsCount(SearchCriteria cri);
	
	// ** selectList
	List<MemberDTO> selectList();

	// ** selectOne
	MemberDTO selectOne(String id);

	// ** insert
	int insert(MemberDTO dto);

	// ** update
	int update(MemberDTO dto);
	
	// ** pwUpdate
	int pwUpdate(MemberDTO dto);

	// ** delete
	int delete(String id);

}

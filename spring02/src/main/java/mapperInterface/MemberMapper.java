package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ncs.spring02.domain.MemberDTO;
import pageTest.SearchCriteria;

public interface MemberMapper {
	
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

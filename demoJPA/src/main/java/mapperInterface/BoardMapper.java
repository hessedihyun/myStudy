package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.BoardDTO;
import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface BoardMapper {
	
	// ** Ajax: id별 boardList 출력 
	@Select("select * from board where id = #{id}")
	public List<BoardDTO> idbList(String id);
	
	// ** Board Check_List
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	public int bCheckRowsCount(SearchCriteria cri);
	
	// ** Board Search Paging
	public List<BoardDTO> bSearchList(SearchCriteria cri);
	public int bSearchRowsCount(SearchCriteria cri);
	
	// ** Board_Paging
	public List<BoardDTO> bPageList(Criteria cri);
	public int totalRowsCount(Criteria cri);
	
	// ** selectList
	public List<BoardDTO> selectList();
	// ** selectOne
	public BoardDTO selectOne(int seq);
	// ** insert
	public int insert(BoardDTO dto);
	// ** rinsert(reply insert)
	public int rinsert(BoardDTO dto);
	// ** stepUpdate
	public int stepUpdate(BoardDTO dto);	
	// ** update
	public int update(BoardDTO dto);
	// ** delete
	public int delete(BoardDTO dto);
}

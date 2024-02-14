package pageTest;
import lombok.Getter;
import lombok.ToString;

//** Criteria : (판단이나 결정을 위한) 기준
//=> 출력할 Row 를 select 하기 위한 클래스
//=> 이것을 위한 기준 값들을 관리

//** PageMaker : UI에 필요한 정보 완성

//** Paging 을 하려면 ... **
//=> 1Page에 출력할 Row 갯수 :  5개
//=> 현재 출력(요청) Page
//=> 출력할 List (Rows) 
// -> start Row 순서번호 : 계산필요
//=> Criteria Class 에 정의 

//=> 1Page 출력 PageNo 갯수 : 10개
// -> PagreBlock 의 First Page No 
// -> PagreBlock 의 Last Page No
// -> 전진, 후진 표시여부
// -> go 전체의 First Page 표시여부
// -> go 전체의 Last Page 표시여부
//=> PageMaker Class 로 처리 

// @Data는 setter까지 포함이라 지금은 setter를 따로 설정해줄 것임. 따로 @Getter, @ToString만 처리
@Getter
@ToString
public class Criteria {
	private int rowsPerPage; // 1Page에 출력할 Row의 갯수
	private int currPage; // 현재 출력(요청) Page
	private int sno; // start Row 순서번호 : 계산필요
	private int eno; // end Row 순서번호 : 계산필요 (Oracle만 필요로 한다. MySQL은 limit이 있기 때문에 시작하는 번호만 있으면 된다.)
	
	// 1) 기본생성자 : 기본값 초기화
	public Criteria() {
		this.rowsPerPage=5;
		this.currPage=1;
	}
	
	// **setter 설정
	// 2) 요청시 값 갱신
	// 2.1) currPage
	public void setCurrPage(int currPage) {
		if(currPage>1) this.currPage = currPage;
		else this.currPage=1;
	}
   // 2.2) rowsPerPage 
   // => 1페이지당 보여줄 Row(Record,튜플) 갯수 확인
   // => 제한조건 점검 ( 50개 까지만 허용)
   // => 당장은 사용하지 않지만 사용가능하도록 작성   
	public void setRowsPerPage(int rowsPerPage) {
		if(rowsPerPage>5 && rowsPerPage<51)
			this.rowsPerPage=rowsPerPage;
		else this.rowsPerPage=5;
	}
	// 2.3) setSnoEno : sno, eno 계산
	public void setSnoEno()  {
		// int 타입 필드에 초기값을 설정하지 않으면 0이 자동 배정됨
		if(this.sno<1) this.sno=1;
		this.sno=(this.currPage-1)*this.rowsPerPage; 
		// => MySql : limit 5,5; // 뜻 : 6번째부터, 5개
		// => Oracle : between 6 and 10;
		// this.sno = (this.currPage-1)*this.rowsPerPage+1;
		// this.eno = (this.sno + this.rowsPerPage)-1);
	}
	
} // class

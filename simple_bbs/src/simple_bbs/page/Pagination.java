package simple_bbs.page;

public class Pagination {
	int pageSize = 10; // 한 페이지에 출력할 게시물 수
	int pageBlock = 5; // 페이지에 표시할 블럭 수 -> [1] [2] [3] [4] [5]
	int curPage; // 현재 페이지
	int cntList; // 총 게시물 수 (available=1)
	
	int startNo; //출력페이지의  시작 게시물 번호
	int endNo; // 출력페이지의 마지막 게시물 번호
	int totalPage; // 출력해야하는 총 페이지 수

	int prevBlock; // 이전 블록
	int nextBlock; // 다음 블록
	
	int prevPage; // 이전 페이지
	int nextPage; // 다음 페이지
	
	public Pagination(int cntList, int curPage) {
		setCurPage(curPage);
		setCntList(cntList);
		
		setEndNo(curPage);
		setStartNo(getEndNo());
		setTotalPage(cntList);
		setPrevBlock(curPage);
		setNextBlock(getPrevBlock() + getPageBlock() + 1);
		setPrevPage(curPage);
		setNextPage(getPrevPage() + getPageBlock() + 1);
		
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getCntList() {
		return cntList;
	}

	public void setCntList(int cntList) {
		this.cntList = cntList;
	}

	public int getStartNo() {
		return startNo;
	}

	//startNo 설정
	public void setStartNo(int endNo) {
		this.startNo = getEndNo() - getPageSize();
	}

	public int getEndNo() {
		return endNo;
	}

	//endNo 설정
	public void setEndNo(int curPage) {
		this.endNo = curPage * getPageSize();
	}

	public int getTotalPage() {
		return totalPage;
	}

	// 총 페이지수  설정
	// ((총게시물 -1)/출력게시물수)+1
	public void setTotalPage(int cntList) {
		this.totalPage = ((cntList-1)/getPageSize()) +1 ;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	// 이전 블록 설정
	// int prevBlock = (int)Math.floor((cPage-1)/pageBlock) * pageBlock;
	public void setPrevBlock(int curPage) {
		this.prevBlock = (int)Math.floor((curPage-1)/getPageBlock()) * getPageBlock();
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPrevPage() {
		return prevPage;
	}

	//int prevPage = (int)Math.floor((cPage-1)/pageBlock)*pageBlock;
	public void setPrevPage(int curPage) {
		this.prevPage = (int)Math.floor((curPage-1)/getPageBlock()) * getPageBlock();
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	
	
}

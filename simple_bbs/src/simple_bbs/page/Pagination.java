package simple_bbs.page;

public class Pagination {
	int pageSize = 10; // �� �������� ����� �Խù� ��
	int pageBlock = 5; // �������� ǥ���� �� �� -> [1] [2] [3] [4] [5]
	int curPage; // ���� ������
	int cntList; // �� �Խù� �� (available=1)
	
	int startNo; //�����������  ���� �Խù� ��ȣ
	int endNo; // ����������� ������ �Խù� ��ȣ
	int totalPage; // ����ؾ��ϴ� �� ������ ��

	int prevBlock; // ���� ���
	int nextBlock; // ���� ���
	
	int prevPage; // ���� ������
	int nextPage; // ���� ������
	
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

	//startNo ����
	public void setStartNo(int endNo) {
		this.startNo = getEndNo() - getPageSize();
	}

	public int getEndNo() {
		return endNo;
	}

	//endNo ����
	public void setEndNo(int curPage) {
		this.endNo = curPage * getPageSize();
	}

	public int getTotalPage() {
		return totalPage;
	}

	// �� ��������  ����
	// ((�ѰԽù� -1)/��°Խù���)+1
	public void setTotalPage(int cntList) {
		this.totalPage = ((cntList-1)/getPageSize()) +1 ;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	// ���� ��� ����
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

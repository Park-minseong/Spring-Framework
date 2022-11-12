package com.spring.boardweb;

//시작 페이지와 끝페이지를 계산, 이전 버튼과 다음버튼이 실행되는지 안되는지 boolean,
//criteria 객체를 가지고있는 클래스
public class PageVO {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	
	private Criteria cri;
	
	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		//시작페이지와 마지막페이지 계산
		//5페이지를 보고 있으면 시작페이지가 1, 마지막페이지가 10
		//16페이지를 보고 있으면 시작페이작 11, 마지막페이지가 20
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		//마지막 데이터가 있는 페이지
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getTotal() {
		return total;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageVO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}

}

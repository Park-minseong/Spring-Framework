package com.spring.boardweb;

//현재 보고있는 페이지 번호, 한페이지에 표시할 게시물 개수, 페이지 시작 번호를 가지는 객체
public class Criteria {
	private int pageNum;
	private int amount;
	private int startNum;
	
	
	
	public Criteria() {
		this(1,10);
	}



	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}



	public int getPageNum() {
		return pageNum;
	}



	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public int getStartNum() {
		return startNum;
	}



	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}



	@Override
	public String toString() {
		return "Critetia [pageNum=" + pageNum + ", amount=" + amount + ", startNum=" + startNum + "]";
	}
	
	
}

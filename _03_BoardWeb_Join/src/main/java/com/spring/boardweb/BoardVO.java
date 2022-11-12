package com.spring.boardweb;

import java.sql.Date;

public class BoardVO {
	private int  boardSeq;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Date boardRegdate;
	private Date boardMdfdate;
	private int boardCnt;
	
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getBoardRegdate() {
		return boardRegdate;
	}
	public void setBoardRegdate(Date boardRegdate) {
		this.boardRegdate = boardRegdate;
	}
	public Date getBoardMdfdate() {
		return boardMdfdate;
	}
	public void setBoardMdfdate(Date boardMdfdate) {
		this.boardMdfdate = boardMdfdate;
	}
	public int getBoardCnt() {
		return boardCnt;
	}
	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}
}

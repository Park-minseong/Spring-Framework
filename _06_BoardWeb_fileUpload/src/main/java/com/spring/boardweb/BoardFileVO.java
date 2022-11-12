package com.spring.boardweb;

public class BoardFileVO {
	private int boardSeq;
	private int fileSeq;
	private String originalFileName;
	private String fileName;
	private String filePath;
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public int getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(int ilfeSeq) {
		this.fileSeq = ilfeSeq;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "BoardFileVO [boardSeq=" + boardSeq + ", ilfeSeq=" + fileSeq + ", originalFileName=" + originalFileName
				+ ", fileName=" + fileName + ", filePath=" + filePath + "]";
	}
	
	

}

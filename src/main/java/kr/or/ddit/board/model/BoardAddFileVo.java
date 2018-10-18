package kr.or.ddit.board.model;

//첨부파일                  
public class BoardAddFileVo {

	private int addFileNum ;	
	private int textNum ;	
	private String addFileUrl ;	
	
	public BoardAddFileVo(){}

	public int getAddFileNum() {
		return addFileNum;
	}

	public void setAddFileNum(int addFileNum) {
		this.addFileNum = addFileNum;
	}

	public int getTextNum() {
		return textNum;
	}

	public void setTextNum(int textNum) {
		this.textNum = textNum;
	}

	public String getAddFileUrl() {
		return addFileUrl;
	}

	public void setAddFileUrl(String addFileUrl) {
		this.addFileUrl = addFileUrl;
	}

	@Override
	public String toString() {
		return "BoardAddFileVo [addFileNum=" + addFileNum + ", textNum="
				+ textNum + ", addFileUrl=" + addFileUrl + "]";
	}

}

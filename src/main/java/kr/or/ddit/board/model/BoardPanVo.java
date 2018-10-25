package kr.or.ddit.board.model;

import java.util.Date;

//게시판
public class BoardPanVo {

	private int panNum;	
	private String panId;			
	private String panName;		
	private String panDel;			
	private String panWriter;			
	private Date panBirth;
	
	public BoardPanVo(){}

	public int getPanNum() {
		return panNum;
	}

	public void setPanNum(int panNum) {
		this.panNum = panNum;
	}

	public String getPanId() {
		return panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public String getPanName() {
		return panName;
	}

	public void setPanName(String panName) {
		this.panName = panName;
	}

	public String getPanDel() {
		return panDel;
	}

	public void setPanDel(String panDel) {
		this.panDel = panDel;
	}

	public String getPanWriter() {
		return panWriter;
	}

	public void setPanWriter(String panWriter) {
		this.panWriter = panWriter;
	}

	public Date getPanBirth() {
		return panBirth;
	}

	public void setPanBirth(Date panBirth) {
		this.panBirth = panBirth;
	}

	@Override
	public String toString() {
		return "BoardPanVo [panNum=" + panNum + ", panId=" + panId + ", panName=" + panName + ", panDel=" + panDel
				+ ", panWriter=" + panWriter + ", panBirth=" + panBirth + "]";
	}

}

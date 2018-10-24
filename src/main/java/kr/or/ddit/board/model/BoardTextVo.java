package kr.or.ddit.board.model;

import java.util.Date;

//게시글                    
public class BoardTextVo {

	private int textNum ;	
	private String panId ;			
	private String textName;			
	private String text;		
	private String textSub ;		
	private String textWriterId ;			
	private Date textWriteDate ;
	private String textDel ;		
	private Date textDelDate;	
	private int seqNum ;
	private int textNumP ;	
	
	public BoardTextVo(){}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getTextNum() {
		return textNum;
	}

	public void setTextNum(int textNum) {
		this.textNum = textNum;
	}

	public String getPanId() {
		return panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

	public String getTextSub() {
		return textSub;
	}

	public void setTextSub(String textSub) {
		this.textSub = textSub;
	}

	public String getTextWriterId() {
		return textWriterId;
	}

	public void setTextWriterId(String textWriterId) {
		this.textWriterId = textWriterId;
	}

	public Date getTextWriteDate() {
		return textWriteDate;
	}

	public void setTextWriteDate(Date textWriteDate) {
		this.textWriteDate = textWriteDate;
	}

	public String getTextDel() {
		return textDel;
	}

	public void setTextDel(String textDel) {
		this.textDel = textDel;
	}

	public Date getTextDelDate() {
		return textDelDate;
	}

	public void setTextDelDate(Date textDelDate) {
		this.textDelDate = textDelDate;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	public int getTextNumP() {
		return textNumP;
	}

	public void setTextNumP(int textNumP) {
		this.textNumP = textNumP;
	}

	@Override
	public String toString() {
		return "BoardTextVo [textNum=" + textNum + ", panId=" + panId + ", textName=" + textName + ", textSub="
				+ textSub + ", textWriterId=" + textWriterId + ", textWriteDate=" + textWriteDate + ", textDel="
				+ textDel + ", textDelDate=" + textDelDate + ", seqNum=" + seqNum + ", textNumP=" + textNumP + "]";
	}
}

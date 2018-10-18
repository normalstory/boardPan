package kr.or.ddit.board.model;

import java.util.Date;

//게시글                    
public class BoardTextVo {

	private int TextNum ;	
	private String PanId ;			
	private String TextName;			
	private String TextSub ;		
	private String TextWriterId ;			
	private Date TextWriteDate ;
	private String TextDel ;		
	private Date TextDelDate;	
	private int SeqNum ;
	private int TextNumP ;	
	
	public BoardTextVo(){}

	public int getTextNum() {
		return TextNum;
	}

	public void setTextNum(int textNum) {
		TextNum = textNum;
	}

	public String getPanId() {
		return PanId;
	}

	public void setPanId(String panId) {
		PanId = panId;
	}

	public String getTextName() {
		return TextName;
	}

	public void setTextName(String textName) {
		TextName = textName;
	}

	public String getTextSub() {
		return TextSub;
	}

	public void setTextSub(String textSub) {
		TextSub = textSub;
	}

	public String getTextWriterId() {
		return TextWriterId;
	}

	public void setTextWriterId(String textWriterId) {
		TextWriterId = textWriterId;
	}

	public Date getTextWriteDate() {
		return TextWriteDate;
	}

	public void setTextWriteDate(Date textWriteDate) {
		TextWriteDate = textWriteDate;
	}

	public String getTextDel() {
		return TextDel;
	}

	public void setTextDel(String textDel) {
		TextDel = textDel;
	}

	public Date getTextDelDate() {
		return TextDelDate;
	}

	public void setTextDelDate(Date textDelDate) {
		TextDelDate = textDelDate;
	}

	public int getSeqNum() {
		return SeqNum;
	}

	public void setSeqNum(int seqNum) {
		SeqNum = seqNum;
	}

	public int getTextNumP() {
		return TextNumP;
	}

	public void setTextNumP(int textNumP) {
		TextNumP = textNumP;
	}
	
	
}

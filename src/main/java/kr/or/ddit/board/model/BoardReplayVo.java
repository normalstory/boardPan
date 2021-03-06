package kr.or.ddit.board.model;

import java.util.Date;

//댓글                      
public class BoardReplayVo {
	
	private String replyId ;		
	private int textNum ;		
	private String replySub	;		
	private String replyerId ;			
	private Date replyDate 	;
	private String replyDel ;			
	private Date replyDelDate ;
	
	public BoardReplayVo(){}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public int getTextNum() {
		return textNum;
	}

	public void setTextNum(int textNum) {
		this.textNum = textNum;
	}

	public String getReplySub() {
		return replySub;
	}

	public void setReplySub(String replySub) {
		this.replySub = replySub;
	}

	public String getReplyerId() {
		return replyerId;
	}

	public void setReplyerId(String replyerId) {
		this.replyerId = replyerId;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getReplyDel() {
		return replyDel;
	}

	public void setReplyDel(String replyDel) {
		this.replyDel = replyDel;
	}

	public Date getReplyDelDate() {
		return replyDelDate;
	}

	public void setReplyDelDate(Date replyDelDate) {
		this.replyDelDate = replyDelDate;
	}

	@Override
	public String toString() {
		return "BoardReplayVo [replyId=" + replyId + ", textNum=" + textNum + ", replySub=" + replySub + ", replyerId="
				+ replyerId + ", replyDate=" + replyDate + ", replyDel=" + replyDel + ", replyDelDate=" + replyDelDate
				+ "]";
	}


}

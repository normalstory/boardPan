package kr.or.ddit.board.model;

import java.util.Date;

//댓글                      
public class BoardReplayVo {
	
	private String ReplyID ;		
	private int TextNum ;		
	private String ReplySub	;		
	private String ReplyerId ;			
	private Date ReplyDate 	;
	private String ReplyDel ;			
	private Date ReplyDelDate ;
	
	public BoardReplayVo(){}

	public String getReplyID() {
		return ReplyID;
	}

	public void setReplyID(String replyID) {
		ReplyID = replyID;
	}

	public int getTextNum() {
		return TextNum;
	}

	public void setTextNum(int textNum) {
		TextNum = textNum;
	}

	public String getReplySub() {
		return ReplySub;
	}

	public void setReplySub(String replySub) {
		ReplySub = replySub;
	}

	public String getReplyerId() {
		return ReplyerId;
	}

	public void setReplyerId(String replyerId) {
		ReplyerId = replyerId;
	}

	public Date getReplyDate() {
		return ReplyDate;
	}

	public void setReplyDate(Date replyDate) {
		ReplyDate = replyDate;
	}

	public String getReplyDel() {
		return ReplyDel;
	}

	public void setReplyDel(String replyDel) {
		ReplyDel = replyDel;
	}

	public Date getReplyDelDate() {
		return ReplyDelDate;
	}

	public void setReplyDelDate(Date replyDelDate) {
		ReplyDelDate = replyDelDate;
	}

	@Override
	public String toString() {
		return "BoardReplayVo [ReplyID=" + ReplyID + ", TextNum=" + TextNum
				+ ", ReplySub=" + ReplySub + ", ReplyerId=" + ReplyerId
				+ ", ReplyDate=" + ReplyDate + ", ReplyDel=" + ReplyDel
				+ ", ReplyDelDate=" + ReplyDelDate + "]";
	}
	
}

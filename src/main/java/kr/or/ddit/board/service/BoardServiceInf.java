package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardAddFileVo;
import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.model.BoardReplayVo;
import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.util.model.PageVo;

public interface BoardServiceInf {
	//게시판관리페이지
	BoardPanVo chackPan(String panId);
	List<BoardPanVo> panList();
	List<BoardPanVo> panListManu();
	int insertPan(BoardPanVo panVo);
	int updatePan(BoardPanVo panVo);
	
	//게시판목록페이지 
	List<BoardTextVo> textVoList(String panId);	
	int pageCnt(String panId);
	Map<String, Object> textVoListPage(PageVo pageVo, String panId);
	
	//게시글페이지
	BoardTextVo textVoDetail(int textNum);
	int insertText(BoardTextVo boardTextVo);
	int updateText(BoardTextVo boardTextVo);
	int textDelUpdate(BoardTextVo boardTextVo);
	
	//첨부파일 
	int addFile(String linkUrl);
	List<BoardAddFileVo> addFilesList(int textNum);
	
	//댓글
	int addReply(BoardReplayVo replyVo);
	List<BoardReplayVo> replyList(BoardReplayVo replyVo);
	int replyDel(String replyId);
}

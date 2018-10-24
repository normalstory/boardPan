package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardAddFileVo;
import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.model.BoardReplayVo;
import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.util.model.PageVo;

public class BoardService implements BoardServiceInf{
	BoardDaoInf boardDao = new BoardDao();

	//게시판관리페이지
	@Override
	public BoardPanVo chackPan(String panId) {
		return boardDao.chackPan(panId);
	}
	
	@Override
	public List<BoardPanVo> panList() {
		return boardDao.panList();
	}

	@Override
	public List<BoardPanVo> panListManu() {
		return boardDao.panListManu();
	}

	@Override
	public int insertPan(BoardPanVo panVo) {
		return boardDao.insertPan(panVo);
	}

	@Override
	public int updatePan(BoardPanVo panVo) {
		return boardDao.updatePan(panVo);
	}

	//게시판목록페이지
	@Override
	public List<BoardTextVo> textVoList(String panId) {
		return boardDao.textVoList(panId);
	}

	@Override
	public int pageCnt(String panId) {
		return boardDao.pageCnt(panId);
	}

	@Override
	public Map<String, Object> textVoListPage(PageVo pageVo, String panId) {
		List<BoardTextVo> pageVoList = boardDao.textVoListPage(pageVo);
		int pageCnt = boardDao.pageCnt(panId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pageVoList", pageVoList);
		resultMap.put("pageNum", (int)Math.ceil((double)pageCnt/pageVo.getPageSize()));
		
		return resultMap;
	}

	//게시판상세페이지
	@Override
	public BoardTextVo textVoDetail(int textNum) {
		return boardDao.textVoDetail(textNum);
	}

	@Override
	public int insertText(BoardTextVo boardTextVo) {
		return boardDao.insertText(boardTextVo);
	}

	@Override
	public int updateText(BoardTextVo boardTextVo) {
		return boardDao.updateText(boardTextVo);
	}

	@Override
	public int textDelUpdate(BoardTextVo boardTextVo) {
		return boardDao.textDelUpdate(boardTextVo);
	}

	@Override
	public int addFile(String linkUrl) {
		return boardDao.addFile(linkUrl);
	}

	@Override
	public List<BoardAddFileVo> addFilesList(int textNum) {
		return boardDao.addFilesList(textNum);
	}

	@Override
	public List<BoardReplayVo> replyList(BoardReplayVo replyVo) {
		return boardDao.replyList(replyVo);
	}

	@Override
	public int addReply(BoardReplayVo replyVo) {
		return boardDao.addReply(replyVo);
	}

	@Override
	public int replyDel(String replyId) {
		return boardDao.replyDel(replyId);
	}
	
}

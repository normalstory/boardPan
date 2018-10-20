package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardPanVo;
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

//	@Override 	// <-- 일반 리스트 
//	public List<BoardTextVo> textVoListPage(PageVo pageVo) {
//		return boardDao.textVoListPage(pageVo);
//	}

//	@Override	// <-- 리스트 + 페이징  //페이지 별 목록의 개수 제한
//	public List<BoardTextVo> textVoListPage(PageVo pageVo) {
//		return boardDao.textVoListPage(pageVo);
//	}

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
}

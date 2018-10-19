package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardPanVo;

public class BoardService implements BoardServiceInf{
	BoardDaoInf boardDao = new BoardDao();

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

}

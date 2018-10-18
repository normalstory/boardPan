package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardPanVo;

public class BoardService implements BoardServiceInf{
	BoardDaoInf boardDao = new BoardDao();

	@Override
	public List<BoardPanVo> panList() {
		return boardDao.panList();
	}

}

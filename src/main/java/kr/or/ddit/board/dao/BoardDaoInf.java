package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardPanVo;

public interface BoardDaoInf {
	BoardPanVo chackPan(String panId);
	
	List<BoardPanVo> panList();
	List<BoardPanVo> panListManu();
	
	int insertPan(BoardPanVo panVo);
	int updatePan(BoardPanVo panVo);
}

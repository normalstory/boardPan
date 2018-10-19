package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardPanVo;

public interface BoardServiceInf {
	BoardPanVo chackPan(String panId);
	
	List<BoardPanVo> panList();
	List<BoardPanVo> panListManu();
	
	int insertPan(BoardPanVo panVo);
	int updatePan(BoardPanVo panVo);
}

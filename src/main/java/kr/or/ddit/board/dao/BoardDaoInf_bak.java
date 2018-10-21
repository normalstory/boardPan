package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.util.model.PageVo;

public interface BoardDaoInf_bak {
	//게시판관리페이지
	BoardPanVo chackPan(String panId);
	
	List<BoardPanVo> panList();
	List<BoardPanVo> panListManu();
	
	int insertPan(BoardPanVo panVo);
	int updatePan(BoardPanVo panVo);
	
	//게시판목록페이지 
	List<BoardTextVo> textVoList(String panId);	//그냥 리스트 
	//List<BoardTextVo> textVoListPage(PageVo pageVo); //페이지처리 리스
	
	//board.xml 쿼리에 pageVo와 panId(? vs BoardTextVo) 두개의 파라미터가 필요하다 
	// 페이지 별 목록의 개수 제한
	List<BoardTextVo> textVoListPage(PageVo pageVo); //페이지처리 수정 + MAP <-- pageVo에 변수 추가
	
	int pageCnt(String panId);
	
	//게시판 상세 페이지
}

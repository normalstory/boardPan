package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.util.model.PageVo;

public interface BoardServiceInf_bak {
	//게시판관리페이지
	BoardPanVo chackPan(String panId);
	
	List<BoardPanVo> panList();
	List<BoardPanVo> panListManu();
	
	int insertPan(BoardPanVo panVo);
	int updatePan(BoardPanVo panVo);
	
	//게시판목록페이지 
	List<BoardTextVo> textVoList(String panId);	//그냥 리스트 
	
	//List<BoardTextVo> textVoListPage(PageVo pageVo); //페이지 별 목록의 개수 제한 + MAP <-- pageVo에 변수 추
	int pageCnt(String panId);
	//페이징 처리 : List + pageCnt
	Map<String, Object> textVoListPage(PageVo pageVo, String panId);
	
	//게시판상세페이지
	BoardTextVo textVoDetail(int textNum);
}

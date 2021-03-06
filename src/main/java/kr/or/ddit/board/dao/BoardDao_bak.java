package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.model.BoardAddFileVo;
import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.model.BoardReplayVo;
import kr.or.ddit.board.model.BoardTextVo;
import kr.or.ddit.db.SqlFactoryBuilder;
import kr.or.ddit.util.model.PageVo;

public class BoardDao_bak implements BoardDaoInf{

	//게시판관리페이지 
	@Override
	public BoardPanVo chackPan(String panId) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		BoardPanVo panVo = session.selectOne("board.chackPan",panId);
		session.close();
		
		return panVo;
	}
	
	@Override
	public List<BoardPanVo> panList() {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();

		List<BoardPanVo> boardList= session.selectList("board.selectUser");
		System.out.println("(dao) boardList : "+boardList);
		session.close();
		
		return boardList;
	}

	@Override
	public List<BoardPanVo> panListManu() {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();

		List<BoardPanVo> panListManu= session.selectList("board.panListManu");
		System.out.println("(dao) panListManu : "+panListManu);
		session.close();
		
		return panListManu;
	}

	@Override
	public int insertPan(BoardPanVo panVo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		int insertPanResult = session.insert("board.insertPan",panVo);
		session.commit();
		session.close();
		
		return insertPanResult;
	}

	@Override
	public int updatePan(BoardPanVo panVo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		int updatePanResult = session.update("board.updatePan",panVo);
		session.commit();
		session.close();
		
		return updatePanResult;
	}

	//게시판목록페이지
	@Override
	public List<BoardTextVo> textVoList(String panId) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		List<BoardTextVo> textVoList = session.selectList("board.textVoList",panId);
		session.close();
		
		return textVoList;
	}

//	@Override	// 일반 리스트 
//	public List<BoardTextVo> textVoListPage(PageVo pageVo) {
//		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
//		SqlSession session= factory.openSession();
//		
//		List<BoardTextVo> textVoListPage = session.selectList("board.textVoListPage",pageVo);
//		session.close();
//		
//		return textVoListPage;
//	}

	// 리스트 + (페이징 + 게시판 지정)을 위한 map 파라미터 <-- pageVo 에 변수 추가
	//페이지 별 목록의 개수 제한
	@Override	
	public List<BoardTextVo> textVoListPage(PageVo pageVo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		List<BoardTextVo> textVoListPage = session.selectList("board.textVoListPage",pageVo);
		session.close();
		
		return textVoListPage;
	}
	
	@Override
	public int pageCnt(String panId) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		int pageCnt = session.selectOne("board.pageCnt",panId);
		session.close();
		
		return pageCnt;
	}
	
	//게시판상세페이지
//	@Override
//	public BoardTextVo textVo(int textNum) {
//		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
//		SqlSession session= factory.openSession();
//		
//		BoardTextVo textVo = session.selectOne("board.textVo",textNum);
//		session.close();
//		
//		return textVo;
//	}

	@Override
	public BoardTextVo textVoDetail(int textNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertText(BoardTextVo boardTextVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateText(BoardTextVo boardTextVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int textDelUpdate(BoardTextVo boardTextVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addFile(String linkUrl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardAddFileVo> addFilesList(int textNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardReplayVo> replyList(BoardReplayVo replyVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addReply(BoardReplayVo replyVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyDel(String replyId) {
		// TODO Auto-generated method stub
		return 0;
	}

}

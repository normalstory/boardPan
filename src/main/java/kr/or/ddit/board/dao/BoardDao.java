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

public class BoardDao implements BoardDaoInf{

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
	
	//게시글 페이지
	@Override
	public BoardTextVo textVoDetail(int textNum) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		BoardTextVo textVo = session.selectOne("board.textVoDetail",textNum);
		session.close();
		
		return textVo;
	}

	@Override
	public int insertText(BoardTextVo boardTextVo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		int resultInsert = session.insert("board.insertText", boardTextVo);
		session.commit();
		session.close();
		
		return resultInsert;
	}

	@Override
	public int updateText(BoardTextVo boardTextVo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		int resultUpdate = session.insert("board.updateText", boardTextVo);
		session.commit();
		session.close();
		
		return resultUpdate;
	}

	@Override
	public int textDelUpdate(BoardTextVo boardTextVo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		int resultTextDelUpdate = session.update("board.textDelUpdate", boardTextVo);
		session.commit();
		session.close();
		
		return resultTextDelUpdate;
	}

	@Override
	public int addFile(String addFileUrl) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		int addFileResult = session.insert("board.addFile",addFileUrl);
		session.commit();
		session.close();
		
		return addFileResult;
	}

	@Override
	public List<BoardAddFileVo> addFilesList(int textNum) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		List<BoardAddFileVo> addFilesList = session.selectList("board.addFilesList",textNum);
		session.close();
		
		return addFilesList;
	}

	@Override
	public int addReply(BoardReplayVo replyVo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		int addReplyResult = session.insert("board.addReply",replyVo);

		session.commit();
		session.close();
		return addReplyResult;
	}
	
	@Override
	public List<BoardReplayVo> replyList(BoardReplayVo replyVo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		List<BoardReplayVo> replyList = session.selectList("board.replyList",replyVo);
		session.close();
		
		return replyList;
	}


}

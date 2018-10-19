package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.db.SqlFactoryBuilder;

public class BoardDao implements BoardDaoInf{

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


}

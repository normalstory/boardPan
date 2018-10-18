package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.db.SqlFactoryBuilder;

public class BoardDao implements BoardDaoInf{

	@Override
	public List<BoardPanVo> panList() {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();

		List<BoardPanVo> boardList= session.selectList("board.selectUser");
		System.out.println("(dao) boardList : "+boardList);
		session.close();
		
		return boardList;
	}

}

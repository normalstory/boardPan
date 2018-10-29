package kr.or.ddit.util.dao;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.SqlFactoryBuilder;

public class UtilDao implements UtilDaoInf{

	@Override
	public Date today() {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		Date today = session.selectOne("util.today");
		session.close();
		
		return today;
	}

}

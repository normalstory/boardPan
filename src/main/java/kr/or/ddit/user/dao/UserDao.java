package kr.or.ddit.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.SqlFactoryBuilder;
import kr.or.ddit.user.model.UserVo;

public class UserDao implements UserDaoInf {

	@Override
	public UserVo selectUser(String userId) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session= factory.openSession();
		
		UserVo userVo = session.selectOne("user.selectUser", userId);
		session.close();
		
		return userVo;
	}
	
}

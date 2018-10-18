package kr.or.ddit.user.service;

import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.dao.UserDaoInf;
import kr.or.ddit.user.model.UserVo;

public class UserService implements UserServiceInf{

	@Override
	public UserVo selectUser(String userid) {
		
		UserDaoInf userDao = new UserDao();
		return userDao.selectUser(userid);
	}
	
}

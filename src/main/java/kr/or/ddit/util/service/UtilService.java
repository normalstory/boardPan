package kr.or.ddit.util.service;

import java.util.Date;

import kr.or.ddit.util.dao.UtilDao;
import kr.or.ddit.util.dao.UtilDaoInf;

public class UtilService implements UtilServiceInf{

	UtilDaoInf utilDao = new UtilDao();
	
	@Override
	public Date today() {
		return utilDao.today();
	}

}

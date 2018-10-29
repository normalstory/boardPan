package kr.or.ddit.util.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class DateTest {

	@Test
	public void dateTest() {

		UtilServiceInf utilService = new UtilService();
		Date today = utilService.today();
		System.out.println("오늘은 "+today);
	}

}

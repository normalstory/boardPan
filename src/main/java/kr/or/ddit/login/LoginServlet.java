package kr.or.ddit.login;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardPanVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.encript.sha.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceInf;
import kr.or.ddit.util.service.UtilService;
import kr.or.ddit.util.service.UtilServiceInf;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId 	= req.getParameter("userId");
		String password = req.getParameter("password");
		String rememberMe = req.getParameter("remember-me");
		
		System.out.println("rememberMe 체크여부: "+rememberMe);
		
		if(rememberMe==null){
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie :cookies ){
				System.out.println("> before cookie : "+ cookie.getName());
				if(cookie.getName().equals("rememberMe") ||  cookie.getName().equals("userId")){
					cookie.setMaxAge(0);
					System.out.println("< after cookie : "+ cookie.getName());
					resp.addCookie(cookie);
				}
			}
		}else{
			Cookie cookie = new Cookie("rememberMe", "Y");
			Cookie userIdCookie = new Cookie("userId",userId);
			resp.addCookie(cookie);
			resp.addCookie(userIdCookie);
		}
		UserServiceInf userService = new UserService();
		UserVo userVo= userService.selectUser(userId);
		
		System.out.println("입력받은 userId :" + userId);
		System.out.println("입력받은 password : "+ password);
		System.out.println("데이터베이스 userVo.getUserId() :" +userVo.getUserId());
		System.out.println("데이터베이스 userVo.getPass() : "+userVo.getPass());
		
		//게시판 목록 출력
		BoardServiceInf boardService = new BoardService();
		List<BoardPanVo> panListManu = boardService.panListManu();
		req.getServletContext().setAttribute("panListManu", panListManu);
		
		//오늘의 날짜
//		UtilServiceInf utilService = new UtilService();
//		Date today = utilService.today();
		SimpleDateFormat todayform= new SimpleDateFormat("yyyy-MM-dd");
		String today = todayform.format(new Date());
		System.out.println("오늘은 "+today);
		req.getSession().setAttribute("today", today);
		
		//*** 암호화 
		String encryptPass=KISA_SHA256.encrypt(password);
		if( userVo!=null && userVo.authPass(encryptPass)){
			req.getSession().setAttribute("S_USER", userVo);
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		}else{	
			resp.sendRedirect("login/login.jsp");
		}
	}
}

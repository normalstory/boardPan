package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.encript.sha.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceInf;

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

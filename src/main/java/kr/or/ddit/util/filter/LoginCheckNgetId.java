package kr.or.ddit.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.user.model.UserVo;

public class LoginCheckNgetId implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest sReq = (HttpServletRequest) request;
		String uri = sReq.getRequestURI();
		
		if(uri.equals("/") || uri.equals("/login/login.jsp") || uri.equals("/loginServlet") ) {
			chain.doFilter(request, response);
			
		}else {
			HttpSession session = sReq.getSession();
			UserVo userVo = (UserVo) session.getAttribute("S_USER");
			request.setAttribute("userVo", userVo);
			
			System.out.println("-- login filter : "+userVo);
			//System.out.println("-- login filter : "+userVo.getName());	//왜 에러지? ***
			
			if(userVo!=null) {
				chain.doFilter(request, response);
			}else {
				request.getRequestDispatcher("/").forward(request, response);
				System.out.println("로그인이 필요합니다");
			}
			
		}
	}

	@Override
	public void destroy() {
	}

}

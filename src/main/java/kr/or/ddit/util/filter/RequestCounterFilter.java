package kr.or.ddit.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

public class RequestCounterFilter implements Filter {

	Map<String,Integer> uriMap = new HashMap<String, Integer>();
	int count;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("--Filter about 페이지별 카운트 측정 of requestCounterFilter");
		
		String uri = ((HttpServletRequest) request).getRequestURI();
		if(uriMap.get(uri)!=null) {
			uriMap.put(uri, ++count);
		}else {
			uriMap.put(uri, 1);
		}
		
		request.getServletContext().setAttribute("uriMap", uriMap);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

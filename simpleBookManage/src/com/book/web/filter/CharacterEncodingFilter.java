package com.book.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		req.setCharacterEncoding("UTF-8");
		HttpServletResponse res=(HttpServletResponse)response;
		String url=req.getRequestURI();
		if(url.indexOf(".css")>0 || url.indexOf(".js")>0 || url.indexOf(".png")>0) {
			chain.doFilter(req,res);
			return;
		}
		res.setContentType("text/html;charset=UTF-8");
		chain.doFilter(request,response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
}

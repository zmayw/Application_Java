package com.book.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */

public class AuthFilter implements Filter {

	//标示符：表示当前用户未登录
	String NO_LOGIN_NO = "NO_LOGIN_NO";
	//String[]此处的url路径需要登录后，才有权访问
	String[] excludeUrls=new String[] {
		"simpleBookManage/categoryList","simpleBookManage/bookList","simpleBookManage/addCategory"
		,"simpleBookManage/addBook","simpleBookManage/updateBook"
	};
	
    /*
     * Default constructor. 
     */
    public AuthFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**过滤器
	 * 判断url数组中的url访问时是否登录 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		HttpSession session=request.getSession();
		String uri=request.getRequestURI();
		
		boolean pass=false;
		if(excludeUrls != null) {
			for(String url:excludeUrls) {
				if(uri.indexOf(url)>-1) {
					pass=true;
					break;
				}
			}
		}
		if(!pass) {
			filterChain.doFilter(servletRequest,servletResponse);
		}else {
			if(session.getAttribute("existUser") != null) {
				filterChain.doFilter(request,response);
			}else {
				String requestType=request.getHeader("X-Requested-With");
				//判断是否为ajax请求
				if(requestType!=null && "XMLHttpRequest".equals(requestType)) {
					response.getWriter().write(this.NO_LOGIN_NO);
				}else {
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				}
				
				return;
			}
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

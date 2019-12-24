package com.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.book.domain.Category;
import com.book.service.impl.CategoryServiceImpl;

@WebServlet("/getCategoryList")
public class GetCategoryListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		CategoryServiceImpl csi=new CategoryServiceImpl();
		String json=JSON.toJSONString(csi.getCategoryList());
		response.getWriter().println(json);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
	
}

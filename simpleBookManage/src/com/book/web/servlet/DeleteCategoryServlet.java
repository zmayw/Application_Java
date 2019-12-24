package com.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.impl.CategoryServiceImpl;

@WebServlet("/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//接收数据
		String id=request.getParameter("categoryId");
		//处理数据
		CategoryServiceImpl csi=new CategoryServiceImpl();
		csi.deleteCategory(id);
		//返回结果
		request.setAttribute("categoryList",csi.getCategoryList());
		request.getRequestDispatcher("/categoryList.jsp").forward(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}
}

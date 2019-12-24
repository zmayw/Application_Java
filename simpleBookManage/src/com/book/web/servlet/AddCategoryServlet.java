package com.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.impl.CategoryServiceImpl;

@WebServlet("/addCategoryServlet")
public class AddCategoryServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addCategory....");
		String id=request.getParameter("categoryId");
		String name=request.getParameter("categoryName");
		if((id==null || id=="")||(name==null ||name=="")) {
			return;
		}
		
		CategoryServiceImpl csi=new CategoryServiceImpl();
		int n=csi.addCategory(id,name);
		System.out.println("addCategory n:"+n);
		String msg;
		if(n>=0) {
			msg="添加成功!";
			request.setAttribute("msg",msg);
			request.setAttribute("categoryList",csi.getCategoryList());
			//response.getWriter().println(json);
			//request.getRequestDispatcher("/categoryList.jsp").forward(request,response);
			System.out.println(csi.getCategoryList());
			request.getRequestDispatcher("/categoryList.jsp").forward(request,response);
		}else {
			msg="ID已存在！";
			request.setAttribute("msg",msg);
			request.getRequestDispatcher("/addCategory.jsp").forward(request, response);
		}
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

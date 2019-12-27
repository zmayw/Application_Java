package com.book.web.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.CategoryServiceImpl;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//接收数据
		String id=request.getParameter("bookId");
		//处理数据
		BookServiceImpl bsi=new BookServiceImpl();
		bsi.deleteBook(id);
		//返回结果
		//request.setAttribute("bookList",bsi.getBookList());
		//request.getRequestDispatcher("/categoryList.jsp").forward(request,response);
		response.sendRedirect(request.getContextPath() + "/bookList.jsp");
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
}

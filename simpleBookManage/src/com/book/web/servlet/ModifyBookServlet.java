package com.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.book.domain.Book;
import com.book.service.impl.BookServiceImpl;

@WebServlet("/modifyBook")
public class ModifyBookServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String bookId=request.getParameter("bookId");
		BookServiceImpl bsi=new BookServiceImpl();
		Book book=bsi.getBookByCondition(bookId);
		//String json=JSON.toJSONString(book);
		//response.getWriter().println(json);
		//response.sendRedirect(request.getContextPath() + "/updateBook.jsp");
		request.setAttribute("book",book);
		request.getRequestDispatcher("/updateBook.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		doGet(request,response);
	}

}

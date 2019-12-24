package com.book.web.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.book.domain.Book;
import com.book.service.impl.BookServiceImpl;

@WebServlet("/updateBookServlet")
public class UpdateBookServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//接收数据
		String id=request.getParameter("bookId");
		String name=request.getParameter("bookName");
		String categoryId=request.getParameter("categoryId");
		float price=Float.parseFloat(request.getParameter("bookPrice"));
		String path=request.getParameter("bookPic");
		
		//封装数据
		Book bk=new Book(id,name,categoryId,price,path);
		//处理数据
		BookServiceImpl bsi=new BookServiceImpl();
		try {
			bsi.updateBook(bk);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json=JSON.toJSONString(bk);
		response.getWriter().println(json);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request,response);
	}

}

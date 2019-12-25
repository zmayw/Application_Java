package com.book.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.book.domain.Book;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.UploadUtils;

@WebServlet("/updateBookServlet")
public class UpdateBookServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		//接收数据
		String id=request.getParameter("bookId");
		String name=request.getParameter("bookName");
		String categoryId=request.getParameter("categoryId");
		float price=Float.parseFloat(request.getParameter("bookPrice"));
		String description=request.getParameter("remarks");
		String path=request.getParameter("bookPic");
		
		//封装数据
		Book bk=new Book(id,name,categoryId,price,path,description);
		//处理数据
		*/
		//接收数据
		Map<String,String> bookMap=UploadUtils.operateFileUploadRequest(request,response);
		String id = bookMap.get("bookId");
		String name = bookMap.get("bookName");
		String categoryId = bookMap.get("categoryId");
		float price=Float.parseFloat(bookMap.get("bookPrice"));
		String imgPath = bookMap.get("path");
		String description=bookMap.get("remarks");

		// 封装数据
		Book book=new Book(id,name,categoryId,price,imgPath,description);
		//处理数据
		BookServiceImpl bsi=new BookServiceImpl();
		try {
			bsi.updateBook(book);
			response.sendRedirect(request.getContextPath() + "/bookList.jsp");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request,response);
	}

}

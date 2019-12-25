package com.book.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.book.domain.Book;
import com.book.domain.User;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.CategoryServiceImpl;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.UploadUtils;

@WebServlet("/addBookServlet")
public class AddBookServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> bookMap=UploadUtils.operateFileUploadRequest(request,response);
		String id = bookMap.get("bookId");
		String name = bookMap.get("bookName");
		String categoryId = bookMap.get("categoryId");
		float price=Float.parseFloat(bookMap.get("bookPrice"));
		String imgPath = bookMap.get("path");
		String description=bookMap.get("remarks");

		// 封装数据
		Book book=new Book(id,name,categoryId,price,imgPath,description);
		// 处理数据
		BookServiceImpl bsi = new BookServiceImpl();
		int n = bsi.addBook(book);
		System.out.println("getBookList:"+bsi.getBookList());
		String msg = "";
		if (n == -1) {
			msg = "添加失败，该书籍Id已存在！";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/addBook.jsp").forward(request, response);
		} else if (n == 0) {
			//msg = "添加成功！";
			//request.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/bookList.jsp");
		//	request.setAttribute("bookList",bsi.getBookList());
		//	request.getRequestDispatcher("/bookList.jsp").forward(request,response);
		}
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

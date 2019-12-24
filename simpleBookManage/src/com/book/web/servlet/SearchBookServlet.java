package com.book.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.impl.BookServiceImpl;
import com.alibaba.fastjson.JSON;
import com.book.domain.Book;

@WebServlet("/searchBookServlet")
public class SearchBookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 接收数据
		String id = request.getParameter("searchId");
		String name = request.getParameter("searchName");
		String categoryId = request.getParameter("searchCategoryId");
		// 处理数据
		BookServiceImpl bsi = new BookServiceImpl();
		List<Book> books = bsi.getBookList();
		List<Book> resultList = new ArrayList<Book>();
		// 处理查询逻辑
		boolean r1 = true, r2 = true, r3 = true;
		System.out.println("hello:" + id + "," + name + "," + categoryId);
		for (Book book : books) {
			if (id != "" && id != null) {
				if (book.getId().equals(id)) {
					r1 = true;
				} else {
					r1 = false;
					continue;
				}
			}

			if (name != "" && name != null) {
				if (book.getName().equals(name)) {
					r2 = true;
				} else {
					r2 = false;
					continue;
				}
			}

			if (categoryId != "" && categoryId != null) {
				System.out.println("bookCateogry");
				System.out.println("categoryId:" + categoryId + ",getCategoryId:" + book.getCategoryId());
				if (book.getCategoryId().equals(categoryId)) {
					r3 = true;
				} else {
					r3 = false;
					continue;
				}
			}

			System.out.println("r1:" + r1 + ",r2:" + r2 + ",r3" + r3);
			System.out.println("id:" + book.getId() + ",name:" + book.getName() + ",category:" + book.getCategoryId());
			if (r1 && r2 && r3) {
				resultList.add(book);
			}
		}
		//返回结果
		String json=JSON.toJSONString(resultList);
		request.setAttribute("bookList",resultList);
		request.getRequestDispatcher("/bookList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}

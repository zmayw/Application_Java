package com.mess.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.MessageCategory;
import com.mess.service.MessageCategoryService;
import com.mess.service.Impl.MessageCategoryServiceImpl;

@WebServlet("/categoryServlet")
public class CategoryServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List<MessageCategory> mcList=new ArrayList<MessageCategory>();
		MessageCategoryService mcs=new MessageCategoryServiceImpl();
		mcList=mcs.getCategoryList();
		request.setAttribute("mcList",mcList);
		System.out.println("mcList:"+mcList);
		request.getRequestDispatcher("/messageCategoryList.jsp").forward(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

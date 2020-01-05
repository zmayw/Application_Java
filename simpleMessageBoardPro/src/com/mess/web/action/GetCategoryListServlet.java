package com.mess.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.mess.domain.MessageCategory;
import com.mess.service.MessageCategoryService;
import com.mess.service.Impl.MessageCategoryServiceImpl;

@WebServlet("/getCategoryList")
public class GetCategoryListServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List<MessageCategory> mcList=new ArrayList<MessageCategory>();
		MessageCategoryService mcs=new MessageCategoryServiceImpl();
		mcList=mcs.getCategoryList();
		System.out.println("mcList....:"+mcList);
		String json=JSON.toJSONString(mcList);
		response.getWriter().println(json);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

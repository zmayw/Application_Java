package com.book.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.book.utils.UploadUtils;

@WebServlet("/updateImg")
public class UpdateImgServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,String> file=UploadUtils.operateFileUploadRequest(request,response);
		String json=JSON.toJSONString(file);
		response.getWriter().println(json);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doGet(request,response);
	}

}

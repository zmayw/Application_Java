	package com.book.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.book.utils.UploadUtils;

@WebServlet("/imgTest")
public class ImgPreviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,String> umap=UploadUtils.operateFileUploadRequest(request,response);

		String filePath = umap.get("path");

		System.out.println(filePath);

		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
}

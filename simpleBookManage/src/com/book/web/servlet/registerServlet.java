package com.book.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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

import com.book.domain.User;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.UploadUtils;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		// 接收数据
		Map<String,String> userMap=new HashMap<String,String>();
		// 1.创建磁盘文件项工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2.创建核心解析
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		// 3。解析请求对象，将请求分成几个部分（FileItem）
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			// 4.遍历集合获得每个部分的对象
			for (FileItem fileItem : list) {
				// 判断是普通 项还是文件上传项
				if (fileItem.isFormField()) {
					// 获得普通项的名称
					String name = fileItem.getFieldName();
					// 获得普通项的值
					String value = fileItem.getString("UTF-8");
					userMap.put(name,value);
				} else {
					// 文件上传项
					// 获得文件的名称
					String fileName = fileItem.getName();
					//获得唯一文件名
					String uuidFileName=UploadUtils.getUuidFileName(fileName);
					// 获得文件的输入流
					InputStream is = fileItem.getInputStream();
					// 需要将文件写入到服务器的某个路径下
					userMap.put("path",request.getContextPath()+"/upload"+uuidFileName);
					// 创建输出流与输入流进行对接
					String url=request.getServletContext().getRealPath("/upload")+"\\"+uuidFileName;
					OutputStream os = new FileOutputStream(url);
					int len = 0;
					byte[] b = new byte[1024];
					while ((len = is.read(b)) != -1) {
						os.write(b, 0, len);
					}
					is.close();
					os.close();
				}
			}
		} catch (FileUploadException e) {
			System.out.println("catch....." + e);
		}
*/
		Map<String,String> userMap=UploadUtils.operateFileUploadRequest(request,response);
		String name = userMap.get("usernameR");
		String password = userMap.get("passwordR");
		String email = userMap.get("emailR");
		String filePath = userMap.get("path");

		// 封装数据
		User user = new User(name, password, email,filePath);
		// 处理数据
		UserServiceImpl usi = new UserServiceImpl();
		int n = usi.regist(user);
		System.out.println(usi.getUsers());
		String msg = "";
		if (n == -1) {
			msg = "注册失败，该用户已存在！";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else if (n == 0) {
			msg = "注册成功！";
			request.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}

package com.mess.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.User;
import com.mess.service.Impl.UserServiceImpl;
import com.mess.utils.UploadUtils;


@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> userMap=UploadUtils.operateFileUploadRequest(request,response);
		System.out.println("userMap"+userMap);
		String name = userMap.get("usernameR");
		String password = userMap.get("passwordR");
		String email = userMap.get("emailR");
		String filePath = userMap.get("path");
		String sex=userMap.get("sexR");

		// 封装数据
		User user = new User(name, password, email,sex,filePath);
		// 处理数据
		UserServiceImpl usi = new UserServiceImpl();
		String msg="";
		if(usi.isExistUser(name)) {
			msg="当前用户名已存在!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			usi.regist(user);
			msg="注册成功";
			request.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
}

package com.mess.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.User;
import com.mess.service.UserService;
import com.mess.service.Impl.UserServiceImpl;
import com.mess.utils.UploadUtils;


@WebServlet("/userServlet")
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			//接收数据
			Map<String,String> userMap=UploadUtils.operateFileUploadRequest(request,response);
			String name=userMap.get("name");
			String password=userMap.get("password");
			String email=userMap.get("email");
			String filePath=userMap.get("userPhoto");
			String sex=userMap.get("sex");
			int id=Integer.parseInt(userMap.get("id"));
			String hiddenText=userMap.get("hiddenText");
			System.out.println("hiddenText=:"+hiddenText+" filePath:"+filePath);
			//前端页面未编辑上传图片的情况，将原地址写到隐藏控件中，提交过来
			if(filePath=="" || filePath==null) {
				filePath=hiddenText;
			}
			System.out.println("hiddenText=:"+hiddenText+"filePath:"+filePath);
			//封装数据
			User user=new User();
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email);
			user.setFilePath(filePath);
			user.setSex(sex);
			user.setId(id);
			System.out.println("user info...:"+user);
			//处理数据
			UserService usi=new UserServiceImpl();
			usi.updateUser(user);
			User userNew=usi.getUser(user.getId());
			request.getSession().setAttribute("existUser",userNew);
			response.sendRedirect(request.getContextPath() + "/showUser.jsp");
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}

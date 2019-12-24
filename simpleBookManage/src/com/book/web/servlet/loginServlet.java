package com.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.impl.UserServiceImpl;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收数据
		//完成验证码的校验：
		String codeBack=(String)request.getSession().getAttribute("checkCode");
		String codeFront=request.getParameter("verifyCode");
		if(!codeFront.equalsIgnoreCase(codeBack)) {
			request.setAttribute("resultMsg","验证码输入不正确！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		String name = request.getParameter("usernameL");
		String password = request.getParameter("passwordL");
		// 处理数据
		UserServiceImpl usi = new UserServiceImpl();
		int n = usi.login(name, password);
		String msg = "";
		if (n >= 0) {
			// 登录成功
			msg = "登录成功！";
			request.setAttribute("resultMsg", msg);
			request.getSession().setAttribute("existUser", usi.getUsers().get(n));
			response.sendRedirect(request.getContextPath()+"/categoryList.jsp");
		} else {
			if (n == -1) {
				// 登录失败，用户名不存在
				msg = "登录失败，用户名不存在!";
			} else if (n == -2) {
				// 登录失败，密码不正确
				msg = "登录失败，密码不正确!";
			}
			request.setAttribute("resultMsg", msg);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}

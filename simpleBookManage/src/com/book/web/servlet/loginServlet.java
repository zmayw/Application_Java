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
		// ��������
		//�����֤���У�飺
		String codeBack=(String)request.getSession().getAttribute("checkCode");
		String codeFront=request.getParameter("verifyCode");
		if(!codeFront.equalsIgnoreCase(codeBack)) {
			request.setAttribute("resultMsg","��֤�����벻��ȷ��");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		String name = request.getParameter("usernameL");
		String password = request.getParameter("passwordL");
		// ��������
		UserServiceImpl usi = new UserServiceImpl();
		int n = usi.login(name, password);
		String msg = "";
		if (n >= 0) {
			// ��¼�ɹ�
			msg = "��¼�ɹ���";
			request.setAttribute("resultMsg", msg);
			request.getSession().setAttribute("existUser", usi.getUsers().get(n));
			response.sendRedirect(request.getContextPath()+"/categoryList.jsp");
		} else {
			if (n == -1) {
				// ��¼ʧ�ܣ��û���������
				msg = "��¼ʧ�ܣ��û���������!";
			} else if (n == -2) {
				// ��¼ʧ�ܣ����벻��ȷ
				msg = "��¼ʧ�ܣ����벻��ȷ!";
			}
			request.setAttribute("resultMsg", msg);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}

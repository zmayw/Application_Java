package com.mess.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.domain.User;
import com.mess.service.Impl.UserServiceImpl;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
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
		String msg="";
		if(usi.isExistUser(name)) {
			User user = usi.login(name, password);
			if (user != null) {
				// ��¼�ɹ�
				msg = "��¼�ɹ���";
				request.setAttribute("msg", msg);
				request.getSession().setAttribute("existUser", user);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			} else {	
				msg = "��¼ʧ�ܣ����벻��ȷ!";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}else {
			msg = "��¼ʧ�ܣ��û���������!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		doGet(request,response);
		
	}

}

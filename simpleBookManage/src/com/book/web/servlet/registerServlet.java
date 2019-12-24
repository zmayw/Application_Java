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
		// ��������
		Map<String,String> userMap=new HashMap<String,String>();
		// 1.���������ļ����
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2.�������Ľ���
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		// 3������������󣬽�����ֳɼ������֣�FileItem��
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			// 4.�������ϻ��ÿ�����ֵĶ���
			for (FileItem fileItem : list) {
				// �ж�����ͨ ����ļ��ϴ���
				if (fileItem.isFormField()) {
					// �����ͨ�������
					String name = fileItem.getFieldName();
					// �����ͨ���ֵ
					String value = fileItem.getString("UTF-8");
					userMap.put(name,value);
				} else {
					// �ļ��ϴ���
					// ����ļ�������
					String fileName = fileItem.getName();
					//���Ψһ�ļ���
					String uuidFileName=UploadUtils.getUuidFileName(fileName);
					// ����ļ���������
					InputStream is = fileItem.getInputStream();
					// ��Ҫ���ļ�д�뵽��������ĳ��·����
					userMap.put("path",request.getContextPath()+"/upload"+uuidFileName);
					// ��������������������жԽ�
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

		// ��װ����
		User user = new User(name, password, email,filePath);
		// ��������
		UserServiceImpl usi = new UserServiceImpl();
		int n = usi.regist(user);
		System.out.println(usi.getUsers());
		String msg = "";
		if (n == -1) {
			msg = "ע��ʧ�ܣ����û��Ѵ��ڣ�";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else if (n == 0) {
			msg = "ע��ɹ���";
			request.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}

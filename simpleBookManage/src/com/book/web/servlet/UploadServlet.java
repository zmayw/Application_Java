package com.book.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("lololo");
		
		// 1.���������ļ����
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2.�������Ľ���
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		// 3������������󣬽�����ֳɼ������֣�FileItem��
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			List<HashMap> parameters = new ArrayList<HashMap>();
			// 4.�������ϻ��ÿ�����ֵĶ���
			for (FileItem fileItem : list) {
				// �ж�����ͨ ����ļ��ϴ���
				if (fileItem.isFormField()) {
					// �����ͨ�������
					String name = fileItem.getFieldName();
					// �����ͨ���ֵ
					String value = fileItem.getString("UTF-8");
					parameters.add(new HashMap() {
						{
							put(name, value);
						}
					});
					
					System.out.println(name+" , "+value);
				} else {
					// �ļ��ϴ���
					// ����ļ�������
					String fileName = fileItem.getName();
					// ����ļ���������
					InputStream is = fileItem.getInputStream();
					// ��Ҫ���ļ�д�뵽��������ĳ��·����
					String path = getServletContext().getRealPath("/upload");
					System.out.println(path);
					// ��������������������жԽ�
					OutputStream os = new FileOutputStream(path + "\\" + fileName);
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
			System.out.println("catch....."+e);
		}

	}


}

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
		
		// 1.创建磁盘文件项工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2.创建核心解析
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		// 3。解析请求对象，将请求分成几个部分（FileItem）
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			List<HashMap> parameters = new ArrayList<HashMap>();
			// 4.遍历集合获得每个部分的对象
			for (FileItem fileItem : list) {
				// 判断是普通 项还是文件上传项
				if (fileItem.isFormField()) {
					// 获得普通项的名称
					String name = fileItem.getFieldName();
					// 获得普通项的值
					String value = fileItem.getString("UTF-8");
					parameters.add(new HashMap() {
						{
							put(name, value);
						}
					});
					
					System.out.println(name+" , "+value);
				} else {
					// 文件上传项
					// 获得文件的名称
					String fileName = fileItem.getName();
					// 获得文件的输入流
					InputStream is = fileItem.getInputStream();
					// 需要将文件写入到服务器的某个路径下
					String path = getServletContext().getRealPath("/upload");
					System.out.println(path);
					// 创建输出流与输入流进行对接
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

package com.book.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadUtils {
	
	public static String getUuidFileName(String fileName) {
		//解决文件重名的问题
		int idx=fileName.lastIndexOf(".");
		String exName=fileName.substring(idx);
		//生成随机字符串
		String uuidFileName=UUID.randomUUID().toString().replace("-","")+exName;
		return uuidFileName;
		
	}
	
	public static Map<String,String> operateFileUploadRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,String> objectMap=new HashMap<String,String>();
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
					objectMap.put(name,value);
				} else {
					
					long startTime=System.currentTimeMillis();
					// 文件上传项
					// 获得文件的名称
					String fileName = fileItem.getName();
					//获得唯一文件名
					String uuidFileName=UploadUtils.getUuidFileName(fileName);
					// 获得文件的输入流
					InputStream is = fileItem.getInputStream();
					BufferedInputStream bis=new BufferedInputStream(is);
					// 需要将文件写入到服务器的某个路径下
					System.out.println("request.getContextPath():"+request.getContextPath());
					objectMap.put("path",request.getContextPath()+"/upload/"+uuidFileName);
					
					String url=request.getServletContext().getRealPath("/upload")+"\\"+uuidFileName;
					System.out.println("url:"+request.getServletContext().getRealPath("/upload"+"\\"+uuidFileName));
					// 创建输出流与输入流进行对接
					OutputStream os = new FileOutputStream(url);
					BufferedOutputStream bos=new BufferedOutputStream(os);
					int len = 0;
					byte[] b = new byte[1024];
					while ((len = bis.read(b)) != -1) {
						os.write(b, 0, len);
					}
					long endTime=System.currentTimeMillis();
					System.out.println(endTime-startTime);
					bos.flush();
					bos.close();
					bis.close();
					is.close();
					os.close();
				}
			}
		} catch (FileUploadException e) {
			System.out.println("catch....." + e);
		}
		
		return objectMap;
	}
	
	
	public static Map<String,String> getFileUploadRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,String> objectMap=new HashMap<String,String>();
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
				if (!fileItem.isFormField()) {
					// 文件上传项
					// 获得文件的名称
					String fileName = fileItem.getName();
					//获得唯一文件名
					String uuidFileName=UploadUtils.getUuidFileName(fileName);
					// 获得文件的输入流
					InputStream is = fileItem.getInputStream();
					// 需要将文件写入到服务器的某个路径下
					System.out.println("request.getContextPath():"+request.getContextPath());
					objectMap.put("path",request.getContextPath()+"/upload/"+uuidFileName);
					// 创建输出流与输入流进行对接
					String url=request.getServletContext().getRealPath("/upload")+"\\"+uuidFileName;
					System.out.println("url:"+request.getServletContext().getRealPath("/upload"+"\\"+uuidFileName));
					OutputStream os = new FileOutputStream(url);
					is.close();
					os.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		return objectMap;
	}
	
	public static void main(String args[]) {
		System.out.println(getUuidFileName("a.jpg"));
	}

}

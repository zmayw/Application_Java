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
		//����ļ�����������
		int idx=fileName.lastIndexOf(".");
		String exName=fileName.substring(idx);
		//��������ַ���
		String uuidFileName=UUID.randomUUID().toString().replace("-","")+exName;
		return uuidFileName;
		
	}
	
	public static Map<String,String> operateFileUploadRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,String> objectMap=new HashMap<String,String>();
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
					objectMap.put(name,value);
				} else {
					
					long startTime=System.currentTimeMillis();
					// �ļ��ϴ���
					// ����ļ�������
					String fileName = fileItem.getName();
					//���Ψһ�ļ���
					String uuidFileName=UploadUtils.getUuidFileName(fileName);
					// ����ļ���������
					InputStream is = fileItem.getInputStream();
					BufferedInputStream bis=new BufferedInputStream(is);
					// ��Ҫ���ļ�д�뵽��������ĳ��·����
					System.out.println("request.getContextPath():"+request.getContextPath());
					objectMap.put("path",request.getContextPath()+"/upload/"+uuidFileName);
					
					String url=request.getServletContext().getRealPath("/upload")+"\\"+uuidFileName;
					System.out.println("url:"+request.getServletContext().getRealPath("/upload"+"\\"+uuidFileName));
					// ��������������������жԽ�
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
				if (!fileItem.isFormField()) {
					// �ļ��ϴ���
					// ����ļ�������
					String fileName = fileItem.getName();
					//���Ψһ�ļ���
					String uuidFileName=UploadUtils.getUuidFileName(fileName);
					// ����ļ���������
					InputStream is = fileItem.getInputStream();
					// ��Ҫ���ļ�д�뵽��������ĳ��·����
					System.out.println("request.getContextPath():"+request.getContextPath());
					objectMap.put("path",request.getContextPath()+"/upload/"+uuidFileName);
					// ��������������������жԽ�
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

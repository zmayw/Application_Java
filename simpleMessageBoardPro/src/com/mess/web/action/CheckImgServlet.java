package com.mess.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkImg")
public class CheckImgServlet extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int width=120;
		int height=30;
		//����һ�����ڴ�������һ��ͼƬ
		BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics graphics=bufferedImage.getGraphics();
		//���ñ���ɫ
		graphics.setColor(getRandColor(200,250));
		graphics.fillRect(0,0,width,height);
		//�������������ͼƬ�����ñ���ɫ�����Ʊ߿�
		graphics.setColor(Color.BLUE);
		graphics.drawRect(0,0,width-1,height-1);
		//������������������ĸ���ĸ�����֣�д�뵽ͼƬ��
		Graphics2D g2d=(Graphics2D)graphics;
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("����",Font.BOLD,18));
		String words="ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random=new Random();
		//����һ���ַ��������ڱ���������ɵ��ĸ��ַ�
		StringBuffer sb=new StringBuffer();
		int x=10;
		for(int i=0;i<4;i++) {
			int idx=random.nextInt(words.length());
			//���ָ��λ�õ��ַ�
			char ch=words.charAt(idx);
			//���������ַ�����StringBuffer��
			sb.append(ch);
			//������ת�Ƕ� -30��+30֮��
			int jiaodu=random.nextInt(60)-30;
			//���Ƕ�ת���ɻ���
			double theta=jiaodu*Math.PI/180;
			g2d.rotate(theta,x,20);
			g2d.drawString(String.valueOf(ch),x,20);
			//���ȸ�ԭ������ȷ������һ���ַ�
			g2d.rotate(-theta,x,20);
			x+=30;
		}
		//���������ַ������뵽session��
		request.getSession().setAttribute("checkCode",sb.toString());
		//�����壺���Ƹ�����
		graphics.setColor(getRandColor(160,200));
		int x1;
		int x2;
		int y1;
		int y2;
		for(int i=0;i<30;i++) {
			x1=random.nextInt(width);
			x2=random.nextInt(12);
			y1=random.nextInt(height);
			y2=random.nextInt(12);
			graphics.drawLine(x1,y1,x1+x2,x2+y2);
		}
		
		//���һ�������ڴ��е�ͼƬ���������
		graphics.dispose();//�ͷ���Դ
		ImageIO.write(bufferedImage,"jpg",response.getOutputStream());
	}

	private Color getRandColor(int fc,int bc) {
		Random random=new Random();
		if(fc>255) {
			fc=255;
		}
		if(bc>255) {
			bc=255;
		}
		int r = fc+random.nextInt(bc-fc);
		int g = fc+random.nextInt(bc-fc);
		int b = fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
}
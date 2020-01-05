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
		//步骤一：在内存中生成一张图片
		BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics graphics=bufferedImage.getGraphics();
		//设置背景色
		graphics.setColor(getRandColor(200,250));
		graphics.fillRect(0,0,width,height);
		//步骤二：操作该图片，设置背景色及绘制边框
		graphics.setColor(Color.BLUE);
		graphics.drawRect(0,0,width-1,height-1);
		//步骤三：生成随机的四个字母或数字，写入到图片中
		Graphics2D g2d=(Graphics2D)graphics;
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("宋体",Font.BOLD,18));
		String words="ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random=new Random();
		//定义一个字符串，用于保存随机生成的四个字符
		StringBuffer sb=new StringBuffer();
		int x=10;
		for(int i=0;i<4;i++) {
			int idx=random.nextInt(words.length());
			//获得指定位置的字符
			char ch=words.charAt(idx);
			//将产生的字符存入StringBuffer中
			sb.append(ch);
			//设置旋转角度 -30至+30之间
			int jiaodu=random.nextInt(60)-30;
			//将角度转换成弧度
			double theta=jiaodu*Math.PI/180;
			g2d.rotate(theta,x,20);
			g2d.drawString(String.valueOf(ch),x,20);
			//弧度复原，以正确绘制下一个字符
			g2d.rotate(-theta,x,20);
			x+=30;
		}
		//将产生的字符串存入到session中
		request.getSession().setAttribute("checkCode",sb.toString());
		//步骤五：绘制干扰线
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
		
		//最后一步：将内存中的图片，进行输出
		graphics.dispose();//释放资源
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
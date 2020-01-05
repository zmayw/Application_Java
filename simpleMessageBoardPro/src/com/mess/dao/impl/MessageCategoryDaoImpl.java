package com.mess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mess.dao.MessageCategoryDao;
import com.mess.domain.MessageCategory;
import com.mess.utils.JDBCUtils;

public class MessageCategoryDaoImpl implements MessageCategoryDao {

	@Override
	public void addCategory(MessageCategory mc) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			String sql="insert into messageCategory values(null,?,?)";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setString(1,mc.getName());
			pstmt.setString(2,mc.getDesc());
		//执行
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(pstmt,conn);
		}
		
	}

	@Override
	public List<MessageCategory> getCategoryList() {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MessageCategory> mcList=null;
		try {
		//获得连接
			System.out.println("before conn");
			conn=JDBCUtils.getConnection();
			System.out.println("conn"+conn);
		//编写SQL
			String sql="select * from messageCategory";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数

		//执行
			mcList=new ArrayList<MessageCategory>();
			rs=pstmt.executeQuery();
			System.out.println("lalaala");
			while(rs.next()) {
				MessageCategory mc=new MessageCategory();
				mc.setCid(rs.getInt("cid"));
				mc.setName(rs.getString("cateName"));
				mc.setDesc(rs.getString("cateDesc"));
				mcList.add(mc);
				System.out.println(rs.getString("cateName"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs,pstmt,conn);
		}
		return mcList;
	}



}

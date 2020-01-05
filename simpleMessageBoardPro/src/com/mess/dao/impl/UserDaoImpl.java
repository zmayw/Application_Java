package com.mess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mess.dao.UserDao;
import com.mess.domain.User;
import com.mess.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String username,String password) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		//�������
			System.out.println("before conn");
			conn=JDBCUtils.getConnection();
			System.out.println("conn"+conn);
		//��дSQL
			String sql="select * from user where username=? and password=?";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setString(1,username);
			pstmt.setString(2,password);
		//ִ��
			rs=pstmt.executeQuery();
			if(rs.next()) {
				User existUser=new User();
				existUser.setId(rs.getInt("id"));
				existUser.setName(rs.getString("username"));
				existUser.setPassword(rs.getString("password"));
				existUser.setEmail(rs.getString("email"));
				existUser.setFilePath(rs.getString("filePath"));
				existUser.setSex(rs.getString("sex"));
				return existUser;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs,pstmt,conn);
		}
		return null;
	}

	
	public boolean isExistUser(String username) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
		//�������
			System.out.println("before conn");
			conn=JDBCUtils.getConnection();
			System.out.println("conn"+conn);
		//��дSQL
			String sql="select * from user where username=?";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setString(1,username);
		//ִ��
			rs=pstmt.executeQuery();
			if(rs.next()) {
				flag=true;
			}else {
				flag=false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs,pstmt,conn);
		}
		return flag;
		
	}


	@Override
	public void register(User user) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			String sql="insert into user values(null,?,?,?,?,?)";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			System.out.println("pstmt"+pstmt);
			pstmt.setString(1,user.getName());
			pstmt.setString(2,user.getPassword());
			pstmt.setString(3,user.getSex());
			pstmt.setString(4,user.getFilePath());
			pstmt.setString(5,user.getEmail());
			
		//ִ��
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(pstmt,conn);
		}
	}

}

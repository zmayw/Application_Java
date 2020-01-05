package com.mess.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * JDBC工具类
 *
 *
 */
public class JDBCUtils {
	
	public static final ComboPooledDataSource dataSource=new ComboPooledDataSource();
	
	/**
	 * 获得连接
	 * @return
	 * @throws Exception
	 */
	public static java.sql.Connection getConnection() throws Exception{
		return dataSource.getConnection();
	}
	
	/**
	 * 获得连接池
	 * @return
	 */
	public static DataSource getDataSource() {
		
		return dataSource;
	}
	
	/**
	 * 释放资源
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	public static void release(java.sql.ResultSet rs,PreparedStatement pstmt, java.sql.Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			pstmt=null;
		}
		if(conn!=null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
	
	public static void release(PreparedStatement pstmt,java.sql.Connection conn) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			pstmt=null;
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				conn=null;
			}
		}
	}

	public static void release(Statement stms,Connection conn) {
		if(stms!=null) {
			try {
				stms.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			stms=null;
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				conn=null;
			}
		}
	}

}

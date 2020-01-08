package com.mess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mess.dao.MessageDao;
import com.mess.domain.Message;
import com.mess.utils.JDBCUtils;

public class MessageDaoImpl implements MessageDao {

	@Override
	public List<Message> getMessageList() {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Message> msList=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			String sql="select * from message as m, messageCategory as mc,user as u where m.cateId=mc.cid and m.userId=u.id ";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
		//执行
			rs=pstmt.executeQuery();
			msList=new ArrayList<Message>();
			while(rs.next()) {
				Message ms=new Message();
				ms.setMid(rs.getInt("mid"));
				ms.setUserid(rs.getInt("userId"));
				ms.setCateId(rs.getInt("cateId"));
				ms.setTitle(rs.getString("title"));
				ms.setContent(rs.getString("content"));
				ms.setCreateTime(rs.getTimestamp("createTime"));
				ms.setModifyTime(rs.getTimestamp("modifyTime"));
				
				//封装信息分类
				ms.getMessageCategory().setCid(rs.getInt("cid"));
				ms.getMessageCategory().setName(rs.getString("cateName"));
				ms.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//封装用户信息
				ms.getUser().setId(rs.getInt("userId"));
				ms.getUser().setName(rs.getString("username"));
				
				msList.add(ms);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return msList;
	}

	@Override
	public void addMessage(Message mess) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			String sql="insert into message values(null,?,?,?,?,?,?)";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setInt(1,mess.getUserid());
			pstmt.setInt(2,mess.getCateId());
			pstmt.setString(3,mess.getTitle());
			pstmt.setString(4,mess.getContent());
			pstmt.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
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
	public int findRowsCount() {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Long count=0l;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			String sql="select count(*) as rowsCount from message";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
		//执行
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//PageBean<Message> pageBean=new PageBean<Message>();
				count=rs.getLong("rowsCount");
				//pageBean.setPagesCount(count.intValue());				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return count.intValue();
	}

	@Override
	public int findRowsCount(int userId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Long count=0l;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			String sql="select count(*) as rowsCount from message where userId=?";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setInt(1,userId);
		//执行
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//PageBean<Message> pageBean=new PageBean<Message>();
				count=rs.getLong("rowsCount");
				//pageBean.setPagesCount(count.intValue());				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return count.intValue();
	}
	
	@Override
	public List<Message> getRowsList(int begin, int limitNum) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Message> messageList=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			//String sql="select * from message limit ?,?";
			String sql="select * from message as m left join messageCategory as mc on m.cateId=mc.cid"
					+ " left join user as u on m.userId=u.id "
					+ "limit ?,?";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setInt(1,begin);
			pstmt.setInt(2,limitNum);
		//执行
			rs=pstmt.executeQuery();
			messageList=new ArrayList<Message>();
			while(rs.next()) {
				Message mess=new Message();
				mess.setMid(rs.getInt("mid"));
				mess.setUserid(rs.getInt("userId"));
				mess.setCateId(rs.getInt("cateId"));
				mess.setTitle(rs.getString("title"));
				mess.setContent(rs.getString("content"));
				mess.setCreateTime(rs.getTimestamp("createTime"));
				mess.setModifyTime(rs.getTimestamp("modifyTime"));
				
				//封装信息分类
				mess.getMessageCategory().setCid(rs.getInt("cid"));
				mess.getMessageCategory().setName(rs.getString("cateName"));
				mess.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//封装用户信息
				mess.getUser().setId(rs.getInt("userId"));
				mess.getUser().setName(rs.getString("username"));
				
				messageList.add(mess);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return messageList;
	}

	@Override
	public List<Message> getMessageList(int userId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Message> messageList=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			//String sql="select * from message limit ?,?";
			String sql="select * from message as m left join messageCategory as mc on m.cateId=mc.cid"
					+ " left join user as u on m.userId=u.id "
					+ "where m.userId=?";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setInt(1,userId);
		//执行
			rs=pstmt.executeQuery();
			messageList=new ArrayList<Message>();
			while(rs.next()) {
				Message mess=new Message();
				mess.setMid(rs.getInt("mid"));
				mess.setUserid(rs.getInt("userId"));
				mess.setCateId(rs.getInt("cateId"));
				mess.setTitle(rs.getString("title"));
				mess.setContent(rs.getString("content"));
				mess.setCreateTime(rs.getTimestamp("createTime"));
				mess.setModifyTime(rs.getTimestamp("modifyTime"));
				
				//封装信息分类
				mess.getMessageCategory().setCid(rs.getInt("cid"));
				mess.getMessageCategory().setName(rs.getString("cateName"));
				mess.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//封装用户信息
				mess.getUser().setId(rs.getInt("userId"));
				mess.getUser().setName(rs.getString("username"));
				
				messageList.add(mess);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return messageList;
	}

	@Override
	public List<Message> getRowsList(int begin, int limit, int userId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Message> messageList=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			//String sql="select * from message limit ?,?";
			String sql="select * from message as m left join messageCategory as mc on m.cateId=mc.cid"
					+ " left join user as u on m.userId=u.id "
					+ "where m.userId=? order by m.mid limit ?,?";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setInt(1,userId);
			pstmt.setInt(2,begin);
			pstmt.setInt(3,limit);
		//执行
			rs=pstmt.executeQuery();
			messageList=new ArrayList<Message>();
			while(rs.next()) {
				Message mess=new Message();
				mess.setMid(rs.getInt("mid"));
				mess.setUserid(rs.getInt("userId"));
				mess.setCateId(rs.getInt("cateId"));
				mess.setTitle(rs.getString("title"));
				mess.setContent(rs.getString("content"));
				mess.setCreateTime(rs.getTimestamp("createTime"));
				mess.setModifyTime(rs.getTimestamp("modifyTime"));
				
				//封装信息分类
				mess.getMessageCategory().setCid(rs.getInt("cid"));
				mess.getMessageCategory().setName(rs.getString("cateName"));
				mess.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//封装用户信息
				mess.getUser().setId(rs.getInt("userId"));
				mess.getUser().setName(rs.getString("username"));
				
				messageList.add(mess);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return messageList;
	}

	@Override
	public Message getMessage(int mid) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Message ms=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			String sql="select * from message as m, messageCategory as mc,user as u where m.cateId=mc.cid and m.mid=? ";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setInt(1,mid);
		//执行
			rs=pstmt.executeQuery();
			ms=new Message();
			if(rs.next()) {
				ms.setMid(rs.getInt("mid"));
				ms.setUserid(rs.getInt("userId"));
				ms.setCateId(rs.getInt("cateId"));
				ms.setTitle(rs.getString("title"));
				ms.setContent(rs.getString("content"));
				ms.setCreateTime(rs.getTimestamp("createTime"));
				ms.setModifyTime(rs.getTimestamp("modifyTime"));
				
				//封装信息分类
				ms.getMessageCategory().setCid(rs.getInt("cid"));
				ms.getMessageCategory().setName(rs.getString("cateName"));
				ms.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//封装用户信息
				ms.getUser().setId(rs.getInt("userId"));
				ms.getUser().setName(rs.getString("username"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return ms;
	}

	@Override
	public void updateMessage(int mid, String title, String content,int userId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			String sql="update message set title=?, content=? ,modifyTime=? where mid=? and userId=?";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(4,mid);
			pstmt.setInt(5,userId);
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
	public void deleteMessage(int mid, int userId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
		//获得连接
			conn=JDBCUtils.getConnection();
		//编写SQL
			String sql="delete from message where mid=? and userId=?";
		//预编译SQL
			pstmt=conn.prepareStatement(sql);
		//设置参数
			pstmt.setInt(1,mid);
			pstmt.setInt(2,userId);
		//执行
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(pstmt,conn);
		}
		
	}

	
	
}

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
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			String sql="select * from message as m, messageCategory as mc,user as u where m.cateId=mc.cid and m.userId=u.id ";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
		//ִ��
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
				
				//��װ��Ϣ����
				ms.getMessageCategory().setCid(rs.getInt("cid"));
				ms.getMessageCategory().setName(rs.getString("cateName"));
				ms.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//��װ�û���Ϣ
				ms.getUser().setId(rs.getInt("userId"));
				ms.getUser().setName(rs.getString("username"));
				
				msList.add(ms);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return msList;
	}

	@Override
	public void addMessage(Message mess) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			String sql="insert into message values(null,?,?,?,?,?,?)";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setInt(1,mess.getUserid());
			pstmt.setInt(2,mess.getCateId());
			pstmt.setString(3,mess.getTitle());
			pstmt.setString(4,mess.getContent());
			pstmt.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
		//ִ��
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
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
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			String sql="select count(*) as rowsCount from message";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
		//ִ��
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//PageBean<Message> pageBean=new PageBean<Message>();
				count=rs.getLong("rowsCount");
				//pageBean.setPagesCount(count.intValue());				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
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
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			String sql="select count(*) as rowsCount from message where userId=?";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setInt(1,userId);
		//ִ��
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//PageBean<Message> pageBean=new PageBean<Message>();
				count=rs.getLong("rowsCount");
				//pageBean.setPagesCount(count.intValue());				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
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
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			//String sql="select * from message limit ?,?";
			String sql="select * from message as m left join messageCategory as mc on m.cateId=mc.cid"
					+ " left join user as u on m.userId=u.id "
					+ "limit ?,?";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setInt(1,begin);
			pstmt.setInt(2,limitNum);
		//ִ��
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
				
				//��װ��Ϣ����
				mess.getMessageCategory().setCid(rs.getInt("cid"));
				mess.getMessageCategory().setName(rs.getString("cateName"));
				mess.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//��װ�û���Ϣ
				mess.getUser().setId(rs.getInt("userId"));
				mess.getUser().setName(rs.getString("username"));
				
				messageList.add(mess);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
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
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			//String sql="select * from message limit ?,?";
			String sql="select * from message as m left join messageCategory as mc on m.cateId=mc.cid"
					+ " left join user as u on m.userId=u.id "
					+ "where m.userId=?";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setInt(1,userId);
		//ִ��
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
				
				//��װ��Ϣ����
				mess.getMessageCategory().setCid(rs.getInt("cid"));
				mess.getMessageCategory().setName(rs.getString("cateName"));
				mess.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//��װ�û���Ϣ
				mess.getUser().setId(rs.getInt("userId"));
				mess.getUser().setName(rs.getString("username"));
				
				messageList.add(mess);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
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
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			//String sql="select * from message limit ?,?";
			String sql="select * from message as m left join messageCategory as mc on m.cateId=mc.cid"
					+ " left join user as u on m.userId=u.id "
					+ "where m.userId=? order by m.mid limit ?,?";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setInt(1,userId);
			pstmt.setInt(2,begin);
			pstmt.setInt(3,limit);
		//ִ��
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
				
				//��װ��Ϣ����
				mess.getMessageCategory().setCid(rs.getInt("cid"));
				mess.getMessageCategory().setName(rs.getString("cateName"));
				mess.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//��װ�û���Ϣ
				mess.getUser().setId(rs.getInt("userId"));
				mess.getUser().setName(rs.getString("username"));
				
				messageList.add(mess);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
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
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			String sql="select * from message as m, messageCategory as mc,user as u where m.cateId=mc.cid and m.mid=? ";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setInt(1,mid);
		//ִ��
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
				
				//��װ��Ϣ����
				ms.getMessageCategory().setCid(rs.getInt("cid"));
				ms.getMessageCategory().setName(rs.getString("cateName"));
				ms.getMessageCategory().setDesc(rs.getString("cateDesc"));
				
				//��װ�û���Ϣ
				ms.getUser().setId(rs.getInt("userId"));
				ms.getUser().setName(rs.getString("username"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs,pstmt,conn);
		}
		
		return ms;
	}

	@Override
	public void updateMessage(int mid, String title, String content,int userId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			String sql="update message set title=?, content=? ,modifyTime=? where mid=? and userId=?";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(4,mid);
			pstmt.setInt(5,userId);
		//ִ��
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(pstmt,conn);
		}	
		
	}

	@Override
	public void deleteMessage(int mid, int userId) {
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
		//�������
			conn=JDBCUtils.getConnection();
		//��дSQL
			String sql="delete from message where mid=? and userId=?";
		//Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//���ò���
			pstmt.setInt(1,mid);
			pstmt.setInt(2,userId);
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

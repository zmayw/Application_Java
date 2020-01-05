package com.mess.domain;

import java.sql.Timestamp;

public class Message {

	private int id;
	private int userid;
	private int cateId;
	private String title;
	private String content;
	private Timestamp createTime;
	private Timestamp modifyTime;
	
	private MessageCategory messageCategory=new MessageCategory();
	private User user=new User();
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", userid=" + userid + ", cateId=" + cateId + ", title=" + title + ", content="
				+ content + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user=user;
	}
	
	public MessageCategory getMessageCategory() {
		return this.messageCategory;
	}
	
	public void setMessageCategory(MessageCategory category) {
		this.messageCategory=category;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp time) {
		this.modifyTime = time;
	}

	
}



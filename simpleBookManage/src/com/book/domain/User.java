package com.book.domain;

public class User {
	private String name;
	private String password;
	private String email;
	private String filePath;
	
	public User() {};
	public User(String name,String password,String email) {
		setName(name);
		setPassword(password);
		setEmail(email);
	}
	
	public User(String name,String password,String email,String path) {
		setName(name);
		setPassword(password);
		setEmail(email);
		setFilePath(path);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String path) {
		this.filePath=path;
	}
	
	public String toString() {
		return "用户信息：name:"+getName()+" 邮箱："+getEmail();
	}
}

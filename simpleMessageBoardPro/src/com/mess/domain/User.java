package com.mess.domain;

public class User {
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String filePath;
	private String sex;

	public User() {};
	public User(String name,String password,String email) {
		setName(name);
		setPassword(password);
		setEmail(email);
	}
	
	public User(String name,String password,String email, String sex) {
		setName(name);
		setPassword(password);
		setEmail(email);
		setSex(sex);
	}
	
	
	public User(String name,String password,String email, String sex,String path) {
		setName(name);
		setPassword(password);
		setEmail(email);
		setFilePath(path);
		setSex(sex);
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex=sex;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
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

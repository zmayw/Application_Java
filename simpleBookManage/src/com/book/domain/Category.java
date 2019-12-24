package com.book.domain;

public class Category {

	private String id;
	private String name;
	private String desciption;
	
	public Category() {}
	
	public Category(String id,String name) {
		setId(id);
		setName(name);
	}
	
	public Category(String id,String name,String description) {
		setId(id);
		setName(name);
		setDescription(description);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return desciption;
	}

	public void setDescription(String desciption) {
		this.desciption = desciption;
	}
	
	public String toString() {
		return "图书分类信息：id:"+getId()+" name:"+getName()+" description:"+getDescription();
	}
}



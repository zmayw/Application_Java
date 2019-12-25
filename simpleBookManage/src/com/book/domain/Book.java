package com.book.domain;

import com.book.service.impl.CategoryServiceImpl;

public class Book {
	private String id;
	private String name;
	private String categoryId;
	private float price;
	private String description;
	private String coverImg;

	public Book() {
	}

	public Book(String id, String name, String categoryId, String descriptionc) {
		setId(id);
		setName(name);
		setCategoryId(categoryId);
		setDescription(description);

	}
	
	public Book(String id, String name, String categoryId, float price, String coverImg,String description) {
		setId(id);
		setName(name);
		setCategoryId(categoryId);
		setPrice(price);
		setCoverImg(coverImg);
		setDescription(description);
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryName() {
		CategoryServiceImpl csi = new CategoryServiceImpl();
		String name="";
		for (Category ca : csi.getCategoryList()) {
			if (ca.getId().equals(getCategoryId())) {
				name=ca.getName();
				break;
			}
		}
		return name;
	}

	public String toString() {
		return "Õº È–≈œ¢£∫id:" + getId() + " name:" + getName() + " catetory:" + getCategoryName() + " description:"
				+ getDescription();
	}
}

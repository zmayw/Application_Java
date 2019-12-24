package com.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.book.domain.Category;
import com.book.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	
	private static final List<Category> categoryList=new ArrayList<Category>();
	
	public List<Category> getCategoryList() {
		return CategoryServiceImpl.categoryList;
	}
	
	public int addCategory(String categoryId,String categoryName) {
		int n=isExistCategory(categoryId);
		if(n>=0) {
			return -1;
		}else {
			categoryList.add(new Category(categoryId,categoryName));
			return 0;
		}
	}
	
	private int isExistCategory(String categoryId) {
		int n=-1;
		for(Category ca:categoryList) {
			if(ca.getId().equals(categoryId)) {
				return categoryList.indexOf(ca);
			}
		}
		return n;
	}
	
	public void deleteCategory(String categoryId) {
		for(Category ca:categoryList) {
			if(ca.getId().equals(categoryId)) {
				categoryList.remove(ca);
				break;
			}
		}
	}
}

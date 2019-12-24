package com.book.service;

import java.util.ArrayList;
import java.util.List;

import com.book.domain.Category;

public interface CategoryService {
	
	public static final List<Object> categoryList=new ArrayList<Object>();

	public int addCategory(String categoryId,String categoryName);
	
	public void deleteCategory(String categoryId);
}

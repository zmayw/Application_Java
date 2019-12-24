package com.book.service;

import java.util.ArrayList;
import java.util.List;

import com.book.domain.Book;

public interface BookService {
	public static final List<Object> bookList=new ArrayList<Object>();

	public int addBook(Book book);
	
	public void updateBook(Book book) throws IllegalArgumentException, IllegalAccessException;
	
	public void deleteBook(String bookId);
	public Book getBookByCondition(String bookId);
	public List<Book> getBooksByCondition(String categoryName);
	
}

package com.book.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.book.domain.Book;
import com.book.service.BookService;


public class BookServiceImpl implements BookService {
	
	private static final List<Book> bookList=new ArrayList<Book>();

	public List<Book> getBookList(){
		return BookServiceImpl.bookList;
	}
	
	/*
	 * 返回值n=-1 表示添加失败，该bookId已存在
	 * 返回值 n=0表示添加成功
	 */
	@Override
	public int addBook(Book book) {
		int n=isExistBook(book.getId());
		if(n>=0) {
			return n=-1;
		}else {
			bookList.add(book);
			return 0;
		}
	}

	/*
	 * 返回值n=-1表示该书不存在
	 * 返回值n>=0表示该书存在，且返回在LIST中的index
	 */
	private int isExistBook(String bookId) {
		int n=-1;
		for(Book book:bookList) {
			if(book.getId().equals(bookId)) {
				return bookList.indexOf(book);
			}
		}
		return n;
	}
	@Override
	public void updateBook(Book book) throws IllegalArgumentException, IllegalAccessException {
		
		for(Book bk:bookList) {
			if(bk.getId().equals(book.getId())) {
				Field[] fields=book.getClass().getDeclaredFields();
				Field[] bkFields=bk.getClass().getDeclaredFields();
				for(int i=0;i<fields.length;i++) {
					fields[i].setAccessible(true);
					bkFields[i].setAccessible(true);
					System.out.println("updateBook:"+"fields[i]:"+fields[i]+", attributeName:"+fields[i].getName());
					String attributeName=fields[i].getName();

					System.out.println("fields[i].getType(): "+fields[i].getType());
					System.out.println("bkFields[i].getType(): "+bkFields[i].getType());
					if(fields[i].getType() == float.class) {
						float bookValue=(float) fields[i].get(book);
						float bkValue=(float) bkFields[i].get(bk);
						if(bookValue != bkValue) {
							bkFields[i].set(bk,bookValue);
						}
						System.out.println("updateBookfloat:"+" attributeName: "+attributeName+", bookValue:"+bookValue+",bkValue:"+bkValue);
					}else {
						String bookValue=(String)fields[i].get(book);
						String bkValue=(String)bkFields[i].get(bk);
						if(!bookValue.equals(bkValue)) {
							bkFields[i].set(bk,bookValue);
						}
						System.out.println("updateBookString:"+" attributeName: "+attributeName+", bookValue:"+bookValue+",bkValue:"+bkValue);
					}	

				}
				break;
			}
		}
	}

	@Override
	public void deleteBook(String bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book getBookByCondition(String bookId) {
		// TODO Auto-generated method stub
		int n=isExistBook(bookId);
		if(n>=0) {
			return bookList.get(n);
		}else {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByCondition(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

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
	 * ����ֵn=-1 ��ʾ���ʧ�ܣ���bookId�Ѵ���
	 * ����ֵ n=0��ʾ��ӳɹ�
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
	 * ����ֵn=-1��ʾ���鲻����
	 * ����ֵn>=0��ʾ������ڣ��ҷ�����LIST�е�index
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
					String attributeName=fields[i].getName();
					String bookValue=fields[i].get(attributeName).toString();
					String bkValue=bkFields[i].get(attributeName).toString();
					System.out.println("llll:"+"attributeName:"+attributeName+"bkValue:"+bookValue);
					if(!bookValue.equals(bkValue)) {
						bkFields[i].set(attributeName,bookValue);
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

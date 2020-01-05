package com.mess.domain;

import java.util.List;

public class PageBean<T> {
	
	private int rowsCount;//总记录数
	private int pagesCount;//总页数
	private int limitRows;//每页限制显示的行数
	private int pageNum;//当前的页数
	private List<T> list;//每页显示的数据集合
	public PageBean() {}
	public int getRowsCount() {
		return rowsCount;
	}
	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}
	public int getPagesCount() {
		return pagesCount;
	}
	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}
	public int getLimitRows() {
		return limitRows;
	}
	public void setLimitRows(int limitRows) {
		this.limitRows = limitRows;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "PageBean [rowsCount=" + rowsCount + ", pagesCount=" + pagesCount + ", limitRows=" + limitRows
				+ ", pageNum=" + pageNum + ", list=" + list + "]";
	};
	
	
	
}

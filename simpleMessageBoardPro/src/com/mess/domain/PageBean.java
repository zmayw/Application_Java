package com.mess.domain;

import java.util.List;

public class PageBean<T> {
	
	private int rowsCount;//�ܼ�¼��
	private int pagesCount;//��ҳ��
	private int limitRows;//ÿҳ������ʾ������
	private int pageNum;//��ǰ��ҳ��
	private List<T> list;//ÿҳ��ʾ�����ݼ���
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

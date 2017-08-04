package com.zd.tools.thumbPage.model;

import java.io.Serializable;

/**
 * 翻页标签翻页信息对象
 * */
public class ThumbPageVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1541210193437268562L;
	
	private int totalPagesNum = 1;  //总页数
	private int currentPageNum = 1;  //当前页数
	private int pageSize = 20; //单页显示记录数
	private int totalItemsNum = 0;  //总记录数
	private String orderField = "";	//排序用字段

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalItemsNum() {
		return totalItemsNum;
	}

	public void setTotalItemsNum(int totalItemsNum) {
		this.totalItemsNum = totalItemsNum;
	}

	public int getTotalPagesNum() {
		return totalPagesNum;
	}

	public void setTotalPagesNum(int totalPagesNum) {
		this.totalPagesNum = totalPagesNum;
	}

}

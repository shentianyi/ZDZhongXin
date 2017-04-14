package com.zd.tools.taglib.table.model;

import java.util.ArrayList;
import java.util.List;

public class RowModel {
	
	private String htmlBegin;
	private String htmlEnd;
	
	private List<CellModel> cellList = new ArrayList<CellModel>();

	public List<CellModel> getCellList() {
		return cellList;
	}

	public void setCellList(List<CellModel> cellList) {
		this.cellList = cellList;
	}

	public String getHtmlBegin() {
		return htmlBegin;
	}

	public void setHtmlBegin(String htmlBegin) {
		this.htmlBegin = htmlBegin;
	}

	public String getHtmlEnd() {
		return htmlEnd;
	}

	public void setHtmlEnd(String htmlEnd) {
		this.htmlEnd = htmlEnd;
	}

}

package com.zd.tools.taglib.table.model;

import java.util.ArrayList;
import java.util.List;

public class TableModel {
	
	private String htmlBegin;
	private String htmlEnd;
	private String var;//用于迭代数据
	
	private RowModel thead;
	private RowModel tbody;
	private List<RowModel> rowList = new ArrayList<RowModel>();
	private List<Object> dataList = new ArrayList<Object>();

	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
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

	public List<RowModel> getRowList() {
		return rowList;
	}

	public void setRowList(List<RowModel> rowList) {
		this.rowList = rowList;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public RowModel getThead() {
		return thead;
	}

	public void setThead(RowModel thead) {
		this.thead = thead;
	}

	public RowModel getTbody() {
		return tbody;
	}

	public void setTbody(RowModel tbody) {
		this.tbody = tbody;
	}
}

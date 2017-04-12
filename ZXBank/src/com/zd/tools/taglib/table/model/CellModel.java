package com.zd.tools.taglib.table.model;

public class CellModel {
	
	private String htmlBegin;
	private String htmlEnd;
	private String content;
	
	public String getContent() {
		if(content == null || "".equals(content)){
			return "&nbsp;";
		}else
			return content;
	}
	public void setContent(String content) {
		this.content = content;
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

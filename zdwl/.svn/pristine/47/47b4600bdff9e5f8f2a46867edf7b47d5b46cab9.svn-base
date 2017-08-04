package com.zd.tools.taglib;

import javax.servlet.jsp.JspException;

import com.zd.tools.StringUtil;

public class HtmlTextareaTagSupport extends HtmlTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2362032404648291454L;
	
	protected String cols;	//列数
	protected String rows;	//行数
	protected String value = null;
	protected String readonly = null;
	protected String disabled = null;
	protected String name = null;	//formbean
	protected String property = null;
	
	public void release() {
		super.release();
		cols = null;
		rows = null;
		value = null;
		readonly = null;
		disabled = null;
		name = null;
		property = null;
	}

	public String getTagBeginHtml(){
		return "<textarea";
	}
	
	public String getTagEndHtml(){
		
		if(value==null){
			value = "";
		}
		
		StringBuffer html = new StringBuffer();
		html.append(">").append(value).append("</textarea>");
		
		return html.toString();
	}
	
	public String getTagParameterHtml() throws JspException {
		
		StringBuffer html = new StringBuffer();
		html.append(super.getTagParameterHtml());
		
		if(!StringUtil.isBlank(cols)){
			html.append(" cols=\"").append(cols).append("\"");
		}

		if(!StringUtil.isBlank(rows)){
			html.append(" rows=\"").append(rows).append("\"");
		}

		if(!StringUtil.isBlank(readonly)){
			html.append(" readonly=\"").append(readonly).append("\"");
		}

		if(!StringUtil.isBlank(disabled)){
			html.append(" disabled=\"").append(disabled).append("\"");
		}

		if(!StringUtil.isBlank(property)){
			html.append(" name=\"").append(property).append("\"");
		}
		
		return html.toString();
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}

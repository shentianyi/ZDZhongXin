package com.zd.tools.taglib;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.Constants;

import com.zd.tools.StringUtil;

/**
 * 适用于input的组成标签（支持了input标签特有的部分属性）
 */
public class HtmlInputTagSupport extends HtmlTagSupport {

	private static final long serialVersionUID = -6355452226221324098L;
	protected String align = null;
	protected String alt = null;
	protected String type = null;
	protected String value = null;
	protected String readonly = null;
	protected String disabled = null;
	protected String name = null;	//formbean
	protected String property = null;
	protected String size = null;
	protected boolean indexed = false;
	protected String indexId = null;
	
	public void release() {
		super.release();
		align = null;
		alt = null;
		type = null;
		value = null;
		readonly = null;
		disabled = null;
		name = null;
		property = null;
		size = null;
		indexed = false;
		indexId = null;
	}

	public String getTagBeginHtml(){
		return "<input";
	}
	
	public String getTagEndHtml(){
		return "/>";
	}
	
	public String getTagParameterHtml() throws JspException {
		
		if(!StringUtil.isBlank(align)){

			if(super.style.toLowerCase().indexOf("text-align:".toLowerCase())==-1){
				if(super.style.trim().lastIndexOf(";")!=super.style.trim().length()-1){
					super.style += ";";
				}
				this.style += "text-align:" + align + ";";
			}
			
		}
		
		if(value==null){
			if(!StringUtil.isBlank(property)){
				if(StringUtil.isBlank(name)){
					Object propertyValue = TagUtils.getInstance().lookup(pageContext, Constants.BEAN_KEY, property, null);
					if(propertyValue!=null){
						value = propertyValue.toString();
					}
				}
			}
		}
		
		StringBuffer html = new StringBuffer();
		
		html.append(super.getTagParameterHtml());

		if(!StringUtil.isBlank(alt)){
			html.append(" alt=\"").append(alt).append("\"");
		}

		if(!StringUtil.isBlank(type)){
			html.append(" type=\"").append(type).append("\"");
		}

		if(!StringUtil.isBlank(readonly)){
			html.append(" readonly=\"").append(readonly).append("\"");
		}

		if(!StringUtil.isBlank(disabled)){
			html.append(" disabled=\"").append(disabled).append("\"");
		}

		if(!StringUtil.isBlank(value)){
			html.append(" value=\"").append(value).append("\"");
		}

		if(!StringUtil.isBlank(property)){
			html.append(" name=\"").append(getInputName()).append("\"");
		}

		if(!StringUtil.isBlank(size)){
			html.append(" size=\"").append(size).append("\"");
		}
		
		return html.toString();
	}
	
	protected String getInputName(){
		StringBuffer html = new StringBuffer();
		if(indexed){
			html.append(name).append("[").append(TagUtils.getInstance().lookup(pageContext, indexId)).append("].").append(property);
		} else{
			html.append(property);
		}
		return html.toString();
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setValue(int value) {
		this.value = String.valueOf(value);
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean getIndexed() {
		return indexed;
	}

	public void setIndexed(boolean indexed) {
		this.indexed = indexed;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	
}

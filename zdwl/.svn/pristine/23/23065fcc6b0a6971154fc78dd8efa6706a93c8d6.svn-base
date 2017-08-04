package com.zd.tools.taglib;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.Constants;

import com.zd.tools.StringUtil;

/**
 * 适用于select的组成标签（支持了select标签特有的部分属性）
 */
public class HtmlSelectTagSupport extends HtmlTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1995240623228143709L;
	
	
	protected String align = null;
	protected String alt = null;
	protected String multiple = null;
	protected String value = null;
	protected String disabled = null;
	protected String name = null;	//formbean
	protected String property = null;
	protected String size = null;

	public void release() {
		super.release();
		align = null;
		alt = null;
		multiple = null;
		value = null;
		disabled = null;
		name = null;
		property = null;
		size = null;
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

		if(!StringUtil.isBlank(multiple)){
			html.append(" multiple=\"").append(multiple).append("\"");
		}

		if(!StringUtil.isBlank(disabled)){
			html.append(" disabled=\"").append(disabled).append("\"");
		}

		if(!StringUtil.isBlank(value)){
			html.append(" value=\"").append(value).append("\"");
		}

		if(!StringUtil.isBlank(property)){
			html.append(" name=\"").append(property).append("\"");
		}

		if(!StringUtil.isBlank(size)){
			html.append(" size=\"").append(size).append("\"");
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

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

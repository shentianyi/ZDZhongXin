package com.zd.tools.taglib;

import javax.servlet.jsp.JspException;

import com.zd.tools.StringUtil;

/**
 * 适用于table的组成标签（table、tr、td）（支持了对齐方式、高宽、边框及背景色）
 */
public class HtmlTableTagSupport extends HtmlTagSupport {
	
	private static final long serialVersionUID = -4737502825261261557L;
	protected String align;
	protected String bgcolor;
	protected String border;
	protected String height;
	protected String width;
	protected String title;
	

	public String getTagParameterHtml() throws JspException {
		return this.getSupportTagParameterHtml();
	}
	

	String getSupportTagParameterHtml() throws JspException {
		StringBuffer html = new StringBuffer();
		
		html.append(super.getSupportTagParameterHtml());
		
		if(!StringUtil.isBlank(align)){
			html.append(" align=\"").append(align).append("\"");
		}

		if(!StringUtil.isBlank(bgcolor)){
			html.append(" bgcolor=\"").append(bgcolor).append("\"");
		}

		if(!StringUtil.isBlank(border)){
			html.append(" border=\"").append(border).append("\"");
		}

		if(!StringUtil.isBlank(height)){
			html.append(" height=\"").append(height).append("\"");
		}

		if(!StringUtil.isBlank(width)){
			html.append(" width=\"").append(width).append("\"");
		}

		if(!StringUtil.isBlank(title)){
			html.append(" title=\"").append(title).append("\"");
		}
		
		return html.toString();
	}
	
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getBorder() {
		return border;
	}
	public void setBorder(String border) {
		this.border = border;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

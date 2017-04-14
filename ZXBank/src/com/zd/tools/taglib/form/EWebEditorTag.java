package com.zd.tools.taglib.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.taglib.html.Constants;

import com.zd.tools.taglib.TagUtils;
import com.zd.tools.StringUtil;
import com.zd.tools.taglib.url.ContextPathTag;

public class EWebEditorTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4340015217245504742L;
	protected String name = null;	//formbean
	protected String property = null;
	protected String styleId = null;
	protected String frameborder = "0";
	protected String scrolling = "no";
	protected String width = "100%";
	protected String height = "350";
	
	public void release() {
		super.release();
		name = null;
		property = null;
		styleId = null;
		frameborder = "0";
		scrolling = "no";
		width = "100%";
		height = "350";
	}
	
	public void init() throws JspException {
		
		if(StringUtil.isBlank(name)){
			name=Constants.BEAN_KEY;
			//throw new JspException("必须设置属性name");
		}
		
		if(StringUtil.isBlank(property)){
			throw new JspException("必须设置属性property");
		}
		
		if(styleId==null){
			styleId = property;
		}
	} 

	public int doStartTag() throws JspException {
		
		//属性值初始化
		init();
		
		Object value = TagUtils.getInstance().lookup(pageContext, name, property);
		if(value==null){
			value = "";
		}
		
		//生成输入框部分代码
		StringBuffer html = new StringBuffer();
		
		html.append("<div style=\"display:none\" id=\"").append(property).append("_object\">").append(value).append("</div>\n");
		
		html.append("<input type=\"hidden\" name=\"").append(property).append("\" value=\"").append("\"/>\n");
		
		html.append("<script>\n")
		.append("document.getElementById(\"").append(property).append("\").value = document.getElementById(\"").append(property).append("_object\").innerHTML;")
		.append("</script>\n");
		
		html.append("<iframe id=\"").append(styleId).append("\" ")
		.append("src=\"").append(ContextPathTag.getContextPath(pageContext)).append("ewebeditor/ewebeditor.jsp?id=").append(property).append("&style=coolblue\" ")
		.append("frameborder=\"").append(frameborder).append("\" scrolling=\"").append(scrolling)
		.append("\" width=\"").append(width).append("\" height=\"").append(height).append("\"></iframe>\n");
		
		
		
		try{
			pageContext.getOut().write(html.toString());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		release();
		
		return EVAL_PAGE;
	}
	
	public String getFrameborder() {
		return frameborder;
	}
	public void setFrameborder(String frameborder) {
		this.frameborder = frameborder;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
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
	public String getScrolling() {
		return scrolling;
	}
	public void setScrolling(String scrolling) {
		this.scrolling = scrolling;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	
	

}

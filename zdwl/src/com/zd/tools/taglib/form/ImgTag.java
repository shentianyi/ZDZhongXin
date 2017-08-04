package com.zd.tools.taglib.form;

import java.io.File;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.core.SystemProperty;
import com.zd.tools.StringUtil;
import com.zd.tools.UUID;
import com.zd.tools.file.FileUtil;

public class ImgTag extends TagSupport {
	
	/**
	 * 将blob类型转成图片输出页面标签
	 */
	private static final long serialVersionUID = 4070728490769104288L;
	private String id;
	private String height;
	private String width;
	private String name;
	private Object value;
	private String imgClass;
	private Object imgid;
	private String alt;
	
	public void release() {
		super.release();
		name = null;
		value = null;
	}
	
	
	public int doStartTag() throws JspException {
		String imgUUID = "img"+UUID.getUID();
		StringBuffer html = new StringBuffer();
		if(value instanceof String){
			String file = (String)value;
			if(!StringUtil.isEmpty(file)){
				
				html.append("<img src=\"").append(FileUtil.getFileFolder()).append(File.separator).append(file).append("\"");
			}
		}else{
			pageContext.getServletContext().setAttribute(imgUUID,value);
			
			html.append("<img src=\"").append(SystemProperty.getPropertyValue("system.properties","staticPath")).append("/common/outimg.jsp?imgUUID=").append(imgUUID).append("\"");
		}
		String aid = "";
		if(imgid instanceof String){
			
			aid = imgid.toString();
		}
		
		if(!StringUtil.isBlank(aid)){
			html.append(" id=\"").append(aid).append("\"");
		}
		if(!StringUtil.isBlank(height)){
			html.append(" height=\"").append(height).append("\"");
		}
		if(!StringUtil.isBlank(width)){
			html.append(" width=\"").append(width).append("\"");
		}
		if(!StringUtil.isBlank(imgClass)){
			html.append(" class=\"").append(imgClass).append("\"");
		}
		if(!StringUtil.isBlank(alt)){
			html.append(" alt=\"").append(alt).append("\"");
		}
		
		html.append(">");
		
		
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) throws JspException {
		this.value = (Object)ExpressionEvaluatorManager.evaluate("value", value.toString(), Object.class, this, pageContext);
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getWidth() {
		return width;
	}


	public void setWidth(String width) {
		this.width = width;
	}

	public String getImgClass() {
		return imgClass;
	}


	public void setImgClass(String imgClass) {
		this.imgClass = imgClass;
	}


	public String getAlt() {
		return alt;
	}


	public void setAlt(String alt) {
		this.alt = alt;
	}


	public Object getImgid() {
		return imgid;
	}


	public void setImgid(Object imgid) throws JspException {
		this.imgid = (String) ExpressionEvaluatorManager.evaluate("imgid",imgid.toString(), String.class, this, pageContext);
	}
	
}

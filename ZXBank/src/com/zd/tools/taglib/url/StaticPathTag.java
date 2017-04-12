package com.zd.tools.taglib.url;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.zd.core.SystemProperty;

/**
 * 样式表服务器根目录地址
 */
public class StaticPathTag extends TagSupport {
	
	private static final long serialVersionUID = 2252660526993128183L;
	
	public static String getStaticPath(PageContext pageContext){
		return getStaticPath(((HttpServletRequest)pageContext.getRequest()));
	}
	
	//（取Apache绝对路径）add by Xingjing
	public static String getStaticPath(HttpServletRequest request){
		String path = SystemProperty.getPropertyValue("system.properties","staticPath");
		if(path==null){
			path = request.getContextPath();
		}
		
		return path;
	}

	public int doStartTag() throws JspException {
		
		try{
			//modified by xingjing
			pageContext.getOut().write(getStaticPath(pageContext));
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return 6;
	}
}

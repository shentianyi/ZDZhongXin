package com.zd.tools.taglib.url;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 应用服务器根目录地址
 */
public class ContextPathTag extends TagSupport {
	
	private static final long serialVersionUID = 2252660526993128183L;
	
	public static String getContextPath(PageContext pageContext){
		return getContextPath(((HttpServletRequest)pageContext.getRequest()));
	}
	
	public static String getContextPath(HttpServletRequest request){
		String path = request.getContextPath();
		
		return path;
	}

	public int doStartTag() throws JspException {

		
		try{
			pageContext.getOut().write(getContextPath(pageContext));
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return 6;
	}
}

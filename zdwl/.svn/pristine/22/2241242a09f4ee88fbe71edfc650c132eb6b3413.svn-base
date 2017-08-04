package com.zd.tools.taglib.form;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.zd.tools.StringUtil;

public class ResourcePathTag extends TagSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7026815541390865707L;
	
	public static String PATH_NAME = "PATH_NAME";

	public int doStartTag() throws JspException {
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		String pathName = request.getParameter(PATH_NAME);
		if(pathName==null){
			pathName = (String)request.getSession().getAttribute(PATH_NAME);
		}
		else{
			request.getSession().setAttribute(PATH_NAME,pathName);
		}
		
		//生成输入框部分代码
		StringBuffer html = new StringBuffer();
		
		if(!StringUtil.isBlank(pathName)){
			html.append("<div id=\"path_name_area\">&nbsp;※&nbsp;当前位置：").append(pathName).append("</div>");
		}
		
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
}

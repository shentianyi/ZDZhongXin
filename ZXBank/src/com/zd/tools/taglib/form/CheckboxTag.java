package com.zd.tools.taglib.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.Constants;

import com.zd.tools.StringUtil;
import com.zd.tools.taglib.HtmlCheckboxTagSupport;
import com.zd.tools.taglib.TagUtils;

public class CheckboxTag extends HtmlCheckboxTagSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1928704564751830458L;
	
	public void init() throws JspException {
		
		if(StringUtil.isBlank(name)){
			name=Constants.BEAN_KEY;
		}
		
		if(StringUtil.isBlank(property)){
			throw new JspException("必须设置属性property");
		}
		
		if(styleId==null){
			styleId = property;
		}
		
		type="checkbox";
	}

	public int doStartTag() throws JspException {
		
		//属性值初始化
		init();

		//生成输入框部分代码
		StringBuffer html = new StringBuffer();
		html.append(getTagBeginHtml())
			.append(getTagParameterHtml());
		
		Object propertyValue = TagUtils.getInstance().lookup(pageContext, name, property, null);
		
		if(isChecked(propertyValue,value)){
			html.append(" checked=\"checked\"");
		}
		
		html.append(getTagEndHtml());

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

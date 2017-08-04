package com.zd.tools.thumbPage.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.taglib.util.ThumPageReceptionTools;


public class ThumbPageToolsReceptionTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 610493255585057513L;
	
	private String tableName;	//翻页表格名称
	private String action;	//请求处理地址
	
	public int doStartTag() throws JspException {
		
		ThumPageReceptionTools tools;
		
		try{
			tools = new ThumPageReceptionTools(pageContext,tableName);
			pageContext.getOut().write(tools.getParamHTML(action));
			pageContext.getOut().write(tools.getHeadToolsHTML());
			//设置翻页列表名称——方便其它辅助标签获取列表名称
			pageContext.setAttribute(ThumbPageConstants.THUMBPAGE_TABLENAME.getCode(),tableName);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return 6;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}

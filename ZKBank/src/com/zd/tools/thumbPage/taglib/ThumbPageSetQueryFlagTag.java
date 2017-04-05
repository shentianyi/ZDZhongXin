package com.zd.tools.thumbPage.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.zd.tools.thumbPage.constants.ThumbPageConstants;


/**
 * 翻页工具设置查询标记标签（在不输出翻页信息时需要使用此标签辅助输出标记指示后台获得相关信息）
 * */
public class ThumbPageSetQueryFlagTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5956177277792815454L;
	private String tableName;	//翻页表格名称
	private String out;

	public int doStartTag() throws JspException {

		StringBuffer html = new StringBuffer();
		
		if(!"true".equals(out)){

			html.append("<input type=\"hidden\" name=\"")
			    .append(tableName).append("_param.").append(ThumbPageConstants.PARAM_REMEMBER.getCode())
			    .append("\" value=\"").append(ThumbPageConstants.PARAM_REMEMBER_TRUE.getCode()).append("\">");
			
			html.append("<input type=\"hidden\" name=\"")
			    .append(tableName).append("_param.").append(ThumbPageConstants.PARAM_QUERY.getCode())
			    .append("\" value=\"").append(ThumbPageConstants.PARAM_QUERY_TRUE.getCode()).append("\">");
			
		}
		else{
			
			html.append(tableName).append("_param.").append(ThumbPageConstants.PARAM_REMEMBER.getCode())
				.append("=").append(ThumbPageConstants.PARAM_REMEMBER_TRUE.getCode()).append("&")
				.append(tableName).append("_param.").append(ThumbPageConstants.PARAM_QUERY.getCode())
				.append("=").append(ThumbPageConstants.PARAM_QUERY_TRUE.getCode());
		}
		
		try{
			pageContext.getOut().write(html.toString());
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

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}
}

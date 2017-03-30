package com.zd.tools.thumbPage.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.taglib.util.ThumPageTools;

/**
 * 翻页工具翻页工具条及翻页信息输出标签
 */
public class ThumbPageToolsTag extends TagSupport {

	private static final long serialVersionUID = 468970603791882408L;

	private String tableName;													//翻页表格名称
	private String toolsType = ThumbPageConstants.TOOLSTYPE_HEAD.getCode();		//工具栏类型－表头、表尾
	private String action;														//请求处理地址
	private String className = ThumbPageConstants.CLASSNAME_DEFAULT.getCode();	//翻页工具使用样式

	public int doStartTag() throws JspException {
		
		ThumPageTools tools;
		
		if(toolsType.equals(ThumbPageConstants.TOOLSTYPE_HEAD.getCode())){
			//工具栏类型为表头时输出以下内容
			try{
				tools = new ThumPageTools(pageContext,tableName,className);
				pageContext.getOut().write(tools.getParamHTML(action));
				pageContext.getOut().write(tools.getHeadToolsHTML());
				//设置翻页列表名称——方便其它辅助标签获取列表名称
				pageContext.setAttribute(ThumbPageConstants.THUMBPAGE_TABLENAME.getCode(),tableName);
			} catch(IOException ioe){
				ioe.printStackTrace();
				throw new JspException(ioe);
			}
			
		} else if(toolsType.equals(ThumbPageConstants.TOOLSTYPE_FOOT.getCode())){
			//工具栏类型为表底时输出以下内容
			try{
				if(tableName==null || "".equals(tableName)){
					tableName = (String)pageContext.findAttribute(ThumbPageConstants.THUMBPAGE_TABLENAME.getCode());
				}
				tools = new ThumPageTools(pageContext,tableName,className);
				pageContext.getOut().write(tools.getFootToolsHTML());
				//清除翻页列表名称－－避免其它辅助标签取错列表名称
				pageContext.removeAttribute(ThumbPageConstants.THUMBPAGE_TABLENAME.getCode());
			} catch(IOException ioe){
				ioe.printStackTrace();
				throw new JspException(ioe);
			}
		}
		
		return 6;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getToolsType() {
		return toolsType;
	}

	public void setToolsType(String toolsType) {
		this.toolsType = toolsType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}

package com.zd.tools.thumbPage.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.zd.tools.thumbPage.constants.ThumbPageConstants;

/**
 * 翻页工具排序标签
 */
public class ThumbPageOrderTag extends TagSupport {
	
	private static final long serialVersionUID = -2939280649740453033L;
	
	//外部设置属性
	private String tableName;	//翻页表格名称
	private String cname;	//显示用的中文名称
	private String filedName;	//排序用的字段名称
	private String styleName;	//样式名称
	private String styleClass;	//样式内容
	private String tagName = "label";	//标签类型
	
	@SuppressWarnings("unused")
	private String clickEvent;	//点击触发事件
	

	public int doStartTag() throws JspException {
		
		//设置默认值
		if(tableName==null || "".equals(tableName)){
			tableName = (String)pageContext.findAttribute(ThumbPageConstants.THUMBPAGE_TABLENAME.getCode());
		}
		
		if(tagName==null || "".equals(tagName)){
			tagName = "label";
		}
		if(cname==null || "".equals(cname)){
			cname = "排序";
		}
		
		clickEvent = "to_order('"+tableName+"')";
		
		StringBuffer html = new StringBuffer();
		html.append("<").append(tagName)
			.append(" filedName=\"" + filedName + "\"");
			/**
			  由于页面filedName使用的属性名。导致排序出错。暂时注释排序方法
			.append(" onclick=\"" + clickEvent + "\"")
			 **/
		
		if(styleName!=null && !"".equals(styleName))
			html.append(" class=\"" + styleName + "\"");
		
		if(styleClass!=null && !"".equals(styleClass))
			html.append(" style=\"" + styleClass + "\"");
		
		html.append(">").append(cname).append("</").append(tagName).append(">");
		try{
			pageContext.getOut().write(html.toString());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return 6;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String name) {
		cname = name;
	}


	public String getFiledName() {
		return filedName;
	}


	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getStyleClass() {
		return styleClass;
	}


	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}


	public String getStyleName() {
		return styleName;
	}


	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}


	public String getTagName() {
		return tagName;
	}


	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}

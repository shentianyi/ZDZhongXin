package com.zd.tools.thumbPage.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.zd.tools.ValueObjectUtil;
import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.model.ThumbPageVO;
import com.zd.tools.thumbPage.taglib.util.ThumWappageTools;

public class ThumbWappageOrderTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2924282324543525310L;
	//外部设置属性
	private String tableName;	//翻页表格名称
	private String cname;	//显示用的中文名称
	private String filedName;	//排序用的字段名称
	private String styleName;	//样式名称
	private String styleClass;	//样式内容
	private String action;	//请求处理地址
	private String tagName = "a";	//标签类型
	
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
		
		ThumWappageTools tools = new ThumWappageTools(pageContext,tableName);
		ThumbPageVO thumbPageVO = (ThumbPageVO)pageContext.findAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
		
		
		ThumbPageVO vo = new ThumbPageVO();
		ValueObjectUtil.setVoValue(vo, thumbPageVO);
		
		String orderField = vo.getOrderField();
		if(orderField.indexOf(filedName + " desc")==0){
			vo.setOrderField(filedName);
		}
		else if(orderField.indexOf(filedName)==0){
			vo.setOrderField(filedName + " desc");
		}
		else{
			vo.setOrderField(filedName);
		}
		
		clickEvent = tools.getUrl(action, vo);
		
		StringBuffer html = new StringBuffer();
		html.append("<").append(tagName)
			.append(" href=\"" + clickEvent + "\"");

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


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}

}

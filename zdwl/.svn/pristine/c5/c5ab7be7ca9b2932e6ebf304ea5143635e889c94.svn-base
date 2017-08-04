package com.zd.tools.thumbPage.taglib.buttons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.model.ThumbPageVO;

/**
 * 翻页标签按钮基础类
 * */
public abstract class PageButton {
	
	//常量
	final public String TYPE_BUTTON = "button";	//按钮类型－按钮
	final public String TYPE_IMAGE = "image";	//按钮类型－图片
	final public String TYPE_TEXT = "text";	//按钮类型－文字

	//实例化时初始属性
	public String tableName;	//翻页列表名称
	public String webroot;	//根目录
	public ThumbPageVO thumbPageVO;	//翻页信息对象
		
	//子类设置的按钮属性
	public boolean disabled;	//按钮是否不可用
	public String type;	//按钮类型－按钮或图片
	public String cName;	//按钮中文名（页面显示名）
	public String clickEvent;	//按钮触发时间（对应匹配的JS）
	
	//按钮类型按钮使用的相应属性
	public String normalStyleName;	//正常时样式名称
	public String normalStyleClass;	//正常时样式
	public String disabledStyleName;	//不可用时样式名称
	public String disabledStyleClass;	//不可用时样式
	
	//图片类型按钮使用的相应属性
	public String normalImageUrl;	//正常时图片路径
	public String disabledImageUrl;	//不可用时图片路径
	
	//构造函数，初始化 根目录 及 翻页信息对象
	public PageButton(PageContext context,String tableName){
		this.tableName = tableName;
		this.webroot = ((HttpServletRequest)context.getRequest()).getContextPath();
		this.thumbPageVO = (ThumbPageVO)context.findAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
	}
	
	//设置按钮是否不可用
	public abstract void setDisabled();
	
	//生成按钮HTML代码
	public String getHTML(){
		
		//设置按钮是否不可用
		setDisabled();

		StringBuffer html = new StringBuffer();
		
		//图片式按钮时生成逻辑
		if(type.equals(TYPE_IMAGE)){
			
			//设置 图片路径
			String url = normalImageUrl;
			
			if(disabled){
				url = disabledImageUrl;
			}
			
			//生成HTML代码
			html.append("<IMG border=\"0\" alt=\"").append(cName).append("\" src=\"").append(url).append("\">");
			if(!disabled && clickEvent!=null && !"".equals(clickEvent)){
				//补充按钮事件
				html = new StringBuffer().append("<a href=\"javascript:").append(clickEvent).append("\">")
				.append(html).append("</a>");
			}
		}
		
		//按钮式按钮时生成逻辑
		else if(type.equals(TYPE_BUTTON)){
			
			//设置按钮样式
			String styleStr = "";
			if(disabled){
				if(disabledStyleName!=null && !"".equals(disabledStyleName)){
					styleStr += " class=\"" + disabledStyleName + "\"";
				}
				if(disabledStyleClass!=null && !"".equals(disabledStyleClass)){
					styleStr += " style=\"" + disabledStyleClass + "\"";
				}
			}
			else{
				if(normalStyleName!=null && !"".equals(normalStyleName)){
					styleStr += " class=\"" + normalStyleName + "\"";
				}
				if(normalStyleClass!=null && !"".equals(normalStyleClass)){
					styleStr += " style=\"" + normalStyleClass + "\"";
				}
			}

			//生成HTML代码
			html.append("<button ").append(styleStr);
			if(!disabled && clickEvent!=null && !"".equals(clickEvent)){
				html.append(" onclick=\"").append(clickEvent).append("\"");
			}
			else{
				html.append(" disabled=\"").append(disabled).append("\"");
			}
			html.append(">").append(cName).append("</button>");
		}
		
		//文字式按钮时生成逻辑
		else if(type.equals(TYPE_TEXT)){
			
			//设置按钮样式
			String styleStr = "";
			if(disabled){
				if(disabledStyleName!=null && !"".equals(disabledStyleName)){
					styleStr += " class=\"" + disabledStyleName + "\"";
				}
				if(disabledStyleClass!=null && !"".equals(disabledStyleClass)){
					styleStr += " style=\"" + disabledStyleClass + "\"";
				}
			}
			else{
				if(normalStyleName!=null && !"".equals(normalStyleName)){
					styleStr += " class=\"" + normalStyleName + "\"";
				}
				if(normalStyleClass!=null && !"".equals(normalStyleClass)){
					styleStr += " style=\"" + normalStyleClass + "\"";
				}
			}

			//生成HTML代码
			html.append("<a href=\"javascript:void(0)\" ").append(styleStr);
			if(!disabled && clickEvent!=null && !"".equals(clickEvent)){
				html.append(" onclick=\"").append(clickEvent).append("\"");
			}
			else{
				html.append(" disabled=\"").append(disabled).append("\"");
			}
			html.append(">").append(cName).append("</a>");
		}
		
		return html.toString();
	}

}

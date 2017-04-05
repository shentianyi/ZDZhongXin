package com.zd.tools.thumbPage.taglib.buttons;

import javax.servlet.jsp.PageContext;

/**
 * 上一页按钮
 */
public class PrevPageButton extends PageButton {
	
	//构造函数
	public PrevPageButton(PageContext context,String tableName){
		super(context,tableName);
		
		//子类设置的按钮属性
		this.disabled = false;	//按钮是否不可用
		this.type = TYPE_BUTTON;	//按钮类型－按钮或图片
		this.cName = "上一页";	//按钮中文名（页面显示名）
		this.clickEvent = "to_prevPage('"+tableName+"')";	//按钮触发时间（对应匹配的JS）
		
		//按钮类型按钮使用的相应属性
		this.normalStyleName = "dividePageButton";	//正常时样式名称
		this.normalStyleClass = null;	//正常时样式
		this.disabledStyleName = "dividePageButton";	//不可用时样式名称
		this.disabledStyleClass = null;	//不可用时样式
		
		//图片类型按钮使用的相应属性
		this.normalImageUrl = webroot + "/images/table/prevPage.gif";	//正常图片路径
		this.disabledImageUrl = webroot + "/images/table/prevPageDisabled.gif";	//不可用时图片路径
	}
	
	//设置按钮是否不可用
	public void setDisabled(){
		//当前页小于等于1时按钮不可用
		if(thumbPageVO.getCurrentPageNum()<=1){
			disabled = true;
		}
	}

}

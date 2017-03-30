package com.zd.tools.thumbPage.taglib.buttons;

import javax.servlet.jsp.PageContext;

/**
 * 选择显示记录数按钮
 */
public class SelectPageSizeButton extends PageButton {
	
	//构造函数
	public SelectPageSizeButton(PageContext context,String tableName){
		super(context,tableName);
		
		//子类设置的按钮属性
		this.disabled = false;	//按钮是否不可用
		this.type = TYPE_BUTTON;	//按钮类型－按钮或图片
		this.cName = "每页";	//按钮中文名（页面显示名）
		this.clickEvent = "to_selectPageSize('"+tableName+"')";	//按钮触发时间（对应匹配的JS）
		
		//按钮类型按钮使用的相应属性
		this.normalStyleName = null;	//正常时样式名称
		this.normalStyleClass = "width:50px;";	//正常时样式
		this.disabledStyleName = null;	//不可用时样式名称
		this.disabledStyleClass = normalStyleClass;	//不可用时样式
		
		//图片类型按钮使用的相应属性
		this.normalImageUrl = null;	//正常图片路径
		this.disabledImageUrl = null;	//不可用时图片路径
	}
	
	//设置按钮是否不可用
	public void setDisabled(){
		//当前页大于等于总页数时按钮不可用
//		if(1>=thumbPageVO.getTotalPagesNum()){
//			disabled = true;
//		}
	}
	
	//生成按钮HTML代码
	public String getHTML(){
		
		//设置按钮是否不可用
		setDisabled();

		StringBuffer html = new StringBuffer();
			
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
			
			String selected10 = "";
			String selected30 = "";
			String selected60 = "";
			
			if(thumbPageVO.getPageSize()==10)
				selected10="selected";
			else if(thumbPageVO.getPageSize()==30)
				selected30="selected";
			else if(thumbPageVO.getPageSize()==60)
				selected60="selected";

			//生成HTML代码
			html.append(cName).append(" <SELECT name=\"").append(tableName).append("_param.selectPageSize\" ")
				.append(styleStr).append(" onchange=\"").append(clickEvent).append("\"");
			
			if(disabled){
				html.append(" disabled=\"true\"");
			}
			
			html.append(">")
				.append("<option value=\"10\" ").append(selected10).append(">10</option>")
				.append("<option value=\"30\" ").append(selected30).append(">30</option>")
				.append("<option value=\"60\" ").append(selected60).append(">60</option>")
				.append("</select> 条");
		
		return html.toString();
	}
}

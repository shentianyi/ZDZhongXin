package com.zd.tools.thumbPage.taglib.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.model.ThumbPageVO;
import com.zd.tools.thumbPage.taglib.buttons.FirstPageButton;
import com.zd.tools.thumbPage.taglib.buttons.LastPageButton;
import com.zd.tools.thumbPage.taglib.buttons.NextPageButton;
import com.zd.tools.thumbPage.taglib.buttons.PageButton;
import com.zd.tools.thumbPage.taglib.buttons.PrevPageButton;

public class ThumPageReceptionTools {

	
	private PageContext pageContext;
	private String tableName;
	
	public ThumPageReceptionTools(PageContext pageContext,String tableName){
		this.pageContext = pageContext;
		this.tableName = tableName;
	}
	
	//生成表头工具条
	public String getHeadToolsHTML(){
		StringBuffer html = new StringBuffer();
		
		html.append(getButtonsHTML()).append(getMessageToolsHTML());
		
		return html.toString();
	}
	
	//生成翻页信息
	public String getMessageToolsHTML(){
		
		ThumbPageVO thumbPageVO = (ThumbPageVO)pageContext.findAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
		
		/*
		int beginPageNum = thumbPageVO.getCurrentPageNum()-1;
		int beginItemNum = thumbPageVO.getPageSize() * beginPageNum + 1;
		int endItemNum = beginItemNum + thumbPageVO.getPageSize()-1;
		if(thumbPageVO.getCurrentPageNum()==thumbPageVO.getTotalPagesNum()){
			endItemNum = thumbPageVO.getTotalItemsNum();
		}
		*/
				
		StringBuffer html = new StringBuffer();
		
		html.append("<font color=\"#000000\">&nbsp;&nbsp;当前页：").append(thumbPageVO.getCurrentPageNum()).append("/").append(thumbPageVO.getTotalPagesNum()).append("</font>");
		
		return html.toString();
	}
	
	//生成按钮部分代码
	public String getButtonsHTML(){
		
		//创建翻页使用的按钮
		PageButton first = new FirstPageButton(pageContext,tableName);	//第一页
		first.type = first.TYPE_TEXT;
		first.cName="首页";
		PageButton prev = new PrevPageButton(pageContext,tableName);	//上一页
		prev.type = prev.TYPE_TEXT;
		PageButton next = new NextPageButton(pageContext,tableName);	//下一页
		next.type = next.TYPE_TEXT;
		PageButton last = new LastPageButton(pageContext,tableName);	//最后页
		last.type = last.TYPE_TEXT;
		last.cName="尾页";
		
		StringBuffer html = new StringBuffer();
		
		html.append(first.getHTML()).append("&nbsp;")
			.append(prev.getHTML()).append("&nbsp;")
			.append(next.getHTML()).append("&nbsp;")
			.append(last.getHTML());
		return html.toString();
	}
	
	//生成记录参数用元素代码
	public String getParamHTML(String action){
		
		ThumbPageVO thumbPageVO = (ThumbPageVO)pageContext.findAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
		
		String tagHTMLBegin = "<input type=\"hidden\"";
		String tagHTMLEnd = ">";

		StringBuffer html = new StringBuffer();
		
		//排序字段
		html.append(tagHTMLBegin)
			.append(getParamElementNameHTML("totalPagesNum"))
			.append(" value=\"").append(thumbPageVO.getTotalPagesNum()).append("\"")
			.append(tagHTMLEnd);
		
		//当前页
		html.append(tagHTMLBegin)
			.append(getParamElementNameHTML("currentPageNum"))
			.append(" value=\"").append(thumbPageVO.getCurrentPageNum()).append("\"")
			.append(tagHTMLEnd);
		
		//排序字段
		html.append(tagHTMLBegin)
			.append(getParamElementNameHTML("pageSize"))
			.append(" value=\"").append(thumbPageVO.getPageSize()).append("\"")
			.append(tagHTMLEnd);
		
		//排序字段
		html.append(tagHTMLBegin)
			.append(getParamElementNameHTML("totalItemsNum"))
			.append(" value=\"").append(thumbPageVO.getTotalItemsNum()).append("\"")
			.append(tagHTMLEnd);
		
		//排序字段
		html.append(tagHTMLBegin)
			.append(getParamElementNameHTML("orderField"))
			.append(" value=\"").append(thumbPageVO.getOrderField()).append("\"")
			.append(tagHTMLEnd);

		//请求处理地址
		if(action!=null && !"".equals(action)){
			html.append(tagHTMLBegin)
				.append(getParamElementNameHTML("action"))
				.append(" value=\"").append(farmotActionUrl(action)).append("\"")
				.append(tagHTMLEnd);
		}

		
		//设定从页面读取翻页信息
		html.append(tagHTMLBegin)
			.append(getParamElementNameHTML(ThumbPageConstants.PARAM_REMEMBER.getCode()))
			.append(" value=\"").append(ThumbPageConstants.PARAM_REMEMBER_TRUE.getCode()).append("\"")
			.append(tagHTMLEnd);
		
		html.append(tagHTMLBegin)
			.append(getParamElementNameHTML(ThumbPageConstants.PARAM_QUERY.getCode()))
			.append(" value=\"").append(ThumbPageConstants.PARAM_QUERY_TRUE.getCode()).append("\"")
			.append(tagHTMLEnd);


		return html.toString();
	}
	
	//生成参数元素对象名称代码
	public String getParamElementNameHTML(String name){
		return " name=\"" + tableName + "_param." + name + "\"";
	}
	
	//格式化请求路径
	public String farmotActionUrl(String defaultUrl){
		if(defaultUrl.indexOf("..")!=0){
			String str = "";
			if(defaultUrl.indexOf("/")!=0){
				str += "/";
			}
			defaultUrl = ((HttpServletRequest)pageContext.getRequest()).getContextPath() + str + defaultUrl;
		}
		return defaultUrl;
	}
}

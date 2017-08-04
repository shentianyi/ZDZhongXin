package com.zd.tools.thumbPage.taglib.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.model.ThumbPageVO;
import com.zd.tools.thumbPage.taglib.buttons.AppointPageButton;
import com.zd.tools.thumbPage.taglib.buttons.FirstPageButton;
import com.zd.tools.thumbPage.taglib.buttons.LastPageButton;
import com.zd.tools.thumbPage.taglib.buttons.NextPageButton;
import com.zd.tools.thumbPage.taglib.buttons.PageButton;
import com.zd.tools.thumbPage.taglib.buttons.PrevPageButton;

/**
 * 翻页标签工具帮助类
 * 用于根据翻页信息输出标题、翻页按钮、工具条等html代码
 * */
public class ThumPageTools {
	
	private PageContext pageContext;
	private String tableName;
	private String className;
	
	public ThumPageTools(PageContext pageContext,String tableName,String className){
		this.pageContext = pageContext;
		this.tableName = tableName;
		this.className = className;
	}

	
	//生成表头工具条
	public String getHeadToolsHTML(){
		StringBuffer html = new StringBuffer();
		
		html.append("<TABLE class=\"").append(className).append("\"><tr>")
			.append("<td align=\"right\">").append(getMessageToolsHTML())
			.append(getButtonsHTML()).append("&nbsp;&nbsp;</td>")
			.append("</tr></TABLE>");
		
		return html.toString();
	}
	
	//生成表头工具条
	public String getHeadToolsHTML2(){
		StringBuffer html = new StringBuffer();
		
		html.append("<div class='box_page'><div class='page'>")
			.append(getMessageToolsHTML2())
			.append("</div></div>");
		
		return html.toString();
	}
	
	//生成翻页信息
	public String getMessageToolsHTML(){
		
		ThumbPageVO thumbPageVO = (ThumbPageVO)pageContext.findAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
		
//		int beginPageNum = thumbPageVO.getCurrentPageNum()-1;
//		int beginItemNum = thumbPageVO.getPageSize() * beginPageNum + 1;
//		int endItemNum = beginItemNum + thumbPageVO.getPageSize()-1;
//		if(thumbPageVO.getCurrentPageNum()==thumbPageVO.getTotalPagesNum()){
//			endItemNum = thumbPageVO.getTotalItemsNum();
//		}
				
		StringBuffer html = new StringBuffer();

		html.append("共 ").append(thumbPageVO.getTotalItemsNum()).append(" 条记录")
			.append("<span style=\"width:20px\"></span>")
			.append(" 第 ").append(thumbPageVO.getCurrentPageNum()).append("/").append(thumbPageVO.getTotalPagesNum()).append(" 页&nbsp;&nbsp;");
		
		return html.toString();
	}
	
	//生成翻页信息
	public String getMessageToolsHTML2(){
		
		ThumbPageVO thumbPageVO = (ThumbPageVO)pageContext.findAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
				
		StringBuffer html = new StringBuffer();

		html.append("<span>共  ").append(thumbPageVO.getTotalPagesNum()).append(" 页  ").append(thumbPageVO.getTotalItemsNum()).append(" 条数据</span>");
		
		boolean isFirst=false;
		if(thumbPageVO.getCurrentPageNum()==1){
			isFirst=true;
		}
		if(isFirst){
			html.append("<a class='btna' href='#'>").append("第一页").append("</a>");
			html.append("<a class='btna' href='#'>").append("上一页").append("</a>");
		}else{
			html.append("<a class='btna curbtn' href=javascript:to_firstPage('").append(tableName).append("')>").append("第一页").append("</a>");
			html.append("<a class='btna curbtn' href=javascript:to_prevPage('").append(tableName).append("')>").append("上一页").append("</a>");
		}
		int begin=thumbPageVO.getCurrentPageNum()-2;
		int end=thumbPageVO.getCurrentPageNum()+2;
		while(true){
			if(begin<=0){
				begin = begin+1;
				if(end<thumbPageVO.getTotalPagesNum()){
					end = end+1;
				}
			}else{
				if(end>thumbPageVO.getTotalPagesNum()){
					end=end-1;
					if(begin>1){
						begin = begin - 1;
					}
				}else{
					break;
				}
			}
		}
		
		for(int i=begin;i<=end;i++){
			html.append("<a class='");
			if(thumbPageVO.getCurrentPageNum()==i){
				html.append("numa curnum");
				html.append("' href='#'>").append(i).append("</a>");
			}else{
				html.append("numa");
				html.append("' href=javascript:to_page('").append(tableName).append("',").append(i).append(")>").append(i).append("</a>");
			}
		}
		boolean isLast=false;
		if(thumbPageVO.getCurrentPageNum()==thumbPageVO.getTotalPagesNum()){
			isLast=true;
		}
		if(isLast){
			html.append("<a class='btna' href='#'>").append("下一页").append("</a>");
			html.append("<a class='btna' href='#'>").append("最末页").append("</a>");
		}else{
			html.append("<a class='btna curbtn' href=javascript:to_nextPage('").append(tableName).append("')>").append("下一页").append("</a>");
			html.append("<a class='btna curbtn' href=javascript:to_lastPage('").append(tableName).append("')>").append("最末页").append("</a>");
		}		
		return html.toString();
	}
	
	//生成表底工具条
	public String getFootToolsHTML(){
		
		StringBuffer html = new StringBuffer();
		
		html.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" border=\"0\"><TBODY><tr><td align=\"right\">")
			.append(getButtonsHTML())
			.append("</tr></TBODY></TABLE>");
		
		return html.toString();
	}
	
	//生成按钮部分代码
	public String getButtonsHTML(){
		
		//创建翻页使用的按钮
		PageButton first = new FirstPageButton(pageContext,tableName);	//第一页
		PageButton prev = new PrevPageButton(pageContext,tableName);	//上一页
		PageButton next = new NextPageButton(pageContext,tableName);	//下一页
		PageButton last = new LastPageButton(pageContext,tableName);	//最后页
		PageButton appoint = new AppointPageButton(pageContext,tableName);	//跳转___页
//		PageButton selectPageSize = new SelectPageSizeButton(pageContext,tableName);	//选择单页显示记录数
//		PageButton separator = new SeparatorImg(pageContext,tableName);	//空白图片
		
		StringBuffer html = new StringBuffer();
		
		html.append(appoint.getHTML())
			.append("&nbsp;")
			.append(first.getHTML())
			.append("&nbsp;")
			.append(prev.getHTML())
			.append("&nbsp;")
			.append(next.getHTML())
			.append("&nbsp;")
			.append(last.getHTML());
			//.append(separator.getHTML())
			//.append(separator.getHTML())
			//.append(selectPageSize.getHTML());
		
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

package com.zd.tools.thumbPage.taglib.util;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.zd.tools.ValueObjectUtil;
import com.zd.tools.thumbPage.constants.ThumbPageConstants;
import com.zd.tools.thumbPage.model.ThumbPageVO;

public class ThumWappageTools {
	
	private PageContext pageContext;
	private String tableName;
	
	public ThumWappageTools(PageContext pageContext,String tableName){
		this.pageContext = pageContext;
		this.tableName = tableName;
	}

	
	//生成表头工具条
	public String getHeadToolsHTML(String defaultUrl){
		StringBuffer html = new StringBuffer();
		ThumbPageVO thumbPageVO = (ThumbPageVO)pageContext.findAttribute(ThumbPageConstants.THUMBPAGE_PARAM.getCode() + tableName);
		
		html.append("<font size=\"2\">第&nbsp;");
		
		for(int i=0;i<thumbPageVO.getTotalPagesNum();i++){

			if(i>0){
				html.append("&nbsp;，");
			}
			
			int pageNumber = i+1;
			if(pageNumber==thumbPageVO.getCurrentPageNum()){
				html.append(pageNumber);
			}
			else{
				
				ThumbPageVO vo = new ThumbPageVO();
				ValueObjectUtil.setVoValue(vo, thumbPageVO);
				
				vo.setCurrentPageNum(pageNumber);
				html.append("<a href=\"").append(getUrl(defaultUrl,vo)).append("\">").append(pageNumber).append("</a>");
			}
			
		}
		
		html.append("&nbsp;页</font>");
		
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
			.append(" name=\"").append(getParamElementNameHTML("totalPagesNum")).append("\"")
			.append(" value=\"").append(thumbPageVO.getTotalPagesNum()).append("\"")
			.append(tagHTMLEnd);
		
		//当前页
		html.append(tagHTMLBegin)
			.append(" name=\"").append(getParamElementNameHTML("currentPageNum")).append("\"")
			.append(" value=\"").append(thumbPageVO.getCurrentPageNum()).append("\"")
			.append(tagHTMLEnd);
		
		//排序字段
		html.append(tagHTMLBegin)
			.append(" name=\"").append(getParamElementNameHTML("pageSize")).append("\"")
			.append(" value=\"").append(thumbPageVO.getPageSize()).append("\"")
			.append(tagHTMLEnd);
		
		//排序字段
		html.append(tagHTMLBegin)
			.append(" name=\"").append(getParamElementNameHTML("totalItemsNum")).append("\"")
			.append(" value=\"").append(thumbPageVO.getTotalItemsNum()).append("\"")
			.append(tagHTMLEnd);
		
		//排序字段
		html.append(tagHTMLBegin)
			.append(" name=\"").append(getParamElementNameHTML("orderField")).append("\"")
			.append(" value=\"").append(thumbPageVO.getOrderField()).append("\"")
			.append(tagHTMLEnd);

		//请求处理地址
		if(action!=null && !"".equals(action)){
			html.append(tagHTMLBegin)
				.append(" name=\"").append(getParamElementNameHTML("action")).append("\"")
				.append(" value=\"").append(farmotActionUrl(action)).append("\"")
				.append(tagHTMLEnd);
		}

		
		//设定从页面读取翻页信息
		html.append(tagHTMLBegin)
			.append(" name=\"").append(getParamElementNameHTML(ThumbPageConstants.PARAM_REMEMBER.getCode())).append("\"")
			.append(" value=\"").append(ThumbPageConstants.PARAM_REMEMBER_TRUE.getCode()).append("\"")
			.append(tagHTMLEnd);
		
		html.append(tagHTMLBegin)
			.append(" name=\"").append(getParamElementNameHTML(ThumbPageConstants.PARAM_QUERY.getCode())).append("\"")
			.append(" value=\"").append(ThumbPageConstants.PARAM_QUERY_TRUE.getCode()).append("\"")
			.append(tagHTMLEnd);


		return html.toString();
	}
	
	public String getUrl(String defaultUrl,ThumbPageVO thumbPageVO){
		
		StringBuffer url = new StringBuffer();
		url.append(farmotActionUrl(defaultUrl))
		.append("&").append(getParamElementNameHTML("pageSize")).append("=").append(thumbPageVO.getPageSize())
		.append("&").append(getParamElementNameHTML("currentPageNum")).append("=").append(thumbPageVO.getCurrentPageNum())
		.append("&").append(getParamElementNameHTML("orderField")).append("=").append(thumbPageVO.getOrderField())
		.append("&").append(getParamElementNameHTML(ThumbPageConstants.PARAM_REMEMBER.getCode())).append("=").append(ThumbPageConstants.PARAM_REMEMBER_TRUE.getCode())
		.append("&").append(getRequestParmentsStr());
		
		return url.toString();
	}
	
	@SuppressWarnings("unchecked")
	private String getRequestParmentsStr(){
		StringBuffer str = new StringBuffer();
		
		ServletRequest request = pageContext.getRequest();
		Enumeration names = request.getParameterNames();
		
		while(names.hasMoreElements()){
			String name = (String)names.nextElement();
			String value = request.getParameter(name);
			str.append(name).append("=").append(value);
			if(names.hasMoreElements()){
				str.append("&");
			}
		}
		
		return str.toString();
	}
	
	//生成参数元素对象名称代码
	public String getParamElementNameHTML(String name){
		return tableName + "_param." + name;
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
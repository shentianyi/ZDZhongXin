package com.zd.tools.taglib.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import com.zd.tools.taglib.url.StaticPathTag;

public class SelectTag extends org.apache.struts.taglib.html.SelectTag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1414569914847988297L;
	
	private String editable = null;	//下拉列表是否可编辑
	
	public void release() {
		super.release();
		editable = null;
	}
	
	//检查是否已经输出引入必用脚本代码
	private void initScriptHTML() throws JspException{
		
		StringBuffer html = new StringBuffer();

		String dhtmlxCommonScript = (String)pageContext.getAttribute(FormTagConstants.KEY_SET_DHTMLX_COMMON_SCRIPT.getCode());
		String dhtmlxComboScript = (String)pageContext.getAttribute(FormTagConstants.KEY_SET_DHTMLX_COMBO_SCRIPT.getCode());
		
		if(!FormTagConstants.VALUE_SET_SCRIPT.getCode().equals(dhtmlxCommonScript)){
			
			html.append("<link rel=\"STYLESHEET\" type=\"text/css\" href=\"").append(StaticPathTag.getStaticPath(pageContext)).append("/dhtmlx/common/style.css\">");
			html.append("<script src=\"").append(StaticPathTag.getStaticPath(pageContext)).append("/dhtmlx/common/dhtmlxcommon.js\"></script>");
			
		}
		
		if(!FormTagConstants.VALUE_SET_SCRIPT.getCode().equals(dhtmlxComboScript)){
			
			html.append("<link rel=\"STYLESHEET\" type=\"text/css\" href=\"").append(StaticPathTag.getStaticPath(pageContext)).append("/dhtmlx/combo/dhtmlxcombo.css\">");
			html.append("<script src=\"").append(StaticPathTag.getStaticPath(pageContext)).append("/dhtmlx/combo/dhtmlxcombo.js\"></script>");
			html.append("<script>window.dhx_globalImgPath=\"").append(StaticPathTag.getStaticPath(pageContext)).append("/dhtmlx/combo/imgs/\";</script>");
			
		}
		
		html.append("<script>dhtmlXComboFromSelect(\"").append(getProperty()).append("\");</script>");
	
		try{
			pageContext.getOut().write(html.toString());
			pageContext.setAttribute(FormTagConstants.KEY_SET_DHTMLX_COMMON_SCRIPT.getCode(), FormTagConstants.VALUE_SET_SCRIPT.getCode());
			pageContext.setAttribute(FormTagConstants.KEY_SET_DHTMLX_COMBO_SCRIPT.getCode(), FormTagConstants.VALUE_SET_SCRIPT.getCode());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
	
	}
	
	public int doEndTag() throws JspException {
		
		int flag = super.doEndTag();

		if("true".equals(editable)){
			initScriptHTML();
		}
		
		return flag;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}
}

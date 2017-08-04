package com.zd.tools.taglib.table;

import javax.servlet.jsp.JspException;

import com.zd.tools.taglib.HtmlTableTagSupport;
import com.zd.tools.StringUtil;
import com.zd.tools.taglib.table.constants.TableTagConstants;
import com.zd.tools.taglib.table.model.RowModel;
import com.zd.tools.taglib.table.model.TableModel;

public class TheadTag extends HtmlTableTagSupport {

	protected TableModel tableModel;
	protected RowModel thead;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3808651803303934216L;
	
	public void release(){
		super.release();
		tableModel=null;
		thead=null;
	}
	
	private void init() throws JspException {
		
		if(StringUtil.isBlank(styleClass)){
			styleClass = "rainListHead";
		}
		
	}

	public int doStartTag() throws JspException {
		init();
		
		tableModel = (TableModel)pageContext.findAttribute(TableTagConstants.TABLEMODEL.getCode());
		
		thead = new RowModel();
		
		StringBuffer html = new StringBuffer();
		html.append("<thead>").append(NEWLINE);
		html.append("<tr").append(getTagParameterHtml()).append(">").append(NEWLINE);
		thead.setHtmlBegin(html.toString());
		
		pageContext.setAttribute(TableTagConstants.ROWMODEL.getCode(),thead);
		tableModel.setThead(thead);
		
		return EVAL_BODY_INCLUDE;
		
	}
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		html.append("</tr>").append(NEWLINE);
		html.append("</thead>").append(NEWLINE);
		thead.setHtmlEnd(html.toString());

		pageContext.setAttribute(TableTagConstants.ROWMODEL.getCode(),new RowModel());
		release();
		
		return EVAL_PAGE;
	}
}

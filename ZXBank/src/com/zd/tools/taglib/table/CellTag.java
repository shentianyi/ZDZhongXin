package com.zd.tools.taglib.table;

import javax.servlet.jsp.JspException;

import com.zd.tools.taglib.HtmlTableTagSupport;
import com.zd.tools.StringUtil;
import com.zd.tools.taglib.table.constants.TableTagConstants;
import com.zd.tools.taglib.table.model.CellModel;
import com.zd.tools.taglib.table.model.RowModel;

public class CellTag extends HtmlTableTagSupport {
	
	protected RowModel rowModel;
	protected CellModel cellModel;
	
	
	private boolean isIndexCell = false;
	private String nowrap = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 677622994009875344L;

	
	public void release(){
		super.release();
		rowModel=null;
		cellModel=null;
		nowrap=null;
		isIndexCell = false;
	}
	
	public void inner(){
		if(isIndexCell && StringUtil.isBlank(align)){
			align = "center";
		}
	}

	public int doStartTag() throws JspException {
		
		inner();
		
		cellModel = new CellModel();
		
		StringBuffer html = new StringBuffer();
		html.append("<td");
		if(!StringUtil.isBlank(nowrap)){
			html.append(" nowrap=\"").append(nowrap).append("\" ");
		}
		html.append(getTagParameterHtml()).append(">");
		cellModel.setHtmlBegin(html.toString());
		
		return EVAL_BODY_BUFFERED;
		
	}
	
	public int doEndTag() throws JspException {
		if(bodyContent != null && !"".equals(bodyContent)){
			cellModel.setContent(bodyContent.getString());
		}
		StringBuffer html = new StringBuffer();
		if(isIndexCell){
			html.append(pageContext.getAttribute(TableTagConstants.ROWNUMBER.getCode()));
		}
		
		html.append("</td>").append(NEWLINE);
		cellModel.setHtmlEnd(html.toString());
		
		rowModel = (RowModel)pageContext.getAttribute(TableTagConstants.ROWMODEL.getCode());
		rowModel.getCellList().add(cellModel);
		release();
		
		return EVAL_PAGE;
	}

	public boolean getIsIndexCell() {
		return isIndexCell;
	}

	public void setIsIndexCell(boolean isIndexCell) {
		this.isIndexCell = isIndexCell;
	}

	public String getNowrap() {
		return nowrap;
	}

	public void setNowrap(String nowrap) {
		this.nowrap = nowrap;
	}

}

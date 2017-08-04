package com.zd.tools.taglib.table;

import java.util.Iterator;

import javax.servlet.jsp.JspException;

import com.zd.tools.taglib.HtmlTableTagSupport;
import com.zd.tools.taglib.table.constants.TableTagConstants;
import com.zd.tools.taglib.table.model.RowModel;
import com.zd.tools.taglib.table.model.TableModel;

public class TbodyTag extends HtmlTableTagSupport {

	protected TableModel tableModel;
	protected RowModel tbody;
	
	protected Iterator<Object> iter;
	
	private int index = 0;//序号

	/**
	 * 
	 */
	private static final long serialVersionUID = -3808651803303934216L;
	
	public void release(){
		super.release();
		tableModel=null;
		tbody = null;
		iter=null;
		index = 0;
	}

	public int doStartTag() throws JspException {
		tableModel = (TableModel)pageContext.findAttribute(TableTagConstants.TABLEMODEL.getCode());
		
		iter = tableModel.getDataList().iterator();
		
		tbody = new RowModel();
		
		StringBuffer html = new StringBuffer();
		html.append("<tbody onmouseover=\"mouseOver()\" onmouseout=\"mouseOut()\" onclick=\"clickRow()\">").append(NEWLINE);
		tbody.setHtmlBegin(html.toString());
		
		tableModel.setTbody(tbody);
		
		if(iter.hasNext()){
			return EVAL_BODY_INCLUDE;
		}
		
		return SKIP_BODY;
		
	}
	
	public int doAfterBody() throws JspException {
		if(!iter.hasNext()){
			return SKIP_BODY;
		}
		Object obj = iter.next();
		
		RowModel row = new RowModel();
		
		if(obj == null) {
			pageContext.removeAttribute(tableModel.getVar());
		} 
		else {
			pageContext.setAttribute(tableModel.getVar(),obj);
		}
		
		StringBuffer htmlBegin = new StringBuffer();
		
		if((index%2)==0){
			htmlBegin.append("<tr class=\"rainListTr_a\" mouseOutBackground=\"#FFFFFF\"").append(getTagParameterHtml()).append(">").append(NEWLINE);
		}
		else{
			htmlBegin.append("<tr class=\"rainListTr_b\" mouseOutBackground=\"#EEF7F9\"").append(getTagParameterHtml()).append(">").append(NEWLINE);
		}
		
		
		row.setHtmlBegin(htmlBegin.toString());
		
		StringBuffer htmlEnd = new StringBuffer();
		htmlEnd.append("</tr>").append(NEWLINE);
		row.setHtmlEnd(htmlEnd.toString());
		
		
		pageContext.setAttribute(TableTagConstants.ROWNUMBER.getCode(),(++index));
		pageContext.setAttribute(TableTagConstants.ROWMODEL.getCode(),row);
		
		tableModel.getRowList().add(row);
		
		return EVAL_BODY_AGAIN;
	}
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		html.append("</tbody>").append(NEWLINE);
		tbody.setHtmlEnd(html.toString());
		
		release();
		
		return EVAL_PAGE;
	}

}

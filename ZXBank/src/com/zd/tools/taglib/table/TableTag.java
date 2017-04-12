package com.zd.tools.taglib.table;



import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.zd.tools.taglib.HtmlTableTagSupport;
import com.zd.tools.StringUtil;
import com.zd.tools.taglib.table.constants.TableTagConstants;
import com.zd.tools.taglib.table.model.CellModel;
import com.zd.tools.taglib.table.model.RowModel;
import com.zd.tools.taglib.table.model.TableModel;

public class TableTag extends HtmlTableTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7911927188137637105L;

	private String items;
	private String var;//用于迭代数据
	
	protected TableModel tableModel;
	
	public void release(){
		super.release();
		items=null;
		var=null;
		tableModel=null;
	}
	
	private void init() throws JspException {
		if(StringUtil.isBlank(styleClass)){
			styleClass = "rainListTalbe";
		}
	}

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		init();
		
		tableModel = new TableModel();
		
		List<Object> dataList =  (List<Object>)pageContext.findAttribute(items);
		pageContext.removeAttribute(items);
		
		tableModel.setDataList(dataList);
		
		StringBuffer html = new StringBuffer();
		html.append("<table").append(getTagParameterHtml()).append(">").append(NEWLINE);
		tableModel.setHtmlBegin(html.toString());
		tableModel.setVar(var);
		
		pageContext.setAttribute(TableTagConstants.TABLEMODEL.getCode(),tableModel);
		
		return EVAL_BODY_INCLUDE;
	}
	
	public int doAfterBody() throws JspException {
		
		return SKIP_BODY;
	}
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		html.append("</table>").append(NEWLINE);
		tableModel.setHtmlEnd(html.toString());
		
		StringBuffer bigHtml = new StringBuffer();
		
		bigHtml.append(tableModel.getHtmlBegin());
		bigHtml.append(tableModel.getThead().getHtmlBegin());
		for(int i=0;i<tableModel.getThead().getCellList().size();i++){
			CellModel cell = tableModel.getThead().getCellList().get(i);
			bigHtml.append(cell.getHtmlBegin());
			if(StringUtil.isEmpty(cell.getContent())){
				bigHtml.append(" ");
			}
			else{
				bigHtml.append(cell.getContent());
			}
			bigHtml.append(cell.getHtmlEnd());
		}
		bigHtml.append(tableModel.getThead().getHtmlEnd());
		
		bigHtml.append(tableModel.getTbody().getHtmlBegin());
		for(int i = 0; i<tableModel.getRowList().size(); i++){
			RowModel row = tableModel.getRowList().get(i);
			bigHtml.append(row.getHtmlBegin());
			for(int j = 0; j<row.getCellList().size(); j++){
				CellModel cell =  row.getCellList().get(j);
				bigHtml.append(cell.getHtmlBegin());
				if(StringUtil.isEmpty(cell.getContent())){
					bigHtml.append(" ");
				}
				else{
					bigHtml.append(cell.getContent());
				}
				bigHtml.append(cell.getHtmlEnd());
			}
			bigHtml.append(row.getHtmlEnd());
		}
		bigHtml.append(tableModel.getTbody().getHtmlEnd());
		
		bigHtml.append(tableModel.getHtmlEnd());
		
		try {
			pageContext.getOut().write(bigHtml.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		release();
		
		return EVAL_PAGE;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}
}

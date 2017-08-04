package com.zd.csms.supervisory.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.supervisory.model.AbnormalQueryVO;
import com.zd.csms.supervisory.model.AbnormalVO;

/**
 * 异常事件/异常数据
 * @author licheng
 *
 */
public class AbnormalForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AbnormalVO abnormal = new AbnormalVO();
	private AbnormalQueryVO query = new AbnormalQueryVO();
	
	private FormFile excelfile;
	
	
	public AbnormalVO getAbnormal() {
		return abnormal;
	}
	public void setAbnormal(AbnormalVO abnormal) {
		this.abnormal = abnormal;
	}
	public AbnormalQueryVO getQuery() {
		return query;
	}
	public void setQuery(AbnormalQueryVO query) {
		this.query = query;
	}
	public FormFile getExcelfile() {
		return excelfile;
	}
	public void setExcelfile(FormFile excelfile) {
		this.excelfile = excelfile;
	}
	
	
}

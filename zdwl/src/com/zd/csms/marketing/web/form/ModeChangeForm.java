package com.zd.csms.marketing.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.marketing.model.ModeChangeQueryVO;
import com.zd.csms.marketing.model.ModeChangeVO;

/**
 * 操作模式变更流转单
 * @author licheng
 *
 */
public class ModeChangeForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModeChangeVO mc = new ModeChangeVO();
	private ModeChangeQueryVO query = new ModeChangeQueryVO();
	
	private Integer[] supervisionMode;
	
	public ModeChangeQueryVO getQuery() {
		return query;
	}
	public void setQuery(ModeChangeQueryVO query) {
		this.query = query;
	}
	public ModeChangeVO getMc() {
		return mc;
	}
	public void setMc(ModeChangeVO mc) {
		this.mc = mc;
	}
	public Integer[] getSupervisionMode() {
		return supervisionMode;
	}
	public void setSupervisionMode(Integer[] supervisionMode) {
		this.supervisionMode = supervisionMode;
	}

}

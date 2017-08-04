package com.zd.csms.planandreport.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.planandreport.model.VideoPlanQueryVO;

/**
 * 视频检查报告及台账列表查询条件实体
 * 
 * @author zhangzhicheng
 *
 */
public class VideoReportQueryForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6971671530810813787L;
	private VideoPlanQueryVO query = new VideoPlanQueryVO();

	public VideoPlanQueryVO getQuery() {
		return query;
	}

	public void setQuery(VideoPlanQueryVO query) {
		this.query = query;
	}

}

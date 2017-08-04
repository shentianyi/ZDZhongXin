package com.zd.csms.planandreport.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.planandreport.model.VideoPlanInfoVO;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;

public class VideoPlanInfoForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private VideoPlanInfoVO videoInfoPlan = new VideoPlanInfoVO();
	
	private VideoPlanQueryVO query = new VideoPlanQueryVO();

	public VideoPlanInfoVO getVideoInfoPlan() {
		return videoInfoPlan;
	}

	public void setVideoInfoPlan(VideoPlanInfoVO videoInfoPlan) {
		this.videoInfoPlan = videoInfoPlan;
	}

	public VideoPlanQueryVO getQuery() {
		return query;
	}

	public void setQuery(VideoPlanQueryVO query) {
		this.query = query;
	}

	
}

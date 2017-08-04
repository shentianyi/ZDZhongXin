package com.zd.csms.planandreport.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.planandreport.model.VideoPlanInfoVO;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.model.VideoPlanVO;

public class VideoPlanForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private VideoPlanVO videoPlan = new VideoPlanVO();
	
	private VideoPlanInfoVO videoPlanInfo = new VideoPlanInfoVO();
	
	private VideoPlanQueryVO query = new VideoPlanQueryVO();

	
	public VideoPlanInfoVO getVideoPlanInfo() {
		return videoPlanInfo;
	}

	public void setVideoPlanInfo(VideoPlanInfoVO videoPlanInfo) {
		this.videoPlanInfo = videoPlanInfo;
	}

	public VideoPlanVO getVideoPlan() {
		return videoPlan;
	}

	public void setVideoPlan(VideoPlanVO videoPlan) {
		this.videoPlan = videoPlan;
	}

	public VideoPlanQueryVO getQuery() {
		return query;
	}

	public void setQuery(VideoPlanQueryVO query) {
		this.query = query;
	}

	
}

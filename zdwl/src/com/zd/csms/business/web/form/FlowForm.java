package com.zd.csms.business.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.business.model.FlowQueryVO;
import com.zd.csms.business.model.FlowVO;

public class FlowForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5990595676431540239L;

	private FlowVO flow = new FlowVO();
	private FlowQueryVO flowquery = new FlowQueryVO();
	
	private FormFile pic;
	private String picpath;
	public FlowVO getFlow() {
		return flow;
	}
	public void setFlow(FlowVO flow) {
		this.flow = flow;
	}
	public FlowQueryVO getFlowquery() {
		return flowquery;
	}
	public void setFlowquery(FlowQueryVO flowquery) {
		this.flowquery = flowquery;
	}
	public FormFile getPic() {
		return pic;
	}
	public void setPic(FormFile pic) {
		this.pic = pic;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	
	
	
}

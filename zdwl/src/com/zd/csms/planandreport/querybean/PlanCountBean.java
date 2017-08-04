package com.zd.csms.planandreport.querybean;

/**
 * 视频检查报告台账列表-计划次数
 * 
 * @author wdc
 *
 */
public class PlanCountBean {
	private int dealerId;// 经销商Id
	private int planCount;// 计划次数;
	
	public int getPlanCount() {
		return planCount;
	}
	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	

}

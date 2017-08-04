package com.zd.csms.planandreport.querybean;

import com.zd.csms.planandreport.model.InspectionPlanInfoVO;

/**
 * 巡检计划列表查询对象
 * @author wdc
 *
 */
public class InspectionPlanInfoQueryBean extends InspectionPlanInfoVO{
	
	private String planBeginTimeMax;//计划开始最大时间
	private String predictCostAmount;//预计产生费用合计
	private String storesAmount;//经销商数
	private String outControlUserAmount;//外控专员总数
	private String performance;//完成情况
	
	private String inControlUserNTT; //内控专员名称

	public String getStoresAmount() {
		return storesAmount;
	}

	public void setStoresAmount(String storesAmount) {
		this.storesAmount = storesAmount;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getPlanBeginTimeMax() {
		return planBeginTimeMax;
	}

	public void setPlanBeginTimeMax(String planBeginTimeMax) {
		this.planBeginTimeMax = planBeginTimeMax;
	}

	public String getPredictCostAmount() {
		return predictCostAmount;
	}

	public void setPredictCostAmount(String predictCostAmount) {
		this.predictCostAmount = predictCostAmount;
	}

	public String getOutControlUserAmount() {
		return outControlUserAmount;
	}

	public void setOutControlUserAmount(String outControlUserAmount) {
		this.outControlUserAmount = outControlUserAmount;
	}

	public String getInControlUserNTT() {
		return inControlUserNTT;
	}

	public void setInControlUserNTT(String inControlUserNTT) {
		this.inControlUserNTT = inControlUserNTT;
	}
	
}

package com.zd.csms.messagequartz.model;

import java.util.Date;


public class MsgOutStorageQueryVO extends MsgQueryVO{
	
	/**
	 * 银行释放消息提醒详细-对象
	 */
	private String draft_num;//汇票号
	private String vin;//车架号
	private String price;//价格
	private Integer state;//状态(1导入2在库3出库4移动5:私自售卖，6：私自移动)
	private Date beginDate;//开始日期
	private Date endDate;//结束日期
	
	public String getDraft_num() {
		return draft_num;
	}
	public void setDraft_num(String draft_num) {
		this.draft_num = draft_num;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}

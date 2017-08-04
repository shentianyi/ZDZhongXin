package com.zd.csms.supervisory.model;

import java.util.Date;

public class CheckStockCarBean extends CheckStockCarVO{

	private static final long serialVersionUID = -6724844389668383776L;
	/**
	 * 车架号后五位
	 */
	private String lastFiveVin;
	/**
	 * 盘点时间
	 */
	private Date check_date;
	
	/**
	 * 备注
	 */
	private String remark;
	public String getLastFiveVin() {
		return lastFiveVin;
	}
	public void setLastFiveVin(String lastFiveVin) {
		this.lastFiveVin = lastFiveVin;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}

package com.zd.csms.supervisory.querybean;

import java.sql.Timestamp;
import java.util.Date;

import com.zd.csms.supervisory.model.SuperviseImportVO;

public class ExportCarInfoBean extends SuperviseImportVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dealerName;
	private String bankFullName;
	private String brandName;
	
	private String billing_money;//开票金额
	private Date billing_date;//开票日
	private Date due_date;//到期日
	/**
	 * 车辆实际状态  1:本库  2：二库 3：移动 4：出库	 5:私自移动、6:私自销售、7:在途销售、8:在途移动、9:信誉额度、10:展厅
	 */
	//private int actualstate;
	/**
	 * 备注
	 */
	//private String remark;
	/**
	 * 首次异常时间
	 */
	//private Timestamp first_abnormal_time;
	
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getBankFullName() {
		return bankFullName;
	}
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBilling_money() {
		return billing_money;
	}
	public void setBilling_money(String billing_money) {
		this.billing_money = billing_money;
	}
	public Date getBilling_date() {
		return billing_date;
	}
	public void setBilling_date(Date billing_date) {
		this.billing_date = billing_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	/*public Timestamp getFirst_abnormal_time() {
		return first_abnormal_time;
	}
	public void setFirst_abnormal_time(Timestamp first_abnormal_time) {
		this.first_abnormal_time = first_abnormal_time;
	}
	public int getActualstate() {
		return actualstate;
	}
	public void setActualstate(int actualstate) {
		this.actualstate = actualstate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}*/
	
	
}

package com.zd.csms.messagequartz.querybean;

import java.util.Date;

import com.zd.csms.messagequartz.model.MsgBillNoCarVO;

/**
 * 消息查询对象
 * 
 * @author wdc
 * 
 */
public class MsgBillNoCarQueryBean extends MsgBillNoCarVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7731218846821228300L;
	private String dealerName;// 经销商名称
	private String bankFullName;// 银行全名
	private String oneBankName;// 总行/金融机构
	private String twoBankName;// 分行/分支机构
	private String threeBankName;// 支行
	private String brandName;// 品牌名称
	private Integer tmsgUserId;//消息详情和用户关联表id
	private Date updDate;//零库存零汇票提醒(最晚一辆车出库的审批时间)
	private Integer read;
	public Integer getTmsgUserId() {
		return tmsgUserId;
	}
	public void setTmsgUserId(Integer tmsgUserId) {
		this.tmsgUserId = tmsgUserId;
	}
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
	public String getOneBankName() {
		return oneBankName;
	}
	public void setOneBankName(String oneBankName) {
		this.oneBankName = oneBankName;
	}
	public String getTwoBankName() {
		return twoBankName;
	}
	public void setTwoBankName(String twoBankName) {
		this.twoBankName = twoBankName;
	}
	public String getThreeBankName() {
		return threeBankName;
	}
	public void setThreeBankName(String threeBankName) {
		this.threeBankName = threeBankName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	public Integer getRead() {
		return read;
	}
	
	public void setRead(Integer read) {
		this.read = read;
	}
}

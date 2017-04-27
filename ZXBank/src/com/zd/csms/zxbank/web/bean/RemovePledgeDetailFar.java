package com.zd.csms.zxbank.web.bean;

/**
 * 解除质押合同详情
 * @author duyong
 */
public class RemovePledgeDetailFar{
	private String cmdCode;//商品代码
	private String cmdName;//商品名称
	private String unit;//计量单位
	private String stkNum;//库存数量
	private String rlsmgNum;//解除质押数量
	private String whCode;// 所在仓库编号
	private String scflonNo;// scf放款批次号
	private String grtcntNo;//动产质押担保合同编号
	private String mvstkNum;//移库数量	
	private String vin;// 车架号
	private String hgzNo;//合格证编号
	private String carPrice;//车价
	private String rdmoprName;//- 赎货经办人姓名 
	private String rdmopridNo;//赎货经办人身份证号码 
	
	public String getCmdCode() {
		return cmdCode;
	}
	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}
	public String getCmdName() {
		return cmdName;
	}
	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStkNum() {
		return stkNum;
	}
	public void setStkNum(String stkNum) {
		this.stkNum = stkNum;
	}
	public String getRlsmgNum() {
		return rlsmgNum;
	}
	public void setRlsmgNum(String rlsmgNum) {
		this.rlsmgNum = rlsmgNum;
	}
	public String getWhCode() {
		return whCode;
	}
	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}
	public String getScflonNo() {
		return scflonNo;
	}
	public void setScflonNo(String scflonNo) {
		this.scflonNo = scflonNo;
	}
	public String getGrtcntNo() {
		return grtcntNo;
	}
	public void setGrtcntNo(String grtcntNo) {
		this.grtcntNo = grtcntNo;
	}
	public String getMvstkNum() {
		return mvstkNum;
	}
	public void setMvstkNum(String mvstkNum) {
		this.mvstkNum = mvstkNum;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getHgzNo() {
		return hgzNo;
	}
	public void setHgzNo(String hgzNo) {
		this.hgzNo = hgzNo;
	}
	public String getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}
	public String getRdmoprName() {
		return rdmoprName;
	}
	public void setRdmoprName(String rdmoprName) {
		this.rdmoprName = rdmoprName;
	}
	public String getRdmopridNo() {
		return rdmopridNo;
	}
	public void setRdmopridNo(String rdmopridNo) {
		this.rdmopridNo = rdmopridNo;
	}
	@Override
	public String toString() {
		return "RemovePledgeDetail [cmdCode=" + cmdCode + ", cmdName=" + cmdName + ", unit=" + unit + ", stkNum="
				+ stkNum + ", rlsmgNum=" + rlsmgNum + ", whCode=" + whCode + ", scflonNo=" + scflonNo + ", grtcntNo="
				+ grtcntNo + ", mvstkNum=" + mvstkNum + ", vin=" + vin + ", hgzNo=" + hgzNo + ", carPrice=" + carPrice
				+ ", rdmoprName=" + rdmoprName + ", rdmopridNo=" + rdmopridNo + "]";
	}
	
}

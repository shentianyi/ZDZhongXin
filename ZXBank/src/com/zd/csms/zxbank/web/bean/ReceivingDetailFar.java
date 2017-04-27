package com.zd.csms.zxbank.web.bean;
/**
 * 发货通知详情
 * @author caizhuo
 *
 */
public class ReceivingDetailFar {
	private String idtplanNo;//实际订单纸质编号
	private String idtplanNm;//实际订单名称
	private String cmdCode;//商品代码
	private String cmdName;//商品名称
	private String csnNum;//发货数量
	private String unit;//计量单位
	private String csnprc;//发货单价
	private String reqcsnval;//发货价值
	private String scflonNo;//scf放款批次号
	private String mtgcntNo;//质押合同编号
	private String remark;//备注
	private String vin;//车架号
	private String hgzNo;//合格证编号 
	private String carPrice;//车价
	private String loanCode;//融资编号
	
	public String getIdtplanNo() {
		return idtplanNo;
	}
	public void setIdtplanNo(String idtplanNo) {
		this.idtplanNo = idtplanNo;
	}
	public String getIdtplanNm() {
		return idtplanNm;
	}
	public void setIdtplanNm(String idtplanNm) {
		this.idtplanNm = idtplanNm;
	}
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
	public String getCsnNum() {
		return csnNum;
	}
	public void setCsnNum(String csnNum) {
		this.csnNum = csnNum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCsnprc() {
		return csnprc;
	}
	public void setCsnprc(String csnprc) {
		this.csnprc = csnprc;
	}
	public String getReqcsnval() {
		return reqcsnval;
	}
	public void setReqcsnval(String reqcsnval) {
		this.reqcsnval = reqcsnval;
	}
	public String getScflonNo() {
		return scflonNo;
	}
	public void setScflonNo(String scflonNo) {
		this.scflonNo = scflonNo;
	}
	public String getMtgcntNo() {
		return mtgcntNo;
	}
	public void setMtgcntNo(String mtgcntNo) {
		this.mtgcntNo = mtgcntNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	
	@Override
	public String toString() {
		return "ReceivingDetail [idtplanNo=" + idtplanNo + ", idtplanNm=" + idtplanNm + ", cmdCode=" + cmdCode
				+ ", cmdName=" + cmdName + ", csnNum=" + csnNum + ", unit=" + unit + ", csnprc=" + csnprc
				+ ", reqcsnval=" + reqcsnval + ", scflonNo=" + scflonNo + ", mtgcntNo=" + mtgcntNo + ", remark="
				+ remark + ", vin=" + vin + ", hgzNo=" + hgzNo + ", carPrice=" + carPrice + ", loanCode=" + loanCode
				+ "]";
	}
}

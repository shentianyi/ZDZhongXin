package com.zd.csms.zxbank.bean;

import java.io.Serializable;

/**
 * 
 *盘库中间表
 */
public class CheckstockVO implements Serializable{

	private static final long serialVersionUID = 2660076725668394339L;
	
	private String scid;//盘库信息表id 主键
	private String whlevel;//仓库级别
	private String whcode;//仓库代码
	private String whaddr;//仓库地址
	private String cmcode;//商品代码
	private String num;//商品数量
	private String cmgrtcntno;//质押合同编号
	private String vin;//车架号
	
	public String getWhlevel() {
		return whlevel;
	}
	public void setWhlevel(String whlevel) {
		this.whlevel = whlevel;
	}
	public String getWhcode() {
		return whcode;
	}
	public void setWhcode(String whcode) {
		this.whcode = whcode;
	}
	public String getWhaddr() {
		return whaddr;
	}
	public void setWhaddr(String whaddr) {
		this.whaddr = whaddr;
	}
	public String getCmcode() {
		return cmcode;
	}
	public void setCmcode(String cmcode) {
		this.cmcode = cmcode;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCmgrtcntno() {
		return cmgrtcntno;
	}
	public void setCmgrtcntno(String cmgrtcntno) {
		this.cmgrtcntno = cmgrtcntno;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
	}
	@Override
	public String toString() {
		return "CheckstockVO [scid=" + scid + ", whlevel=" + whlevel
				+ ", whcode=" + whcode + ", whaddr=" + whaddr + ", cmcode="
				+ cmcode + ", num=" + num + ", cmgrtcntno=" + cmgrtcntno
				+ ", vin=" + vin + ", getWhlevel()=" + getWhlevel()
				+ ", getWhcode()=" + getWhcode() + ", getWhaddr()="
				+ getWhaddr() + ", getCmcode()=" + getCmcode() + ", getNum()="
				+ getNum() + ", getCmgrtcntno()=" + getCmgrtcntno()
				+ ", getVin()=" + getVin() + ", getScid()=" + getScid()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}

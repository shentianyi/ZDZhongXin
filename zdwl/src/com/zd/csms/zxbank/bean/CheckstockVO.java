package com.zd.csms.zxbank.bean;

import java.io.Serializable;

/**
 * 
 *盘库中间表
 */
public class CheckstockVO implements Serializable {

	private static final long serialVersionUID = 2660076725668394339L;

	private String scid;//盘库信息表id 主键
	private String whlevel;//仓库级别
	private String whcode;//仓库代码
	private String whaddr;//仓库地址
	private String whname;//仓库名称
	private String num;//车辆数量
	private String vin;//车架号

	public String getWhname() {
		return whname;
	}

	public void setWhname(String whname) {
		this.whname = whname;
	}

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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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
		return "CheckstockVO [scid=" + scid + ", whlevel=" + whlevel + ", whcode=" + whcode + ", whaddr=" + whaddr
				+ ", whname=" + whname + ", num=" + num + ", vin=" + vin + "]";
	}
}

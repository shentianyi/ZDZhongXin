package com.zd.csms.zxbank.bean;
/**
 * 盘库信息 ---监管仓库列表
 * @author caizhuo
 *
 */
public class Checkwarehouse {
	private int chId;//--主键 监管仓库id
	private String chWhlevel;//--仓库级别
	private String chWhcode;//--仓库代码
	private String chWhaddr;//--仓库地址
	private int chNum;//--车辆数量
	public int getChId() {
		return chId;
	}
	public void setChId(int chId) {
		this.chId = chId;
	}
	public String getChWhlevel() {
		return chWhlevel;
	}
	public void setChWhlevel(String chWhlevel) {
		this.chWhlevel = chWhlevel;
	}
	public String getChWhcode() {
		return chWhcode;
	}
	public void setChWhcode(String chWhcode) {
		this.chWhcode = chWhcode;
	}
	public String getChWhaddr() {
		return chWhaddr;
	}
	public void setChWhaddr(String chWhaddr) {
		this.chWhaddr = chWhaddr;
	}
	public int getChNum() {
		return chNum;
	}
	public void setChNum(int chNum) {
		this.chNum = chNum;
	}
}

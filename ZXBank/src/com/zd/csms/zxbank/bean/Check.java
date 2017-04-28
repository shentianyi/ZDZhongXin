package com.zd.csms.zxbank.bean;

import com.zd.core.annotation.table;

/**
 * 盘库信息 ---监管仓库及商品列表
 * @author caizhuo
 *
 */
@table(name="ZX_CHECK")
public class Check {
	private int ckId;//--主键    监管仓库和商品id
	private int ckCsid;//--外键引用盘库表主键
	private String ckSpvwhcode;//--监管仓库代码
	//private String  ckCmcode;//--商品代码
	private String ckVin;//--车架号
	public int getCkId() {
		return ckId;
	}
	public void setCkId(int ckId) {
		this.ckId = ckId;
	}
	public int getCkCsid() {
		return ckCsid;
	}
	public void setCkCsid(int ckCsid) {
		this.ckCsid = ckCsid;
	}
	public String getCkSpvwhcode() {
		return ckSpvwhcode;
	}
	public void setCkSpvwhcode(String ckSpvwhcode) {
		this.ckSpvwhcode = ckSpvwhcode;
	}
	/*public String getCkCmcode() {
		return ckCmcode;
	}
	public void setCkCmcode(String ckCmcode) {
		this.ckCmcode = ckCmcode;
	}*/
	public String getCkVin() {
		return ckVin;
	}
	public void setCkVin(String ckVin) {
		this.ckVin = ckVin;
	}
	@Override
	public String toString() {
		return "Check [ckId=" + ckId + ", ckCsid=" + ckCsid + ", ckSpvwhcode="
				+ ckSpvwhcode + ", ckVin=" + ckVin + "]";
	}
	
}

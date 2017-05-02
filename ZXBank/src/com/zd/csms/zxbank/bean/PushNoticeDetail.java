package com.zd.csms.zxbank.bean;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 通知推送明细
 * @author caizhuo
 *
 */
@table(name="ZX_PUSH_NOTICE_DETAIL")
public class PushNoticeDetail {
	private int pndid;//--通知推送明细id
	private int nid;//--通知推送表id  外键
	private String pndEcifcode;//--中信银行ECIF客户号
	private String pndOperorg;//--经办行
	private String pndVin;//--车架号
	private String pndLoancode;//--融资编号
	public int getPndid() {
		return pndid;
	}
	public void setPndid(int pndid) {
		this.pndid = pndid;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getPndEcifcode() {
		return pndEcifcode;
	}
	public void setPndEcifcode(String pndEcifcode) {
		this.pndEcifcode = pndEcifcode;
	}
	public String getPndOperorg() {
		return pndOperorg;
	}
	public void setPndOperorg(String pndOperorg) {
		this.pndOperorg = pndOperorg;
	}
	public String getPndVin() {
		return pndVin;
	}
	public void setPndVin(String pndVin) {
		this.pndVin = pndVin;
	}
	public String getPndLoancode() {
		return pndLoancode;
	}
	public void setPndLoancode(String pndLoancode) {
		this.pndLoancode = pndLoancode;
	}
	@Override
	public String toString() {
		return "PushNoticeDetail [pndid=" + pndid + ", nid=" + nid
				+ ", pndEcifcode=" + pndEcifcode + ", pndOperorg=" + pndOperorg
				+ ", pndVin=" + pndVin + ", pndLoancode=" + pndLoancode + "]";
	}
	
}

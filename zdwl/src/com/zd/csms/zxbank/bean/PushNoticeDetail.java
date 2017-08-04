package com.zd.csms.zxbank.bean;

import com.zd.core.annotation.table;

/**
 * 通知推送明细
 * @author caizhuo
 *
 */
@table(name = "ZX_PUSH_NOTICE_DETAIL")
public class PushNoticeDetail {
	private int id;//--通知推送明细id
	private String ntcno;//--变更通知编号
	private String pndecifcode;//--中信银行ECIF客户号
	private String pndoperorg;//--经办行
	private String pndvin;//--车架号
	private String pndloancode;//--融资编号
	private int state;//变更状态  1.变更成功。2.变更失败

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getPndecifcode() {
		return pndecifcode;
	}

	public void setPndecifcode(String pndecifcode) {
		this.pndecifcode = pndecifcode;
	}

	public String getPndoperorg() {
		return pndoperorg;
	}

	public void setPndoperorg(String pndoperorg) {
		this.pndoperorg = pndoperorg;
	}

	public String getPndvin() {
		return pndvin;
	}

	public void setPndvin(String pndvin) {
		this.pndvin = pndvin;
	}

	public String getPndloancode() {
		return pndloancode;
	}

	public void setPndloancode(String pndloancode) {
		this.pndloancode = pndloancode;
	}

	public String getNtcno() {
		return ntcno;
	}

	public void setNtcno(String ntcno) {
		this.ntcno = ntcno;
	}

	@Override
	public String toString() {
		return "PushNoticeDetail [id=" + id + ", ntcno=" + ntcno
				+ ", pndecifcode=" + pndecifcode + ", pndoperorg=" + pndoperorg
				+ ", pndvin=" + pndvin + ", pndloancode=" + pndloancode
				+ ", state=" + state + "]";
	}
}

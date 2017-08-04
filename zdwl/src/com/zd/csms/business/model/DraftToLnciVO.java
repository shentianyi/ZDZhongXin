package com.zd.csms.business.model;

import java.io.Serializable;
import com.zd.core.annotation.table;

@table(name="t_draft_lnci")
public class DraftToLnciVO implements Serializable {


	private static final long serialVersionUID = 3140746418099892771L;
	private String draft_num;//汇票号码
	private String lnciNo;//借据号
	private String lnciId;//借据ID
	public String getDraft_num() {
		return draft_num;
	}
	public void setDraft_num(String draft_num) {
		this.draft_num = draft_num;
	}
	public String getLnciNo() {
		return lnciNo;
	}
	public void setLnciNo(String lnciNo) {
		this.lnciNo = lnciNo;
	}
	public String getLnciId() {
		return lnciId;
	}
	public void setLnciId(String lnciId) {
		this.lnciId = lnciId;
	}
}

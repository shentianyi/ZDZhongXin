package com.zd.csms.zxbank.bean;

/**
 * 融资信息查询类
 * 
 */
public class FinancingQueryVO {
	private String fgLonentNo;// 借款企业ID
	private String fgStDateStart;// 融资开始开始时间
	private String fgStDateEnd;// 融资开始关闭时间

	public FinancingQueryVO() {
	}

	public FinancingQueryVO(String fgLonentNo) {
		super();
		this.fgLonentNo = fgLonentNo;
	}

	public String getFgLonentNo() {
		return fgLonentNo;
	}

	public void setFgLonentNo(String fgLonentNo) {
		this.fgLonentNo = fgLonentNo;
	}

	public String getFgStDateStart() {
		return fgStDateStart;
	}

	public void setFgStDateStart(String fgStDateStart) {
		this.fgStDateStart = fgStDateStart;
	}

	public String getFgStDateEnd() {
		return fgStDateEnd;
	}

	public void setFgStDateEnd(String fgStDateEnd) {
		this.fgStDateEnd = fgStDateEnd;
	}

	@Override
	public String toString() {
		return "FinancingQueryVO [fgLonentNo=" + fgLonentNo + ", fgStDateStart=" + fgStDateStart + ", fgStDateEnd="
				+ fgStDateEnd + "]";
	}

}

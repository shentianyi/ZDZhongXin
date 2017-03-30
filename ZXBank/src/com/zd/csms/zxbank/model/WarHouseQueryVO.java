package com.zd.csms.zxbank.model;


public class WarHouseQueryVO {
	/**
	 * 查询条件：主键ID
	 */
	private Integer id;
	
	private String custNo;//客户号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}


}

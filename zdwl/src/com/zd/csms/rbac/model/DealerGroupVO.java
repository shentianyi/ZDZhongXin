package com.zd.csms.rbac.model;

import java.io.Serializable;
import java.util.Date;
import com.zd.core.annotation.table;

@table(name = "t_rbac_dealerGroup")
public class DealerGroupVO implements Serializable {
	/**
	 * 经销商集团表
	 */
	private static final long serialVersionUID = -2385509593831124662L;
	private int id;
	private String name;
	private Date createTime;
	private Date modifyTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}

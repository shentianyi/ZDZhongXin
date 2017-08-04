package com.zd.csms.rbac.model.brand;

import java.io.Serializable;
import com.zd.core.annotation.table;

@table(name = "T_RBAC_brandGROUP_USER")
public class BrandGroupUserVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupId;
	private int userId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}

package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Customer;


public interface ZXIBankDockDao extends IDAO{
	/**
	 * @author yixiangyang 
	 * @return
	 */
	public List<Customer> findAllList();
	/**
	 * @author yixiangyang
	 * 根据组织机构代码查询客户详情
	 * @param organizationcode 组织机构代码
	 * @return
	 */
	public String getcust(String organizationcode);
	
}

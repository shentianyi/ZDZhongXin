package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Customer;

import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICustomerDAO extends IDAO{
	/**
	 * 分页查询所有客户
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<Customer> findcustallList(Customer customer,IThumbPageTools tools);
}

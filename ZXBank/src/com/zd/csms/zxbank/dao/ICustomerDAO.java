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
	/**
	 * 
	 * @param customer 插入客户数据
	 * @return
	 */
	public boolean add(Customer customer);
	public boolean upadat(Customer customer);
	/**
	 * 简单查询所有客户信息
	 * @return
	 */
	public List<Customer> query();
}

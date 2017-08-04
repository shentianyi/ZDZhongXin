package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICustomerDAO extends IDAO {
	/**
	 * 分页查询所有客户
	 */
	public List<Customer> findcustallList(Customer customer, IThumbPageTools tools);

	/**
	 * 插入客户数据
	 */
	public boolean add(Customer customer);

	/**
	 * 更新数据 
	 */
	public boolean update(Customer customer);

	/**
	 * 简单查询所有客户信息
	 */
	public List<Customer> query();

	/**
	 * 查询所有ECIF客户号 
	 */
	public List<String> findAllByECIF();

	/**
	 *  根据客户号查询客户
	 */
	public Customer findByNo(String no);

	public Customer findByQuery(Customer query);
	
	/**
	 * 根据经销商id查询经销商的地址
	 */
	public String getCuAddress(int id);
}

package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.ICustomerDao;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

public class CustomerService extends ServiceSupport{
	private ICustomerDao idao = ZXBankDAOFactory.getcustDAO();
	
	public List<Customer> findcustallList(Customer customer,IThumbPageTools tools){
		return idao.findcustallList(customer, tools);
	}
}

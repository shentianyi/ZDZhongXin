package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.Customer;

public class CustomerForm extends ActionForm{
	private static final long serialVersionUID = 7972188204567388006L;
	private Customer customer= new Customer();
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}

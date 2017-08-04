package com.zd.csms.salary.service;

import com.zd.core.ServiceSupport;
import com.zd.csms.salary.dao.ISalary;
import com.zd.csms.salary.dao.SalaryFactory;
import com.zd.csms.salary.model.BasePay;

public class SalaryService extends ServiceSupport{
	private ISalary dao = SalaryFactory.getSalaryDao();
	
	public BasePay getBasePay(){
		return dao.getBasePay();
	}
	
	public boolean updateBasetPay(BasePay basePay){
		return dao.update(basePay);
	}
}

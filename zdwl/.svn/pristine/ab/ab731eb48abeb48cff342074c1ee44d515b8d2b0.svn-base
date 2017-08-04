package com.zd.csms.salary.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.salary.dao.impl.SalaryDaoImpl;


public class SalaryFactory {
	
	private static ISalary salaryDao;
	
	
	public static ISalary getSalaryDao(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(salaryDao==null){
				salaryDao = new SalaryDaoImpl(dataSourceName);
				return salaryDao;
			}
			else 
				return salaryDao;
		}
		return null;
	}
}

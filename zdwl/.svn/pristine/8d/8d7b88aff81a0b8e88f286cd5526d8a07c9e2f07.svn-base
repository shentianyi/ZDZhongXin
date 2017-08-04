package com.zd.csms.bank.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.bank.dao.oracle.BankDao;
import com.zd.csms.bank.dao.oracle.BankDockDao;
import com.zd.csms.bank.dao.oracle.BankManagerDao;

public class BankDAOFactory {
	
	private static IBankDAO bankDao;
	private static IBankManagerDao bankManagerDao;
	private static IBankDockDAO bankDockDao;
	
	public static IBankDAO getBankDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(bankDao==null){
				bankDao = new BankDao(dataSourceName);
				return bankDao;
			}
			else 
				return bankDao;
		}
		return null;
	}
	
	public static IBankManagerDao getBankManagerDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(bankManagerDao==null){
				bankManagerDao = new BankManagerDao(dataSourceName);
				return bankManagerDao;
			}
			else 
				return bankManagerDao;
		}
		return null;
	}
	
	public static IBankDockDAO getBankDockDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(bankDockDao==null){
				bankDockDao = new BankDockDao(dataSourceName);
				return bankDockDao;
			}
			else 
				return bankDockDao;
		}
		return null;
	}
}

package com.zd.csms.zxbank.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.zxbank.dao.oracle.CustomerDao;
import com.zd.csms.zxbank.dao.oracle.NoticeDao;
import com.zd.csms.zxbank.dao.oracle.WareHouseDao;
import com.zd.csms.zxbank.dao.oracle.ZXBankDockDao;




public class ZXBankDAOFactory {
	
	private static ZXIBankDockDao bankDockDao;
	private static IWareHouseDAO iwareHouseDao;
	private static ICustomerDao icustomerDao;
	private static INoticeDao inoticeDao;
	
	public static ZXIBankDockDao getBankDockDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(bankDockDao==null){
				bankDockDao = new ZXBankDockDao(dataSourceName);
				return bankDockDao;
			}
			else 
				return bankDockDao;
		}
		return null;
	}
	
	public static ICustomerDao getcustDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(icustomerDao==null){
				icustomerDao = new CustomerDao(dataSourceName);
				return icustomerDao;
			}
			else 
				return icustomerDao;
		}
		return null;
	}
	
	public static IWareHouseDAO getWareHouseDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(iwareHouseDao==null){
				iwareHouseDao = new WareHouseDao(dataSourceName);
				return iwareHouseDao;
			}
			else 
				return iwareHouseDao;
		}
		return null;
	}
	public static INoticeDao getnoticeDao(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(inoticeDao==null){
				inoticeDao = new NoticeDao(dataSourceName);
				return inoticeDao;
			}
			else 
				return inoticeDao;
		}
		
		return null;
	}
	
}

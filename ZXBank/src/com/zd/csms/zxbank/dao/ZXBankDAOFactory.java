package com.zd.csms.zxbank.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.zxbank.dao.oracle.WareHouseDao;
import com.zd.csms.zxbank.dao.oracle.ZXBankDockDao;




public class ZXBankDAOFactory {
	
	private static ZXIBankDockDao bankDockDao;
	private static WareHouseDao wareHouseDao;
	
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
	
	public static WareHouseDao getWareHouseDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(wareHouseDao==null){
				wareHouseDao = new WareHouseDao(dataSourceName);
				return wareHouseDao;
			}
			else 
				return wareHouseDao;
		}
		return null;
	}
	
}

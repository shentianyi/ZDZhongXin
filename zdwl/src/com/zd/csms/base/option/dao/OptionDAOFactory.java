package com.zd.csms.base.option.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.base.option.dao.mysql.OptionMysqlDAO;

public class OptionDAOFactory {
	
	public static IOptionDAO getOptionsDAO(){
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			return new OptionMysqlDAO(dataSourceName);
		}
		
		return null;
		
	}
}

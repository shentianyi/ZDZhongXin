package com.zd.csms.roster.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.roster.dao.oracle.RosterOracleDAO;

public class RosterDAOFactory {
	
	public static IRosterDAO getRosterDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new RosterOracleDAO(dataSourceName);
		}
		return null;
	}
}

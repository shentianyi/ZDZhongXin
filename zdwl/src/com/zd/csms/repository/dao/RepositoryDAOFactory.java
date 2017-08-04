package com.zd.csms.repository.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.repository.dao.oracle.RepositoryOracleDAO;

public class RepositoryDAOFactory {
	
	public static IRepositoryDAO getRepositoryDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new RepositoryOracleDAO(dataSourceName);
		}
		return null;
	}
}

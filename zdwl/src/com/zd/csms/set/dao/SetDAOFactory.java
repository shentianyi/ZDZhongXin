package com.zd.csms.set.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.set.dao.oracle.DistribsetOracleDAO;

public class SetDAOFactory {

	public static IDistribsetDAO getDistribsetDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new DistribsetOracleDAO(dataSourceName);
		}
		return null;
	}
}

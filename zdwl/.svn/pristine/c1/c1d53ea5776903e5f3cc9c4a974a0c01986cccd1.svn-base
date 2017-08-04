package com.zd.csms.region.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.region.dao.oracle.RegionOracleDAO;

public class RegionDAOFactory {

	public static IRegionDAO getRegionDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new RegionOracleDAO(dataSourceName);
		}
		return null;
	}
}

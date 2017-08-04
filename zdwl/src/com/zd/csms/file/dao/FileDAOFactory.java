package com.zd.csms.file.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.file.dao.oracle.UploadfileOracleDAO;

public class FileDAOFactory {

	public static IUploadfileDAO getUploadfileDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new UploadfileOracleDAO(dataSourceName);
		}
		return null;
	}
}

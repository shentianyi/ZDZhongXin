package com.zd.csms.rbac.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.rbac.dao.oracle.BrandGroupOracleDAO;
import com.zd.csms.rbac.dao.oracle.DealerGroupOracleDAO;
import com.zd.csms.rbac.dao.oracle.ResourceOracleDAO;
import com.zd.csms.rbac.dao.oracle.RoleOracleDAO;
import com.zd.csms.rbac.dao.oracle.RoleResourceOracleDAO;
import com.zd.csms.rbac.dao.oracle.UserOracleDAO;
import com.zd.csms.rbac.dao.oracle.UserRoleOracleDAO;

public class RbacDAOFactory {

	public static IUserDAO getUserDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new UserOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IUserRoleDAO getUserRoleDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new UserRoleOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IResourceDAO getResourceDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new ResourceOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IRoleDAO getRoleDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new RoleOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IRoleResourceDAO getRoleResourceDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new RoleResourceOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IDealerGroupDAO getDealerGroupDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new DealerGroupOracleDAO(dataSourceName);
		}
		return null;
	}
	public static IBrandGroupDAO getBrandGroupDAO() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new BrandGroupOracleDAO(dataSourceName);
		}
		return null;
	}
}

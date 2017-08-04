package com.zd.csms.supervisorymanagement.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.supervisorymanagement.dao.oracle.ExtraworkApplyOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.FixedAssetListOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.FixedAssetsOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.HandoverLogOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.HandoverPlanOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.LeaveApplyOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.ResignApplyOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.SupMaMsgOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.SystemManageOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.SystemSuperviseOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.TransferOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.UsernameManageOracleDAO;

public class SupervisoryManagementDAOFactory {

	public static IUsernameManageDAO getUsernameManageDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new UsernameManageOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IFixedAssetsDAO getFixedAssetsDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new FixedAssetsOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IFixedAssetListDAO getFixedAssetListDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new FixedAssetListOracleDAO(dataSourceName);
		}
		return null;
	}
	
	
	public static ISystemSuperviseDAO getSystemSuperviseDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SystemSuperviseOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static ISystemManageDAO getSystemManageDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SystemManageOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IHandoverLogDAO getHandoverLogDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new HandoverLogOracleDAO(dataSourceName);
		}
		return null;
	}
	public static ITransferDAO getTransferDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new TransferOracleDAO(dataSourceName);
		}
		return null;
	}
	public static IHandoverPlanDAO getHandoverPlanDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new HandoverPlanOracleDAO(dataSourceName);
		}
		return null;
	}

	public static ILeaveApplyDAO getLeaveApplyDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new LeaveApplyOracleDAO(dataSourceName);
		}
		return null;
	}

	public static IExtraworkApplyDAO getExtraworkApplyDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new ExtraworkApplyOracleDAO(dataSourceName);
		}
		return null;
	}

	public static IResignApplyDAO getResignApplyDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new ResignApplyOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static ISupMaMsgDAO getSupMaMsgDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupMaMsgOracleDAO(dataSourceName);
		}
		return null;
	}
}

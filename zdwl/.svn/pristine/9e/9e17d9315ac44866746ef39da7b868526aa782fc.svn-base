package com.zd.csms.finance.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.finance.dao.oracle.OpenInvoiceListOracleDAO;
import com.zd.csms.finance.dao.oracle.OpenInvoiceOracleDAO;
import com.zd.csms.finance.dao.oracle.RefundInvoiceOracleDAO;
import com.zd.csms.finance.dao.oracle.SupervisionfeeOracleDAO;

public class FinanceDAOFactory {

	public static ISupervisionfeeDAO getSupervisionfeeDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupervisionfeeOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IRefundInvoiceDAO getRefundInvoiceDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new RefundInvoiceOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IOpenInvoiceDAO getOpenInvoiceDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new OpenInvoiceOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IOpenInvoiceListDAO getOpenInvoiceListDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new OpenInvoiceListOracleDAO(dataSourceName);
		}
		return null;
	}
}

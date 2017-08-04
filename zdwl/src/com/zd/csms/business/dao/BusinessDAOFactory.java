package com.zd.csms.business.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.business.dao.oracle.AddresslistOracleDAO;
import com.zd.csms.business.dao.oracle.ComplaintDaoImpl;
import com.zd.csms.business.dao.oracle.DraftOracleDAO;
import com.zd.csms.business.dao.oracle.FlowOracleDAO;
import com.zd.csms.business.dao.oracle.MailingOracleDAO;
import com.zd.csms.business.dao.oracle.NoteOracleDAO;
import com.zd.csms.business.dao.oracle.TwoAddressOracleDAO;
import com.zd.csms.business.dao.oracle.YwBankDaoImpl;

public class BusinessDAOFactory {
	
	private static IComplaintDAO complaintDao;
	private static IYwBankDao ywBankDao;

	public static IAddresslistDAO getAddresslistDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new AddresslistOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IDraftDAO getDraftDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new DraftOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static ITwoAddressDAO getTwoAddressDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new TwoAddressOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IComplaintDAO getComplaintDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(complaintDao==null){
				complaintDao = new ComplaintDaoImpl(dataSourceName);
				return complaintDao;
			}
			else 
				return complaintDao;
		}
		return null;
	}
	
	public static IMailingDAO getMailingDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new MailingOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IFlowDAO getFlowDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new FlowOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static INoteDAO getNoteDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new NoteOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IYwBankDao getYwBankDao() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(ywBankDao==null){
				ywBankDao = new YwBankDaoImpl(dataSourceName);
				return ywBankDao;
			}
			else 
				return ywBankDao;
		}
		return null;
	}
}

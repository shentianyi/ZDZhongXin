package com.zd.csms.supervisory.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.messagequartz.dao.DealerSupervisorDao;
import com.zd.csms.messagequartz.dao.InspectRemindDao;
import com.zd.csms.messagequartz.dao.InspectionSupervisorDao;
import com.zd.csms.messagequartz.dao.UnInspectDao;
import com.zd.csms.messagequartz.dao.UnInspectionPlanDao;
import com.zd.csms.messagequartz.dao.oracle.DealerSupervisorDaoImpl;
import com.zd.csms.messagequartz.dao.oracle.InspectRemindDaoImpl;
import com.zd.csms.messagequartz.dao.oracle.InspectionSupervisorDaoImpl;
import com.zd.csms.messagequartz.dao.oracle.UnInspectDaoImpl;
import com.zd.csms.messagequartz.dao.oracle.UnInspectionPlanDaoImpl;
import com.zd.csms.supervisory.dao.costmaill.SupervisorCostMailMessageDao;
import com.zd.csms.supervisory.dao.movestock.SupervisorMoveStockMessageDao;
import com.zd.csms.supervisory.dao.oracle.AbnormalDaoImpl;
import com.zd.csms.supervisory.dao.oracle.ActualAddressOracleDAO;
import com.zd.csms.supervisory.dao.oracle.BankApproveOracleDAO;
import com.zd.csms.supervisory.dao.oracle.CarManualOracleDAO;
import com.zd.csms.supervisory.dao.oracle.CarOperationOracleDAO;
import com.zd.csms.supervisory.dao.oracle.CheckStockDaoImpl;
import com.zd.csms.supervisory.dao.oracle.CheckStockManageDao;
import com.zd.csms.supervisory.dao.oracle.DuedateOracleDAO;
import com.zd.csms.supervisory.dao.oracle.RepairCostOracleDAO;
import com.zd.csms.supervisory.dao.oracle.SuperviseImportOracleDAO;
import com.zd.csms.supervisory.dao.oracle.SupervisoryOracleDao;
import com.zd.csms.supervisory.dao.oracle.costmail.SupervisorCostMailMessageImpl;
import com.zd.csms.supervisory.dao.oracle.movestock.SupervisorMoveStockMessageImpl;
import com.zd.csms.supervisory.dao.oracle.outstock.SupervisorOutStockMessageImpl;
import com.zd.csms.supervisory.dao.oracle.overtime.SupervisorOverTimeMessageImpl;
import com.zd.csms.supervisory.dao.oracle.repairecostms.SupervisorRepairCostMessageImpl;
import com.zd.csms.supervisory.dao.outstock.SupervisorOutStockMessageDao;
import com.zd.csms.supervisory.dao.overtime.SupervisorOverTimeMessageDao;
import com.zd.csms.supervisory.dao.repairecostms.SupervisorRepairCostMessageDao;
import com.zd.csms.supervisorymanagement.dao.IDataMailcostDAO;
import com.zd.csms.supervisorymanagement.dao.IMailcostDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.DataMailcostOracleDAO;
import com.zd.csms.supervisorymanagement.dao.oracle.MailcostOracleDAO;

public class SupervisorDAOFactory {
	private static ICheckStockDAO checkStockDao;
	private static IAbnormalDAO abnormalDao;

	public static ISupervisoryDao getSupervisoryDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupervisoryOracleDao(dataSourceName);
		}
		return null;
	}
	
	public static ISuperviseImportDAO getSuperviseImportDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SuperviseImportOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IActualAddressDAO getActualAddressDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new ActualAddressOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IDuedateDAO getDuedateDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new DuedateOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IMailcostDAO getMailcostDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new MailcostOracleDAO(dataSourceName);
		}
		return null;
	}
	public static IDataMailcostDAO getDataMailcostDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new DataMailcostOracleDAO(dataSourceName);
		}
		return null;
	}
	public static ICheckStockDAO getCheckStockDAO(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(checkStockDao==null){
				checkStockDao = new CheckStockDaoImpl(dataSourceName);
				return checkStockDao;
			}
			else 
				return checkStockDao;
		}
		return null;
	}
	public static ICheckStockManageDAO getCheckStockManageDAO(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new CheckStockManageDao(dataSourceName);
		}
		return null;
	}
	public static IAbnormalDAO getAbnormalDAO(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(abnormalDao==null){
				abnormalDao = new AbnormalDaoImpl(dataSourceName);
				return abnormalDao;
			}
			else 
				return abnormalDao;
		}
		return null;
	}
	
	public static IRepairCostDAO getRepairCostDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new RepairCostOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static SupervisorRepairCostMessageDao getSupervisorRepairCost() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupervisorRepairCostMessageImpl(dataSourceName);
		}
		return null;
	}
	
	public static DealerSupervisorDao getDealerSupervisorDao() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new DealerSupervisorDaoImpl(dataSourceName);
		}
		return null;
	}
	public static SupervisorCostMailMessageDao getSupervisorCostMail() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupervisorCostMailMessageImpl(dataSourceName);
		}
		return null;
	}
	public static SupervisorOverTimeMessageDao getSupervisorOverTime() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupervisorOverTimeMessageImpl(dataSourceName);
		}
		return null;
	}
	public static SupervisorOutStockMessageDao getSupervisorOutStock() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupervisorOutStockMessageImpl(dataSourceName);
		}
		return null;
	}
	public static SupervisorMoveStockMessageDao getSupervisorMoveStock() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");//		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupervisorMoveStockMessageImpl(dataSourceName);
		}
		return null;
	}
	public static InspectionSupervisorDao getSupervisorInspection() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new InspectionSupervisorDaoImpl(dataSourceName);
		}
		return null;
	}
	
	public static UnInspectionPlanDao getUnInspectionPlan() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new UnInspectionPlanDaoImpl(dataSourceName);
		}
		return null;
	}
	public static InspectRemindDao getInspectRemindDao() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new InspectRemindDaoImpl(dataSourceName);
		}
		return null;
	}
	public static UnInspectDao getUnInspectDao() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new UnInspectDaoImpl(dataSourceName);
		}
		return null;
	}
	
	/**
	 * @return the checkStockDao
	 */
	public static ICheckStockDAO getCheckStockDao() {
		return checkStockDao;
	}

	/**
	 * @param checkStockDao the checkStockDao to set
	 */
	public static void setCheckStockDao(ICheckStockDAO checkStockDao) {
		SupervisorDAOFactory.checkStockDao = checkStockDao;
	}

	public static ICarOperationDAO getCarOperationDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new CarOperationOracleDAO(dataSourceName);
		}
		return null;
	}
	public static ICarManualDAO getCarManualDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new CarManualOracleDAO(dataSourceName);
		}
		return null;
	}
	public static IBankApproveDAO getBankApproveDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new BankApproveOracleDAO(dataSourceName);
		}
		return null;
	}
}

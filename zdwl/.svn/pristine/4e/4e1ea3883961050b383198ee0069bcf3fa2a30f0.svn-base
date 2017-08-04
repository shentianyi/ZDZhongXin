package com.zd.csms.marketing.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.marketing.dao.oracle.AgreementBackOracleDAO;
import com.zd.csms.marketing.dao.oracle.AgreementSendOracleDAO;
import com.zd.csms.marketing.dao.oracle.BindDealerDaoImpl;
import com.zd.csms.marketing.dao.oracle.BrandOracleDAO;
import com.zd.csms.marketing.dao.oracle.BusinessransferDAOImpl;
import com.zd.csms.marketing.dao.oracle.DealerDAOImpl;
import com.zd.csms.marketing.dao.oracle.DealerExitDaoImpl;
import com.zd.csms.marketing.dao.oracle.DealerRefundDaoImpl;
import com.zd.csms.marketing.dao.oracle.DealerSupervisorOracleDAO;
import com.zd.csms.marketing.dao.oracle.ExpenseChangeDaoImpl;
import com.zd.csms.marketing.dao.oracle.InvoiceDAOImpl;
import com.zd.csms.marketing.dao.oracle.MarketApprovalDAOImpl;
import com.zd.csms.marketing.dao.oracle.MarketProjectCirculationDAOImpl;
import com.zd.csms.marketing.dao.oracle.ModeChangeDaoImpl;
import com.zd.csms.marketing.dao.oracle.PaymentDAOImpl;
import com.zd.csms.marketing.dao.oracle.SuperviseArrearsOracleDAO;
import com.zd.csms.marketing.dao.oracle.SupervisePayOracleDAO;
import com.zd.csms.marketing.dao.oracle.UnBindDealerDaoImpl;


public class MarketFactory {
	
	private static IMarketApprovalDAO marketApprovalDao;
	private static IMarketProjectCirculationDAO marketProjectCirculationDao;
	private static IDealerDAO iDealerDAO;
	private static IBusinessTransferDAO iBusinessTransferDAO;
	private static IBindDealerDAO bindDealerDao;
	private static IModeChangeDAO modeChangeDao;
	private static IExpenseChangeDAO expenseChangeDao;
	private static IUnBindDealerDAO unBindDealerDao;
	private static IDealerExitDAO dealerExitDao;
	private static IDealerRefundDAO dealerRefundDao;
	private static IInvoiceDAO invoiceDao;
	private static IPaymentDAO paymentDao;
	private static IBrandDAO brandDao;
	
	public static IMarketApprovalDAO getMarketApprovalDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(marketApprovalDao==null){
				marketApprovalDao = new MarketApprovalDAOImpl(dataSourceName);
				return marketApprovalDao;
			}
			else 
				return marketApprovalDao;
		}
		return null;
	}
	
	public static IMarketProjectCirculationDAO getMarketProjectCirculationDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(marketProjectCirculationDao==null){
				marketProjectCirculationDao = new MarketProjectCirculationDAOImpl(dataSourceName);
				return marketProjectCirculationDao;
			}
			else 
				return marketProjectCirculationDao;
		}
		return null;
	}
	
	public static IDealerDAO getIDealerDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(iDealerDAO==null){
				iDealerDAO = new DealerDAOImpl(dataSourceName);
				return iDealerDAO;
			}
			else 
				return iDealerDAO;
		}
		return null;
	}
	
	public static IBusinessTransferDAO getIBusinessTransferDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(iBusinessTransferDAO==null){
				iBusinessTransferDAO = new BusinessransferDAOImpl(dataSourceName);
				return iBusinessTransferDAO;
			}
			else 
				return iBusinessTransferDAO;
		}
		return null;
	}
	
	public static IAgreementSendDAO getAgreementSendDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new AgreementSendOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IAgreementBackDAO getAgreementBackDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new AgreementBackOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static ISupervisePayDAO getSupervisePayDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SupervisePayOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IDealerSupervisorDAO getDealerSupervisorDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new DealerSupervisorOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static ISuperviseArrearsDAO getSuperviseArrearsDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			return new SuperviseArrearsOracleDAO(dataSourceName);
		}
		return null;
	}
	
	public static IBindDealerDAO getBindDealerDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(bindDealerDao==null){
				bindDealerDao = new BindDealerDaoImpl(dataSourceName);
				return bindDealerDao;
			}
			else 
				return bindDealerDao;
		}
		return null;
	}
	
	public static IModeChangeDAO getModeChangeDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(modeChangeDao==null){
				modeChangeDao = new ModeChangeDaoImpl(dataSourceName);
				return modeChangeDao;
			}
			else 
				return modeChangeDao;
		}
		return null;
	}
	
	public static IExpenseChangeDAO getExpenseChangeDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(expenseChangeDao==null){
				expenseChangeDao = new ExpenseChangeDaoImpl(dataSourceName);
				return expenseChangeDao;
			}
			else 
				return expenseChangeDao;
		}
		return null;
	}
	
	public static IUnBindDealerDAO getUnBindDealerDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(unBindDealerDao==null){
				unBindDealerDao = new UnBindDealerDaoImpl(dataSourceName);
				return unBindDealerDao;
			}
			else 
				return unBindDealerDao;
		}
		return null;
	}
	
	public static IDealerExitDAO getDealerExitDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(dealerExitDao==null){
				dealerExitDao = new DealerExitDaoImpl(dataSourceName);
				return dealerExitDao;
			}
			else 
				return dealerExitDao;
		}
		return null;
	}
	
	public static IDealerRefundDAO getDealerRefundDAO(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(dealerRefundDao==null){
				dealerRefundDao = new DealerRefundDaoImpl(dataSourceName);
				return dealerRefundDao;
			}
			else 
				return dealerRefundDao;
		}
		return null;
	}
	
	public static IInvoiceDAO getInvoiceDAO(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(invoiceDao==null){
				invoiceDao = new InvoiceDAOImpl(dataSourceName);
				return invoiceDao;
			}
			else 
				return invoiceDao;
		}
		return null;
	}
	
	public static IPaymentDAO getpaymentDAO(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(paymentDao==null){
				paymentDao = new PaymentDAOImpl(dataSourceName);
				return paymentDao;
			}
			else 
				return paymentDao;
		}
		return null;
	}
	
	public static IBrandDAO getBrandDAO(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(brandDao==null){
				brandDao = new BrandOracleDAO(dataSourceName);
				return brandDao;
			}
			else 
				return brandDao;
		}
		return null;
	}
}

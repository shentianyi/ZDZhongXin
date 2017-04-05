package com.zd.csms.zxbank.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.zxbank.dao.oracle.*;

public class ZXBankDAOFactory {
	
	private static ZXIBankDockDAO bankDockDAO;
	private static IWareHouseDAO iwareHouseDAO;
	private static ICustomerDAO icustomerDAO;
	private static INoticeDAO inoticeDAO;
	private static IFinancingDAO ifanancingDAO;
	private static IAgreementDAO iagreementDAO;
	private static IRemovePledgeDAO iremovepledgeDAO;
	private static IRemovePledgeDetailDAO iremoveoledgedetailDAO;
	private static IMoveNoticeDAO imovenoticeDAO;
	private static IMoveDetailDAO imovedetailDAO;
	private static IReceivingNoticeDAO ireceivingnoticeDAO;
	private static IReceivingDetailDAO ireceivingdetailDAO;
	private static IGagerDAO igagerDAO;
	private static ICommodityDAO icommodityDAO;
	
	public static ZXIBankDockDAO getBankDockDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(bankDockDAO==null){
				bankDockDAO = new ZXBankDockDAO(dataSourceName);
				return bankDockDAO;
			}
			else 
				return bankDockDAO;
		}
		return null;
	}
	
	public static ICustomerDAO getcustDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(icustomerDAO==null){
				icustomerDAO = new CustomerDAO(dataSourceName);
				return icustomerDAO;
			}
			else 
				return icustomerDAO;
		}
		return null;
	}
	
	public static IWareHouseDAO getWareHouseDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(iwareHouseDAO==null){
				iwareHouseDAO = new WareHouseDAO(dataSourceName);
				return iwareHouseDAO;
			}
			else 
				return iwareHouseDAO;
		}
		return null;
	}
	public static INoticeDAO getnoticeDAO(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(inoticeDAO==null){
				inoticeDAO = new NoticeDAO(dataSourceName);
				return inoticeDAO;
			}
			else 
				return inoticeDAO;
		}
		
		return null;
	}
	public static IFinancingDAO getFinancingDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(ifanancingDAO==null){
				ifanancingDAO = new FinancingDAO(dataSourceName);
				return ifanancingDAO;
			}
			else 
				return ifanancingDAO;
		}
		return null;
	}
	
	public static IAgreementDAO getAgreementDAO() {
		String dataSourceName=SystemProperty.getPropertyValue("system.properties","dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(iagreementDAO==null){
				iagreementDAO=new AgreementDAO(dataSourceName);
				return iagreementDAO;
			}else{
				return iagreementDAO;
			}
		}
		return null;
	}

	public static IRemovePledgeDAO getRemovePledgeDAO() {
		String dataSourceName=SystemProperty.getPropertyValue("system.properties","dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(iremovepledgeDAO==null){
				iremovepledgeDAO=new RemovePledgeDAO(dataSourceName);
				return iremovepledgeDAO;
			}else{
				return iremovepledgeDAO;
			}
		}
		return null;
	}
	
	public static IRemovePledgeDetailDAO getRemovePledgeDetailDAO() {
		String dataSourceName=SystemProperty.getPropertyValue("system.properties","dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(iremoveoledgedetailDAO==null){
				iremoveoledgedetailDAO=new RemovePledgeDetailDAO(dataSourceName);
				return iremoveoledgedetailDAO;
			}else{
				return iremoveoledgedetailDAO;
			}
		}
		return null;
	}
	
	public static IMoveNoticeDAO getMoveNoticeDAO() {
		String dataSourceName=SystemProperty.getPropertyValue("system.properties","dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(imovenoticeDAO==null){
				imovenoticeDAO=new MoveNoticeDAO(dataSourceName);
				return imovenoticeDAO;
			}else{
				return imovenoticeDAO;
			}
		}
		return null;
	}
	
	public static IMoveDetailDAO getMoveDetailDAO() {
		String dataSourceName=SystemProperty.getPropertyValue("system.properties","dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(imovedetailDAO==null){
				imovedetailDAO=new MoveDetailDAO(dataSourceName);
				return imovedetailDAO;
			}else{
				return imovedetailDAO;
			}
		}
		return null;
	}

	public static IReceivingNoticeDAO getReceivingNoticeDAO(){
		String dataSourceName=SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(ireceivingnoticeDAO==null){
				ireceivingnoticeDAO=new ReceivingNoticeDAO(dataSourceName);
				return ireceivingnoticeDAO;
			}else{
				return ireceivingnoticeDAO;
			}
		}
		return ireceivingnoticeDAO;
	}
	
	public static IReceivingDetailDAO getReceivingDetailDAO(){
		String dataSourceName=SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(ireceivingdetailDAO==null){
				ireceivingdetailDAO=new ReceivingDetailDAO(dataSourceName);
				return ireceivingdetailDAO;
			}else{
				return ireceivingdetailDAO;
			}
		}
		return ireceivingdetailDAO;
	}
 
	public static IGagerDAO getGagerDAO() {
		String dataSourceName=SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(igagerDAO==null){
				igagerDAO=new GagerDAO(dataSourceName);
				return igagerDAO;
			}else{
				return igagerDAO;
			}
		}
		return igagerDAO;
	}

	public static ICommodityDAO getCommodityDAO() {
		String dataSourceName=SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			if(icommodityDAO==null){
				icommodityDAO=new CommodityDAO(dataSourceName);
				return icommodityDAO;
			}else{
				return icommodityDAO;
			}
		}
		return icommodityDAO;
	}
}

package com.zd.csms.messageAndWaring.message.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.messageAndWaring.message.dao.business.IBunisessMessageTypeDAO;
import com.zd.csms.messageAndWaring.message.dao.market.IMarketMessageTypeDAO;
import com.zd.csms.messageAndWaring.message.dao.oracle.MessageManagerDAOImpl;
import com.zd.csms.messageAndWaring.message.dao.oracle.MessageReceiveDAOImpl;
import com.zd.csms.messageAndWaring.message.dao.oracle.business.BunisessMessageTypeDAOImpl;
import com.zd.csms.messageAndWaring.message.dao.oracle.market.MarketMessageTypeDAOImpl;

public class MessageDAOFactory {

	private static IMessageManagerDAO messageDAO;
	private static IMarketMessageTypeDAO marketMessageTypeDAO;
	private static IBunisessMessageTypeDAO bunisessMessageTypeDAO ;
	private static IMessageReceiveDAO messageReceiveDAO ;
	
	public static IMessageManagerDAO getMessageManagerDAO() {

		String dataSourceName = SystemProperty.getPropertyValue(
				"system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(
				BeanManager.getDbDriver(dataSourceName))) {
			if (messageDAO == null) {
				messageDAO = new MessageManagerDAOImpl(
						dataSourceName);
				return messageDAO;
			} else {
				return messageDAO;
			}
		}
		return null;
	}
	
	public static IMarketMessageTypeDAO getMarketMessageTypeDAO() {
		String dataSourceName = SystemProperty.getPropertyValue(
				"system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(
				BeanManager.getDbDriver(dataSourceName))) {
			if (marketMessageTypeDAO == null) {
				marketMessageTypeDAO = new MarketMessageTypeDAOImpl(
						dataSourceName);
				return marketMessageTypeDAO;
			} else {
				return marketMessageTypeDAO;
			}
		}
		return null;
	}
	

	public static IBunisessMessageTypeDAO getBusinessMessageTypeDAO() {
		String dataSourceName = SystemProperty.getPropertyValue(
				"system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(
				BeanManager.getDbDriver(dataSourceName))) {
			if (bunisessMessageTypeDAO == null) {
				bunisessMessageTypeDAO = new BunisessMessageTypeDAOImpl(dataSourceName) ;
				return bunisessMessageTypeDAO;
			} else {
				return bunisessMessageTypeDAO;
			}
		}
		return null;
	}

	
	public static IMessageReceiveDAO getMessageReceiveDAO() {
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if (messageReceiveDAO == null) {
				messageReceiveDAO = new MessageReceiveDAOImpl(dataSourceName);
				return messageReceiveDAO;
			} else {
				return messageReceiveDAO;
			}
		}
		return null;
	}
}

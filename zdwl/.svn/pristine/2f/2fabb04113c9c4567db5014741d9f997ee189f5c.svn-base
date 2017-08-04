package com.zd.csms.payment.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.payment.dao.oracle.PaymentInfoDaoImpl;

public class PaymentInfoDAOFactory {
	
	private static IPaymentInfoDao paymentInfoDao;

	public static IPaymentInfoDao getPaymentInfoDao() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(paymentInfoDao==null){
				paymentInfoDao = new PaymentInfoDaoImpl(dataSourceName);
				return paymentInfoDao;
			}
			else 
				return paymentInfoDao;
		}
		return null;
	}
}

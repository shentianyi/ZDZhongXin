package com.zd.tools.time.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.tools.time.dao.mysql.TimeMysqlDAO;
import com.zd.tools.time.dao.oracle.TimeOracleDAO;

/**
 * ݿʱ乤dao
 * */
public class TimeDAOFactory {
	
	public static ITimeDAO getTimeDAO(String dataSourceName){
		
		if(Constants.DB_DRIVER_MYSQL.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			return new TimeMysqlDAO(dataSourceName);
		}
		
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			return new TimeOracleDAO(dataSourceName);
		}
		
		return null;
		
	}

}

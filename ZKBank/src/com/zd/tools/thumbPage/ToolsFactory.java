package com.zd.tools.thumbPage;

import javax.servlet.http.HttpServletRequest;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.tools.thumbPage.impl.ThumbPageToolsForMysql;
import com.zd.tools.thumbPage.impl.ThumbPageToolsForOracle;

/**
 * 翻页工具工厂类
 * 根据数据库类型返回不同实现类
 * */
public class ToolsFactory {
	
	public static IThumbPageTools getThumbPageTools(String tableName, HttpServletRequest request){

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		IThumbPageTools tools = null;
		if(Constants.DB_DRIVER_MYSQL.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			tools = new ThumbPageToolsForMysql(dataSourceName);
		}
		
		if(Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))){
			tools = new ThumbPageToolsForOracle(dataSourceName);
		}
		tools.init(tableName, request);
		
		return tools;
	}

}

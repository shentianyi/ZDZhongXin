package com.zd.csms.windcontrol.dao;
import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.windcontrol.dao.oracle.InspectionOracleDAO;
public class WindControlDAOFactory {
  private static IInspectionDAO inspectionDAO ;
  public static IInspectionDAO getInspectionDAO() {
		String dataSourceName = SystemProperty.getPropertyValue(
				"system.properties", "dataSourceName");
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(
				BeanManager.getDbDriver(dataSourceName))) {
			if(inspectionDAO==null){
				inspectionDAO=new InspectionOracleDAO(dataSourceName);
				return inspectionDAO ;
			}else{
				return inspectionDAO ;
			}
		}
		return null;
	}
  
  
}

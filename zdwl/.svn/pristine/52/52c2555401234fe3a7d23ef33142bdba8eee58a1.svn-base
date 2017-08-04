package com.zd.csms.planandreport.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.planandreport.dao.oracle.InspectionPlanDaoImpl;
import com.zd.csms.planandreport.dao.oracle.InspectionPlanInfoDaoImpl;
import com.zd.csms.planandreport.dao.oracle.VideoPlanDaoImpl;
import com.zd.csms.planandreport.dao.oracle.VideoPlanInfoDaoImpl;
import com.zd.csms.planandreport.dao.oracle.VideoReportDaoImpl;

public class PlanAndReportDAOFactory {
	
	private static IVideoPlanDAO videoPlanDao;
	private static IInspectionPlanDAO inspectionPlanDAO;
	private static IVideoPlanInfoDAO videoPlanInfoDao;
	private static IInspectionPlanInfoDAO inspectionPlanInfoDao;
    private static IVideoReportDAO videoReportDao;
	public static IVideoPlanDAO getVideoPlanDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(videoPlanDao==null){
				videoPlanDao = new VideoPlanDaoImpl(dataSourceName);
				return videoPlanDao;
			}
			else 
				return videoPlanDao;
		}
		return null;
	}
	
	public static IVideoPlanInfoDAO getVideoPlanInfoDAO() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(videoPlanInfoDao==null){
				videoPlanInfoDao = new VideoPlanInfoDaoImpl(dataSourceName);
				return videoPlanInfoDao;
			}
			else 
				return videoPlanInfoDao;
		}
		return null;
	}
	
	public static IInspectionPlanDAO getInspectionPlanDAO() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(inspectionPlanDAO==null){
				inspectionPlanDAO = new InspectionPlanDaoImpl(dataSourceName);
				return inspectionPlanDAO;
			}
			else 
				return inspectionPlanDAO;
		}
		return null;
	}
	
	public static IInspectionPlanInfoDAO getInspectionPlanInfoDAO() {
		
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");
		
		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(inspectionPlanInfoDao==null){
				inspectionPlanInfoDao = new InspectionPlanInfoDaoImpl(dataSourceName);
				return inspectionPlanInfoDao;
			}
			else 
				return inspectionPlanInfoDao;
		}
		return null;
	}


	public static IVideoReportDAO getVideoReportDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(videoReportDao==null){
				videoReportDao=new VideoReportDaoImpl(dataSourceName);
				return videoReportDao;
			}else {
				return videoReportDao;}
		}
		return null;
	}


}

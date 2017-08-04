package com.zd.csms.message.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.message.approval.ApprovalDao;
import com.zd.csms.message.dao.oracle.MessageOracleDAO;
import com.zd.csms.message.dao.oracle.NoticeContentOracleDao;
import com.zd.csms.message.dao.oracle.NoticeOracleDAO;

public class MessageDAOFactory {
	private static  ApprovalDao approvalDao;
	private static INoticeDAO noticeDao;
	private static IMessageDAO messageDao;
	private static INoticeContentDao noticeContentDao;

	public static IMessageDAO getMessageDAO() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(messageDao==null){
				messageDao = new MessageOracleDAO(dataSourceName);
				return messageDao;
			}else{
				return messageDao;
			}
		}
		return null;
	}
	
	
	
	public static ApprovalDao getApprovalDao(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(approvalDao==null){
				approvalDao = new ApprovalDao(dataSourceName);
				return approvalDao;
			}else{
				return approvalDao;
			}
		}
		return null;
	}

	public static INoticeDAO getNoticeDao(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(noticeDao==null){
				noticeDao = new NoticeOracleDAO(dataSourceName);
				return noticeDao;
			}else{
				return noticeDao;
			}
		}
		return null;
	}
	
	public static INoticeContentDao getNoticeContentDao(){
		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(noticeContentDao==null){
				noticeContentDao = new NoticeContentOracleDao(dataSourceName);
				return noticeContentDao;
			}else{
				return noticeContentDao;
			}
		}
		return null;
	}
}

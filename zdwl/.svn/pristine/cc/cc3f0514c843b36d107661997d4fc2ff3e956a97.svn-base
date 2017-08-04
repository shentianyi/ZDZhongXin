package com.zd.csms.attendance.dao;

import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.core.SystemProperty;
import com.zd.csms.attendance.dao.oralce.AttendanceDaoImpl;
import com.zd.csms.business.dao.oracle.AddresslistOracleDAO;
import com.zd.csms.business.dao.oracle.ComplaintDaoImpl;
import com.zd.csms.business.dao.oracle.DraftOracleDAO;
import com.zd.csms.business.dao.oracle.FlowOracleDAO;
import com.zd.csms.business.dao.oracle.MailingOracleDAO;
import com.zd.csms.business.dao.oracle.NoteOracleDAO;
import com.zd.csms.business.dao.oracle.TwoAddressOracleDAO;
import com.zd.csms.business.dao.oracle.YwBankDaoImpl;

public class AttendanceDAOFactory {
	
	private static IAttendanceDao attendanceDao;

	public static IAttendanceDao getAttendanceDao() {

		String dataSourceName = SystemProperty.getPropertyValue("system.properties", "dataSourceName");

		if (Constants.DB_DRIVER_ORACLE.getCode().equals(BeanManager.getDbDriver(dataSourceName))) {
			if(attendanceDao==null){
				attendanceDao = new AttendanceDaoImpl(dataSourceName);
				return attendanceDao;
			}
			else 
				return attendanceDao;
		}
		return null;
	}
}

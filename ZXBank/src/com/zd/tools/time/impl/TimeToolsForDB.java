package com.zd.tools.time.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.zd.tools.time.ITimeTools;
import com.zd.tools.time.dao.ITimeDAO;
import com.zd.tools.time.dao.TimeDAOFactory;

public class TimeToolsForDB implements ITimeTools {
	
	private ITimeDAO dao = TimeDAOFactory.getTimeDAO(null);
	private static Long timeDifference;

	public Date getCurrentDate() {
		return getCurrentTimestamp();
	}

	public Timestamp getCurrentTimestamp() {
		Timestamp time;
		if(TimeToolsForDB.timeDifference==null){
			time = dao.getCurrentTimestamp();
			long sl = System.currentTimeMillis();
			TimeToolsForDB.timeDifference = time.getTime()-sl;
		} else{
			long sl = System.currentTimeMillis();
			time = new Timestamp(sl+TimeToolsForDB.timeDifference);
		}
		return time;
	}

	public long getTime() {
		return getCurrentTimestamp().getTime();
	}

}

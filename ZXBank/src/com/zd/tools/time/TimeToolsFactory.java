package com.zd.tools.time;

import com.zd.tools.time.constants.TimeToolsConstants;
import com.zd.tools.time.impl.TimeToolsForDB;
import com.zd.tools.time.impl.TimeToolsForSystem;

public class TimeToolsFactory {

	public static ITimeTools getTimeTools(){
		return getTimeTools(TimeToolsConstants.SYSTEM);
	}

	public static ITimeTools getTimeTools(TimeToolsConstants type){
		if(TimeToolsConstants.DB.equals(type)){
			return new TimeToolsForDB();
		} else if(TimeToolsConstants.SYSTEM.equals(type)){
			return new TimeToolsForSystem();
		}
		return null;
	}
}

package com.zd.tools.time.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.zd.tools.time.ITimeTools;

public class TimeToolsForSystem implements ITimeTools {

	public Date getCurrentDate() {
		return getCurrentTimestamp();
	}

	public Timestamp getCurrentTimestamp() {
		return new Timestamp(getTime());
	}

	public long getTime() {
		return System.currentTimeMillis();
	}
	
	public java.sql.Date getNowDate() {
		return new java.sql.Date(getCurrentDate().getTime());
	}
	
	
	
	public static void main(String[] args) {
		
		Calendar calendar=Calendar.getInstance();   
		   calendar.setTime(new Date()); 
		   System.out.println(calendar.get(Calendar.DATE));//今天的日?
		   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+50);//让日期加1  
		   System.out.println(calendar.get(Calendar.DATE));//?之后的日期Top 

	}

}

package com.zd.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.zd.tools.time.impl.TimeToolsForSystem;

/**
 * Date工具?
 * 提供方法处两个日期差?
 * */
public class DateUtil {
	
	//两日期差
	public static final int daysBetween(Date early, Date late) { 
	     
        java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(early);   
         caled.setTime(late);   
         //设置时间??  
         calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         calst.set(java.util.Calendar.MINUTE, 0);   
         calst.set(java.util.Calendar.SECOND, 0);   
         caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         caled.set(java.util.Calendar.MINUTE, 0);   
         caled.set(java.util.Calendar.SECOND, 0);   
        //得到两个日期相差的天?  
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }   
  
	
	//date+days 和现在时间做比较
	public static final int nowdaysBetween(Date date, int num) { 
		
		java.util.Calendar calst = java.util.Calendar.getInstance();   
		java.util.Calendar caled = java.util.Calendar.getInstance();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String strDate=sdf.format(date);
		calst.setTime(new TimeToolsForSystem().getCurrentDate());   
		caled.setTime(testToDate(strDate,num));   
		//设置时间??  
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
		calst.set(java.util.Calendar.MINUTE, 0);   
		calst.set(java.util.Calendar.SECOND, 0);   
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
		caled.set(java.util.Calendar.MINUTE, 0);   
		caled.set(java.util.Calendar.SECOND, 0);   
		//得到两个日期相差的天?  
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
				.getTime().getTime() / 1000)) / 3600 / 24;   
		
		return days;   
	}   
	
	
	public static final String testDate(String time, int day){ 
		
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dt;
			 String reStr = "";
			try {
				dt = sdf.parse(time);
			
		        Calendar rightNow = Calendar.getInstance();
		        rightNow.setTime(dt);
		        rightNow.add(Calendar.DAY_OF_YEAR,day);//日期?0?
		        Date dt1=rightNow.getTime();
		        reStr = sdf.format(dt1);
		        
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return reStr;
	      
	}   
	public static final Date testToDate(String time, int day){ 
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt;
		Date dt1 = null;
		try {
			dt = sdf.parse(time);
			
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.DAY_OF_YEAR,day);//日期?0?
			dt1=rightNow.getTime();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dt1;
		
	}   
	
	
//	public static void main(String[] args) throws ParseException {
//		
////			Date date = new Date(System.currentTimeMillis());
////		  DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
////		  String dt = df.format(date);
////		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
////		  date=sdf.parse(dt);
////		  System.out.println(date);
//		  
////		  dt = df.format(date);
////		  System.out.println(dt);
//		
//		DateUtil test =new DateUtil();
//		System.out.println(test.nowdaysBetween(test.testToDate("2013-10-25", 0), 200));
//		
//	}
   
 

}

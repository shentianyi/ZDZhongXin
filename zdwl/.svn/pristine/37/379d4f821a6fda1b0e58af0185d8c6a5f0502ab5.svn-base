package com.zd.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CommonUtil {
	/** 
	 * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询 
	 */  
	public static String converToString(String[] ig) {  
	    String str = "";  
	    if (ig != null && ig.length > 0) {  
	        for (int i = 0; i < ig.length; i++) {  
	            str += ig[i] + ",";  
	        }  
	    }  
	    str = str.substring(0, str.length() - 1);  
	    return str;  
	}  
	  
	/** 
	 * @Description:把list转换为一个用逗号分隔的字符串 
	 */  
	public static String listToString(List list) {  
	    StringBuilder sb = new StringBuilder();  
	    if (list != null && list.size() > 0) {  
	        for (int i = 0; i < list.size(); i++) {  
	            if (i < list.size() - 1) {  
	                sb.append(list.get(i) + ",");  
	            } else {  
	                sb.append(list.get(i));  
	            }  
	        }  
	    }  
	    return sb.toString();  
	}
	
	/* 
	  * 计算两个日期相差的月份数
	  *
	  * @param date1 日期1
	  * @param date2 日期2
	  * @param pattern 日期1和日期2的日期格式
	  * @return 相差的月份数
	  * @throws ParseException
	  Author:LSF
	  Date:20170513
	  */
	public static int countMonths(String date1,String date2,String pattern) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));
		int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		//开始日期若小月结束日期
		if(year<0){
			year=-year;
			return year*12+month1-month2;
		}else if (year == 0) {
			return month1-month2;
		}else{
			return year*12+month2-month1;
		}
	}
}

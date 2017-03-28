package com.zd.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 时间类工具
 * 用于进行各种类型的时间对象转换
 * 将事件转换为各种格式的字符串
 * */
public class TimestampUtil {
	
	/**
	 * 将传入Timestamp对象按指定的格式转为字符串
	 * @param timestamp 时间对象
	 * @param pattern 时间格式，例：yyyy-MM-dd hh:mm:ss
	 * @return Timestamp
	 * */
	public static String parseTimestampToString(Timestamp timestamp, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(timestamp);
	}
	
	/**
	 * 将传入字符串按指定的格式转为Timestamp对象
	 * @param date 时间字符串
	 * @param pattern 时间格式，例：yyyy-MM-dd hh:mm:ss
	 * @return Timestamp
	 * */
	public static Timestamp parseStringToTimestamp(String date, String pattern) throws ParseException {
		if(date == null || "".equals(date.trim())){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Timestamp ts = null;
		Date d = sdf.parse(date);	
		if(d != null){
			ts = new Timestamp(d.getTime());
		}
		return ts;
	}
	
	/**
	 * 将传入字符串按指定的格式转为Date对象
	 * @param date 时间字符串
	 * @param pattern 时间格式，例：yyyy-MM-dd hh:mm:ss
	 * @return Date
	 * */
	public static Date parseStringToDate(String date, String pattern){
		if(date == null || "".equals(date.trim())){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = sdf.parse(date);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 将传入字符串转为Timestamp对象
	 * @param str 时间字符串，支持以下格式：yyyy-MM-dd hh:mm:ss,yyyy-MM-dd hh:mm，yyyy-MM-dd hh，yyyy-MM-dd
	 * @return Timestamp
	 * */
	public static Timestamp getTimestamp(String str) throws ParseException{
		if(StringUtils.isBlank(str)){
			return null;
		}
		
		if(str.length()==4){
			str += "-01-01 00:00:00";
		} else if(str.length()==7){
			str += "-01 00:00:00";
		} else if(str.length()==10){
			str += " 00:00:00";
		} else if(str.length()==13){
			str += ":00:00";
		} else if(str.length()==16){
			str += ":00";
		}
		
		return parseStringToTimestamp(str,"yyyy-MM-dd hh:mm:ss");
	}
	
	/**
	 * 将传入字符串转为Timestamp对象
	 * @param year 年
	 * @return Timestamp
	 * */
	public static Timestamp timestamp(String year) throws ParseException{
	 	return timestamp(year,null, null,null, null, null);
	}
	
	/**
	 * 将传入字符串转为Timestamp对象
	 * @param year 年
	 * @param month 月
	 * @return Timestamp
	 * */
	public static Timestamp timestamp(String year,String month) throws ParseException{
		return timestamp(year,month, null,null, null, null);
	}
	
	/**
	 * 将传入字符串转为Timestamp对象
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return Timestamp
	 * */
	public static Timestamp timestamp(String year,String month, String day) throws ParseException{
		return timestamp(year,month, day,null, null, null);
	}
	
	/**
	 * 将传入字符串转为Timestamp对象
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 时
	 * @return Timestamp
	 * */
	public static Timestamp timestamp(String year,String month, String day,String hour) throws ParseException{
		return timestamp(year,month, day,hour, null, null);
	}
	
	/**
	 * 将传入字符串转为Timestamp对象
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 时
	 * @param minute 分
	 * @return Timestamp
	 * */
	public static Timestamp timestamp(String year,String month, String day,String hour, String minute) throws ParseException{
		return timestamp(year,month, day,hour, minute, null);
	}
	
	/**
	 * 将传入字符串转为Timestamp对象
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 时
	 * @param minute 分
	 * @param seconds 秒
	 * @return Timestamp
	 * */
	public static Timestamp timestamp(String year,String month, String day,String hour, String minute, String seconds) throws ParseException{
		if(StringUtils.isBlank(month)){
				month = "01";
		}
		if(StringUtils.isBlank(day)){
			day = "01";
		}
		if(StringUtils.isBlank(hour)){
			hour = "00";
		}
		if(StringUtils.isBlank(minute)){
			minute = "00";
		}
		if(StringUtils.isBlank(seconds)){
			seconds = "00";
		}
		
		return parseStringToTimestamp(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+seconds,"yyyy-MM-dd hh:mm:ss");
	}
	
	/**
	 * 获得一个月内的全部天数时间对象
	 * @param timestamp 时间对象
	 * @param pattern 时间格式，例：yyyy-MM-dd hh:mm:ss
	 * @return Timestamp
	 * */
	public static List<Timestamp> dayListByMonth(int year,int month){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		if(year<0 
		|| month<0
		|| month>13){
			return null;
		}
		
		List<Timestamp> dayList = new ArrayList<Timestamp>();
		
		String yearStr = year+"";
		String monthStr = month+"";
		if(monthStr.length()<2){
			monthStr = "0"+monthStr;
		}
		
		int i=1;
		
		while(i<=31){
			String dayStr = i+"";
			if(dayStr.length()<2){
				dayStr = "0"+dayStr;
			}
			
			try {
				Date date1 = format.parse(yearStr+"-"+monthStr+"-"+dayStr);
				dayList.add(new Timestamp(date1.getTime()));
			} catch(ParseException e){
				break;
			}
			i++;
		}
		return dayList;
	}
	
	/**
	 * 获得某月包含天数
	 * @param year 年
	 * @param month 月
	 * @return int
	 * */
	public static int getDayCount(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获得某天是周几
	 * @param year 年
	 * @param month 月
	 * @param date 日
	 * @return int 0为周日，其余数字表示周1-6
	 * */
	public static int getDayOfWeek(Date date) throws ParseException {
		Calendar calendar =Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	/**
	 * 转换时间为中文格式
	 * @param date时间对象
	 * @return String yyyy年MM月dd日 星期一
	 * */
    public static String formatChinaStr(Date date) throws ParseException {

		Calendar calendar =Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		
		String weekStr = "";
		
		if(week==1){
			weekStr="星期日";
		}
		else if(week==2){
			weekStr="星期一";
		}
		else if(week==3){
			weekStr="星期二";
		}
		else if(week==4){
			weekStr="星期三";
		}
		else if(week==5){
			weekStr="星期四";
		}
		else if(week==6){
			weekStr="星期五";
		}
		else if(week==7){
			weekStr="星期六";
		}
		
		String tody_str = "";
		tody_str += year + "年";
		tody_str += (month + 1) + "月";
		tody_str += day + "日";
		tody_str += " " + weekStr;
		
		return tody_str;
    }
}

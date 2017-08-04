package com.zd.csms.zxbank.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.zd.csms.util.DAOManager;
import com.zd.tools.StringUtil;

/**
 * ZXDateUtil
 * 
 * @author caizhuo
 * 
 */
public class DateUtil {
	public static final String FORMAT_LONGDATETIME = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String FORMAT_SHORTDATETIME = "yyyy-MM-dd";
	public static final String FORMAT_LONGDATETIME_COMPACT = "yyyyMMddHHmmssSSS";
	public static final String FORMAT_SHORTDATETIME_COMPACT = "yyyyMMdd";

	public static final SimpleDateFormat[] formats = new SimpleDateFormat[] {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyyMMddHHmmss"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy/MM/dd"),
			new SimpleDateFormat("yyyyMMdd"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
			new SimpleDateFormat("yyyyMMddHHmmssSSS"),
			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS") };

	private DateUtil() {
	}
	
	public static Date StringToDate(String date) {
		Date d = null;
		for (SimpleDateFormat format : formats) {
			try {
				d = format.parse(date);
				return d;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return null;

	}

	// 当天的凌晨时间 如：2009-08-08 00:00:00 000
	public static Timestamp getWeeHoursDateTime() {
		Calendar begin = Calendar.getInstance();
		begin.setTimeInMillis(System.currentTimeMillis());
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		begin.set(Calendar.MILLISECOND, 0);
		Timestamp time = new Timestamp(begin.getTimeInMillis());
		return time;
	}

	public static java.sql.Timestamp getNowDateTime() {
		java.sql.Timestamp curDateTime = getMasterDateTime();
		return curDateTime;
	}

	public static java.util.Date getNowDate() {
		java.util.Date curDateTime = getMasterDateTime();
		return curDateTime;
	}

	public static Timestamp getBeginDate(String dateString) {
		if (StringUtil.isBlank(dateString)) {
			return null;
		}

		return getDateTimeFormatByString(dateString);
	}

	/**
	 * 日期值加一返回
	 * 
	 * @param dateString
	 * @return
	 */
	public static Timestamp getEndDate(String dateString) {
		Timestamp end = getBeginDate(dateString);
		if (end == null)
			return end;

		return nowDateTimeChange(end, Calendar.DATE, 1);
	}

	public static java.sql.Timestamp nowDateTimeChange(int oFlag, int amount) {
		java.sql.Timestamp curDateTime = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getMasterDateTime());
			cal.add(oFlag, amount);
			curDateTime = new java.sql.Timestamp(cal.getTimeInMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curDateTime;
	}

	public static java.sql.Timestamp nowDateTimeChange(
			java.sql.Timestamp nowDateTime, int oFlag, int amount) {
		java.sql.Timestamp curDateTime = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(nowDateTime);
			cal.add(oFlag, amount);
			curDateTime = new java.sql.Timestamp(cal.getTimeInMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curDateTime;
	}

	public static java.util.Date nowDateChange(int oFlag, int amount) {
		java.util.Date curDateTime = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getMasterDateTime());
			cal.add(oFlag, amount);
			curDateTime = new java.util.Date(cal.getTimeInMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curDateTime;
	}

	public static java.util.Date nowDateChange(java.util.Date nowDate,
			int oFlag, int amount) {
		java.util.Date curDateTime = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(nowDate);
			cal.add(oFlag, amount);
			curDateTime = new java.util.Date(cal.getTimeInMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curDateTime;
	}

	/**
	 * 取得上一个月的第一天
	 * 
	 * @return 上一个月的第一天
	 */
	public static Timestamp lastMothFirstDateTime() {
		Timestamp lastMothFirstDateTime = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getMasterDateTime());
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
			StringBuilder dateBuilder = new StringBuilder();
			dateBuilder.append(cal.get(Calendar.YEAR)).append("-")
					.append(cal.get(Calendar.MONTH) + 1).append("-01 00:00:00");
			lastMothFirstDateTime = DateUtil.getDateTimeFormatByString(
					dateBuilder.toString(), DateUtil.FORMAT_LONGDATETIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastMothFirstDateTime;
	}

	/**
	 * 取得上一个月的最后一天
	 * 
	 * @return 上一个月的最后一天
	 */
	public static Timestamp lastMotLastDateTime() {
		Timestamp lastMothFirstDateTime = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getMasterDateTime());
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			StringBuilder dateBuilder = new StringBuilder();
			dateBuilder.append(cal.get(Calendar.YEAR)).append("-")
					.append(cal.get(Calendar.MONTH) + 1).append("-")
					.append(cal.get(Calendar.DATE)).append(" 00:00:00");
			lastMothFirstDateTime = DateUtil.getDateTimeFormatByString(
					dateBuilder.toString(), DateUtil.FORMAT_LONGDATETIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastMothFirstDateTime;
	}

	public static int compareDateTime(java.util.Date firstDateTime,
			java.util.Date secondDateTime) {
		int rlt = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(firstDateTime);
			long l1 = cal.getTimeInMillis();
			cal.setTime(secondDateTime);
			long l2 = cal.getTimeInMillis();
			long rr = l1 - l2;
			if (rr == 0) {
				return 0;
			}
			if (rr < 0) {
				return -1;
			}
			if (rr > 0) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlt;
	}

	private static java.sql.Timestamp formatDateTime(String argDate,
			String fFlag, Locale locale) {
		java.sql.Timestamp tt = null;
		try {
			if (locale == null) {
				locale = Locale.getDefault();
			}
			java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(
					fFlag, locale);
			java.util.Date date = new java.util.Date(argDate);
			String s = objSDF.format(date);
			int lng = s.trim().length();
			objSDF.applyPattern(fFlag.trim().substring(0, lng));
			tt = new java.sql.Timestamp(objSDF.parse(s).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tt;

	}

	public static java.sql.Timestamp getDateTimeFormatByString(String argDate) {
		return getDateTimeFormatByString(argDate, "yyyy-MM-dd HH:mm:ss.S");
	}

	public static java.sql.Timestamp getDateTimeFormatByString(String argDate,
			String fFlag) {
		java.sql.Timestamp tt = null;
		try {
			int lng = argDate.trim().length();
			java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(
					fFlag);
			objSDF.applyPattern(fFlag.trim().substring(0, lng));
			tt = new java.sql.Timestamp(objSDF.parse(argDate).getTime());
		} catch (Exception e) {
			try {
				tt = formatDateTime(argDate, fFlag, null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return tt;
	}

	public static void main(String[] args) {
		System.out.println(StringToDate("19960209"));
	}

	public static java.sql.Timestamp getDateTimeFormatByString(String argDate,
			String fFlag, Locale locale) {
		java.sql.Timestamp tt = null;
		try {
			int lng = argDate.trim().length();
			java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(
					fFlag, locale);
			objSDF.applyPattern(fFlag.trim().substring(0, lng));
			tt = new java.sql.Timestamp(objSDF.parse(argDate).getTime());
		} catch (Exception e) {
			try {
				tt = formatDateTime(argDate, fFlag, locale);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return tt;
	}

	public static java.util.Date getDateFormatByString(String argDate,
			String fFlag) {
		java.util.Date tt = null;
		try {
			int lng = argDate.trim().length();
			java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(
					fFlag);
			objSDF.applyPattern(fFlag.trim().substring(0, lng));
			tt = new java.util.Date(objSDF.parse(argDate).getTime());
		} catch (Exception e) {
			try {
				tt = formatDateTime(argDate, fFlag, null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return tt;
	}

	public static java.util.Date getDateFormatByString(String argDate,
			String fFlag, Locale locale) {
		java.util.Date tt = null;
		try {
			int lng = argDate.trim().length();
			java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(
					fFlag, locale);
			objSDF.applyPattern(fFlag.trim().substring(0, lng));
			tt = new java.util.Date(objSDF.parse(argDate).getTime());
		} catch (Exception e) {
			try {
				tt = formatDateTime(argDate, fFlag, locale);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return tt;
	}

	public static String getStringFormatByTimestamp(java.sql.Timestamp argDate,
			String fFlag) {
		String strDateTime = "";
		try {
			java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(
					fFlag.trim());
			strDateTime = objSDF.format(argDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDateTime;
	}

	public static String getStringFormatByDate(java.util.Date argDate,
			String fFlag) {
		String strDateTime = "";
		try {
			java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(
					fFlag.trim());
			if (argDate != null) {
				strDateTime = objSDF.format(argDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDateTime;
	}

	public static java.sql.Timestamp getMasterDateTime() {
		java.sql.Timestamp value = new java.sql.Timestamp(Calendar
				.getInstance().getTimeInMillis());
		return value;
	}

	public static java.sql.Date getMasterDateTime2() {
		java.sql.Date value = new java.sql.Date(getMasterDateTime().getTime());
		return value;
	}

	public static boolean judgeDate(String argDate) {
		boolean tag = false;
		try {
			java.sql.Timestamp tt = getDateTimeFormatByString(argDate,
					FORMAT_LONGDATETIME);
			tag = true;
		} catch (Exception e) {
			tag = false;
		}
		return tag;
	}

	public static String[] getStringArrayFormatByString(String argDate,
			String regex) {
		// 0:年,1:月,2:日
		String result[] = argDate.split(regex);
		// 去零操作,如出现:2007-06-02
		for (int i = 0; i < result.length; i++) {
			if (result[i].startsWith("0")) {
				int idx = result[i].indexOf("0");
				result[i] = result[i].substring(idx);
			}
		}
		return result;
	}

	public static int[] getIntArrayFormatByString(String argDate, String regex) {
		String strResult[] = getStringArrayFormatByString(argDate, regex);
		int intResult[] = new int[3];
		for (int i = 0; i < intResult.length; i++) {
			intResult[i] = Integer.parseInt(strResult[i]);
		}
		return intResult;
	}

	/**
	 * 取得当前数据库的系统时间函数
	 * 
	 * @return 数据时间函数
	 * @throws Exception
	 */
	public static String getNowFunc() {
		String func = null;
		try {
			if (DAOManager.getDataBaseType().equals("oracle")) {
				func = "sysdate";
			} else if (DAOManager.getDataBaseType().equals("mysql")) {
				func = "now()";
			} else {
				throw new Exception(
						"not supported. currently only supports oracle/mysql");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return func;
	}

	/**
	 * 把字符串转换成日期，如: oracle 写法：to_date('2008-06-07 17:02:23','yyyy-MM-DD
	 * HH24:MI:SS') mysql 写法： timestamp('2008-06-07 17:02:23')
	 * 
	 * @param date
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static String getToDateString(String date, String format)
			throws Exception {
		StringBuffer sqlBuffer = new StringBuffer();
		if (DAOManager.getDataBaseType().equals("oracle")) {
			sqlBuffer.append(" to_date('");
			sqlBuffer.append(date);
			sqlBuffer.append("','");
			sqlBuffer.append(format);
			sqlBuffer.append("') ");
			return sqlBuffer.toString();
		} else if (DAOManager.getDataBaseType().equals("mysql")) {
			sqlBuffer.append(" timestamp('");
			sqlBuffer.append(date);
			sqlBuffer.append("') ");
			return sqlBuffer.toString();
		} else {
			throw new Exception(
					"not supported. currently only supports oracle/mysql");
		}
	}

	/**
	 * 把字符串转换成日期，如: oracle 写法：to_date('2008-06-07 17:02:23','yyyy-MM-DD
	 * HH24:MI:SS') mysql 写法： timestamp('2008-06-07 17:02:23')
	 * 
	 * @param date
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static String getToDateString(Timestamp date, String format)
			throws Exception {
		StringBuffer sqlBuffer = new StringBuffer();
		if (DAOManager.getDataBaseType().equals("oracle")) {
			sqlBuffer.append(" to_date('");
			sqlBuffer.append(DateUtil.getStringFormatByTimestamp(date, format));
			sqlBuffer.append("','");
			sqlBuffer.append(format);
			sqlBuffer.append("') ");
			return sqlBuffer.toString();
		} else if (DAOManager.getDataBaseType().equals("mysql")) {
			sqlBuffer.append(" timestamp('");
			sqlBuffer.append(DateUtil.getStringFormatByTimestamp(date, format));
			sqlBuffer.append("') ");
			return sqlBuffer.toString();
		} else {
			throw new Exception(
					"not supported. currently only supports oracle/mysql");
		}
	}

	/**
	 * 获取天或月或者周的时间
	 * 
	 * @param selType
	 *            通过类型(curDay,curMonth,curWeek)
	 * @return
	 */
	public static List<Timestamp> getDayOrMonthOrWeek(String selType) {
		List<Timestamp> timestampList = new ArrayList<Timestamp>();
		// 日历对象
		Calendar calendar = Calendar.getInstance();
		// 暂时月或日或周的存贮
		String dymdStr = "";
		// 返回天或月的开始及结束时间
		String dmwFirstAndEndStr[] = new String[2];
		// 天的开始时
		String dayBeginTime = " 00:00:00";
		// 天的结束时
		String dayEndTime = " 23:59:59";
		// 月的开始时
		String monthBeginTime = "-01 00:00:00";
		// 按日查
		if (selType != null) {
			if (selType.trim().equalsIgnoreCase("curDay")) {
				StringBuffer sb = new StringBuffer(8);
				sb.append(calendar.get(Calendar.YEAR));
				sb.append("-");
				sb.append(one2TwoDigit((calendar.get(Calendar.MONTH) + 1)));
				sb.append("-");
				sb.append(one2TwoDigit(calendar.get(Calendar.DAY_OF_MONTH)));
				dymdStr = sb.toString();

				dmwFirstAndEndStr[0] = dymdStr + dayBeginTime;
				dmwFirstAndEndStr[1] = dymdStr + dayEndTime;
			}
			// 按月查
			if (selType.trim().equalsIgnoreCase("curMonth")) {
				StringBuffer sb = new StringBuffer(10);
				sb.append(calendar.get(Calendar.YEAR));
				sb.append("-");
				sb.append(one2TwoDigit((calendar.get(Calendar.MONTH) + 1)));
				dymdStr = sb.toString();

				calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
				calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
				int MaxDate = calendar.get(Calendar.DATE);

				dmwFirstAndEndStr[0] = dymdStr + monthBeginTime;
				dmwFirstAndEndStr[1] = dymdStr + "-" + MaxDate + dayEndTime;
			}
			// 按周查
			if (selType.trim().equalsIgnoreCase("curWeek")) {
				Calendar startDate = (Calendar) calendar.clone();
				Calendar endDate = (Calendar) calendar.clone();
				int nowWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
				startDate.add(Calendar.DATE, 1 - nowWeekNum);
				endDate.add(Calendar.DATE, 7 - nowWeekNum);
				// 开始时间
				StringBuffer startSB = new StringBuffer(8);
				startSB.append(startDate.get(Calendar.YEAR));
				startSB.append("-");
				startSB.append(one2TwoDigit((startDate.get(Calendar.MONTH) + 1)));
				startSB.append("-");
				startSB.append(one2TwoDigit(startDate
						.get(Calendar.DAY_OF_MONTH)));
				startSB.append(dayBeginTime);

				// 结束时间
				StringBuffer endSB = new StringBuffer(8);
				endSB.append(endDate.get(Calendar.YEAR));
				endSB.append("-");
				endSB.append(one2TwoDigit((endDate.get(Calendar.MONTH) + 1)));
				endSB.append("-");
				endSB.append(one2TwoDigit(endDate.get(Calendar.DAY_OF_MONTH)));
				endSB.append(dayEndTime);

				dmwFirstAndEndStr[0] = startSB.toString();
				dmwFirstAndEndStr[1] = endSB.toString();
			}
		}
		timestampList.add(Timestamp.valueOf(dmwFirstAndEndStr[0]));
		timestampList.add(Timestamp.valueOf(dmwFirstAndEndStr[1]));

		return timestampList;
	}

	/**
	 * 一位转两位，如 1 则转为 01 ,即在一位前补零
	 * 
	 * @param one
	 * @return
	 */
	private static String one2TwoDigit(int one) {
		if (one / 10 >= 1)
			return String.valueOf(one);
		return "0" + String.valueOf(one);
	}

	/**
	 * <p>
	 * 方法名称: dateTimeErrand|描述: 计算两个时间，返回差值
	 * </p>
	 * 
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws ParseException
	 * @author: jason.Lee
	 * @date: 2014-4-2 下午07:00:59
	 */
	public static String dateTimeErrand(String starttime, String endtime)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date end = df.parse(endtime);
		java.util.Date start = df.parse(starttime);
		long l = end.getTime() - start.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return day + ":" + hour + ":" + min + ":" + s;
	}

	public static Date addMonth(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, amount);
		return c.getTime();
	}

	/**
	 * 时间格式类型组
	 */
	final static String[] pattern = new String[] { "yyyy-MM-dd HH:mm:ss.SSS",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyyMMddHHmmss",
			"yyyyMMddHHmmssSSS", "yyyyMMddHHmmssSSS", "yyyyMMdd", "yyMMdd",
			"yyyy-MM-dd" };

	public static int MinutesBetWeen(Date first, Date second) throws Exception {
		if (first == null || second == null) {
			throw new Exception("Parameters cannot be empty");
		}
		int min = (int) ((first.getTime() / (1000 * 60)) - (second.getTime() / (1000 * 60)));
		return min;
	}

	public static Date getLastDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}
}

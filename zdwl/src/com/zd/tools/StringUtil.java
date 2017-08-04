package com.zd.tools;

import java.util.List;

/**
 * String工具类
 * 用于常见的字符串判断及类型转换
 */
public class StringUtil {

	private StringUtil() {
	}

	/**
	 * 判断字符为空或空串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

	/**
	 * 判断字符为空或空串或内容为空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (str == null) || (str.length() == 0) || ("".equals(str.replaceAll(" ", "")));
	}

	/**
	 * 判断字符串是否是纯数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isBlank(str)) {
			return false;
		}

		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((!Character.isDigit(str.charAt(i))) && (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断字符串是否是纯数字(允许包含小数点('.')但只能有一个)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumberD(String str) {
		if (isBlank(str)) {
			return false;
		}
		
		int sz = str.length();
		int count = 0;
		for (int i = 0; i < sz; i++) {
			char c = str.charAt(i);
			if (count > 1) {
				return false;
			}
			if (c == '.') {
				count++;
				continue;
			}
			if (i == 0 && c == '-') {
				continue;
			}
			if ((!Character.isDigit(c)) && (c != ' ')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将字符串转换成double数值 提供转换服务
	 * 
	 * @param str
	 *            要转换成double数值的字符串
	 * @param d
	 *            如果转换失败的默认值
	 * @return
	 */
	private static Double value(String str, Number defaultNumber) {
		if (isNumberD(str)) {
			String reStr = str.replaceAll(" ", "");
			return Double.parseDouble(reStr);
		}

		return defaultNumber.doubleValue();
	}

	/**
	 * 将字符串转换成double数值
	 * 
	 * @param str
	 *            要转换成double数值的字符串
	 * @param d
	 *            如果转换失败的默认值
	 * @return
	 */
	public static double doubleValue(String str, double defaultDoubleValue) {
		return value(str, defaultDoubleValue);
	}

	/**
	 * 将字符串转换成int数值
	 * 
	 * @param str
	 *            要转换成int数值的字符串
	 * @param defaultIntValue
	 *            如果转换失败的默认值
	 * @return
	 */
	public static int intValue(String str, int defaultIntValue) {
		Double d = null;
		d = value(str, defaultIntValue);

		if (d != null && d.intValue() <= Integer.MAX_VALUE) {
			return d.intValue();
		}
		return defaultIntValue;  
	}

	/**
	 * 将字符串转换成long数值
	 * 
	 * @param str
	 *            要转换成long数值的字符串
	 * @param defaultLongValue
	 *            如果转换失败的默认值
	 * @returnlong
	 */
	public static long longValue(String str, long defaultLongValue) {
		Double d = null;
		d = value(str, defaultLongValue);

		if (d != null && d <= Long.MAX_VALUE) {
			return d.longValue();
		}
		return defaultLongValue;
	}
	
	/**
	 * 用于对录入数据中可能产生Xss攻击的字符替换为其它占位符
	 * @param str 要替换的字符串
	 * @return String
	 * */
	public static String replaceXssCharacter(String str){
		if(str==null){
			return str;
		}
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot");
		str = str.replaceAll("'", "‘");
		return str;
	}
	
	public static String ArrayToString(List<String> list){
		if(list == null || list.size()<=0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str+",");
		}
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	public static String ArrayToString(String[] list){
		if(list == null || list.length<=0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str+",");
		}
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	public static boolean isNull(Integer num){
		boolean flag = false;
		if (null == num || num <= 0) {
			flag = true;
		}
		return flag;
	}
	
	public static boolean isNull(Integer...nums){
		boolean flag = false;
		if (null == nums || nums.length <= 0){
			flag = true;
		}else {
			for (Integer num : nums) {
				if (isNull(num)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
}


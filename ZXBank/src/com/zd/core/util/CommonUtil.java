package com.zd.core.util;

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
}

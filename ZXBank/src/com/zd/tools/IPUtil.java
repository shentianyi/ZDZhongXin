package com.zd.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * 提供方法通过Request获取客户端或服务器端ip。
 * */
public class IPUtil {
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return ip
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip;

	}
	
	/**
	 * 获取服务器ip
	 * @param request
	 * @return ip
	 */
	public static String getHostAddress(HttpServletRequest request) {
		String ip = null;
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

}

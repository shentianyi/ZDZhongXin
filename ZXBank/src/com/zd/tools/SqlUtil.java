package com.zd.tools;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zd.core.BeanManager;
import com.zd.core.Constants;

/**
 * sql语句运行状态打印工具
 * */
public class SqlUtil {

	/**
	 * 打印SQL，用于调试
	 * @param String dataSourceName	数据库连接名
	 * @param String sql	调试sql
	 * @param Object[] param	调试sql参数
	 * @param String flag	调试提示文字
	 * @return void
	 * */
	public static void debug(String dataSourceName, String sql,Object[] param,String flag){
		
		if(flag==null){
			flag="";
		}
		else{
			flag += ":";
		}
		
		String[] sqlStrs = splitSQL(sql);
		StringBuffer newSQL= new StringBuffer();
		
		for(int i=0;i<sqlStrs.length;i++){
			newSQL.append(sqlStrs[i]);
			if(i!=sqlStrs.length-1 && param.length>i){
				newSQL.append(getSQLValue(dataSourceName,param[i]));
			}
		}
		
		newSQL.append("\n").append(getCaller());
		
		System.out.println(flag+newSQL);
		Logger.getRootLogger().debug(flag+newSQL);
	}

	public static void debug(String dataSourceName, String sql,Object[] param){
		debug(dataSourceName,sql,param,null);
	}

	public static void debug(String dataSourceName, String sql,String flag){
		debug(dataSourceName,sql,new Object[]{},flag);
	}

	public static void debug(String dataSourceName, String sql){
		debug(dataSourceName,sql,new Object[]{},null);
	}
	
	private static String[] splitSQL(String sql){
		List<String> list = new ArrayList<String>();
		while(sql.indexOf("?")>-1){
			list.add(sql.substring(0,sql.indexOf("?")));
			sql=sql.substring(sql.indexOf("?")+1);
		}
		list.add(sql);
		String[] sqlStrs = new String[list.size()];
		for(int i=0;i<list.size();i++){
			sqlStrs[i]=(String)list.get(i);
		}
		return sqlStrs;
	}
	
	private static String getSQLValue(String dataSourceName, Object obj){
		
		if(obj==null){
			return null;
		}
		if(obj instanceof String){
			return " '"+obj.toString()+"' ";
		}

		String dbDriver = BeanManager.getDbDriver(dataSourceName);
		
		if(obj instanceof Timestamp){
			if(Constants.DB_DRIVER_MYSQL.getCode().equals(dbDriver)){
				return " '"+obj.toString().substring(0,19)+"' ";
			}
			if(Constants.DB_DRIVER_ORACLE.getCode().equals(dbDriver)){
				return " to_date('"+obj.toString().substring(0,19)+"','yyyy-MM-dd hh24:mi:ss') ";
			}
		}
		return obj.toString();
	}
	
	/**
	 * 获取调用者
	 * @return String
	 */
	private static String getCaller() {
		
		StringBuffer result = new StringBuffer();
		
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		
		boolean flag = false;
		StackTraceElement s;
		for (int i = 0; i < stack.length; i++) {
			if(i > 1){
				s = stack[i];
				if(s.getClassName().indexOf("com.comall")==0){
					if(flag){
						result.append("\n");
					}
					result.append(s.getClassName()).append(".").append(s.getMethodName()).append(":").append(s.getLineNumber());
					flag = true;
				}
			}
		}
		return result.toString();
	}
}

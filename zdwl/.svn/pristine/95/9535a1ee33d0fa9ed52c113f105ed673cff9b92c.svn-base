package com.zd.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统配置文件管理类，专门用于获得系统配置文件属性。
 * */
public class SystemProperty {
	
	private static Map<String,Properties> files = new ConcurrentHashMap<String,Properties>();
	private static Properties _null = new Properties();
	
	/**
	 * 获取某配置文件中的属性
	 * @param String filepath 文件名及路径，以WEB-INF为根目录
	 * @param String property 属性名
	 * @return String
	 * */
	public static String getPropertyValue(String filepath,String property){
		
		Properties properties = files.get(filepath);
		if(properties==_null){
			return null;
		} else if(properties==null){
			properties = new Properties();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(new File(System.getProperty(Constants.ROOT_WEBAPP.getCode()) + Constants.ROOT_CONFIG.getCode() + filepath));
//				fis = new FileInputStream(new File(System.getProperty("csms.root") +Constants.ROOT_CONFIG.getCode() + filepath));
				properties.load(fis);
				files.put(filepath, properties);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				files.put(filepath, _null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				files.put(filepath, _null);
			}finally{
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return properties.getProperty(property);
	}
  
}

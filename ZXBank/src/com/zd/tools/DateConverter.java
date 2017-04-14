package com.zd.tools;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter{
	/**
     * 日期格式化对象.
     */
    private static SimpleDateFormat df=new SimpleDateFormat();

	/**
     * 模式集合.
     */
    private static Set<String> patterns=new HashSet<String>();
	// 注册一下日期的转换格式
	static {
		DateConverter.patterns.add("yyyy-MM-dd");
		DateConverter.patterns.add("yyyy-MM-dd HH:mm");
		DateConverter.patterns.add("yyyy-MM-dd HH:mm:ss");
		DateConverter.patterns.add("yyyy/MM/dd HH:mm:ss");
	}

	/**
     * 日期转换器.
     * @param type Class
     * @param value Object
     * return Date Object.
     */
    public Object convert(Class type,Object value){
        if(value== null){
            return null;
        }else if(value instanceof String){
            Object dateObj = null;
            Iterator it = patterns.iterator();
            while(it.hasNext()){
                try{
                    String pattern =(String)it.next();
                    df.applyPattern(pattern);
                    dateObj = df.parse((String)value);
                    break;
                }catch(Exception ex){
                    continue;
                }
            }
            return dateObj;
        }else{
            return null;
        }
    }
}

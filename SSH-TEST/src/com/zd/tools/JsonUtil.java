package com.zd.tools;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * @author </br> <a href="mailto:fx19800215@163.com"> robert.feng</a>
 *
 */
public class JsonUtil {

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Object getObject4JsonString(String jsonString,Class pojoCalss){
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject( jsonString );  
        pojo = JSONObject.toBean(jsonObject,pojoCalss);
        return pojo;
    }
    
    
    
    /**
     * 从json HASH表达式中获取?map，改map支持嵌套功能
     * @param jsonString
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map getMap4Json(String jsonString){
        JSONObject jsonObject = JSONObject.fromObject( jsonString );  
        Iterator  keyIter = jsonObject.keys();
        String key;
        Object value;
        Map valueMap = new HashMap();

        while( keyIter.hasNext())
        {
            key = (String)keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key.toLowerCase(), value);
        }
        
        return valueMap;
    }
    
    
    /**
     * 从json数组中得到相应java数组
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
        
    }
    
    
    /**
     * 从json对象集合表达式中得到?java对象列表
     * @param jsonString
     * @param pojoClass
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List getList4Json(String jsonString, Class pojoClass){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;
        
        List list = new ArrayList();
        for ( int i = 0 ; i<jsonArray.size(); i++){
            
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject,pojoClass);
            list.add(pojoValue);
            
        }
        return list;

    }
    
    /**
     * 从json数组中解析出java字符串数?
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            stringArray[i] = jsonArray.getString(i);
            
        }
        
        return stringArray;
    }
    
    /**
     * 从json数组中解析出javaLong型对象数?
     * @param jsonString
     * @return
     */
    public static Long[] getLongArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            longArray[i] = jsonArray.getLong(i);
            
        }
        return longArray;
    }
    
    /**
     * 从json数组中解析出java Integer型对象数?
     * @param jsonString
     * @return
     */
    public static Integer[] getIntegerArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            integerArray[i] = jsonArray.getInt(i);
            
        }
        return integerArray;
    }
    
    /**
     * 从json数组中解析出java Date 型对象数组，使用本方法必须保?
     * @param jsonString
     * @return
     */
    public static Timestamp[] getDateArray4Json(String jsonString,String DataFormat){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Timestamp[] dateArray = new Timestamp[jsonArray.size()];
        String dateString;
        Timestamp date;
        
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            dateString = jsonArray.getString(i);
            try{
            	date = TimestampUtil.getTimestamp(dateString);
            	dateArray[i] = date;
			}catch (Exception e){
				e.printStackTrace();
			}
        }
        return dateArray;
    }
    
    /**
     * 从json数组中解析出java Integer型对象数?
     * @param jsonString
     * @return
     */
    public static Double[] getDoubleArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            doubleArray[i] = jsonArray.getDouble(i);
            
        }
        return doubleArray;
    }
    
    
    /**
     * 将java对象转换成json字符?
     * @param javaObj
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj){
        
        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();
        
    }
    
    
    
    
    /**
     * 将java对象转换成json字符?并设定日期格?
     * @param javaObj
     * @param dataFormat
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj , String dataFormat){
        
        JSONObject json;
        JsonConfig jsonConfig = configJson(dataFormat);
        json = JSONObject.fromObject(javaObj,jsonConfig);
        return json.toString();
        
        
    }
    
    
    
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        // TODO 自动生成方法存根
//
//    }
    
    /**
     * JSON 时间解析器具
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String datePattern) {   
            JsonConfig jsonConfig = new JsonConfig();   
            jsonConfig.setExcludes(new String[]{""});   
            jsonConfig.setIgnoreDefaultExcludes(false);   
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
            jsonConfig.registerJsonValueProcessor(Date.class,   
                new DateJsonValueProcessor(datePattern));   
          
            return jsonConfig;   
        }  
    
    /**
     * 
     * @param excludes
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String[] excludes,   
            String datePattern) {   
            JsonConfig jsonConfig = new JsonConfig();   
            jsonConfig.setExcludes(excludes);   
            jsonConfig.setIgnoreDefaultExcludes(false);   
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
            jsonConfig.registerJsonValueProcessor(Date.class,   
                new DateJsonValueProcessor(datePattern));   
          
            return jsonConfig;   
        }  

}
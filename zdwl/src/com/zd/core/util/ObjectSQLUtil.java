package com.zd.core.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zd.core.annotation.table;
import com.zd.core.annotation.updateIgnore;

/**
 * 反射拼接sql类重构版本
 * @author ShenLiang
 * @create 2014年7月3日
 */
public class ObjectSQLUtil {
	
	//private static logger log = loggerFactory.getlogger(ObjectSQLUtil.class);
	
	/**
	 * 经过拼接的sql会缓存在此map中
	 */
	private static Map<String, String> objSQLMap = new HashMap<String, String>();
	
	/**
	 * 获取插入数据sql
	 * @param obj
	 * @return
	 */
	public static String getAdd(Object po) {
		String key = getAddKey(po.getClass().getName());
		if(objSQLMap.containsKey(key)){
			return objSQLMap.get(key);
		}
		Field[] fields = po.getClass().getDeclaredFields();
		StringBuffer sql = null;
		if( ArrayUtils.isNotEmpty(fields) ){
			sql = new StringBuffer();
			String tableName = null;
			if(po.getClass().getAnnotation(table.class) != null){
				tableName = po.getClass().getAnnotation(table.class).name();
			}else{
				//log.error("数据库字符串拼接错误，PO未加@table标记");
			}
			sql.append("insert into ");
			sql.append(tableName);
			sql.append(" (");
			int count = 0;
			for(int i = 0; i < fields.length; i ++){
				String attrName = fields[i].getName();
				if("serialVersionUID".equals(attrName)){
					continue;
				}
				if(attrName!=null&&attrName.length()>3&&attrName.substring(attrName.length()-3, attrName.length()).equals("NTT")){
					continue;
				}
				if(count>0){
					sql.append(",");
				}
				sql.append(attrName);
				count++;
			}
			sql.append(") ");
			sql.append("values(");
			sql.append("?");
			for(int i = 1;i < count; i++){
				sql.append(",?");
			}
			sql.append(")");
			objSQLMap.put(key, sql.toString());
			return sql.toString();
		}else{
			//log.error("PO对象无属性");
		}
		return null;
	}
	
	/**
	 * 获取查询sql
	 * @param obj
	 * @return
	 */
	public static String getCount(Class<?> obj) {
		String key = getCountKey(obj.getName());
		if(objSQLMap.containsKey(key)){
			return objSQLMap.get(key); 
		}
		StringBuffer sql = new StringBuffer();
		String tableName =null;
		if(obj.getAnnotation(table.class) != null){
			tableName = obj.getAnnotation(table.class).name();
		}else{
			//log.error("数据库字符串拼接错误，PO未加@table标记");
		}
		sql.append("select count(id) as totalcount from ");
		sql.append(tableName);
		objSQLMap.put(key, sql.toString());
		return sql.toString();
	}
	
	public static String getQuery(Class<?> obj) {
		String key = getPageKey(obj.getName());
		if(objSQLMap.containsKey(key)){
			return objSQLMap.get(key);
		}
		Field[] fields = obj.getDeclaredFields();
		StringBuffer sql = null;
		if( ArrayUtils.isNotEmpty(fields) ){
			sql = new StringBuffer();
			String tableName =null;
			if(obj.getAnnotation(table.class) != null){
				tableName = obj.getAnnotation(table.class).name();
			}else{
				//log.error("数据库字符串拼接错误，PO未加@table标记");
			}
			sql.append("select ");
			boolean first = true;
			for(int i = 0; i < fields.length; i ++){
				String attrName = fields[i].getName();
				if("serialVersionUID".equals(attrName)){
					continue;
				}
				if(attrName!=null&&attrName.length()>3&&attrName.substring(attrName.length()-3, attrName.length()).equals("NTT")){
					continue;
				}
				if(first){
					first = false;
				}else{
					sql.append(",");
				}
				sql.append(attrName);
			}
			sql.append(" from ");
			sql.append(tableName);
			objSQLMap.put(key, sql.toString());
			return sql.toString();
		}else{
			//log.error("PO对象无属性");
		}
		return null;
	}
	
	/**
	 * 获取查询sql
	 * @param obj
	 * @return
	 */
	public static String getQuery(Object obj) {
		String key = getQueryKey(obj.getClass().getName());
		if(objSQLMap.containsKey(key)){
			return objSQLMap.get(key);
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer sql = null;
		if( ArrayUtils.isNotEmpty(fields) ){
			sql = new StringBuffer();
			String tableName =null;
			if(obj.getClass().getAnnotation(table.class) != null){
				tableName = obj.getClass().getAnnotation(table.class).name();
			}else{
				//log.error("数据库字符串拼接错误，PO未加@table标记");
			}
			sql.append("select ");
			boolean first = true;
			for(int i = 0; i < fields.length; i ++){
				String attrName = fields[i].getName();
				if("serialVersionUID".equals(attrName)){
					continue;
				}
				if(attrName!=null&&attrName.length()>3&&attrName.substring(attrName.length()-3, attrName.length()).equals("NTT")){
					continue;
				}
				if(first){
					first = false;
				}else{
					sql.append(",");
				}
				sql.append(attrName);
			}
			sql.append(" from ");
			sql.append(tableName);
			sql.append(" where id = ");
			sql.append("? order by id desc");
			objSQLMap.put(key, sql.toString());
			return sql.toString();
		}else{
			//log.error("PO对象无属性");
		}
		return null;
	}
	
	public static <T> String getAll(Class<T> elementType){
		String key = getQueryAllKey(elementType.getName());
		if(objSQLMap.containsKey(key)){
			return objSQLMap.get(key);
		}
		Field[] fields = elementType.getDeclaredFields();
		StringBuffer sql = null;
		if( ArrayUtils.isNotEmpty(fields) ){
			sql = new StringBuffer();
			String tableName =null;
			if(elementType.getAnnotation(table.class) != null){
				tableName = elementType.getAnnotation(table.class).name();
			}else{
				//log.error("数据库字符串拼接错误，PO未加@table标记");
			}
			sql.append("select ");
			boolean first = true;
			for(int i = 0; i < fields.length; i ++){
				String attrName = fields[i].getName();
				if("serialVersionUID".equals(attrName)){
					continue;
				}
				if(attrName!=null&&attrName.length()>3&&attrName.substring(attrName.length()-3, attrName.length()).equals("NTT")){
					continue;
				}
				if(!first){
					sql.append(",");
				}else{
					first = false;
				}
				sql.append(attrName);
			}
			sql.append(" from ");
			sql.append(tableName);
			sql.append(" order by id desc");
			objSQLMap.put(key, sql.toString());
			return sql.toString();
		}else{
			//log.error("PO对象无属性");
		}
		return null;
	}
	
	/**
	 * 获取删除sql
	 * @param obj
	 * @return
	 */
	public static <T> String getDelete(Class<T> elementType) {
		String key = getDeleteKey(elementType.getName());
		if(objSQLMap.containsKey(key)){
			return objSQLMap.get(key);
		}
		StringBuffer sql = new StringBuffer();
		String tableName = elementType.getSimpleName();
		if(elementType.getAnnotation(table.class) != null){
			tableName = elementType.getAnnotation(table.class).name();
		}else{
			//log.error("数据库字符串拼接错误，PO未加@table标记");
		}
		sql.append("delete from ");
		sql.append(tableName);
		sql.append(" where id = ?");
		objSQLMap.put(key, sql.toString());
		return sql.toString();
	}
	/**
	 * 根据类名取得 对应的表明
	 * @param elementType
	 * @return
	 */
	
	public static <T> String getTableName(Class<T> elementType) {
		return elementType.getAnnotation(table.class).name();
	}
	
	/**
	 * 插入新数据时, 对象属性转化数组
	 * @param obj
	 * @return
	 */
	public static Object[] getAddParameters(Object obj) {
		return getParameterList(obj).toArray();
	}
	
	/**
	 * 修改数据时，对象属性转换为数组
	 * @param obj
	 * @return
	 */
	public static Object[] getUpdateParameters(Object obj) {
		List<Object> parameters = getUpdateParameterList(obj);
		Object id = parameters.get(0);
		parameters.remove(0);
		parameters.add(id);
		return parameters.toArray();
	}
	
	private static String getAddKey(String name){
		return name + "_add";
	}
	
	private static String getQueryKey(String name){
		return name + "_query";
	}
	
	private static String getCountKey(String name){
		return name + "_count";
	}
	
	private static String getPageKey(String name){
		return name + "_page";
	}
	
	private static String getQueryAllKey(String name){
		return name + "_queryAll";
	}
	
	private static String getUpdateKey(String name){
		return name + "_update";
	}
	
	private static String getDeleteKey(String name){
		return name + "_delete";
	}
	
	/**
	 * 获取对象值list
	 * @param obj
	 * @return
	 */
	private static List<Object> getParameterList(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<Object> parameters = new ArrayList<Object>();
		PropertyDescriptor pd = null;
		Object value = null;
		for(Field field : fields) {
			String attrName = field.getName();
			if("serialVersionUID".equals(attrName)){
				continue;
			}
			if(attrName!=null&&attrName.length()>3&&attrName.substring(attrName.length()-3, attrName.length()).equals("NTT")){
				continue;
			}
			try {
				pd = new PropertyDescriptor(attrName, obj.getClass());
			} catch (IntrospectionException e) {
				//log.error("拼接字符串异常："+e);
			}
			Method method = pd.getReadMethod();
			try {
				value = method.invoke(obj);
			} catch (Exception e) {
				//log.error("拼接字符串异常："+e);
			} 
			parameters.add(value);
		}
		return parameters;
	}
	
	private static List<Object> getUpdateParameterList(Object obj){
		Field[] fields = obj.getClass().getDeclaredFields();
		List<Object> parameters = new ArrayList<Object>();
		PropertyDescriptor pd = null;
		Object value = null;
		for(Field field : fields) {
			String attrName = field.getName();
			if("serialVersionUID".equals(attrName)){
				continue;
			}
			if(attrName!=null&&attrName.length()>3&&attrName.substring(attrName.length()-3, attrName.length()).equals("NTT")){
				continue;
			}
			if(field.getAnnotation(updateIgnore.class) != null){
				continue;
			}
			try {
				pd = new PropertyDescriptor(attrName, obj.getClass());
			} catch (IntrospectionException e) {
				//log.error("拼接字符串异常："+e);
			}
			Method method = pd.getReadMethod();
			try {
				value = method.invoke(obj);
			} catch (Exception e) {
				//log.error("拼接字符串异常："+e);
			} 
			parameters.add(value);
		}
		return parameters;
	}
	
	/**
	 * 获取更新缓存数据sql
	 * @param obj
	 * @return
	 */
	public static String getUpdate(Object obj){
		String key = getUpdateKey(obj.getClass().getName());
		if(objSQLMap.containsKey(key)){
			return objSQLMap.get(key);
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer sql = null;
		if(fields != null && fields.length > 0){
			sql = new StringBuffer();
			String tableName = null;
			if(obj.getClass().getAnnotation(table.class) != null){
				tableName = obj.getClass().getAnnotation(table.class).name();
			}else{
				//log.error("数据库字符串拼接错误，PO未加@table标记");
			}
			sql.append("update ");
			sql.append(tableName);
			sql.append(" set ");
			boolean first = true;
			for(int i = 0; i < fields.length; i ++){
				String attrName = fields[i].getName();
				if("serialVersionUID".equals(attrName) || "id".equals(attrName.toLowerCase())){
					continue;
				}
				if(attrName!=null&&attrName.length()>3&&attrName.substring(attrName.length()-3, attrName.length()).equals("NTT")){
					continue;
				}
				if(fields[i].getAnnotation(updateIgnore.class) != null){
					continue;
				}
				if(!first){
					sql.append(",");
				}else{
					first = false;
				}
				sql.append(attrName);
				sql.append(" = ? ");
			}
			sql.append(" where id = ");
			sql.append("?");
			
			objSQLMap.put(key, sql.toString());
			return sql.toString();
		}else{
			//log.error("PO对象无属性");
		}
		return null;
	}
}

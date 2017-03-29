package com.zd.tools;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import oracle.sql.TIMESTAMP;

import org.apache.log4j.Logger;

import com.zd.core.jdbc.DBMap;

/**
 * VO工具类，提供基于VO与Map转换以及VO属性取值赋值操作的工具。
 * */
public class ValueObjectUtil {
	
	private static Logger log = Logger.getLogger(ValueObjectUtil.class);
	
	/**
	 * 将VO内的属性拼成字符串输出
	 * @param Object obj 需要输出内容的obj
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public static String toString(Object obj){
		
		StringBuffer str = new StringBuffer();
		
		if(obj instanceof Map){
			
			Map mapObj = (Map)obj;
			
			Set keyset = mapObj.keySet();
			
			str.append("{");
			for(Iterator iter = keyset.iterator(); iter.hasNext();){
				String key = (String)iter.next();
				Object value = mapObj.get(key);
				if(value instanceof Timestamp){
					Timestamp time = (Timestamp)value;
					if(time.getTime()==0){
						value = "";
					}
				}
				else if(value instanceof TIMESTAMP){
					TIMESTAMP time = (TIMESTAMP)value;
					value = time.stringValue();
				}
				
				str.append(key.toLowerCase()).append("=").append(value);
				if(iter.hasNext()){
					str.append(",");
				}
			}
			str.append("}");
		}
		else{
			
			Method[] methods = obj.getClass().getMethods();

			str.append("{");
			//获得所有VO中的GET方法
			for(int i = 0; i < methods.length; i++){
				
				String methodName = methods[i].getName();
				if(methodName.indexOf("get")==0 && !methodName.equals("getClass") && !methodName.equals("getInstance")){   
					Method method = methods[i];
					Object result = null;
					try{
						result = method.invoke(obj,new Object[0]);
					}catch(Exception e){
						
						StringBuffer msg = new StringBuffer();
						
						msg.append("ValueObjectUtil：toString(Object obj)方法执行 Method.invoke(").append(obj.getClass()).append(",new Object[0]);异常，");
						msg.append("方法名：").append(methodName).append("，");
						
						StringBuffer parameters = new StringBuffer();
						
						for(int j=0;j<method.getParameterTypes().length;j++){
							parameters.append(method.getParameterTypes()[j]);
						}
						
						msg.append("方法参数：").append(parameters).append("，");
						
						msg.append("返回值：").append(method.getReturnType()).append("，");

						log.error(msg,e);
					}
					
					if(str.length()>0){
						str.append(",");
					}
					str.append(methodName.substring(3).toLowerCase()).append("=").append(result);
					
				}
			}
			str.append("}");
		}
		
		return str.toString();
		
	}
	
	/**
	 * 判断两个VO是否存在不同值
	 * @param Object obj1	 参与比较的对象
	 * @param Object obj2	 参与比较的对象
	 * @return boolean	true存在不同，false无不同
	 * */
	public static boolean hasDifferentValue(Object obj1,Object obj2){
		
		Method[] methods = obj1.getClass().getMethods();
		
		//获得所有VO中的GET方法
		for(int i = 0; i < methods.length; i++){
			
			String methodName = methods[i].getName();
			if(methodName.indexOf("get")==0 && !methodName.equals("getClass") && !methodName.equals("getInstance")){  
				Method method = methods[i];
				Object result1 = null;
				Object result2 = null;

				try{
					result1 = method.invoke(obj1,new Object[0]);
				}catch(Exception e){
					
					StringBuffer msg = new StringBuffer();
					
					msg.append("ValueObjectUtil：hasDifferentValue(Object obj1,Object obj2)方法执行 Method.invoke(").append(obj1.getClass()).append(",new Object[0]);异常，");
					msg.append("方法名：").append(methodName).append("，");
					
					StringBuffer parameters = new StringBuffer();
					
					for(int j=0;j<method.getParameterTypes().length;j++){
						parameters.append(method.getParameterTypes()[j]);
					}
					
					msg.append("方法参数：").append(parameters).append("，");
					
					msg.append("返回值：").append(method.getReturnType()).append("，");

					log.error(msg,e);
				}

				try{
					result2 = method.invoke(obj2,new Object[0]);
				}catch(Exception e){
					
					StringBuffer msg = new StringBuffer();
					msg.append("ValueObjectUtil：hasDifferentValue(Object obj1,Object obj2)方法执行 Method.invoke(").append(obj2.getClass()).append(",new Object[0]);异常，");
					msg.append("方法名：").append(methodName).append("，");
					
					StringBuffer parameters = new StringBuffer();
					
					for(int j=0;j<method.getParameterTypes().length;j++){
						parameters.append(method.getParameterTypes()[j]);
					}
					
					msg.append("方法参数：").append(parameters).append("，");
					
					msg.append("返回值：").append(method.getReturnType()).append("，");

					log.error(msg,e);
				}
				
				if(result1==null && result2==null){
					continue;
				}
				
				if(result1==null || result2==null){
					return true;
				}
				
				if(!result1.equals(result2)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 获得属性的类型
	 * @param Object obj
	 * @param String property	 属性名
	 * @return Class	属性类型
	 * */
	@SuppressWarnings("unchecked")
	public static Class getPropertyType(Object obj,String property){
		if(obj instanceof Map){
		}
		else{
			String methodName = property;
			methodName = methodName.substring(0,1).toUpperCase() + methodName.substring(1);
			methodName = "get"+methodName;
			try{
				Method method = obj.getClass().getMethod(methodName,new Class[]{});
				return method.getReturnType();
			}catch(Exception e){
				StringBuffer msg = new StringBuffer();
				msg.append("ValueObjectUtil：getValue(Object obj,String property)方法执行 Method.invoke(").append(obj.getClass()).append(",new Object[]{});异常，");
				msg.append("方法名：").append(methodName).append("，");
				log.error(msg,e);
			}
		}
		
		return null;
	}
	
	/**
	 * 获得对象中的某个属性
	 * @param Object obj
	 * @param String property	 属性名
	 * @return Object	属性值
	 * */
	@SuppressWarnings("unchecked")
	public static Object getValue(Object obj,String property){
		Object returnValue = null;
		if(obj instanceof Map){
			returnValue = ((Map)obj).get(property);
		}
		else{
			String methodName = property;
			methodName = methodName.substring(0,1).toUpperCase() + methodName.substring(1);
			methodName = "get"+methodName;
			try{
				Method method = obj.getClass().getMethod(methodName,new Class[]{});
				returnValue = method.invoke(obj,new Object[]{});
			}catch(Exception e){
				
				StringBuffer msg = new StringBuffer();
				msg.append("ValueObjectUtil：getValue(Object obj,String property)方法执行 Method.invoke(").append(obj.getClass()).append(",new Object[]{});异常，");
				msg.append("方法名：").append(methodName).append("，");
				log.error(msg,e);
			}
		}
		
		return returnValue;
	}
	
	/**
	 * 设置对象中的某个属性
	 * @param Object obj
	 * @param String property	 属性名
	 * @param Object value	 属性值
	 * @return boolean
	 * */
	@SuppressWarnings("unchecked")
	public static boolean setValue(Object obj,String property,Object value){
		if(obj instanceof Map){
			((Map)obj).put(property,value);
			return true;
		}
		else{
			String methodName = property;
			methodName = methodName.substring(0,1).toUpperCase() + methodName.substring(1);
			methodName = "set"+methodName;
			try{
				Method method = obj.getClass().getMethod(methodName,new Class[]{value.getClass()});
				method.invoke(obj,new Object[]{value});
				return true;
			}catch(Exception e){
				e.printStackTrace();
				StringBuffer msg = new StringBuffer();
				msg.append("ValueObjectUtil：getValue(Object obj,String property)方法执行 Method.invoke(").append(obj.getClass()).append(",new Object[]{});异常，");
				msg.append("方法名：").append(methodName).append("，");
				log.error(msg,e);
			}
		}
		
		return false;
	}
	
	/**
	 * 将VO中的值SET到MAP中（KEY对应属性）
	 * @param Object obj
	 * @return Map<String,Object>
	 * */
	public static Map<String,Object> getVoValue(Object vo){
		
		Map<String,Object> map = new DBMap();
		
		Method[] methods = vo.getClass().getMethods();
		
		//获得所有VO中的GET方法
		for(int i = 0; i < methods.length; i++){
			
			String methodName = methods[i].getName();
			if(methodName.indexOf("get")==0 && !methodName.equals("getClass") && !methodName.equals("getInstance")){  
				Method method = methods[i];
				Object result = null;

				try{
					result = method.invoke(vo,new Object[0]);
				}catch(Exception e){

					StringBuffer msg = new StringBuffer();
					msg.append("ValueObjectUtil：getVoValue(Object vo)方法执行 Method.invoke(").append(vo.getClass()).append(",new Object[0]);异常，");
					msg.append("方法名：").append(methodName+"，");
					
					StringBuffer parameters = new StringBuffer();
					
					for(int j=0;j<method.getParameterTypes().length;j++){
						parameters.append(method.getParameterTypes()[j]);
					}
					msg.append("方法参数：").append(parameters).append("，");
					
					msg.append("返回值：").append(method.getReturnType()).append("，");

					log.error(msg,e);
					
					//e.printStackTrace();
				}
				
				String key = methodName.substring(methodName.indexOf("get")+3);
				//key = key.toLowerCase();
				map.put(key,result);
			}
		}
		
		return map;
		
	}
	
	/**
	 * 将MAP值SET到VO中（KEY对应属性）
	 * @param Object obj
	 * @param Map map
	 * @return void
	 * */
	@SuppressWarnings("unchecked")
	public static void setVoValue(Object vo,Map map){
		setVoValue(vo,map,false);
	}
	
	/**
	 * 将MAP值SET到VO中（KEY对应属性）
	 * @param Object obj
	 * @param Map map
	 * @param boolean setEmptyValue 是否将map中没有的数据替换为空
	 * @return void
	 * */
	@SuppressWarnings("unchecked")
	public static void setVoValue(Object vo,Map map,boolean setEmptyValue){
		
		Method[] methods = vo.getClass().getMethods();
		
		//获得所有VO中的GET方法
		for(int i = 0; i < methods.length; i++){
			
			String methodName = methods[i].getName();
			
			if(methodName.indexOf("set")==0){
				
				Object obj = map.get(methodName.substring(3).toUpperCase());
				
				if(setEmptyValue){
					if(obj!=null){
						continue;
					}
				}


				try{
					methods[i].invoke(vo,new Object[]{obj});
				}catch(Exception e){
					StringBuffer msg = new StringBuffer();
					msg.append("ValueObjectUtil：setVoValue(Object vo,Map map)方法执行 Method.invoke(")
					.append(vo.getClass()).append(",new Object[]{String.valueOf(").append(obj.getClass()).append(")});异常，");
					msg.append("方法名：").append(methodName).append("，");
					
					StringBuffer parameters = new StringBuffer();
					
					for(int j=0;j<methods[i].getParameterTypes().length;j++){
						parameters.append(methods[i].getParameterTypes()[j]);
					}
					msg.append("方法参数：").append(parameters).append("，");
					
					msg.append("返回值：").append(methods[i].getReturnType()).append("，");

					log.error(msg,e);
				}
				
			}
			
		}
	}
	
	/**
	 * 将某一VO中值SET到目标VO中
	 * @param Object targetVo
	 * @param Object fromVo
	 * @return void
	 * */
	public static void setVoValue(Object targetVo,Object fromVo){
		setVoValue(targetVo,fromVo,false);
	}
	
	/**
	 * 将某一VO中值SET到目标VO中
	 * @param Object targetVo
	 * @param Object fromVo
	 * @param boolean setEmptyValue 是否将fromVo中没有的数据替换为空
	 * @return void
	 * */
	public static void setVoValue(Object targetVo,Object fromVo,boolean setEmptyValue){
		
		Method[] methods = fromVo.getClass().getMethods();
		
		//获得所有VO中的GET方法
		for(int i = 0; i < methods.length; i++){
			if(methods[i].getName().indexOf("get")==0 && !methods[i].getName().equals("getClass") && !methods[i].getName().equals("getInstance")){  
				Method method = methods[i];
				Object result = null;
				
				try{
					result = method.invoke(fromVo,new Object[0]);
				}catch(Exception e){
					
					StringBuffer msg = new StringBuffer();
					msg.append("ValueObjectUtil：setVoValue(Object targetVo,Object fromVo)方法执行 Method.invoke(")
					.append(fromVo.getClass()).append(",new Object[0]);异常，");
					msg.append("方法名：").append(methods[i].getName()).append("，");
					
					StringBuffer parameters = new StringBuffer();
					
					for(int j=0;j<method.getParameterTypes().length;j++){
						parameters.append(method.getParameterTypes()[j]);
					}
					msg.append("方法参数：").append(parameters).append("，");
					
					msg.append("返回值：").append(method.getReturnType()).append("，");

					log.error(msg,e);
				}
				Method setMethod = null;
				
				if(setEmptyValue){
					if(result==null){
						continue;
					}
					
				}
				
				//返回值不为NULL则查找目标VO中是否存在对应SET方法
				try{
					setMethod = targetVo.getClass().getMethod("s"+method.getName().substring(1),new Class[]{method.getReturnType()});
				}catch(NoSuchMethodException e){
					
					StringBuffer msg = new StringBuffer();
					msg.append("ValueObjectUtil：setVoValue(Object targetVo,Object fromVo)方法执行 ")
					   .append(targetVo.getClass()).append(".getMethod(s").append(method.getName().substring(1))
					   .append(",new Class[]{").append(method.getReturnType()).append("});异常");

					log.error(msg,e);
				}
				//目标VO中存在对应SET方法则执行设值
				if(setMethod!=null){
					
					try{
						setMethod.invoke(targetVo,new Object[]{result});
					}catch(Exception e){

						StringBuffer msg = new StringBuffer();
						msg.append("ValueObjectUtil：setVoValue(Object targetVo,Object fromVo)方法执行 Method.invoke(")
						   .append(targetVo.getClass()).append(",new Object[").append(result.getClass()).append("]);异常，");
						msg.append("方法名：").append(setMethod.getName()).append("，");
						
						StringBuffer parameters = new StringBuffer();
						
						for(int j=0;j<method.getParameterTypes().length;j++){
							parameters.append(method.getParameterTypes()[j]);
						}
						msg.append("方法参数：").append(parameters).append("，");
						
						msg.append("返回值：").append(setMethod.getReturnType()).append("，");
						
						log.error(msg,e);
					}
				}
			}
		}
	}

}

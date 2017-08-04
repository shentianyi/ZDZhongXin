package com.zd.core.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/** 
 * 用于存储数据库查询结果的map实例
 * 用于queryForList查询的实现，保证字段大小写不一致时可正确获取对应的值
 */
public class DBMap implements Map<String, Object> {
	
	private Map<String, Object> coreMap = new HashMap<String, Object>();
	
	private String conversion(String key){
		if(key==null){
			return key;
		}
		return key.toUpperCase();
	}

	public void clear() {
		coreMap.clear();
	}

	public boolean containsKey(Object key) {
		return coreMap.containsKey(conversion((String)key));
	}

	public boolean containsValue(Object value) {
		return coreMap.containsValue(value);
	}

	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return coreMap.entrySet();
	}

	public Object get(Object key) {
		return coreMap.get(conversion((String)key));
	}

	public boolean isEmpty() {
		return coreMap.isEmpty();
	}

	public Set<String> keySet() {
		return coreMap.keySet();
	}

	public Object put(String key, Object value) {
		return coreMap.put(conversion(key),value);
	}

	public void putAll(Map<? extends String, ? extends Object> t) {
		
//		for (Entry<? extends String, ? extends Object> tempEntry : t.entrySet()) {
//			coreMap.put(tempEntry.getKey(), tempEntry.getValue());
//		}
		
		Set<?> set = t.keySet();
		
		for(Iterator<?> iter = set.iterator(); iter.hasNext();){
			String key = (String)iter.next();
			coreMap.put(conversion(key),t.get(key));
		}
	}

	public Object remove(Object key) {
		return coreMap.remove(conversion((String)key));
	}

	public int size() {
		return coreMap.size();
	}

	public Collection<Object> values() {
		return coreMap.values();
	}

	public String toString(){
		return coreMap.toString();
	}
}

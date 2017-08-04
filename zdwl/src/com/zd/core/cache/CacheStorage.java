package com.zd.core.cache;

/**
 * 缓存块接口
 * 用于指定缓存块内数据的维护方法
 * */
public interface CacheStorage {

	/**
	 * 通过缓存模块唯一值获取缓存块数据对象
	 * @param Object key 缓存唯一值（使用常量类管理）
	 * @return Object 数据对象
	 * */
    public Object get(Object key);
    
    /**
	 * 向缓存模块中增添数据
	 * @param Object key 缓存数据唯一值（使用常量类管理）
	 * @param  Object value 数据（使用常量类管理）
	 * @return void 
	 * */
    public void put(Object key, Object value);
    
    /**
	 * 根据数据唯一值清除缓存模块中的数据对象
	 * @param Object key 缓存数据唯一值（使用常量类管理）
	 * @return void 
	 * */
    public void remove(Object key);
    
    /**
	 * 清除指定模块的全部数据
	 * @return void 
	 * */
    public void clear();

}

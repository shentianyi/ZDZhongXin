 package com.zd.core.cache;

/**
 * 缓存块工厂接口
 * 用于指定不同缓存实现构造缓存块实例的方法
 * */
public interface CacheStoreFactory {
	
	/**
	 * 通过缓存名获取缓存块对象
	 * @param String name 缓存名称（使用常量类管理）
	 * @return CacheStorage 缓存块对象
	 * */
	public CacheStorage get(String name);
}

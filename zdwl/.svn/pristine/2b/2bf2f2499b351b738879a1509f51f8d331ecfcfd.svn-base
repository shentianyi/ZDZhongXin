package com.zd.core.cache.localcache;

/**
 * 清除缓存策略接口
 * 用于指定清除缓存策略必须实现的方法
 * */
public interface LocalCacheEviction {
	/**
	 * 设置清除策略的最长寿命
	 * @param long maxAge 时间
	 * @return void
	 * */
	public void setMaxAge(long maxAge);
	/**
	 * 设置清除策略的内存大小
	 * @param int maxSize 内存大小的数值
	 * @return void
	 * */
	public void setMaxSize(int maxSize);
	/**
	 * 缓存模块名称
	 * @param String name 缓存模块名称
	 * @return void
	 * */
	public void setName(String name);
	/**
	 * 获取清除策略的最长寿命
	 * @return long  时间值
	 * */
	public long getMaxAge();
	/**
	 * 获取清除策略的最大内存值
	 * @return int 内存大小
	 * */
	public int getMaxSize();
	/**
	 * 获取要执行清除策略的模块名称
	 * @return String 模块名称
	 * */
	public String getName();
	/**
	 * 清除策略的执行方法
	 * @param LocalCacheStore store 缓存模块
	 * @return void
	 * */
	public void eviction(LocalCacheStore store);
}

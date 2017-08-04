package com.zd.core.cache.localcache;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.zd.core.cache.CacheStoreFactory;

/**
 * 本地缓存的工厂类实现
 * 用于创建本地缓存块
 * */
public class LocalCacheFactory implements CacheStoreFactory {

	private ConcurrentMap<String,LocalCacheStore> cacheSource = new ConcurrentHashMap<String,LocalCacheStore>();
	
	private LocalCacheListener listener;
	
	/**
	 * 通过缓存块名称获取缓存块实例
	 * @param name 缓存块名称
	 * @return LocalCacheStore 缓存块实例
	 * */
	public LocalCacheStore get(String name){
		//首先从cacheSource中查找是否有与名称一致的缓存块实例，如果没有则重新创建实例并放入cacheSource保证数据一致。
		LocalCacheStore o = cacheSource.get(name);
		if(o==null){
			o = new LocalCacheStore(name);
			cacheSource.put(name, o);
		}
		return o;
	}

	/**
	 * 获取本地缓存下所有缓存块
	 * @return Collection<LocalCacheStore> 缓存块实例集合
	 * */
	public Collection<LocalCacheStore> getStores(){
		return cacheSource.values();
	}

	/**
	 * 获取本地缓存下运行的监听对象实例
	 * @return LocalCacheListener 缓存监听实例
	 * */
	public LocalCacheListener getListener() {
		return listener;
	}

	/**
	 * 设置本地缓存下运行的监听对象
	 * @param listener 缓存监听实例
	 * @return void
	 * */
	public void setListener(LocalCacheListener listener) {
		this.listener = listener;
		listener.setFactory(this);
		listener.start();	//运行监听对象
	}
}

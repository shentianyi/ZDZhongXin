package com.zd.core.cache.localcache;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.zd.core.cache.CacheStorage;

/**
 * 缓存块接口
 * 用于指定缓存块内数据的维护方法
 * */
public class LocalCacheStore implements CacheStorage {
	
	private ConcurrentMap<Object,LocalCacheNode> cacheData = new ConcurrentHashMap<Object,LocalCacheNode>();	//缓存数据
	private List<LocalCacheNode> indexData = new Vector<LocalCacheNode>();	//缓存数据索引集合
	private String name;	//缓存块名称

	/**
	 * 实例化缓存块
	 * @param name 缓存块名称
	 * */
	public LocalCacheStore(String name){
		this.name = name;
	}

	/**
	 * 清空缓存块全部数据
	 * @return void
	 * */
	public void clear(){
		cacheData.clear();
		indexData.clear();
	}

	/**
	 * 按key清除单条缓存数据
	 * @param key 缓存数据key
	 * @return void
	 * */
	public void remove(Object key){
		if(key==null){
			return;
		}
		LocalCacheNode node = cacheData.get(key);
		if(node==null){
			return;
		}
		cacheData.remove(key);
		indexData.remove(node);
	}

	/**
	 * 通过key更新缓存数据
	 * @param key 缓存key
	 * @param value 缓存数据
	 * @return void
	 * */
	public void put(Object key,Object value){
		if(key==null){
			return;
		}
		//从字符串缓冲池中取出key同值字符串对象作为锁
		synchronized(key.toString().intern()){
			//更新缓存数据前，首先将数据从索引集合中移出，保证缓存顺序更新
			LocalCacheNode node = cacheData.get(key);
			if(node==null){
				node = new LocalCacheNode();
				node.setKey(key);
			} else{
				indexData.remove(node);
			}
			
			//如果缓存数据为null则驱逐原有缓存数据
			if(value!=null){
				node.setPutTime(System.currentTimeMillis());
				node.setObj(value);
				node.setObjSize(1);
				cacheData.put(key, node);
				indexData.add(0,node);
			} else{
				cacheData.remove(key);
			}
		}
	}

	/**
	 * 通过key更新缓存数据
	 * @param key 缓存key
	 * @return Object 缓存数据
	 * */
	public Object get(Object key){
		if(key==null){
			return null;
		}
		LocalCacheNode node = cacheData.get(key);
		if(node==null){
			return null;
		}
		return node.getObj();
	}

	/**
	 * 获取键值对形式的缓存数据集合
	 * @return ConcurrentMap<Object, LocalCacheNode> 缓存数据集合
	 * */
	public ConcurrentMap<Object, LocalCacheNode> getCacheData() {
		return cacheData;
	}
	
	public void setCacheData(ConcurrentMap<Object, LocalCacheNode> cacheData) {
		this.cacheData = cacheData;
	}

	/**
	 * 获取索引形式的缓存数据集合
	 * @return List<LocalCacheNode> 缓存数据集合
	 * */
	public List<LocalCacheNode> getIndexData() {
		return indexData;
	}

	public void setIndexData(Vector<LocalCacheNode> indexData) {
		this.indexData = indexData;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

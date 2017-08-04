package com.zd.core.cache.localcache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存监听对象
 * 用于维持监听心跳，定时触发本地缓存驱逐策略的执行
 * */
public class LocalCacheListener extends Thread {
	
	private long wakeUpInterval = 0;	//心跳间隔

	private LocalCacheEviction defaultEviction = null;	//默认驱逐策略
	
	private List<LocalCacheEviction> evictionList = null;	//按缓存块设置的自定义驱逐策略
	
	private LocalCacheFactory factory = null;	//localcache工厂，用于同步获取缓存块，LocalCacheListener实例化时注入

	/**
	 * 使监听对象进入运行状态，保持心跳
	 * @return void
	 * */
	public void run(){
		//满足缓存驱逐策略监听线程启动情况下才继续往下进行，否则中断心跳
		if(wakeUpInterval<=0 || evictionList==null || evictionList.isEmpty() || factory==null){
			return;
		}

		int i=0;
		int size = evictionList.size();
		
		LocalCacheEviction eviction;
		
		//如果存在默认驱逐策略，则将所有自定义驱逐策略做一个hash对应便于执行默认策略时检索缓存块是否存在自定义策略
		Map<String, LocalCacheEviction> evictionMap=null;
		if(defaultEviction!=null){
			evictionMap = new ConcurrentHashMap<String, LocalCacheEviction>();
			for(i=0; i<size; i++){
				eviction = evictionList.get(i);
				evictionMap.put(eviction.getName(), eviction);
			}
		}
		
		while(true){
			try {
				//循环所有自定义驱逐策略，对缓存块进行驱逐
				for(i=0; i<size; i++){
					eviction = evictionList.get(i);
					eviction.eviction(factory.get(eviction.getName()));
				}
	
				//如果存在默认驱逐策略，则将所有未单独设置驱逐策略的缓存块按默认策略进行驱逐
				if(defaultEviction!=null){
					Object[] stores = factory.getStores().toArray();
					int storeSize = stores.length;
					LocalCacheStore store;
					//循环所有缓存块预备按默认策略进行驱逐
					for(i=0; i<storeSize; i++){
						store = (LocalCacheStore)stores[i];
						//如果缓存块单独设置了驱逐策略就不再执行默认驱逐策略
						if(!evictionMap.containsKey(store.getName())){
							defaultEviction.eviction(store);
						}
					}
				}
			    //按心跳时间进行睡眠，准备下移周期驱逐
				Thread.sleep(wakeUpInterval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public long getWakeUpInterval() {
		return wakeUpInterval;
	}

	public void setWakeUpInterval(long wakeUpInterval) {
		this.wakeUpInterval = wakeUpInterval;
	}

	public LocalCacheEviction getDefaultEviction() {
		return defaultEviction;
	}

	public void setDefaultEviction(LocalCacheEviction defaultEviction) {
		this.defaultEviction = defaultEviction;
	}

	public List<LocalCacheEviction> getEvictionList() {
		return evictionList;
	}

	public void setEvictionList(List<LocalCacheEviction> evictionList) {
		this.evictionList = evictionList;
	}
	
	public LocalCacheFactory getFactory() {
		return factory;
	}

	public void setFactory(LocalCacheFactory factory) {
		this.factory = factory;
	}
	
}

package com.zd.core.cache.localcache;

import java.util.List;

/**
 * 默认缓存取值策略
 * maxAge定义为缓存最长生命周期（按缓存放入时间起计算）
 * maxSize 定义为保存最多缓存个数，超过时保留最新放入的缓存
 * */
public class LocalCacheEvictionDefault implements LocalCacheEviction {
	
	private long maxAge = 0;		//缓存最长寿命
	private int maxSize = 0;		//缓存最大长度（数量或字节）
	private String name = null;		//缓存块名称

	/**
	 * 执行清楚策略
	 * @param store 缓存块实例
	 * @return void
	 * */
	public void eviction(LocalCacheStore store) {
		//按生命周期驱逐
		clearByAge(store);
		//按缓存长度驱逐
		clearBySize(store);
	}

	/**
	 * 按生命周期驱逐
	 * @param store 缓存块实例
	 * @return void
	 * */
	private void clearByAge(LocalCacheStore store){
		//如生命周期时间设置无效则不进行驱逐
		if(maxAge<=0){
			return;
		}
		
		//获取缓存块下缓存集合（按更新顺序）
		List<LocalCacheNode> indexData = store.getIndexData();
		LocalCacheNode node;
		int size = indexData.size();
		long now = System.currentTimeMillis();
		//缓存更新时间+最长寿命小余当前时间时开始清除缓存
		for(int i=0; i<size; i++){
			node = indexData.get(i);
			try {
				if(node != null){
					if(node.getPutTime() + maxAge < now){
						store.remove(node.getKey());
						i--;
						size--;
					}		
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("LocalCacheNode驱逐异常  node:"+node+"; size="+size+"; i="+i);
			}
		}
	}

	/**
	 * 按缓存长度驱逐
	 * @param store 缓存块实例
	 * @return void
	 * */
	private void clearBySize(LocalCacheStore store){
		//如缓存最大长度设置无效则不进行驱逐
		if(maxSize<=0){
			return;
		}
		List<LocalCacheNode> indexData = store.getIndexData();
		LocalCacheNode node;
		int size = indexData.size();
		//如果清除索引大于缓存数据长度，则不执行清除策略
		if(maxSize>=size){
			return;
		}
		//从索引位置起开始清除缓存
		for(int i=maxSize; i<size; i++){
			node = indexData.get(i);
			if(node!=null){
				store.remove(node.getKey());
				i--;
				size--;
			}
		}
	}

	public long getMaxAge() {
		return maxAge;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxAge(long maxAge) {
		this.maxAge = maxAge;
	}
	
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

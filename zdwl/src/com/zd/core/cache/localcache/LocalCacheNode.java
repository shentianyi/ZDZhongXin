package com.zd.core.cache.localcache;

/**
 * 本地缓存节点对象
 * 用于存储单条缓存数据
 * */
public class LocalCacheNode {
	
	private long putTime = 0;	//缓存数据创建时间
	private long objSize = 0;	//缓存对象字节大小
	private Object key;			//缓存对象key值
	private Object obj;			//缓存数据对象
	
	public long getPutTime() {
		return putTime;
	}
	public void setPutTime(long putTime) {
		this.putTime = putTime;
	}
	public long getObjSize() {
		return objSize;
	}
	public void setObjSize(long objSize) {
		this.objSize = objSize;
	}
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String toString(){
		return "key:"+key+";obj:"+obj;
	}
}

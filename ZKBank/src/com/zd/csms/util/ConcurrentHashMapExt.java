package com.zd.csms.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Created by co-mall
 * User: davip
 * Date: 2009-2-6
 * Time: 22:32:12
 * 功能描述:
 */
public class ConcurrentHashMapExt<K, V> extends ConcurrentHashMap<K, V> {
    public ConcurrentHashMapExt() {
        super();
    }

    public ConcurrentHashMapExt(int initialCapacity) {
        super(initialCapacity);
    }

    public ConcurrentHashMapExt(Map<? extends K, ? extends V> m) {
        super(m);
    }

    synchronized public V put(K key, V value) {
        if (value != null) {
            return super.put(key, value);
        }else {
            super.remove(key);
        }
        return value;
    }
}

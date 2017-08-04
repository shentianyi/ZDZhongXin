package com.zd.tools.dynamic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 支持动态新增行的list对象，可以将前台提交值按定义的类型进行封装。
 * */
public class DynamicList<T> extends ArrayList<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -966648680717533719L;
	private Class<T> clazz;
	
	public DynamicList(Class<T> clazz)
    {
        this.clazz = clazz;
    }
	
	public T get(int i) {
        try {
            for(; super.size() < i + 1; add(clazz.newInstance()));
        } catch(Exception e) { }
        return super.get(i);
    }
	
	private void removeTempletItem(){
		int size = super.size();
		Object obj;
		for(int i = 0; i < size; i++){
			obj = super.get(i);
			try{
				int stateFlag = Integer.parseInt(obj.getClass().getMethod("getStateFlag",new Class[]{}).invoke(obj,new Object[]{}).toString());
				if(stateFlag==-1){
					remove(i);
					i--;
					size--;
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public int size(){
		removeTempletItem();
		return super.size();
	}
	
	public Iterator<T> iterator(){
		removeTempletItem();
		return super.iterator();
	}

}

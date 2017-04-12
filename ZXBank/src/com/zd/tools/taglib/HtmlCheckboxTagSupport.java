package com.zd.tools.taglib;

import java.util.Collection;
import java.util.Iterator;

public class HtmlCheckboxTagSupport extends HtmlInputTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 277787065709395741L;
	
	protected String checked = null;
	
	public void release() {
		super.release();
		checked = null;
		type = "checkbox";
	}

	public boolean isChecked(Object propertyValue, Object value){
		if(checked!=null && !checked.equals("false")){
			return true;
		}
		
		if(propertyValue instanceof Object[]){
			Object[] values = (Object[])propertyValue;
			for(int i=0;i<values.length;i++){
				if(values[i].toString().equals(value.toString())){
					return true;
				}
			}
		} else if(propertyValue instanceof Collection<?>){
			for(Iterator<?> iter = ((Collection<?>)propertyValue).iterator(); iter.hasNext();){
				if(iter.next().equals(value)){
					return true;
				}
			}
		} else if(propertyValue instanceof Number){
			if(String.valueOf(propertyValue).equals(String.valueOf(value))){
				return true;
			}
		} else if(propertyValue instanceof String){
			if(String.valueOf(propertyValue).equals(String.valueOf(value))){
				return true;
			}
		}
		return false;
	}

}

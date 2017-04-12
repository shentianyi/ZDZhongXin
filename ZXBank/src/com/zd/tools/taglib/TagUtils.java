package com.zd.tools.taglib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.util.IteratorAdapter;

public class TagUtils extends org.apache.struts.taglib.TagUtils {

    private static final TagUtils instance = new TagUtils();
	
	public static TagUtils getInstance(){
        return instance;
	}
	
	public Object lookup(PageContext pageContext,String name){
		if(name.indexOf(".")!=-1){
			String property = name.substring(name.indexOf(".")+1);
			name = name.substring(0,name.indexOf("."));
			return lookup(pageContext,name,property);
		}
		return pageContext.findAttribute(name);
	}
	
	public Object lookup(PageContext pageContext,String name,String property){
		Object bean = pageContext.findAttribute(name);
		if(property!=null){
			try{
			return PropertyUtils.getProperty(bean, property);
			}catch(Exception e){
				///e.printStackTrace();
			}
		}
		return bean;
	}
	
	//通过collection类型进行不同处理以提取iterator
	@SuppressWarnings("unchecked")
	public Iterator getIterator(Object collection) throws JspException {
		
		Iterator iterator;
		
		if(collection.getClass().isArray()){
	        try{
	            iterator = Arrays.asList((Object[])collection).iterator();
	        }
	        catch(ClassCastException e){
	            int length = Array.getLength(collection);
	            ArrayList<Object> c = new ArrayList<Object>(length);
	            for(int i = 0; i < length; i++){
	                c.add(Array.get(collection, i));
	            }
	            iterator = c.iterator();
	        }
	    }
		else{
			if(collection instanceof Collection){
				iterator = ((Collection)collection).iterator();
			}
			else{
				if(collection instanceof Iterator){
					iterator = (Iterator)collection;
				}
				else{
					if(collection instanceof Map){
						iterator = ((Map)collection).entrySet().iterator();
					}
					else{
						if(collection instanceof Enumeration){
							iterator = new IteratorAdapter((Enumeration)collection);
						}
						else{
							JspException e = new JspException("collection 是无效的 iterator");
							throw e;
						}
					}
				}
			}
		}
		
		return iterator;
	}
	
	public int getIntValueByStringProperty(PageContext pageContext,String property) throws JspException {
		int intValue;
		if(property == null){
			intValue = 0;
	    }
	    else{
	        try{
	        	intValue = Integer.parseInt(property);
	        }
	        catch(NumberFormatException e){
	            Integer offsetObject = (Integer)lookup(pageContext, property, null);
	            if(offsetObject == null){
	            	intValue = 0;
	            }
	            else{
	            	intValue = offsetObject.intValue();
	            }
	        }
	    }
	    if(intValue < 0){
	    	intValue = 0;
	    }
	    
	    return intValue;
	}

}

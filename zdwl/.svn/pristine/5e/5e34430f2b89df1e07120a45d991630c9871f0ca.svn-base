package com.zd.csms.rbac.login.common;

import java.io.Serializable;
import java.util.Comparator;

import com.zd.csms.rbac.model.ResourceVO;

@SuppressWarnings("serial")
public class ResourceComparator implements Comparator<ResourceVO>, Serializable {

	public int compare(ResourceVO o1, ResourceVO o2){
		
		//默认index1与index2相等
		int returnValue = 0;
		
		String index1 = o1.getResource_index();
		String index2 = o2.getResource_index();
		
		if(index1==null){
			index1 = "";
		}
		if(index2==null){
			index2 = "";
		}
		
		for(int i=0;i<index1.length();i++){
			
			//index1的长度大于index2，返回index1大
			if(index2.length()==i){
				returnValue = 1;
				break;
			}
			
			//index1的第i位大于index2，返回index1大
			if(index1.codePointAt(i)>index2.codePointAt(i)){
				returnValue = 1;
				break;
			}
			//index1的第i位小于index2，返回index2大
			else if(index1.codePointAt(i)<index2.codePointAt(i)){
				returnValue = -1;
				break;
			}
			
			//index1的长度小于index2，返回index2大
			if(i==index1.length()-1 && i<index2.length()-1){
				returnValue = -1;
				break;
			}
			
		}

		return returnValue;
	}
}

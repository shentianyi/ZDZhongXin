package com.zd.csms.util.tagutil;

import com.zd.csms.marketing.contants.PayModeContant;

public class PayModeUtil {

	public String payMode(int id){
		
		String name = "";
		
		for(PayModeContant payMode: PayModeContant.values()){
			if(id==payMode.getCode()){
				name = payMode.getName();
			}
		}
		return name;
	}
}

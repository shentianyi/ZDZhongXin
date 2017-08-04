package com.zd.csms.planandreport.contants;

import com.zd.csms.base.code.constants.BusinessCodeConstants;

/**
 * 编辑经销商列表数据对应项ID
 * @author wdc
 *
 */
public enum ObjIdContants {
	
	
	PREDICTCHECKDAYS(1,"预计开始时间"),
	
	PREDICTCOST(2,"预计产生费用"),
	
	PREDICTFINISHDATE(3,"预计完成日期"),
	
	REMARK(4,"备注");
	
	private int code;
	private String name;
	private ObjIdContants(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	/**
	 * 通过name获取code
	 * @param name
	 * @return int
	 * */
	public static int getCode(String name){
		for(BusinessCodeConstants businessCode : BusinessCodeConstants.values()){
			if(name.equals(businessCode.getName())){
				return businessCode.getCode();
			}
		}
		return -1;
	}
	
	/**
	 * 通过code获取name
	 * @param code
	 * @return String
	 * */
	public static String getName(int code){
		for(BusinessCodeConstants businessCode : BusinessCodeConstants.values()){
			if(code==businessCode.getCode()){
				return businessCode.getName();
			}
		}
		return null;
	}
}

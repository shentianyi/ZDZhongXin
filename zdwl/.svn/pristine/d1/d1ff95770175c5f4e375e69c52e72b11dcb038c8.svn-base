package com.zd.csms.planandreport.contants;

import com.zd.csms.base.code.constants.BusinessCodeConstants;

/**
 * 审批状态
 * @author wdc
 *
 */
public enum ApproveStateContants {
	
	CHECKING(5,"正在审核"),
	UNCOMMIT(1,"未提交"),
	
	UNCHECK(2,"待审核"),
	
//	SUPERVISEEXPENSE(3,"已提交审核"),
	
	CHECKPASS(3,"审核通过"),
	
	UNCHECKPASS(4,"审核未通过");
	
	
	private int code;
	private String name;
	private ApproveStateContants(int code, String name) {
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

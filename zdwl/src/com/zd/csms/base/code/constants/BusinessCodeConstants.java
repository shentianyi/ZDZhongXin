package com.zd.csms.base.code.constants;

/**
 * 
* <p>版权所有:北京科码先锋软件技术有限公司</p>
* @作者: JIANGYE
* @日期: 2013-7-18 上午10:32:29
* @描述: [BusinessCodeConstants]业务编号常量枚举
 */
public enum BusinessCodeConstants{
	/**
	 * 授信资质管理
	 */
	CREDIT_QUALITY(1,"授信资质管理"),
	/**
	 * 授信额度管理
	 */
	CREDIT_DETAIL(2,"授信额度管理"),
	/**
	 * 车辆抵押管理
	 */
	MORTGAGE_VEHICLE(3,"车辆抵/质押管理"),
	/**
	 * 车辆评估管理
	 */
	EVALUATE_VEHICLE(4,"车辆评估管理"),
	/**
	 * 车辆过户管理
	 */
	MORTGAGE_VEHICLE_TRANSFER(5,"车辆抵/质押过户管理"),
	/**
	 * 车辆解押管理
	 */
	MORTGAGE_VEHICLE_RELEASE(6,"车辆解押管理"),
	/**
	 * 放款管理
	 */
	CREDIT_LOAN(7,"放款管理"),
	/**
	 * 还款管理
	 */
	CREDIT_REPAYMENT(8,"还款管理"),
	/**
	 * 基础信息管理
	 */
	BASIC_INFORMATION(9,"基础信息管理"),
	/**
	 * 角色管理
	 */
	RBAC_ROLE(10,"角色管理"),
	/**
	 * 资源管理
	 */
	RBAC_RESOURCE(11,"资源管理"),
	/**
	 * 监管业务管理
	 */
	SUPERVISORY_AUDIT(12,"监管业务管理"),
	/**
	 * 账户管理
	 */
	RBAC_USER(13,"账户管理"),
	/**
	 * 解押过户管理
	 */
	MORTGAGE_VEHICLE_TRANSFER_RELEASE(14,"解押过户管理"),
	/**
	 * 收费管理
	 */
	VEHICLE_FINANCE(15,"收费管理"),
	/**
	 * 车辆信息采集
	 */
	SHARE_VEHICLE(16,"车辆信息采集"),
	/**
	 * 车辆置换
	 */
	REPLACEMENT_VEHICLE(17,"置换车辆"),
	/**
	 * 车辆置换
	 */
	CHECK_CAR(18,"质押车辆抽查"),
	;
	
	
	private int code;
	private String name;
	
	private BusinessCodeConstants(int code, String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getName(){
		return name;
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

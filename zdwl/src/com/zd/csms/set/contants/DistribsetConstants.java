package com.zd.csms.set.contants;


/**
 * @author licheng
 *
 */
public enum DistribsetConstants {
	
	/**
	 * 经销商车辆移动百分比
	 */
	MOVE(1,"move"),
	/**
	 * 对接银行类型
	 */
	BankDock(2,"bankDock"),
	
	/**
	 * 浙商银行客户号
	 */
	ZSCustNo(3,"zsCustNo");
	
	private int code;
	private String name;
	
	private DistribsetConstants(int code, String name){
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
		for(DistribsetConstants state : DistribsetConstants.values()){
			if(name.equals(state.getName())){
				return state.getCode();
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
		for(DistribsetConstants state : DistribsetConstants.values()){
			if(code==state.getCode()){
				return state.getName();
			}
		}
		return null;
	}
}
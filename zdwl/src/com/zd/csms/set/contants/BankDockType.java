package com.zd.csms.set.contants;


/**
 * 对接银行类型
 */
public enum BankDockType {
	/**
	 * 浙商银行
	 */
	ZSBANK(1,"浙商银行"),
	
	ZXBANK(2,"中信银行");
	
	
	private int code;
	private String name;
	
	private BankDockType(int code, String name){
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
		for(BankDockType state : BankDockType.values()){
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
		for(BankDockType state : BankDockType.values()){
			if(code==state.getCode()){
				return state.getName();
			}
		}
		return "";
	}
}

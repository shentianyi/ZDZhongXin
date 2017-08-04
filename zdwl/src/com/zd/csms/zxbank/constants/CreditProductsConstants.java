package com.zd.csms.zxbank.constants;

public enum CreditProductsConstants {
	
	BANKACCEPTANCE(1,"银行承兑汇票"),
	LIQUIDITYLOAN(2,"流动资金贷款"),
	OVEROFCORACCOUNT(3,"法人账户透支"),
	BUTIDI(4,"商票贴现"),
	DOLEOFCR(5,"国内信用证"),
	IMPORTCREDIT(6,"进口信用证"),
	IMPORTUNCT(7,"非信用证项下进口押汇");
	
	private int code;
	private String name;
	
	
	private CreditProductsConstants(int code, String name){
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
	 * @return String
	 * */
	public static int getCode(String name){
		for(CreditProductsConstants item : CreditProductsConstants.values()){
			if(name.equals(item.getName())){
				return item.getCode();
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
		for(CreditProductsConstants item : CreditProductsConstants.values()){
			if(code == item.getCode()){
				return item.getName();
			}
		}
		return null;
	}
}

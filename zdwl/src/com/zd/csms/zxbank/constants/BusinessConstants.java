package com.zd.csms.zxbank.constants;

/**
 * 数据字典字段常量 --业务模式
 * @author caizhuo
 *
 */
public enum BusinessConstants {
	ATFCTGATP(1,"先票后货三方"),
	ATFTTGATS(2,"先票后货两方"),
	CONTHESTOH(3,"保兑仓"),
	CHATTEL(4,"动产质押（静态）"),
	PLEDGEDPLEDGE(5,"动产质押（总量）"),
	DOEQBUCR(6,"国内设备买方信贷"),
	TSOTWR(7,"标准仓单"),
	B2BMBS(8,"B2B先款后货"),
	ATFPOAFTGATP(11,"汽车金融先票后货三方"),
	TFTSOTCFT(12,"汽车金融先票后货两方"),
	TAFIW(13,"汽车金融保兑仓");
	
	private int code;
	private String name;
	
	
	private BusinessConstants(int code, String name){
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
		for(BusinessConstants item : BusinessConstants.values()){
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
		for(BusinessConstants item : BusinessConstants.values()){
			if(code == item.getCode()){
				return item.getName();
			}
		}
		return null;
	}

}

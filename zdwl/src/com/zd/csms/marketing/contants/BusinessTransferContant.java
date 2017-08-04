package com.zd.csms.marketing.contants;

public enum BusinessTransferContant {
	/**
	 * 超级管理员
	 */
	ADMINISTRATOR(1,"超级管理员"),
	/**
	 * 监管员管理部数据专员
	 */
	SUPERVISORYMANAGEMENT_DATA(2,"监管员管理部数据专员"),
	/**
	 * 监管员管理部综合专员
	 */
	SUPERVISORYMANAGEMENT_COMPREHENSIVE(3,"监管员管理部综合专员"),
	/**
	 * 监管员管理部招聘专员
	 */
	SUPERVISORYMANAGEMENT_RECRUIT(4,"监管员管理部招聘专员"),
	/**
	 * 监管员管理部调配专员
	 */
	SUPERVISORYMANAGEMENT_DEPLOY(5,"监管员管理部调配专员"),
	/**
	 * 监管员管理部薪酬专员
	 */
	SUPERVISORYMANAGEMENT_PAYMENT(6,"监管员管理部薪酬专员"),
	/**
	 * 监管员管理部培训专员
	 */
	SUPERVISORYMANAGEMENT_TRAIN(7,"监管员管理部培训专员"),
	/**
	 * 监管员管理部考勤专员
	 */
	SUPERVISORYMANAGEMENT_ATTENDANCE(8,"监管员管理部考勤专员"),
	/**
	 * 监管员管理部经理
	 */
	SUPERVISORYMANAGEMENT_AMALDAR(9,"监管员管理部经理"),
	/**
	 * 监管员
	 */
	/*SUPERVISORY(10,"监管员"),*/
	/**
	 * 市场部专员
	 */
	MARKET_COMMISSIONER(11,"市场部专员"),
	/**
	 * 市场部数据专员
	 */
	MARKET_DATA(12,"市场部数据专员"),
	/**
	 * 市场部经理
	 */
	MARKET_AMALDAR(13,"市场部经理"),
	/**
	 * 业务部专员
	 */
	BUSINESS_COMMISSIONER(14,"业务部专员"),
	/**
	 * 业务部数据专员
	 */
	BUSINESS_DATA(15,"业务部数据专员"),
	/**
	 * 业务部经理
	 */
	BUSINESS_AMALDAR(16,"业务部经理"),
	/**
	 * 风控部数据专员
	 */
	WINDCONRTOL_DATA(17,"风控部数据专员"),
	/**
	 * 风控部内控专员
	 */
	WINDCONRTOL_INTERNAL(18,"风控部内控专员"),
	/**
	 * 风控部外控专员
	 */
	WINDCONRTOL_EXTERNAL(19,"风控部外控专员"),
	/**
	 * 风控部经理
	 */
	WINDCONRTOL_AMALDAR(20,"风控部经理"),
	/**
	 * 视频部专员
	 */
	VIDEO_COMMISSIONER(21,"视频部专员"),
	/**
	 * 视频部数据专员
	 */
	VIDEO_DATA(22,"视频部数据专员"),
	/**
	 * 视频部经理
	 */
	VIDEO_AMALDAR(23,"视频部经理"),
	/**
	 * 财务部会计
	 */
	FINANCE_ACCOUNTING(24,"财务部会计"),
	/**
	 * 财务部经理
	 */
	FINANCE_AMALDAR(25,"财务部经理"),
	/**
	 * 风险管理部部长
	 */
	RISKMANAGEMENT_MINISTER(26,"风险管理部部长"),
	/**
	 * 市场管理部部长
	 */
	MARKETMANAGEMENT_MINISTER(27,"市场管理部部长"),
	/**
	 * 运营管理部部长
	 */
	OPERATIONMANAGEMENT_MINISTER(28,"运营管理部部长"),
	/**
	 * 物流金融中心总监
	 */
	LOGISTICSFINANCECENTER_MAJORDOMO(29,"物流金融中心总监"),
	
	/**
	 * 经销商授权人
	 */
	DISTRIBUTOR(30,"经销商授权人"),
	/**
	 * 超级角色
	 */
	SR(80,"超级角色");
	
	private int code;
	private String name;
	
	private BusinessTransferContant(int code, String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getName(){
		return name;
	}
}

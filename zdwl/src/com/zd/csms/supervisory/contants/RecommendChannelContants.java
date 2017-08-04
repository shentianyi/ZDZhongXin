package com.zd.csms.supervisory.contants;

public enum RecommendChannelContants {
	
	/**
	 * 招聘渠道：校园招聘
	 */
	CAMPUS_RECRUITMENT(1,"校园招聘"),
	
	/**
	 * 招聘渠道：监管员推荐
	 */
	SUPERVISORYRECOMMEND(2,"监管员推荐"),
	
	/**
	 * 招聘渠道：社会招聘
	 */
	SOCIETY_RECRUITMENT(3,"社会招聘"),
	
	/**
	 * 内部人员推荐
	 */
	INTERNAL_RECOMMEND(4,"内部人员推荐");
	
	
	private RecommendChannelContants(int code, String name) {
		this.code = code;
		this.name = name;
	}
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static  RecommendChannelContants getByCode(int code) {
		for(RecommendChannelContants contant:RecommendChannelContants.values()){
			if(code==contant.getCode()){
				return contant;
			}
		}
		return null;
	}
	
}

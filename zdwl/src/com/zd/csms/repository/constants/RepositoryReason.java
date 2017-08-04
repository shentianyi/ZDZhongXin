package com.zd.csms.repository.constants;


public enum RepositoryReason {

	/**
	 * 撤店
	 */
	REVOKE(1,"撤店"),
	/**
	 * 放弃(已有新工作)
	 */
	HAVE_NEW_JOB(2,"放弃(已有新工作)"),
	/**
	 * 放弃(薪资问题)
	 */
	PAY_REASON(3,"放弃(薪资问题)"),
	/**
	 * 辞退
	 */
	DISMISS(4,"辞退"),
	/**
	 * 辞职
	 */
	RESIGN(5,"辞职");
	private RepositoryReason(int code, String name) {
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
	public static  String getNameByCode(int code) {
		for(RepositoryReason contant:RepositoryReason.values()){
			if(code==contant.getCode()){
				return contant.getName();
			}
		}
		return null;
	}
}

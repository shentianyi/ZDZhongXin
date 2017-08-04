package com.zd.csms.rbac.constants;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.util.ArrayUtils;

/**
 * @author licheng
 *
 */
public enum ClientTypeConstants {

	/**
	 * 超级管理员
	 */
	ADMINISTRATOR(1, "超级管理员"),

	/**
	 * 监管员管理部
	 */
	SUPERVISORYMANAGEMENT(2, "监管员管理部"),

	/**
	 * 监管员
	 */
	SUPERVISORY(3, "监管员"),

	/**
	 * 业务部
	 */
	BUSINESS(4, "业务部"),

	/**
	 * 市场部
	 */
	MARKET(5, "市场部"),

	/**
	 * 风控部
	 */
	WINDCONRTOL(6, "风控部"),

	/**
	 * 视频部
	 */
	VIDEO(7, "视频部"),

	/**
	 * 财务部
	 */
	FINANCE(8, "财务部"),

	/**
	 * 运营管理部
	 */
	OPERATIONMANAGEMENT(9, "运营管理部"),

	/**
	 * 市场管理部
	 */
	MARKETMANAGEMENT(10, "市场管理部"),

	/**
	 * 风险管理部
	 */
	RISKMANAGEMENT(11, "风险管理部"),

	/**
	 * 物流金融中心总监
	 */
	LOGISTICSFINANCECENTER(12, "物流金融中心总监"),
	/**
	 * 银行
	 */
	BANK(13, "银行"),
	/**
	 * 经销商集团
	 */
	DEALERMASTER(14, "经销商集团"),
	/**
	 * 品牌集团
	 */
	BRANDMASTER(15, "品牌集团"),
	/**
	 * 超级角色
	 */
	SR(30, "超级角色");

	private int code;
	private String name;

	private ClientTypeConstants(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	/**
	 * 通过name获取code
	 * 
	 * @param name
	 * @return int
	 */
	public static int getCode(String name) {
		for (ClientTypeConstants item : ClientTypeConstants.values()) {
			if (name.equals(item.getName())) {
				return item.getCode();
			}
		}
		return -1;
	}

	/**
	 * 通过code获取name
	 * 
	 * @param code
	 * @return String
	 */
	public static String getName(int code) {
		for (ClientTypeConstants item : ClientTypeConstants.values()) {
			if (code == item.getCode()) {
				return item.getName();
			}
		}
		return null;
	}

	/**
	 * 获取全部部门
	 * 
	 * @param ids
	 *            除去参数里的部门
	 * @return
	 */
	public static String[] getIds(int... exceptIds) {
		
		ClientTypeConstants[] ccs = ClientTypeConstants.values();
		List<String> ids = new ArrayList<String>();
		for (ClientTypeConstants cc : ccs) {
			if(!ArrayUtils.contains(exceptIds, cc.getCode())){
				ids.add(cc.getCode()+"");
			}
		}
		return ids.toArray(new String[]{});
	}
	
	
	
}

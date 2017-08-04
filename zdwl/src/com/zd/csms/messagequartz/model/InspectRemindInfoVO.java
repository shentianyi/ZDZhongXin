package com.zd.csms.messagequartz.model;

import java.io.Serializable;
/**
 * @author
 * 未按风控巡检计划执行提醒
 */
public class InspectRemindInfoVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String outControlUserName;//风控专员
	
	private int outcontroluserid;
	
	/**
	 * 经销商名字
	 */
	private String dealername;
	/**
	 * 金融机构的名字
	 */
	private String bankfullname;
	
	
	private String brandname;//品牌
	
	private String provincename;
	
	private String cityname;

	/**
	 * @return the outcontroluserid
	 */
	public int getOutcontroluserid() {
		return outcontroluserid;
	}

	/**
	 * @param outcontroluserid the outcontroluserid to set
	 */
	public void setOutcontroluserid(int outcontroluserid) {
		this.outcontroluserid = outcontroluserid;
	}

	/**
	 * @return the outControlUserName
	 */
	public String getOutControlUserName() {
		return outControlUserName;
	}

	/**
	 * @param outControlUserName the outControlUserName to set
	 */
	public void setOutControlUserName(String outControlUserName) {
		this.outControlUserName = outControlUserName;
	}

	/**
	 * @return the 经销商名字
	 */
	public String getDealername() {
		return dealername;
	}

	/**
	 * @param 经销商名字 the dealername to set
	 */
	public void setDealername(String dealername) {
		this.dealername = dealername;
	}

	/**
	 * @return the 金融机构的名字
	 */
	public String getBankfullname() {
		return bankfullname;
	}
	/**
	 * @param 金融机构的名字 the bankfullname to set
	 */
	public void setBankfullname(String bankfullname) {
		this.bankfullname = bankfullname;
	}

	/**
	 * @return the brandname
	 */
	public String getBrandname() {
		return brandname;
	}

	/**
	 * @param brandname the brandname to set
	 */
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	/**
	 * @return the provincename
	 */
	public String getProvincename() {
		return provincename;
	}

	/**
	 * @param provincename the provincename to set
	 */
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	/**
	 * @return the cityname
	 */
	public String getCityname() {
		return cityname;
	}

	/**
	 * @param cityname the cityname to set
	 */
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	

}

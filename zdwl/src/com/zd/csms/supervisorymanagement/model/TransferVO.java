package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="T_TRANSFER")
public class TransferVO implements Serializable {

	/**
	 * 调动表
	 */
	private static final long serialVersionUID = -7934939329442326385L;
	/**
	 * 主键ID
	 */
	private int id;                      	
	/**
	 * 经销商ID
	 */
	private int dealerId;       
	/**
	 * 工牌个数
	 */
	private int workCardNumber;
	/**
	 * 标识个数
	 */
	private int identifyNumber;
	/**
	 * 发放日期
	 */
	private Date grantDate;
	/**
	 * QQ号码
	 */
	private String qqNumber;
	/**
	 * QQ密码
	 */
	private String qqPassword;
	/**
	 * QQ密保
	 */
	private String qqPasswordProtect;
	/**
	 * 创建人
	 */
	private int createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改人
	 */
	private int lastModifyUser;
	/**
	 * 最后修改时间
	 */
	private Date lastModifyTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getWorkCardNumber() {
		return workCardNumber;
	}
	public void setWorkCardNumber(int workCardNumber) {
		this.workCardNumber = workCardNumber;
	}
	public int getIdentifyNumber() {
		return identifyNumber;
	}
	public void setIdentifyNumber(int identifyNumber) {
		this.identifyNumber = identifyNumber;
	}
	public Date getGrantDate() {
		return grantDate;
	}
	public void setGrantDate(Date grantDate) {
		this.grantDate = grantDate;
	}
	public String getQqNumber() {
		return qqNumber;
	}
	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	public String getQqPassword() {
		return qqPassword;
	}
	public void setQqPassword(String qqPassword) {
		this.qqPassword = qqPassword;
	}
	public String getQqPasswordProtect() {
		return qqPasswordProtect;
	}
	public void setQqPasswordProtect(String qqPasswordProtect) {
		this.qqPasswordProtect = qqPasswordProtect;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(int lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

}

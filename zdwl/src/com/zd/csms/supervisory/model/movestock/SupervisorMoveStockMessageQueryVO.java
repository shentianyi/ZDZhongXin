package com.zd.csms.supervisory.model.movestock;

import java.util.Date;

import com.zd.csms.messagequartz.model.MsgQueryVO;


public class SupervisorMoveStockMessageQueryVO extends MsgQueryVO{

	/**
	 * 移动申请  提醒
	 */
	private static final long serialVersionUID = -1484965636212845084L;
	
	private int id;
	/**
	 * 经销商名字
	 */
	private String merchantname;
	/**
	 * 金融机构的名字
	 */
	private String bankname;
	
	private int importId;//监管物Id
	
	private String brandname;//品牌
	
	private String vin;//车架号
	
	private String draft_num;//票号
	
	private Date moveTime;//移动时间
	
	private Date moveEndTime;//移动时间
	
	private String moveaddress;//移动地点
	/**
	 * 消息类型
	 */
	private String messagetype;
	
	/**
	 * 1未读 2已读
	 */
	private int isread;
	/**
	 * 消息表ID
	 */
	private int messageId;
	/**
	 * 插入时间
	 */
	private Date createDate;
	
	/**
	 * 当前的角色
	 */
	private Integer currRole;//当前角色

	/**
	 * @return the moveEndTime
	 */
	public Date getMoveEndTime() {
		return moveEndTime;
	}

	/**
	 * @param moveEndTime the moveEndTime to set
	 */
	public void setMoveEndTime(Date moveEndTime) {
		this.moveEndTime = moveEndTime;
	}

	/**
	 * @return the moveTime
	 */
	public Date getMoveTime() {
		return moveTime;
	}

	/**
	 * @param moveTime the moveTime to set
	 */
	public void setMoveTime(Date moveTime) {
		this.moveTime = moveTime;
	}

	/**
	 * @return the moveaddress
	 */
	public String getMoveaddress() {
		return moveaddress;
	}

	/**
	 * @param moveaddress the moveaddress to set
	 */
	public void setMoveaddress(String moveaddress) {
		this.moveaddress = moveaddress;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the 经销商名字
	 */
	public String getMerchantname() {
		return merchantname;
	}

	/**
	 * @param 经销商名字 the merchantname to set
	 */
	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	/**
	 * @return the 金融机构的名字
	 */
	public String getBankname() {
		return bankname;
	}

	/**
	 * @param 金融机构的名字 the bankname to set
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	/**
	 * @return the importId
	 */
	public int getImportId() {
		return importId;
	}

	/**
	 * @param importId the importId to set
	 */
	public void setImportId(int importId) {
		this.importId = importId;
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
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * @return the draft_num
	 */
	public String getDraft_num() {
		return draft_num;
	}

	/**
	 * @param draft_num the draft_num to set
	 */
	public void setDraft_num(String draft_num) {
		this.draft_num = draft_num;
	}

	/**
	 * @return the 消息类型
	 */
	public String getMessagetype() {
		return messagetype;
	}

	/**
	 * @param 消息类型 the messagetype to set
	 */
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	/**
	 * @return the 1未读2已读
	 */
	public int getIsread() {
		return isread;
	}

	/**
	 * @param 1未读2已读 the isread to set
	 */
	public void setIsread(int isread) {
		this.isread = isread;
	}

	/**
	 * @return the 消息表ID
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * @param 消息表ID the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the 插入时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param 插入时间 the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the 当前的角色
	 */
	public Integer getCurrRole() {
		return currRole;
	}

	/**
	 * @param 当前的角色 the currRole to set
	 */
	public void setCurrRole(Integer currRole) {
		this.currRole = currRole;
	}
	
	
}

package com.zd.csms.messagequartz.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_msg_outstorage")
public class MsgOutStorageVO implements Serializable {
	
	/**
	 * 银行释放消息提醒详细-对象
	 */
	private static final long serialVersionUID = 73191462093432682L;
	
	private int id;//主键id
	private String draft_num;//汇票号
	private String vin;//车架号
	private String price;//价格
	private Integer state;//状态(1.待审批2.审核通过3.审核不通过)
	private Integer type;//消息类别
	private Date createDate;//创建时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDraft_num() {
		return draft_num;
	}
	public void setDraft_num(String draft_num) {
		this.draft_num = draft_num;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}

package com.zd.csms.messagequartz.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_msg_movestorage")
public class MsgMoveStorageVO implements Serializable {
	
	/**
	 * 银行移动消息提醒详细-对象
	 */
	private static final long serialVersionUID = 877890010178748638L;
	
	private int id;//主键id
	private String vin;//车架号
	private String price;//价格
	private String moveAddress;//移动位置
	private Integer state;//状态(1.待审批2.审核通过3.审核不通过)
	private Integer totalPrice;//合计价格
	private Integer type;//消息类别
	private Date createDate;//创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMoveAddress() {
		return moveAddress;
	}
	public void setMoveAddress(String moveAddress) {
		this.moveAddress = moveAddress;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
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

package com.zd.csms.messagequartz.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_msg_noprocesscar")
public class MsgNoProcessCarVO implements Serializable {

	/**
	 *  移动车辆超过25天未移动提醒、移动车辆超过总库存20%提醒、异常车辆超过总库存15%提醒    - 对象  
	 */
	private static final long serialVersionUID = -3483763035017640099L;
	private int id;//主键id
	private String vin;//车架号
	private Date moveDate;//移动时间
	private String money;//车辆价值
	private Integer moveCount;//移动数量
	private Integer skuTotalCount;//库存总数量
	private Double moveCarMoney;//移动车辆价格
	private Double inStockCarMoney;//在库车辆金额
	private Integer car_num;//台数
	private Integer type;//消息类别
	private Date createDate;//创建时间
	private Integer dealerId;//经销商Id
	
	public Integer getDealerId() {
		return dealerId;
	}
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
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
	public Date getMoveDate() {
		return moveDate;
	}
	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Integer getMoveCount() {
		return moveCount;
	}
	public void setMoveCount(Integer moveCount) {
		this.moveCount = moveCount;
	}
	public Integer getSkuTotalCount() {
		return skuTotalCount;
	}
	public void setSkuTotalCount(Integer skuTotalCount) {
		this.skuTotalCount = skuTotalCount;
	}
	public Double getMoveCarMoney() {
		return moveCarMoney;
	}
	public void setMoveCarMoney(Double moveCarMoney) {
		this.moveCarMoney = moveCarMoney;
	}
	public Double getInStockCarMoney() {
		return inStockCarMoney;
	}
	public void setInStockCarMoney(Double inStockCarMoney) {
		this.inStockCarMoney = inStockCarMoney;
	}
	public Integer getCar_num() {
		return car_num;
	}
	public void setCar_num(Integer car_num) {
		this.car_num = car_num;
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

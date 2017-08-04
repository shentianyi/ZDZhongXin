package com.zd.csms.supervisory.model;

import java.io.Serializable;

import com.zd.core.annotation.table;

@table(name="T_CHECKSTOCK_CAR")
public class CheckStockCarVO implements Serializable{

	private static final long serialVersionUID = -6724844389668383776L;
	/**
	 * 主键ID
	 */
	private int id;
	/**
	 * 日查库ID
	 */
	private int checkstock_id;
	/**
	 * 车架号
	 */
	private String vin;
	/**
	 * 型号
	 */
	private String model;
	/**
	 * 颜色
	 */
	private String color;
	/**
	 * 车辆当前状态  1:本库  2：二库 3：移动
	 */
	private int currstate;
	/**
	 * 车辆实际状态  1:本库  2：二库 3：移动 4：出库	 5:私自移动、6:私自销售、7:在途销售、8:在途移动、9:信誉额度、10:展厅
	 */
	private int actualstate;
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCheckstock_id() {
		return checkstock_id;
	}
	public void setCheckstock_id(int checkstock_id) {
		this.checkstock_id = checkstock_id;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getCurrstate() {
		return currstate;
	}
	public void setCurrstate(int currstate) {
		this.currstate = currstate;
	}
	public int getActualstate() {
		return actualstate;
	}
	public void setActualstate(int actualstate) {
		this.actualstate = actualstate;
	}

	
	
}

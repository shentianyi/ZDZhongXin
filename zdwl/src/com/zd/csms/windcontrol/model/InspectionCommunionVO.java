package com.zd.csms.windcontrol.model;

import java.io.Serializable;
import com.zd.core.annotation.table;

/**
 * @author zhangzhicheng 巡店报告-与店方沟通情况
 */
@table(name = "windcontrol_inspec_communion")
public class InspectionCommunionVO implements Serializable {
	private static final long serialVersionUID = 667981122425714922L;
	private int id;// 巡店报告id
	private String name;// 店方人员姓名
	private String position;// 职位
	private Integer month_sales;// 月销量
	//private Integer car_count;// 自有车辆
	private String car_count; // 自有车辆 - 需求78 更改该字段类型
	private String content;// 座谈内容
	private String info;// 店方情况
	private String company_info;// 我司情况

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getMonth_sales() {
		return month_sales;
	}

	public void setMonth_sales(Integer month_sales) {
		this.month_sales = month_sales;
	}

	/*public Integer getCar_count() {
		return car_count;
	}

	public void setCar_count(Integer car_count) {
		this.car_count = car_count;
	}*/
	

	public String getContent() {
		return content;
	}

	public String getCar_count() {
		return car_count;
	}

	public void setCar_count(String car_count) {
		this.car_count = car_count;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCompany_info() {
		return company_info;
	}

	public void setCompany_info(String company_info) {
		this.company_info = company_info;
	}

}

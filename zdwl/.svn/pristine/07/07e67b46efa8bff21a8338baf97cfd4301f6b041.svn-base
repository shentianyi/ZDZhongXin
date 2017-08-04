package com.zd.csms.windcontrol.model;

import java.io.Serializable;
import com.zd.core.annotation.table;

/**
 * @author zhangzhicheng 巡店报告-巡检总结
 */
@table(name = "windcontrol_inspec_result")
public class InspectionResultVO implements Serializable {
	private static final long serialVersionUID = 2964699486674223272L;
	private int id;// 巡店报告id
	private Integer danger_level;// 风险等级 :4无 1：A级 2:B级 3:C级
	private Integer dealer_level;// 经销店等级：4无 1：A级 2:B级 3:C级
	private Integer supervisor_level;// 监管员等级：4无 1：A级 2:B级 3:C级
	private String remak;// 巡店总结

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getDanger_level() {
		return danger_level;
	}

	public void setDanger_level(Integer danger_level) {
		this.danger_level = danger_level;
	}

	public Integer getDealer_level() {
		return dealer_level;
	}

	public void setDealer_level(Integer dealer_level) {
		this.dealer_level = dealer_level;
	}

	public Integer getSupervisor_level() {
		return supervisor_level;
	}

	public void setSupervisor_level(Integer supervisor_level) {
		this.supervisor_level = supervisor_level;
	}

	public String getRemak() {
		return remak;
	}

	public void setRemak(String remak) {
		this.remak = remak;
	}

}

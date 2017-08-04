package com.zd.csms.windcontrol.querybean;

import java.util.List;
import java.util.Map;

import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.windcontrol.model.InspectionCommunionVO;
import com.zd.csms.windcontrol.model.InspectionInfoVO;
import com.zd.csms.windcontrol.model.InspectionItemVO;
import com.zd.csms.windcontrol.model.InspectionRecordVO;
import com.zd.csms.windcontrol.model.InspectionResultVO;
import com.zd.csms.windcontrol.model.InspectionSupervisorVO;

/**
 * 巡检报告查询实体
 */

public class InspectionReportQueryBean {
	// 巡检报告基本信息
	private InspectionInfoVO info;
	// 巡检报告-检查内容
	private InspectionItemVO item;

	// 巡检报告与店方沟通情况
	private InspectionCommunionVO communion;

	// 巡检报告监管员信息
	private InspectionSupervisorVO supervisor;
	// 巡检总结
	private InspectionResultVO inspecResult;
	// 巡店报告-检查过程中发现的问题及监管员优/缺点
	private Map<String, List<InspectionRecordVO>> records;
	// 巡店报告-店面拍照
	private List<UploadfileVO> pictures;

	public InspectionInfoVO getInfo() {
		return info;
	}

	public void setInfo(InspectionInfoVO info) {
		this.info = info;
	}

	public InspectionItemVO getItem() {
		return item;
	}

	public void setItem(InspectionItemVO item) {
		this.item = item;
	}

	public InspectionCommunionVO getCommunion() {
		return communion;
	}

	public void setCommunion(InspectionCommunionVO communion) {
		this.communion = communion;
	}

	public InspectionSupervisorVO getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(InspectionSupervisorVO supervisor) {
		this.supervisor = supervisor;
	}

	public InspectionResultVO getInspecResult() {
		return inspecResult;
	}

	public void setInspecResult(InspectionResultVO inspecResult) {
		this.inspecResult = inspecResult;
	}

	public Map<String, List<InspectionRecordVO>> getRecords() {
		return records;
	}

	public void setRecords(Map<String, List<InspectionRecordVO>> records) {
		this.records = records;
	}

	public List<UploadfileVO> getPictures() {
		return pictures;
	}

	public void setPictures(List<UploadfileVO> pictures) {
		this.pictures = pictures;
	}

}

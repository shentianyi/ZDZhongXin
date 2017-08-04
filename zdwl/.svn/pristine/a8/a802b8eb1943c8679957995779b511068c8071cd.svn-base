package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.ExtHandoverPlanVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IHandoverPlanDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<HandoverPlanVO> searchHandoverPlanList(HandoverPlanQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<HandoverPlanVO> searchHandoverPlanListByPage(HandoverPlanQueryVO query,IThumbPageTools tools);
	//提交轮岗计划，进入审批流程
	public boolean updHandoverPlanEditStatus(HandoverPlanVO handoverPlan);
	/**
	 * 导出轮岗计划表台账 --需求57
	 * @time 20170517
	 */
	public List<ExtHandoverPlanVO> ExtHandoverPlanLedgerList(HandoverPlanQueryVO handoverPlanQuery);
}

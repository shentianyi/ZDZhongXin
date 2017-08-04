package com.zd.csms.planandreport.dao;

import java.util.List;
import com.zd.core.IDAO;
import com.zd.csms.planandreport.model.InspectionPlanQueryVO;
import com.zd.csms.planandreport.querybean.InspectionPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.InspectionPlanQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IInspectionPlanDAO extends IDAO {
	/**
	 * 分页查询(未审核)
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionPlanInfoQueryBean> findList(
			InspectionPlanQueryVO query, IThumbPageTools tools);

	public List<InspectionPlanInfoQueryBean> findListLedger(
			InspectionPlanQueryVO query, IThumbPageTools tools);

	/**
	 * 新增页面待选经销商列表分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionPlanQueryBean> loadDealerList(
			InspectionPlanQueryVO query, IThumbPageTools tools);

	/**
	 * 加载经销商信息对象
	 * 
	 * @param id
	 * @return
	 */
	public InspectionPlanQueryBean loadDealerInfo(int id);

	/**
	 * 加载所有已选销商信息
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionPlanQueryBean> findListAlready(
			InspectionPlanQueryVO query, IThumbPageTools tools);

	/**
	 * 根据编号记载经销商记录
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionPlanQueryBean> findListByPlanCode(
			InspectionPlanQueryVO query);

	/**
	 * 巡检计划台账
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionPlanQueryBean> findListDealerLedger(
			InspectionPlanQueryVO query, IThumbPageTools tools);

	public List<InspectionPlanQueryBean> getDealerPlanCodes(
			InspectionPlanQueryVO query);

	/**
	 * 根据巡检编号删除已选经销商记录
	 * 
	 * @param planCode
	 * @return
	 */
	public boolean deleteByPlanCode(String planCode);
	
	public boolean deletePlan();
	public boolean deletePlanInfo();

	public List<InspectionPlanInfoQueryBean> findListAlreadyCheck(
			InspectionPlanQueryVO query, IThumbPageTools tools);

	/*
	 * 需求38 -- 导出巡检计划台账（未审核）
	 * @time 20170519
	*/
	public List<InspectionPlanInfoQueryBean> ExtFindListLedgerNotAudited(
			InspectionPlanQueryVO query);

}

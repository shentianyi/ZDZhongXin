package com.zd.csms.planandreport.dao;

import java.util.List;
import java.util.Map;

import com.zd.core.IDAO;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.querybean.VideoPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IVideoPlanDAO extends IDAO {
	public List<VideoPlanQueryBean> loadDealerList(VideoPlanQueryVO query,
			IThumbPageTools tools);

	public List<VideoPlanInfoQueryBean> findList(VideoPlanQueryVO query,
			IThumbPageTools tools);

	public List<VideoPlanQueryBean> findListAlready(VideoPlanQueryVO query,
			IThumbPageTools tools);

	public List<VideoPlanQueryBean> findListDealerLedger(
			VideoPlanQueryVO query, IThumbPageTools tools);

	public List<VideoPlanInfoQueryBean> findListAlreadyCheck(
			VideoPlanQueryVO query, IThumbPageTools tools);

	public VideoPlanQueryBean loadDealerInfo(int id);

	public boolean deleteByPlanCode(String planCode);

	public boolean update(String planCode);
	
	public boolean deletePlan();
	public boolean deletePlanInfo();
	
	/**
	 * 更新视频检查报告状态
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updateStatus(int status, int id);

	/*
	 * 需求38 -- 导出视频检查计划台账
	 * @time 20170518
	*/
	public List<VideoPlanQueryBean> ExtVideoFindListDealerLedger(VideoPlanQueryVO query);
	// 视频检查计划台账-计划次数
	public Map<Integer, Integer> findCountPlan(String dealers);

}

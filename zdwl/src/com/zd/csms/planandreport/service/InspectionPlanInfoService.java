package com.zd.csms.planandreport.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.planandreport.dao.IInspectionPlanInfoDAO;
import com.zd.csms.planandreport.dao.IVideoPlanInfoDAO;
import com.zd.csms.planandreport.dao.PlanAndReportDAOFactory;
import com.zd.csms.planandreport.model.InspectionPlanInfoVO;
import com.zd.csms.planandreport.model.InspectionPlanQueryVO;
import com.zd.csms.planandreport.model.VideoPlanInfoVO;
import com.zd.csms.planandreport.querybean.InspectionPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.VideoPlanInfoQueryBean;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.util.Util;


public class InspectionPlanInfoService extends ServiceSupport {
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private IInspectionPlanInfoDAO dao = PlanAndReportDAOFactory.getInspectionPlanInfoDAO();

	public InspectionPlanInfoVO get(int id) {
		return dao.get(InspectionPlanInfoVO.class, id, new BeanPropertyRowMapper(InspectionPlanInfoVO.class));
	}
	public InspectionPlanInfoVO getInspectionPlanInfoVO(int id) {
		return dao.get(InspectionPlanInfoVO.class, id, new BeanPropertyRowMapper(InspectionPlanInfoVO.class));
	}

	public int add(InspectionPlanInfoVO bean) throws Exception {
		bean.setId(Util.getID(InspectionPlanInfoVO.class));
		if (dao.add(bean)) {
			return Util.getID(InspectionPlanInfoVO.class);
		}
		return 0;
	}

	public boolean update(InspectionPlanInfoVO bean) throws Exception {
		return dao.update(bean);
	}
	
	//批量更新
	public boolean update(String plandCode) throws Exception {
		return dao.update(plandCode);
	}

	public boolean delete(InspectionPlanInfoVO bean) throws Exception {
		return dao.delete(InspectionPlanInfoVO.class, bean.getId());
	}
	
	public InspectionPlanInfoVO get(String plandCode){
		return dao.get(plandCode);
	}

	/**
	 * 根据计划编号统计对应经销商信息
	 * @param planCode
	 * @return
	 * @throws Exception
	 */
	public InspectionPlanInfoQueryBean getDealerInfoByPlanCode(String planCode) throws Exception {
		return dao.getDealerInfoByPlanCode(planCode);
	}
	/**
	 * 根据计划编号统计对应经销商信息(台账)
	 * @param planCode
	 * @return
	 * @throws Exception
	 */
	public InspectionPlanInfoQueryBean getLedgerDealerInfo(InspectionPlanQueryVO query) throws Exception {
		return dao.getLedgerDealerInfo(query);
	}
	public InspectionPlanInfoQueryBean getLedgerInfo(InspectionPlanQueryVO query) throws Exception {
		return dao.getLedgerInfo(query);
	}
	public InspectionPlanInfoQueryBean getDealerInfo(String planCode) throws Exception {
		return dao.getDealerInfo(planCode);
	}
	
}

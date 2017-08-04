package com.zd.csms.planandreport.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.planandreport.dao.IVideoPlanInfoDAO;
import com.zd.csms.planandreport.dao.PlanAndReportDAOFactory;
import com.zd.csms.planandreport.model.VideoPlanInfoVO;
import com.zd.csms.planandreport.querybean.VideoPlanInfoQueryBean;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.util.Util;


public class VideoPlanInfoService extends ServiceSupport {
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private IVideoPlanInfoDAO dao = PlanAndReportDAOFactory.getVideoPlanInfoDAO();

	public VideoPlanInfoVO get(int id) {
		return dao.get(VideoPlanInfoVO.class, id, new BeanPropertyRowMapper(VideoPlanInfoVO.class));
	}
	public VideoPlanInfoVO getVideoPlanInfoVO(int id) {
		return dao.get(VideoPlanInfoVO.class, id, new BeanPropertyRowMapper(VideoPlanInfoVO.class));
	}

	public int add(VideoPlanInfoVO bean) throws Exception {
		bean.setId(Util.getID(VideoPlanInfoVO.class));
		if (dao.add(bean)) {
			return Util.getID(VideoPlanInfoVO.class);
		}
		return 0;
	}

	public boolean update(VideoPlanInfoVO bean) throws Exception {
		return dao.update(bean);
	}
	//批量更新
	public boolean update(String plandCode) throws Exception {
		return dao.update(plandCode);
	}

	public boolean delete(VideoPlanInfoVO bean) throws Exception {
		return dao.delete(VideoPlanInfoVO.class, bean.getId());
	}
	
	public VideoPlanInfoVO get(String plandCode){
		return dao.get(plandCode);
	}

	/**
	 * 根据计划编号统计对应经销商信息
	 * @param planCode
	 * @return
	 * @throws Exception
	 */
	public VideoPlanInfoQueryBean getDealerInfoByPlanCode(String planCode) throws Exception {
		return dao.getDealerInfoByPlanCode(planCode);
	}
	
}

package com.zd.csms.planandreport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;
import com.zd.core.ServiceSupport;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.planandreport.dao.IVideoPlanDAO;
import com.zd.csms.planandreport.dao.PlanAndReportDAOFactory;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.model.VideoPlanVO;
import com.zd.csms.planandreport.querybean.VideoPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class VideoPlanService extends ServiceSupport {
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private IVideoPlanDAO dao = PlanAndReportDAOFactory.getVideoPlanDAO();

	public VideoPlanVO get(int id) {
		return dao.get(VideoPlanVO.class, id, new BeanPropertyRowMapper(
				VideoPlanVO.class));
	}

	public VideoPlanVO getVideoPlanVO(int id) {
		return dao.get(VideoPlanVO.class, id, new BeanPropertyRowMapper(
				VideoPlanVO.class));
	}

	public int add(VideoPlanVO bean) throws Exception {
		bean.setId(Util.getID(VideoPlanVO.class));
		if (dao.add(bean)) {
			return bean.getId();
		}
		return 0;
	}

	public boolean update(VideoPlanVO bean) throws Exception {
		return dao.update(bean);
	}

	/**
	 * 批量更新提交时间状态//
	 */
	public boolean update(String planCode) {
		return dao.update(planCode);
	}

	public boolean delete(VideoPlanVO bean) throws Exception {
		return dao.delete(VideoPlanVO.class, bean.getId());
	}
	
	public boolean deleteInfo() throws Exception {
		boolean flag = false;
		flag = dao.deletePlanInfo();
		flag = dao.deletePlan();
		return flag;
	}

	public boolean deleteByPlanCode(String planCode) throws Exception {
		return dao.deleteByPlanCode(planCode);
	}

	public List<VideoPlanInfoQueryBean> findList(VideoPlanQueryVO query,
			IThumbPageTools tools) throws Exception {
		List<VideoPlanInfoQueryBean> list = dao.findList(query, tools);
		return list;
	}

	public List<VideoPlanQueryBean> findListAlready(VideoPlanQueryVO query,
			IThumbPageTools tools) throws Exception {
		List<VideoPlanQueryBean> list = dao.findListAlready(query, tools);
		return list;
	}


	public List<VideoPlanQueryBean> findListDealerLedger(
			VideoPlanQueryVO query, IThumbPageTools tools,HttpServletRequest request) throws Exception {
		List<VideoPlanQueryBean> list = dao.findListDealerLedger(query, tools);
		/*if (list !=null && list.size()>0) {
			Set<Integer> dealerIds = new HashSet<Integer>();
			for (VideoPlanQueryBean dealersVO : list) {
				if (dealerIds!=null) {
					dealerIds.add(dealersVO.getDealerId());
				}
			}
			Map<Integer, Integer> planCountMap=new HashMap<Integer, Integer>();
			if (dealerIds.size()>0) {
				planCountMap=dao.findCountPlan(dealerIds.toString());
				
				request.setAttribute("planCountMap", planCountMap);
			}
		}*/
		
		return list;
	}

	public List<VideoPlanInfoQueryBean> findListAlreadyCheck(
			VideoPlanQueryVO query, IThumbPageTools tools) throws Exception {
		List<VideoPlanInfoQueryBean> list = dao.findListAlreadyCheck(query,
				tools);
		return list;
	}

	public List<VideoPlanQueryBean> loadDealerList(VideoPlanQueryVO query,
			IThumbPageTools tools) throws Exception {
		List<VideoPlanQueryBean> list = dao.loadDealerList(query, tools);
		return list;
	}

	public VideoPlanQueryBean loadDealerInfo(int id) throws Exception {
		return dao.loadDealerInfo(id);
	}

	
	
	/**
	 * 更新视频检查报告状态
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updateStatus(int status, int id) {
		
		return dao.updateStatus(status,id);
	}

	/*
	 * 需求38 -- 导出视频检查计划台账
	 * @time 20170518
	*/
	public List<VideoPlanQueryBean> ExtVideoFindListDealerLedger(VideoPlanQueryVO query) {
		return dao.ExtVideoFindListDealerLedger(query);
	}

}

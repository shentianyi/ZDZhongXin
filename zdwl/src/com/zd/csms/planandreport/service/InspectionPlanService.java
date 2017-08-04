package com.zd.csms.planandreport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.planandreport.dao.IInspectionPlanDAO;
import com.zd.csms.planandreport.dao.PlanAndReportDAOFactory;
import com.zd.csms.planandreport.model.InspectionPlanQueryVO;
import com.zd.csms.planandreport.model.InspectionPlanVO;
import com.zd.csms.planandreport.querybean.InspectionPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.InspectionPlanQueryBean;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class InspectionPlanService extends ServiceSupport {
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private IInspectionPlanDAO dao = PlanAndReportDAOFactory.getInspectionPlanDAO();

	public InspectionPlanVO get(int id) {
		return dao.get(InspectionPlanVO.class, id, new BeanPropertyRowMapper(InspectionPlanVO.class));
	}
	public InspectionPlanVO getInspectionPlanVO(int id) {
		return dao.get(InspectionPlanVO.class, id, new BeanPropertyRowMapper(InspectionPlanVO.class));
	}

	public int add(InspectionPlanVO bean) throws Exception {
		bean.setId(Util.getID(InspectionPlanVO.class));
		if (dao.add(bean)){
			return bean.getId();
		}
		return 0;
	}

	public boolean update(InspectionPlanVO bean) throws Exception {
		return dao.update(bean);
	}

	public boolean delete(InspectionPlanVO bean) throws Exception {
		return dao.delete(InspectionPlanVO.class, bean.getId());
	}
	
	public boolean deleteInfo() throws Exception {
		boolean flag = false;
		flag = dao.deletePlanInfo();
		flag = dao.deletePlan();
		return flag;
	}

	/**
	 * 巡检计划列表分页查询(未审核)
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<InspectionPlanInfoQueryBean> findList(InspectionPlanQueryVO query, IThumbPageTools tools) throws Exception {
		List<InspectionPlanInfoQueryBean> list = dao.findList(query, tools);
		return list;
	}
	
	public List<InspectionPlanInfoQueryBean> findListLedger(InspectionPlanQueryVO query, IThumbPageTools tools) throws Exception {
		List<InspectionPlanInfoQueryBean> list = dao.findListLedger(query, tools);
		return list;
	}
	/**
	 * 新增页面待选经销商列表
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<InspectionPlanQueryBean> loadDealerList(InspectionPlanQueryVO query, IThumbPageTools tools) throws Exception {
		List<InspectionPlanQueryBean> list = dao.loadDealerList(query, tools);
		return list;
	}
	/**
	 * 加载经销商信息对象   为渲染已选经销商列表做准备
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InspectionPlanQueryBean loadDealerInfo(int id) throws Exception {
		return dao.loadDealerInfo(id);
	}
	
	/**
	 * 加载所有已选经销商信息列表
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<InspectionPlanQueryBean> findListAlready(InspectionPlanQueryVO query, IThumbPageTools tools) throws Exception {
		List<InspectionPlanQueryBean> list = dao.findListAlready(query, tools);
		return list;
	}
	
	/**
	 * 根据编号加载经销商信息
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<InspectionPlanQueryBean> findListByPlanCode(InspectionPlanQueryVO query) throws Exception {
		List<InspectionPlanQueryBean> list = dao.findListByPlanCode(query);
		return list;
	}
	
	/**
	 * 巡检计划台账
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<InspectionPlanQueryBean> findListDealerLedger(InspectionPlanQueryVO query, IThumbPageTools tools) throws Exception {
		List<InspectionPlanQueryBean> list = dao.findListDealerLedger(query, tools);
		return list;
	}
	public List<InspectionPlanQueryBean> getDealerPlanCodes(InspectionPlanQueryVO query) throws Exception {
		List<InspectionPlanQueryBean> list = dao.getDealerPlanCodes(query);
		return list;
	}
	
	/**
	 * 加载未审核列表
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<InspectionPlanInfoQueryBean> findListAlreadyCheck(InspectionPlanQueryVO query, IThumbPageTools tools) throws Exception {
		List<InspectionPlanInfoQueryBean> list = dao.findListAlreadyCheck(query, tools);
		return list;
	}
	
	/**
	 * 根据巡检编号删除经销商记录
	 * @param planCode
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByPlanCode(String planCode) throws Exception {
		return dao.deleteByPlanCode(planCode);
	}
	/*
	 * 需求38 -- 导出巡检计划台账（未审核）
	 * @time 20170519
	*/
	public List<InspectionPlanInfoQueryBean> ExtFindListLedgerNotAudited(
			InspectionPlanQueryVO query) {
		return dao.ExtFindListLedgerNotAudited(query);
	}
	
	
	
}

package com.zd.csms.supervisory.service;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.dao.ICheckStockDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.model.CheckStockDealerVO;
import com.zd.csms.supervisory.model.CheckStockQueryVO;
import com.zd.csms.supervisory.model.CheckStockVO;
import com.zd.csms.supervisory.querybean.CheckStockDealerQueryBean;
import com.zd.csms.supervisory.querybean.CheckStockQueryBean;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class CheckStockService extends ServiceSupport{
	
	private ICheckStockDAO dao = SupervisorDAOFactory.getCheckStockDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	
	
	public CheckStockVO get(int id){
		return dao.get(CheckStockVO.class, id, new BeanPropertyRowMapper(CheckStockVO.class));
	}
	
	public boolean add(CheckStockVO bean) throws Exception{
		bean.setId(Util.getID(CheckStockVO.class));
		return dao.add(bean);
	}
	
	public boolean update(CheckStockVO bean) throws Exception{
		CheckStockVO checkStock = get(bean.getId());
		checkStock.setCount(bean.getCount());
		checkStock.setReason(bean.getReason());
		return dao.update(checkStock);
	}
	
	public boolean delete(CheckStockVO bean) throws Exception{
		return dao.delete(CheckStockVO.class, bean.getId());
	}
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockQueryBean> findList(CheckStockQueryVO query, IThumbPageTools tools){
		return dao.findList(query, tools);
	}
	
	/**
	 * 市场专员-市场部经理--市场部部长--总监
	 * @param query
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean approval(CheckStockQueryVO query,UserSession user) throws Exception{
		CheckStockVO bean = dao.get(CheckStockVO.class, query.getId(), new BeanPropertyRowMapper(CheckStockVO.class));
		int currRole = query.getCurrRole();
		UserVO userVO =  user.getUser();
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.WINDCONRTOL_INTERNAL.getCode()
					&&RoleUtil.roleValidate(RoleConstants.WINDCONRTOL_INTERNAL.getCode(), currRole) ){
			
				bean.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
			}else if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole)){
				bean.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.BUSINESS_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole)){
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完成
			}
		}
		dao.update(bean);
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.CHECKSTOCK.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		return true;
	}
	
	public boolean submit(int id) throws Exception{
		CheckStockVO bean =  get(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.CHECKSTOCK.getCode());
		bean.setNextApproval(RoleConstants.WINDCONRTOL_INTERNAL.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		return dao.update(bean);
	}

	public boolean addDealer(CheckStockDealerVO csd) throws Exception {
		csd.setId(Util.getID(CheckStockDealerVO.class));
		return dao.add(csd);
	}
	
	/**
	 * 查询查库频次申请列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockDealerQueryBean> findDealerList(CheckStockQueryVO query, IThumbPageTools tools){
		return dao.findDealerList(query, tools);
	}
	
	/**
	 * 查询查库频次申请列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockDealerQueryBean> findDealerList(CheckStockQueryVO query){
		return dao.findDealerList(query);
	}

	public boolean deleteDealer(CheckStockDealerVO csd) {
		return dao.delete(CheckStockDealerVO.class, csd.getId());
	}
}

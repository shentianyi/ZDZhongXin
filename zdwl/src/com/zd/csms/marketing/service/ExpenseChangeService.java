package com.zd.csms.marketing.service;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.dao.IExpenseChangeDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.ExpenseChangeQueryVO;
import com.zd.csms.marketing.model.ExpenseChangeVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.querybean.ExpenseChangeQueryBean;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class ExpenseChangeService extends ServiceSupport{
	private IExpenseChangeDAO dao = MarketFactory.getExpenseChangeDAO();
	//private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private DealerService dealerService = new DealerService();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	
	public ExpenseChangeVO get(int id){
		return dao.get(ExpenseChangeVO.class, id, new BeanPropertyRowMapper(ExpenseChangeVO.class));
	}
	
	public boolean add(ExpenseChangeVO bean) throws Exception{
		bean.setId(Util.getID(ExpenseChangeVO.class));
		return dao.add(bean);
	}
	
	public boolean update(ExpenseChangeVO bean) throws Exception{
		ExpenseChangeVO	vo = get(bean.getId());
		vo.setChangeDate(bean.getChangeDate());
		vo.setChangemoney(vo.getChangemoney());
		vo.setDealerId(bean.getDealerId());
		vo.setRemark(bean.getRemark());
		vo.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		vo.setLastModifyDate(bean.getLastModifyDate());
		vo.setLastModifyUserId(bean.getLastModifyUserId());
		return dao.update(vo);
	}
	
	public boolean delete(ExpenseChangeVO bean) throws Exception{
		return dao.delete(ExpenseChangeVO.class, bean.getId());
	}
	
	/**
	 * 监管费变更分页查询
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception 
	 */
	public List<ExpenseChangeQueryBean> findList(ExpenseChangeQueryVO query,IThumbPageTools tools) throws Exception{
		List<ExpenseChangeQueryBean> list= dao.findList(query, tools);
		return list;
	}
	
	public List<ExpenseChangeQueryBean> managementLedgerList(ExpenseChangeQueryVO query,IThumbPageTools tools) throws Exception{
		List<ExpenseChangeQueryBean> list= dao.managementLedgerList(query, tools);
		return list;
	}
	
	/**
	 * 市场专员-市场部经理--市场部部长--总监
	 * @param query
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean approval(ExpenseChangeQueryVO query,UserSession user,int currRole) throws Exception{
		ExpenseChangeVO bean = dao.get(ExpenseChangeVO.class, query.getId(), new BeanPropertyRowMapper(ExpenseChangeVO.class));
		DealerVO dealer = dao.get(DealerVO.class, bean.getDealerId(), new BeanPropertyRowMapper(DealerVO.class));
		UserVO userVO =  user.getUser();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.MARKET_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKET_AMALDAR.getCode(), currRole) ){
				//市场部经理
				bean.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
			}else if(bean.getNextApproval()==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(), currRole)){
				//市场部部长
				bean.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
			}else if(bean.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//总监
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完成
				if(bean.getDealerId()>0){
					//修改经销商名录表
					
					String beforeOperationMode = null;	//变更前标准费用
					String lastOperationMode = dealer.getSupervisionMode();	//变更后标准费用
					Date modifyTime = null;	//监管费变更时间
						
					ModeChangeService ecService = new ModeChangeService();
					ModeChangeLogVO mclVO = dao.selectLastModeChangeByDearlerId(bean.getDealerId());
							if(mclVO != null){
								beforeOperationMode = mclVO.getBeforeOperationMode();
								
								modifyTime = mclVO.getModifyTime();
								
								lastOperationMode = mclVO.getLastOperationMode();
								
								if(lastOperationMode == null || "".equals(lastOperationMode.trim())){
									lastOperationMode = dealer.getSupervisionMode();
								}
									
							}
						
					//TODO 记录变更日志
					ModeChangeLogVO mcl = new ModeChangeLogVO(
													Util.getID(ModeChangeLogVO.class)
													,dealer.getId()
													,beforeOperationMode
													,lastOperationMode
													//变更前监管费用
													,dealer.getSuperviseMoney()
													//变更后监管费用 
													,bean.getChangemoney()
													,modifyTime
													,bean.getChangeDate()
													,bean.getCreateUserId()
													,user.getUser().getId()
													,bean.getCreateDate()
													,new Date());
					ecService.addModeChangeLog(mcl);
					
				}
					
					
					
					dealer.setChangeBeforeInfo(dealer.getSuperviseMoney());
					dealer.setSuperviseMoney(bean.getChangemoney());
					dealer.setIsChangeSuperviseMoney(DealerContant.ISCHANGESUPERVISEMONEY_YES.getCode());
					dealer.setChangeSuperviseMoneyDate(bean.getChangeDate());
					dao.update(dealer);
			}
		}
		bean.setLastModifyDate(new Date());
		bean.setLastModifyUserId(user.getUser().getId());
		dao.update(bean);
		
		
		
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.EXPENSECHANGE.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "监管费变更流转单:"+dealer.getDealerName(), "/market/expenseChange.do?method=findList", 1, 
						MessageTypeContant.EXPENSECHANGE.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "监管费变更流转单:"+dealer.getDealerName()+"审批未通过，请查看", "/market/expenseChange.do?method=findList&query.pageType=2",1, MessageTypeContant.EXPENSECHANGE.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "监管费变更流转单:"+dealer.getDealerName()+"审批已通过，请查看", "/market/expenseChange.do?method=findList&query.pageType=2",1, MessageTypeContant.EXPENSECHANGE.getCode(),bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean submit(int id) throws Exception{
		ExpenseChangeVO bean =  get(id);
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.EXPENSECHANGE.getCode());*/
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		DealerVO dealer = dao.get(DealerVO.class, bean.getDealerId(), new BeanPropertyRowMapper(DealerVO.class));
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "监管费变更流转单:"+dealer.getDealerName(), "/market/expenseChange.do?method=findList", 1, 
						MessageTypeContant.EXPENSECHANGE.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.setCreateDate(new Date());
		return dao.update(bean);
	}
	
	public List<ExpenseChangeVO> selectLastTwoExpenseChangeByDearlerId(int dearlerId){
		return dao.selectLastTwoExpenseChangeByDearlerId(dearlerId);
	}
}

package com.zd.csms.marketing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Message;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.BusinessTransferContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.dao.IDealerExitDAO;
import com.zd.csms.marketing.dao.IDealerSupervisorDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerExitQueryVO;
import com.zd.csms.marketing.model.DealerExitVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.DealerExitQueryBean;
import com.zd.csms.marketing.querybean.SupervisorQueryBean;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.contant.NoticeTypeContant;
import com.zd.csms.message.dao.INoticeDAO;
import com.zd.csms.message.dao.MessageDAOFactory;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messageAndWaring.message.service.market.MarketMessageTypeService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserDAO;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商撤店信息流转
 * @author licheng
 *
 */

public class DealerExitService extends ServiceSupport{
	private IDealerExitDAO dao = MarketFactory.getDealerExitDAO();
	private IDealerSupervisorDAO dsDao = MarketFactory.getDealerSupervisorDAO();
	private INoticeDAO noticeDao = MessageDAOFactory.getNoticeDao();
	private IUserDAO userDao = RbacDAOFactory.getUserDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	
	private MarketMessageTypeService typeService = new MarketMessageTypeService();
	
	public DealerExitVO get(int id){
		return dao.get(DealerExitVO.class, id, new BeanPropertyRowMapper(DealerExitVO.class));
	}
	
	public boolean add(DealerExitVO bean) throws Exception{
		bean.setId(Util.getID(DealerExitVO.class));
		return dao.add(bean);
	}
	
	/**
	 * 不同的角色更新的内容不同
	 * @param bean
	 * @param currRole
	 * @return
	 * @throws Exception
	 */
	public boolean update(DealerExitVO bean,int currRole) throws Exception{
		DealerExitVO oldBean = get(bean.getId());
		if(currRole == RoleConstants.SR.getCode())
			currRole = oldBean.getNextApproval();
		
		if(RoleConstants.MARKET_COMMISSIONER.getCode()==currRole){//市场部专员
			oldBean.setDealerId(bean.getDealerId());
			oldBean.setExitDateByMarket(oldBean.getExitDateByMarket());
			oldBean.setIsRefundByMarket(bean.getIsRefundByMarket());
			oldBean.setCreateDate(bean.getCreateDate());
			oldBean.setRemarkByMarket(bean.getRemarkByMarket());
			oldBean.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		}else if(RoleConstants.FINANCE_ACCOUNTING.getCode()==currRole){//财务部会计
			oldBean.setLastPayDate(bean.getLastPayDate());
			oldBean.setPayMoney(bean.getPayMoney());
			oldBean.setPayMode(bean.getPayMode());
			oldBean.setIsArrears(bean.getIsArrears());
			oldBean.setArrearsDate(bean.getArrearsDate());
			oldBean.setArrearsNumber(bean.getArrearsNumber());
			oldBean.setIsRefundByFinance(bean.getIsRefundByFinance());
			oldBean.setExitDateByFinance(bean.getExitDateByFinance());
			oldBean.setRemarkByFinance(bean.getRemarkByFinance());
		}else if(RoleConstants.BUSINESS_COMMISSIONER.getCode()==currRole){//业务部专员
			oldBean.setBusinessCommissioner(bean.getBusinessCommissioner());
			oldBean.setIsBusinessEnd(bean.getIsBusinessEnd());
			oldBean.setEndMode(bean.getEndMode());
			oldBean.setExitDateByBusiness(bean.getExitDateByBusiness());
			oldBean.setRemarkByBusiness(bean.getRemarkByBusiness());
		}else if(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()==currRole){//监管员招聘专员
			oldBean.setSupervisorId(bean.getSupervisorId());
			oldBean.setExitDateBySupervise(bean.getExitDateBySupervise());
			oldBean.setRemarkBySupervise(bean.getRemarkBySupervise());
		}
		oldBean.setLastModifyDate(bean.getLastModifyDate());
		oldBean.setLastModifyUserId(bean.getLastModifyUserId());
		return dao.update(oldBean);
	}
	
	public boolean delete(DealerExitVO bean) throws Exception{
		return dao.delete(DealerExitVO.class, bean.getId());
	}
	
	public DealerExitVO get(DealerExitVO bean) throws Exception{
		DealerExitVO dealerExit = dao.get(DealerExitVO.class, bean.getId(), new BeanPropertyRowMapper(DealerExitVO.class));
		return dealerExit;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<DealerExitQueryBean> findList(DealerExitQueryVO query,IThumbPageTools tools){
		return dao.findList(query, tools);
	}
	
	/**
	 * 审批流程
	 * @param user
	 * @param bean
	 * @param query
	 * @throws Exception
	 */
	public void approval(UserSession user,DealerExitQueryVO query,DealerExitVO bean ) throws Exception{
		DealerExitVO dealerExit = get(bean.getId());
		int currRole = query.getCurrRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = dealerExit.getNextApproval();
		DealerVO dealer = dao.get(DealerVO.class, dealerExit.getDealerId(), new BeanPropertyRowMapper(DealerVO.class));
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			dealerExit.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			if(dealerExit.getNextApproval()==RoleConstants.MARKET_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKET_AMALDAR.getCode(), currRole)){
				//市场部经理
				dealerExit.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
			}else if(dealerExit.getNextApproval()==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(), currRole)){
				//市场部部长
				dealerExit.setNextApproval(RoleConstants.FINANCE_ACCOUNTING.getCode());
			}else if(dealerExit.getNextApproval()==RoleConstants.FINANCE_ACCOUNTING.getCode()
					&&RoleUtil.roleValidate(RoleConstants.FINANCE_ACCOUNTING.getCode(), currRole)){
				//财务部会计
				dealerExit.setNextApproval(RoleConstants.FINANCE_AMALDAR.getCode());
			}else if(dealerExit.getNextApproval()==RoleConstants.FINANCE_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.FINANCE_AMALDAR.getCode(), currRole)){
				//财务部经理
				dealerExit.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
			}else if(dealerExit.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole)){
				//业务专员
				dealerExit.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
			}/*else if(dealerExit.getNextApproval()==RoleConstants.BUSINESS_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole)){
				//业务部经理
				dealerExit.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode());
			}else if(dealerExit.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(), currRole)){
				//监管员管理部招聘专员补充表单
				dealerExit.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
			}else if(dealerExit.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole)){
				//监管员管理部经理
				dealerExit.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			}else if(dealerExit.getNextApproval()==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)){
				//运营管理部部长
				dealerExit.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
			}else if(dealerExit.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//总监
				dealerExit.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
			}*/else if(dealerExit.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(), currRole)){
				//综合专员
				dealerExit.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
				if(dealerExit.getDealerId()>0){
					//审批成功，执行业务更新经销商名录为退出状态
					try {
						dealer.setCooperationState(DealerContant.COOPERATIONSTATE_EXIT.getCode());
						dealer.setExitDate(bean.getExitDateByMarket());
						dao.update(dealer);
						dsDao.deleteByRepoIdanddealerId(dealer.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		dao.update(dealerExit);
		
		//记录流程
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalPerson(user.getUser().getUserName());
		approval.setApprovalObjectId(dealerExit.getId());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalType(ApprovalTypeContant.DEALEREXIT.getCode());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		dao.add(approval);
		
		try {
			if(dealerExit.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{dealerExit.getNextApproval()+""}, "经销商撤店信息流转单："+dealer.getDealerName(), "/market/dealerExit.do?method=findList", 1, 
						MessageTypeContant.DEALEREXIT.getCode(), dealerExit.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "经销商撤店信息流转"+dealer.getDealerName()+"审批未通过，请查看", "/market/dealerExit.do?method=findList&query.pageType=2",1, MessageTypeContant.DEALEREXIT.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "经销商撤店信息流转"+dealer.getDealerName()+"审批已通过，请查看", "/market/dealerExit.do?method=findList",1, MessageTypeContant.DEALEREXIT.getCode(),bean.getCreateUserId());
				//项目撤出流转单(新改造)
				typeService.projectOut(dealerExit.getId());
				
				try {
					//给所有人发送消息提醒 除监管员，银行，品牌集团，经销商集团
					for(BusinessTransferContant approvalRole:BusinessTransferContant.values()){
						String userid = userRoleDao.findUsingUserIdByRole(approvalRole.getCode()+"");
						if(StringUtil.isNotEmpty(userid)){
							String[] userids = userid.split(",");
							for (String uid : userids) {
								MessageService msgService = new MessageService();
								MessageVO msg = msgService.loadMsgByUserAndType(Integer.parseInt(uid), MessageTypeContant.DEALEREXIT.getCode());
								if(msg != null){
									int name = 1;
									if(StringUtil.isNumber(msg.getName())){
										name = Integer.parseInt(msg.getName())+1;
									}
									msg.setName(name+"");
									msgService.updMessage(msg);
								}else{
									MessageUtil.addMsg(Integer.parseInt(uid),  1+"", "/market/dealerExit.do?method=findList", 1, 
											MessageTypeContant.DEALEREXIT.getCode(), dealerExit.getCreateUserId());
								}
							}
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submit(int id) throws Exception{
		DealerExitVO bean =  get(id);
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.DEALEREXIT.getCode());*/
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		DealerVO dealer = dao.get(DealerVO.class, bean.getDealerId(), new BeanPropertyRowMapper(DealerVO.class));
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "经销商撤店信息流转"+dealer.getDealerName(), "/market/dealerExit.do?method=findList", 1, 
						MessageTypeContant.DEALEREXIT.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		final int objId = bean.getId();
		final String name = dealer.getDealerName();
		new Thread(new Runnable() {
			@Override
			public void run() {
				//发送通知公告
				try {
					String ids = userDao.findAllIdByClientType(
							ClientTypeConstants.getIds(ClientTypeConstants.SUPERVISORY.getCode()));
					if (ids != null) {
						String[] id = ids.split(",");
						List<NoticeVO> list = new ArrayList<NoticeVO>();
						for (String string : id) {
							NoticeVO notice = new NoticeVO();
							notice.setId(Util.getID(NoticeVO.class));
							notice.setObjectId(objId);
							notice.setType(NoticeTypeContant.URL.getCode());
							notice.setUrl("/market/dealerExit.do?method=detailPage&de.id=" + objId);
							notice.setUserId(Integer.parseInt(string));
							notice.setTitle("经销商撤店：" + name);
							list.add(notice);
						}
						noticeDao.addBatch(list);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	
		return dao.update(bean);
	}
	
	/**
	 * 根据储备库ID获取监管员信息
	 * @param id
	 * @return
	 */
	public SupervisorQueryBean getSupervisor(int id) throws Exception{
		return dao.getSupervisor(id);
	}

	/**
	 * 经销商撤店台账
	 */
	public List<DealerExitQueryBean> DealerWithdrawalLedger(
			DealerExitQueryVO query, IThumbPageTools tools) {
		return dao.DealerWithdrawalLedger(query,tools);
	}
}

package com.zd.csms.marketing.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zd.core.ServiceSupport;
import com.zd.core.util.CommonUtil;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.BusinessTransferContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.contants.SupervisorAttributeContant;
import com.zd.csms.marketing.dao.IUnBindDealerDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.model.UnBindDealerQueryVO;
import com.zd.csms.marketing.model.UnBindDealerVO;
import com.zd.csms.marketing.querybean.UnBindDealerQueryBean;
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
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.dao.IRepositoryDAO;
import com.zd.csms.repository.dao.RepositoryDAOFactory;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商/监管员拆绑提醒（流转）
 * @author licheng
 *
 */

public class UnBindDealerService extends ServiceSupport{
	private IUnBindDealerDAO dao = MarketFactory.getUnBindDealerDAO();
	//private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private DealerSupervisorService dsService = new DealerSupervisorService();
	private DealerService dealerService = new DealerService();
	private IUserDAO userDao = RbacDAOFactory.getUserDAO();
	private INoticeDAO noticeDao = MessageDAOFactory.getNoticeDao();
	private IRepositoryDAO repDao = RepositoryDAOFactory.getRepositoryDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	
	private MarketMessageTypeService  typeService = new MarketMessageTypeService();
	
	public UnBindDealerVO get(int id,HttpServletRequest request){
		UnBindDealerVO bean = dao.get(UnBindDealerVO.class, id, new BeanPropertyRowMapper(UnBindDealerVO.class));
		JSONArray jsonArray = JSONArray.parseArray(bean.getChangeMoney());
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			jsonObj.put("money", dealerService.getMoneyByClientId(jsonObj.getString("money")
					, UserSessionUtil.getUserSession(request).getUser().getClient_type()));
		}
		bean.setChangeMoney(jsonArray.toJSONString());
		return bean;
	}
	
	public boolean add(UnBindDealerVO bean) throws Exception{
		bean.setId(Util.getID(UnBindDealerVO.class));	
		return dao.add(bean);
	}
	
	public boolean update(UnBindDealerVO bean,int currRole,HttpServletRequest request) throws Exception{
		UnBindDealerVO oldBean = dao.get(UnBindDealerVO.class, bean.getId(), new BeanPropertyRowMapper(UnBindDealerVO.class));
		if(currRole == RoleConstants.SR.getCode())
			currRole = oldBean.getNextApproval();
		
		if(RoleUtil.roleValidate(RoleConstants.MARKET_COMMISSIONER.getCode(), currRole)){
			oldBean.setDealerId(bean.getDealerId());
			oldBean.setChangeMoney(bean.getChangeMoney());
			oldBean.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		}else if(oldBean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()
				&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(), currRole)){
			//选择监管员----------------------------------------------
			oldBean.setRepositoryId(bean.getRepositoryId());
			oldBean.setSupervisorSource(bean.getSupervisorSource());
			oldBean.setSupervisorAttribute(bean.getSupervisorAttribute());
			oldBean.setQq(bean.getQq());
			oldBean.setPwd(bean.getPwd());
			oldBean.setLaterReason(bean.getLaterReason());
			oldBean.setArriveDate(bean.getArriveDate());
			oldBean.setGqpx(bean.getGqpx());
		}else if(oldBean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
				&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole)){
			//填写业务部数据--------------------------
			oldBean.setActualinDate(bean.getActualinDate());
			oldBean.setBusinessDelegate(bean.getBusinessDelegate());
			oldBean.setTrainDate(bean.getTrainDate());
			oldBean.setAuthorizationBook(bean.getAuthorizationBook());
			oldBean.setAppointmentBook(bean.getAppointmentBook());
			oldBean.setTraineffect(bean.getTraineffect());
		}
		oldBean.setLastModifyDate(bean.getLastModifyDate());
		oldBean.setLastModifyUserId(bean.getLastModifyUserId());
		return dao.update(oldBean);
	}
	
	public boolean delete(UnBindDealerVO bean){
		return dao.delete(UnBindDealerVO.class, bean.getId());
	}
	
	public UnBindDealerVO getUnBindDealer(int id){
		UnBindDealerVO bd = dao.get(UnBindDealerVO.class, id, new BeanPropertyRowMapper(UnBindDealerVO.class));
		return bd;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<UnBindDealerQueryBean> findList(UnBindDealerQueryVO query,IThumbPageTools tools){
		return dao.findList(query, tools);
	}
	
	/**
	 * 该表单流程是，市场部专员->市场部经理->市场部部长
	 * ->监管员管理部招聘专员->监管员管理部经理->业务部专员->业务部经理->运营管理部部长->物流金融中心总监
	 * 审批流程
	 * @param user
	 * @param query
	 * @param bean
	 */
	public void approval(UserSession user,UnBindDealerQueryVO query,UnBindDealerVO bean) throws Exception{
		int currRole = query.getCurrRole();
		UnBindDealerVO dealer = dao.get(UnBindDealerVO.class, bean.getId(), new BeanPropertyRowMapper(UnBindDealerVO.class));
		if(currRole == RoleConstants.SR.getCode())
			currRole = dealer.getNextApproval();
		//如果审批不通过
		if(query.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
			dealer.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
			if(dealer.getNextApproval()==RoleConstants.MARKET_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKET_AMALDAR.getCode(), currRole)){
				//市场部经理
				dealer.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
			}else if(dealer.getNextApproval()==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(), currRole)){
				//市场部部长
				dealer.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode());
			}else if(dealer.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(), currRole)){
				//监管员管理部招聘专员补充表单
				dealer.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
			}else if(dealer.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole)){
				dealer.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
				
				DealerSupervisorQueryVO dQuery = new DealerSupervisorQueryVO();
				dQuery.setDealerId(dealer.getDealerId());
				
				
				//修改监管费用
				String changeMoney = dealer.getChangeMoney();
				JSONArray dealers = JSON.parseArray(changeMoney);
				List<Integer> dealerIds = new ArrayList<Integer>();
				if(dealers!=null&&dealers.size()>0){
					for (int i = 0;i<dealers.size();i++) {
						JSONObject obj = dealers.getJSONObject(i);
						int id = obj.getIntValue("id");
						if(id>0){
							if(id!=dealer.getDealerId())
								dealerIds.add(id);
							DealerVO vo = dealerService.get(id);
							vo.setChangeBeforeInfo(vo.getSuperviseMoney());
							vo.setSuperviseMoney(obj.getString("money"));
							vo.setPayMode(obj.getIntValue("mode"));
							vo.setChangeSuperviseMoneyDate(DateUtil.getDateFormatByString(obj.getString("changeDate"), "yyyy-MM-dd"));
							vo.setIsChangeSuperviseMoney(DealerContant.ISCHANGESUPERVISEMONEY_YES.getCode());
							dealerService.update(vo);
						}
					}
				}
				
				//更新经销商绑定的监管员
				try {
					if(dealer.getDealerId()>0){
						dsService.updateRepoByDealerId(dealer.getDealerId(), dealer.getRepositoryId());
						DealerVO dea = dealerService.get(dealer.getDealerId());
						dea.setDdorbd(2);
						dea.setBindInfo("");
						dea.setBindCount(1);
						dealerService.update(dea);
					}				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//更新经销商的绑定信息
				try {
					dealerIds.remove(dealer.getDealerId()+"");
					for (Integer id : dealerIds) {
						DealerVO dvo = dealerService.get(id);
						if(dvo!=null){
							String bindInfo =  dvo.getBindInfo();
							List<String> bindInfos = new ArrayList<String>(Arrays.asList(bindInfo.split(",")));//绑定信息为多个经销商id用","隔开
							for (int i = 0; i < bindInfos.size(); i++) {
								if(bindInfos.get(i).equals(dealer.getDealerId()+"")){
									bindInfos.remove(i);
									break;
								}
							}
							dvo.setBindInfo(CommonUtil.listToString(bindInfos));//将移除后的绑定信息重新改为字符串
							if(dvo.getBindCount()>1){//改变绑定状态  和 绑定数量
								dvo.setBindCount(dvo.getBindCount()-1);
								if(dvo.getBindCount()>1)
									dvo.setDdorbd(1);
								else
									dvo.setDdorbd(2);
							}else{
								dvo.setDdorbd(2);
							}
							dealerService.update(dvo);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				

				
				//新增花名册
				try {
					RosterService rosterService=new RosterService();
					List<RosterVO> rosters=rosterService.searchRosterByRepositoryId(dealer.getRepositoryId());
					if(CollectionUtils.isEmpty(rosters)){
						RosterVO roster = new RosterVO();
						roster.setId(Util.getID(RosterVO.class));
						String staffNo=new DecimalFormat("000000").format(roster.getId());;
						roster.setStaffNo(staffNo);
						roster.setRepositoryId(dealer.getRepositoryId());
						RepositoryVO repvo = repDao.get(RepositoryVO.class,dealer.getRepositoryId(),new BeanPropertyRowMapper(RepositoryVO.class));
						if(repvo!=null)
							roster.setSupervisorId(repvo.getSupervisorId());
						roster.setCreateTime(new Date());
						roster.setCreateUser(user.getUser().getId());
						boolean flag=rosterService.add(roster);
						if(flag){
							RepositoryService repositoryService=new RepositoryService();
							repositoryService.updateRepositoryStatus(dealer.getRepositoryId(),RepositoryStatus.ALREADYPOST.getCode(),0);
							SupervisoryService supervisoryService=new SupervisoryService();
							if(repvo!=null){
								supervisoryService.updateSupervisorEntryDate(repvo.getSupervisorId(),new Date());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try{
					TransferService transferService=new TransferService();
					//经销商绑定或拆绑监管员时， 新增经销商监管员记录
					TransferRepositoryVO transferRepository=new TransferRepositoryVO();
					transferRepository.setDealerId(dealer.getDealerId());
					transferRepository.setRepositoryId(dealer.getRepositoryId());
					transferRepository.setEntryTime(new Date());
					String entryNature=SupervisorAttributeContant.getName(dealer.getSupervisorAttribute());
					transferRepository.setEntryNature(entryNature==null?" ":entryNature);
					transferRepository.setSupervisorSource(bean.getSupervisorSource());
					transferService.addDealerSupervisor(transferRepository);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
		}
		dao.update(dealer);
		
		//记录流程
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalPerson(user.getUser().getUserName());
		approval.setApprovalObjectId(dealer.getId());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalType(ApprovalTypeContant.UNBINDDEALER.getCode());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		approval.setRemark(query.getRemark());
		approval.setCreateDate(new Date());
		dao.add(approval);
		
		try {
			if(dealer.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{dealer.getNextApproval()+""}, "经销商/金融机构拆分合作信息流程审阅", "/market/unbindDealer.do?method=findList", 1, 
						MessageTypeContant.UNBINDDEALER.getCode(), dealer.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(dealer.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(dealer.getCreateUserId(), "经销商/金融机构拆分审批未通过，请查看", "/market/unbindDealer.do?method=findList&query.pageType=2",1, MessageTypeContant.UNBINDDEALER.getCode(),dealer.getCreateUserId());
			}else if(dealer.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(dealer.getCreateUserId(), "经销商/金融机构拆分审批已通过，请查看", "/market/unbindDealer.do?method=findList",1, MessageTypeContant.UNBINDDEALER.getCode(),dealer.getCreateUserId());
				//项目解绑流转单(新改造)
				typeService.projectUnBinding(dealer.getId());
				
				/*try {
					//给所有人发送消息提醒 除监管员，银行，品牌集团，经销商集团
					for(BusinessTransferContant approvalRole:BusinessTransferContant.values()){
						String userid = userRoleDao.findUsingUserIdByRole(approvalRole.getCode()+"");
						if(StringUtil.isNotEmpty(userid)){
							String[] userids = userid.split(",");
							for (String uid : userids) {
								MessageUtil.addMsg(Integer.parseInt(uid), "经销商/金融机构拆分审批已通过，请查看", "/market/unbindDealer.do?method=findList",1, MessageTypeContant.UNBINDDEALER.getCode(),dealer.getCreateUserId());
							}
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}*/
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
									MessageUtil.addMsg(Integer.parseInt(uid), 1+"", "/market/unbindDealer.do?method=findList",1,
											MessageTypeContant.UNBINDDEALER.getCode(),dealer.getCreateUserId());
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
		UnBindDealerVO bean =  dao.get(UnBindDealerVO.class, id, new BeanPropertyRowMapper(UnBindDealerVO.class));
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.UNBINDDEALER.getCode());*/
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "经销商/金融机构拆分合作信息流程审阅", "/market/unbindDealer.do?method=findList", 1, 
						MessageTypeContant.UNBINDDEALER.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//发送通知公告,除了监管员其他部门都发
		try {
			final DealerVO vo = dealerService.get(bean.getDealerId());
			final int objId = bean.getId();
			new Thread(new Runnable() {	
				@Override
				public void run() {
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
								notice.setUrl("/market/unbindDealer.do?method=detailPage&unbd.id=" + objId);
								notice.setUserId(Integer.parseInt(string));
								notice.setTitle("经销商拆绑：" + vo.getDealerName());
								list.add(notice);
							}
							noticeDao.addBatch(list);
						} 
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
			}).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.update(bean);
	}
	
	/**
	 * 移除list集合中的指定id 并返回字符串
	 * @param list
	 * @param id
	 * @return
	 */
	private String listToIds(List<DealerSupervisorVO> list,int id,int id2){
		StringBuffer ids = new StringBuffer();
		for (DealerSupervisorVO dvo : list) {
			if(dvo.getDealerId()!=id&&dvo.getDealerId()!=id2){
				ids.append(dvo.getDealerId()+",");
			}
		}
		if(ids.length()>0){
			ids.deleteCharAt(ids.length()-1);
		}
		return ids.toString();
	}

	/*
	 * 经销商/金融机构拆绑台账
	 * @time 20170621
	*/
	public List<UnBindDealerQueryBean> dealerFinancialInstitutionLedger(
			UnBindDealerQueryVO query, IThumbPageTools tools) {
		return dao.dealerFinancialInstitutionLedger(query,tools);
	}
	
}

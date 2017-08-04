package com.zd.csms.marketing.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.BusinessTransferContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.contants.SupervisorAttributeContant;
import com.zd.csms.marketing.dao.IBindDealerDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.BindDealerQueryVO;
import com.zd.csms.marketing.model.BindDealerVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.BindDealerQueryBean;
import com.zd.csms.marketing.querybean.DealerBankQueryBean;
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
 * 经销商/金融机构绑定信息
 * @author licheng
 *
 */

public class BindDealerService extends ServiceSupport{
	private IBindDealerDAO dao = MarketFactory.getBindDealerDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	//private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private DealerService dealerService = new DealerService();
	private DealerSupervisorService dsService = new DealerSupervisorService();
	private IUserDAO userDao = RbacDAOFactory.getUserDAO();
	private INoticeDAO noticeDao = MessageDAOFactory.getNoticeDao();
	private MarketMessageTypeService  typeService = new MarketMessageTypeService();
	private RosterService rosterService=new RosterService();
	public BindDealerVO get(int id,HttpServletRequest request) throws Exception{
		BindDealerVO bean = dao.get(BindDealerVO.class, id, new BeanPropertyRowMapper(BindDealerVO.class));
		JSONArray jsonArray = JSONArray.parseArray(bean.getChangeMoney());
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			jsonObj.put("money", dealerService.getMoneyByClientId(jsonObj.getString("money")
					, UserSessionUtil.getUserSession(request).getUser().getClient_type()));
		}
		bean.setChangeMoney(jsonArray.toJSONString());
		return bean;
	}
	
	public boolean add(BindDealerVO bean) throws Exception{
		bean.setId(Util.getID(BindDealerVO.class));
		return dao.add(bean);
	}
	
	public boolean update(BindDealerVO bean,int currRole,HttpServletRequest request) throws Exception{
		BindDealerVO dealer =dao.get(BindDealerVO.class, bean.getId(), new BeanPropertyRowMapper(BindDealerVO.class));
		if(currRole == RoleConstants.SR.getCode()){
			currRole = dealer.getNextApproval();
		}
		
		if(RoleUtil.roleValidate(RoleConstants.MARKET_COMMISSIONER.getCode(), currRole)){
			dealer.setDealerId(bean.getDealerId());
			dealer.setDealerName(bean.getDealerName());
			dealer.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			dealer.setChangeMoney(bean.getChangeMoney());
			String[] ids = dealer.getDealerId().split(",");
			if(ids!=null&&ids.length>0){
				Integer[] dealerIds = new Integer[ids.length];
				for (int i = 0; i < ids.length; i++) {
					dealerIds[i]=new Integer(ids[i]);
				}
				BankVO bank = dealerService.getBankByDealerId(dealerIds);
				dealer.setBankName(bank.getBankName());
			}
		}else if(dealer.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()
				&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(), currRole)){
			//选择监管员----------------------------------------------
			dealer.setRepositoryId(bean.getRepositoryId());
			dealer.setSupervisorSource(bean.getSupervisorSource());
			dealer.setSupervisorAttribute(bean.getSupervisorAttribute());
			dealer.setQq(bean.getQq());
			dealer.setPwd(bean.getPwd());
			dealer.setLaterReason(bean.getLaterReason());
			dealer.setArriveDate(bean.getArriveDate());
			dealer.setGqpx(bean.getGqpx());
		}else if(dealer.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
				&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole)){
			//填写业务部数据--------------------------
			dealer.setActualinDate(bean.getActualinDate());
			dealer.setBusinessDelegate(bean.getBusinessDelegate());
			dealer.setTrainDate(bean.getTrainDate());
			dealer.setAuthorizationBook(bean.getAuthorizationBook());
			dealer.setAppointmentBook(bean.getAppointmentBook());
			dealer.setTraineffect(bean.getTraineffect());
		}
		dealer.setLastModifyUserId(bean.getLastModifyUserId());
		dealer.setLastModifyDate(bean.getLastModifyDate());
		return dao.update(dealer);
	}
	
	public boolean delete(BindDealerVO bean){
		return dao.delete(BindDealerVO.class, bean.getId());
	}
	
	public BindDealerVO getBindDealer(int id){
		BindDealerVO bd = dao.get(BindDealerVO.class, id, new BeanPropertyRowMapper(BindDealerVO.class));
		return bd;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception 
	 */
	public List<BindDealerQueryBean> findList(BindDealerQueryVO query,IThumbPageTools tools) throws Exception{
		List<BindDealerQueryBean> list = dao.findList(query, tools);
		if(list!=null&&list.size()>0)
			for (BindDealerQueryBean bean : list) {
				String names =  bean.getDealerName();
				if(StringUtil.isNotEmpty(names)){
					String[]name = names.split(",");
					String[]bankName = null;
					if(StringUtil.isNotEmpty(bean.getBankName())){
						bankName = bean.getBankName().split(",");
					}
					List<DealerBankQueryBean> dealers = new ArrayList<DealerBankQueryBean>();
					for (int i = 0; i < name.length; i++) {
						DealerBankQueryBean db = new DealerBankQueryBean();
						db.setDealerName(name[i]);
						if(bankName!=null&&bankName.length>i){
							db.setBankName(bankName[i]);
						}
						dealers.add(db);
					}
					bean.setDealers(dealers);
				}
			}
		return list;
	}
	
	/**
	 * 该表单流程是，市场部专员->市场部经理->市场部部长
	 * ->监管员管理部招聘专员->监管员管理部经理->业务部专员->业务部经理->运营管理部部长->物流金融中心总监
	 * 审批流程
	 * @param user
	 * @param query
	 * @param bean
	 */
	public void approval(UserSession user,BindDealerQueryVO query,BindDealerVO bean) throws Exception{
		int currRoleId = query.getCurrRole();
		BindDealerVO dealer = dao.get(BindDealerVO.class, bean.getId(), new BeanPropertyRowMapper(BindDealerVO.class));
		if(currRoleId == RoleConstants.SR.getCode()){
			currRoleId = dealer.getNextApproval();
		}
		
		//如果审批不通过
		if(query.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
			dealer.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
			if(dealer.getNextApproval()==RoleConstants.MARKET_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKET_AMALDAR.getCode(), currRoleId)){
				//市场部经理
				dealer.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
			}else if(dealer.getNextApproval()==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(), currRoleId)){
				//市场部部长
				dealer.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode());
			}else if(dealer.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(), currRoleId)){
				//监管员管理部招聘专员补充表单
				dealer.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
			}else if(dealer.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRoleId)){
				//业务专员 
				dealer.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
				
				//修改监管费用
				String changeMoney = dealer.getChangeMoney();
				JSONArray dealers = JSON.parseArray(changeMoney);//绑定的店的id集合
				List<Integer> dealerIds = new ArrayList<Integer>();
				
				
				if(dealers!=null&&dealers.size()>0){
					for (int i = 0;i<dealers.size();i++) {
						JSONObject obj = dealers.getJSONObject(i);
						int id = obj.getIntValue("id");
						if(id>0){
							dealerIds.add(id);
						}
					}
					
					for (int i = 0;i<dealers.size();i++) {
						JSONObject obj = dealers.getJSONObject(i);
						int id = obj.getIntValue("id");
						if(id>0){
							DealerVO vo = dealerService.get(id);
							vo.setChangeBeforeInfo(vo.getSuperviseMoney());
							vo.setSuperviseMoney(obj.getString("money"));
							vo.setPayMode(obj.getIntValue("mode"));
							vo.setChangeSuperviseMoneyDate(DateUtil.getDateFormatByString(obj.getString("changeDate"), "yyyy-MM-dd"));
							vo.setIsChangeSuperviseMoney(DealerContant.ISCHANGESUPERVISEMONEY_YES.getCode());
							
							vo.setDdorbd(1);//店状态改为绑定
							vo.setBindCount(dealerIds.size());
							List<Integer> temp = new ArrayList<Integer>(dealerIds);
							for (Integer dId : temp) {
								if(dId.intValue() == vo.getId()){
									temp.remove(dId);
									break;
								}
							}
							vo.setBindInfo(CommonUtil.listToString(temp));
							dealerService.update(vo);
							//绑定关系
							dsService.updateRepoByDealerId(vo.getId(), dealer.getRepositoryId());
						}
					}
				}
				
				
				
				//新增花名册
				try {
					RosterService rosterService=new RosterService();
					RepositoryService repositoryService=new RepositoryService();
					SupervisoryService supervisoryService=new SupervisoryService();
					List<RosterVO> rosters=rosterService.searchRosterByRepositoryId(dealer.getRepositoryId());
					if(CollectionUtils.isEmpty(rosters)){
						RosterVO roster = new RosterVO();
						roster.setId(Util.getID(RosterVO.class));
						String staffNo=new DecimalFormat("000000").format(roster.getId());;
						roster.setStaffNo(staffNo);
						roster.setRepositoryId(dealer.getRepositoryId());
						RepositoryVO repository=repositoryService.get(dealer.getRepositoryId());
						if(repository!=null){
							roster.setSupervisorId(repository.getSupervisorId());
						}
						roster.setCreateTime(new Date());
						roster.setCreateUser(user.getUser().getId());
						boolean flag=rosterService.add(roster);
						if(flag){
							repositoryService.updateRepositoryStatus(dealer.getRepositoryId(),RepositoryStatus.ALREADYPOST.getCode(),0);
							if(repository!=null){
								supervisoryService.updateSupervisorEntryDate(repository.getSupervisorId(),new Date());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try{
					String[] ids = dealer.getDealerId().split(",");
					for (int i = 0; i < ids.length; i++) {
						TransferService transferService=new TransferService();
						//经销商绑定或拆绑监管员时， 新增经销商监管员记录
						TransferRepositoryVO transferRepository=new TransferRepositoryVO();
						transferRepository.setDealerId(Integer.parseInt(ids[i]));
						transferRepository.setRepositoryId(dealer.getRepositoryId());
						transferRepository.setEntryTime(new Date());
						String entryNature=SupervisorAttributeContant.getName(dealer.getSupervisorAttribute());
						transferRepository.setEntryNature(entryNature==null?" ":entryNature);
						transferRepository.setSupervisorSource(bean.getSupervisorSource());
						transferService.addDealerSupervisor(transferRepository);
					}
					
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
		approval.setApprovalType(ApprovalTypeContant.BINDDEALER.getCode());
		approval.setRemark(query.getRemark());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRoleId);
		approval.setCreateDate(new Date());
		dao.add(approval);
		
		try {
			if(dealer.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{dealer.getNextApproval()+""}, "经销商/金融机构绑定信息审批", "/market/bindDealer.do?method=findList", 1, 
						MessageTypeContant.BINDDEALER.getCode(), dealer.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "经销商/金融机构绑定信息流转单审批未通过，请查看", "/market/bindDealer.do?method=findList&query.pageType=2",1, 
						MessageTypeContant.BINDDEALER.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "经销商/金融机构绑定信息流转单审批已通过，请查看", "/market/bindDealer.do?method=findList",1, 
						MessageTypeContant.BINDDEALER.getCode(),bean.getCreateUserId());
				//项目绑定流转单(新改造)
				typeService.projectBinding(dealer.getId());
				
				/*try {
					//给所有人发送消息提醒 除监管员，银行，品牌集团，经销商集团
					for(BusinessTransferContant approvalRole:BusinessTransferContant.values()){
						String userid = userRoleDao.findUsingUserIdByRole(approvalRole.getCode()+"");
						if(StringUtil.isNotEmpty(userid)){
							String[] userids = userid.split(",");
							for (String uid : userids) {
								MessageUtil.addMsg(Integer.parseInt(uid),  "经销商/金融机构绑定信息流转单审批已通过，请查看", "/market/bindDealer.do?method=findList",1, 
										MessageTypeContant.BINDDEALER.getCode(),bean.getCreateUserId());
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
									MessageUtil.addMsg(Integer.parseInt(uid),  1+"", "/market/bindDealer.do?method=findList",1, 
											MessageTypeContant.BINDDEALER.getCode(),bean.getCreateUserId());
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
		BindDealerVO bean =  dao.get(BindDealerVO.class, id, new BeanPropertyRowMapper(BindDealerVO.class));
	/*	if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.BINDDEALER.getCode());*/
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		dao.update(bean);
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "经销商/金融机构绑定信息审批", "/market/bindDealer.do?method=findList", 1, 
						MessageTypeContant.BINDDEALER.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			final int objId = bean.getId();
			final String name = bean.getDealerName();
			new Thread(new Runnable() {
				@Override
				public void run() {
					//发送通知公告,除了监管员其他部门都发
					try {
						String ids = userDao.findAllIdByClientType(ClientTypeConstants.getIds(ClientTypeConstants.SUPERVISORY.getCode()));
						if(ids!=null){
							String[] id = ids.split(",");
							
							List<NoticeVO> list = new ArrayList<NoticeVO>();
							for (String string : id) {
								NoticeVO notice = new NoticeVO();
								notice.setId(Util.getID(NoticeVO.class));
								notice.setObjectId(objId);
								notice.setType(NoticeTypeContant.URL.getCode());
								notice.setUrl("/market/bindDealer.do?method=detailPage&bd.id="+objId);
								notice.setUserId(Integer.parseInt(string));
								notice.setTitle("经销商绑定："+name);
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
			// TODO: handle exception
		}
		
		return true;
	}
	
	private String arrayToString(String[] ids , String id){
		StringBuffer sb = new StringBuffer();
		for (String string : ids) {
			if(!string.equals(id)){
				sb.append(string+",");
			}
		}
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	/*
	 * 经销商/金融机构绑定台账
	 * @time 20170621
	*/
	public List<BindDealerQueryBean> dealerFinancialInstitutionBindingLedger(
			BindDealerQueryVO query, IThumbPageTools tools) {
		List<BindDealerQueryBean> list = dao.dealerFinancialInstitutionBindingLedger(query, tools);
		if(list!=null&&list.size()>0)
			for (BindDealerQueryBean bean : list) {
				String names =  bean.getDealerName();
				if(StringUtil.isNotEmpty(names)){
					String[]name = names.split(",");
					String[]bankName = null;
					if(StringUtil.isNotEmpty(bean.getBankName())){
						bankName = bean.getBankName().split(",");
					}
					List<DealerBankQueryBean> dealers = new ArrayList<DealerBankQueryBean>();
					for (int i = 0; i < name.length; i++) {
						DealerBankQueryBean db = new DealerBankQueryBean();
						db.setDealerName(name[i]);
						if(bankName!=null&&bankName.length>i){
							db.setBankName(bankName[i]);
						}
						dealers.add(db);
					}
					bean.setDealers(dealers);
				}
			}
		return list;
	}
	
}

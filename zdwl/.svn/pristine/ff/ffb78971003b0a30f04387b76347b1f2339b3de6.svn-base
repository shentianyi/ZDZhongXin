package com.zd.csms.marketing.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.CollectionUtils;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.BusinessTransferContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.contants.SupervisorAttributeContant;
import com.zd.csms.marketing.dao.IBusinessTransferDAO;
import com.zd.csms.marketing.dao.IDealerDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.BusinessTransferQueryVO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.BusinessTransferQueryBean;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.contant.NoticeTypeContant;
import com.zd.csms.message.dao.INoticeDAO;
import com.zd.csms.message.dao.MessageDAOFactory;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messageAndWaring.message.service.market.MarketMessageTypeService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserDAO;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.LeaveApplyApprovalRoleContants;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.model.TransferVO;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.csms.supervisorymanagement.warning.SupervisoryManagementWarning;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 业务进店流转
 * @author licheng
 *
 */

public class BusinessTransferService {
	IBusinessTransferDAO dao = MarketFactory.getIBusinessTransferDAO();
	//private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private INoticeDAO noticeDao = MessageDAOFactory.getNoticeDao();
	private IUserDAO userDao = RbacDAOFactory.getUserDAO();
	//private DealerService dealerService = new DealerService();
	private IDealerDAO dealerDao = MarketFactory.getIDealerDAO();
	private DealerSupervisorService dsService = new DealerSupervisorService();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	
	private MarketMessageTypeService typeService = new MarketMessageTypeService();
	
	public BusinessTransferVO get(int id) throws Exception{
		return dao.get(BusinessTransferVO.class, id, new BeanPropertyRowMapper(BusinessTransferVO.class));
	}
	
	/**
	 * 新增业务流转单
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public boolean add(BusinessTransferVO bean) throws Exception{
		bean.setId(Util.getID(BusinessTransferVO.class));
		return dao.add(bean);
	}
	
	public boolean update(BusinessTransferVO bean,UserSession user,int currRole) throws Exception{
		BusinessTransferVO oldBean = dao.get(BusinessTransferVO.class, bean.getId(), new BeanPropertyRowMapper(BusinessTransferVO.class));
		if(currRole == RoleConstants.SR.getCode()){
			currRole = oldBean.getNextApproval();
		}
		if(currRole == RoleConstants.MARKET_COMMISSIONER.getCode()){
			//市场部专员权限
			oldBean.setDealerId(bean.getDealerId());
			oldBean.setActualInDate(bean.getActualInDate());
			oldBean.setJiaojieDate(bean.getJiaojieDate());
			oldBean.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			
		}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()){
			//招聘专员
			oldBean.setRepositoryId(bean.getRepositoryId());//储备库主键ID
			oldBean.setSupervisorSource(bean.getSupervisorSource());
			oldBean.setSupervisorAttribute(bean.getSupervisorAttribute());
			oldBean.setQq(bean.getQq());
			oldBean.setPwd(bean.getPwd());
			oldBean.setSystemUid(bean.getSystemUid());
			oldBean.setSystemPwd(bean.getSystemPwd());
			oldBean.setLaterReason(bean.getLaterReason());
			oldBean.setSupervisorRemark(bean.getSupervisorRemark());
			oldBean.setRecommendPeople(bean.getRecommendPeople());
			oldBean.setGqpx(bean.getGqpx());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			//业务专员
			oldBean.setActualInDate(bean.getActualInDate());
			oldBean.setBusinessTraining(bean.getBusinessTraining());
			oldBean.setTrainingDate(bean.getTrainingDate());
			oldBean.setWarrantProduction(bean.getWarrantProduction());
			oldBean.setMandateLetterProduction(bean.getMandateLetterProduction());
			oldBean.setTrainingEffect(bean.getTrainingEffect());	
			oldBean.setBusinessRemark(bean.getBusinessRemark());
		}
		oldBean.setLastModifyDate(new Date());
		oldBean.setLastModifyUserId(user.getUser().getId());
		
		return dao.update(oldBean);
	}
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BusinessTransferQueryBean> findList(BusinessTransferQueryVO query,IThumbPageTools tools){
		return dao.findList(query, tools);
	}
	
	public List<BusinessTransferQueryBean> findAllList(BusinessTransferQueryVO query,IThumbPageTools tools){
		return dao.findAllList(query, tools);
	}
	
	public List<BusinessTransferQueryBean> findAllList(BusinessTransferQueryVO query){
		return dao.findAllList(query);
	}
	
	public boolean delete(int id){
		return dao.delete(BusinessTransferVO.class, id);
	}
	
	/**
	 * 发起者提交
	 * @param bean
	 * @throws Exception 
	 */
	public void submit(BusinessTransferVO bean) throws Exception{
		BusinessTransferVO newBean = 
				dao.get(BusinessTransferVO.class, bean.getId(), new BeanPropertyRowMapper(BusinessTransferVO.class));
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.BUSINESSRANSFER.getCode());*/
		newBean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		newBean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode());
		DealerVO dealer = dao.get(DealerVO.class, newBean.getDealerId(), new BeanPropertyRowMapper(DealerVO.class));
		dealer.setInDate(newBean.getInStoreDate());
		dealer.setPayDate(newBean.getInStoreDate());
		dao.update(dealer);
		dao.update(newBean);
		//发送消息
		try {
			if(newBean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{newBean.getNextApproval()+""}, "业务流转单:"+dealer.getDealerName()+"请查阅填写", 
						"/market/businessTransfer.do?method=findList", 1, 
						MessageTypeContant.BUSINESSTRANSFER.getCode(), newBean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 业务流程
	 * @param user
	 * @param bean
	 */
	public void approval(UserSession user,BusinessTransferVO oldBean,int currRole) throws Exception{
		BusinessTransferVO bean = get(oldBean.getId());
		if(currRole == RoleConstants.SR.getCode()){
			currRole = bean.getNextApproval();
		}
		DealerVO dealer = dao.get(DealerVO.class, bean.getDealerId(), new BeanPropertyRowMapper(DealerVO.class));
		if(bean.getNextApproval() == RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()&&
				RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(), currRole)){
			bean.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
			//增加经销商和监管员的关联
			if(bean.getDealerId()>0&&bean.getRepositoryId()>0){
				DealerBankVO db = dealerDao.getDealerBankByDealerId(dealer.getId());
				DealerSupervisorVO ds = new DealerSupervisorVO();
				ds.setId(Util.getID(DealerSupervisorVO.class));
				ds.setDealerId(bean.getDealerId());
				ds.setRepositoryId(bean.getRepositoryId());
				ds.setQq(bean.getQq());
				ds.setQqPwd(bean.getPwd());
				ds.setSupervisorSource(bean.getSupervisorSource());
			 	ds.setBankId(db.getBankId());
			 	//关键时添加关联时间
			 	ds.setBindtime((java.util.Date) new Date());
				dao.add(ds);
			}
			/*String bindInfo = dealer.getBindInfo();
			if(bindInfo!=null){
				String[] bindInfos = bindInfo.split(",");
				for (String bi : bindInfos) {
					try {
						dsService.updateRepoByDealerId(Integer.parseInt(bi),bean.getRepositoryId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}*/
			//新增花名册
			RosterService rosterService=new RosterService();
			try {
				RepositoryService repositoryService=new RepositoryService();
				SupervisoryService supervisoryService=new SupervisoryService();
				List<RosterVO> rosters=rosterService.searchRosterByRepositoryId(bean.getRepositoryId());
				if(CollectionUtils.isEmpty(rosters)){
					RosterVO roster = new RosterVO();
					roster.setId(Util.getID(RosterVO.class));
					String staffNo=new DecimalFormat("000000").format(roster.getId());;
					roster.setStaffNo(staffNo);
					roster.setRepositoryId(bean.getRepositoryId());
					RepositoryVO repository=repositoryService.get(bean.getRepositoryId());
					if(repository!=null){
						roster.setSupervisorId(repository.getSupervisorId());
					}
					roster.setCreateTime(new Date());
					roster.setCreateUser(user.getUser().getId());
					boolean flag=rosterService.add(roster);
					if(flag){
						repositoryService.updateRepositoryStatus(bean.getRepositoryId(),RepositoryStatus.ALREADYPOST.getCode(),0);
						if(repository!=null){
							supervisoryService.updateSupervisorEntryDate(repository.getSupervisorId(),new Date());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try{
				TransferService transferService=new TransferService();
				//经销商入驻时，新增调动表
				TransferVO transfer=new TransferVO();
				transfer.setDealerId(bean.getDealerId());
				transfer.setCreateTime(new Date());
				transfer.setCreateUser(user.getUser().getId());
				transferService.add(transfer);
				
				//经销商绑定或拆绑监管员时， 新增经销商监管员记录
				TransferRepositoryVO transferRepository=new TransferRepositoryVO();
				transferRepository.setDealerId(bean.getDealerId());
				transferRepository.setRepositoryId(bean.getRepositoryId());
				transferRepository.setEntryTime(new Date());
				String entryNature=SupervisorAttributeContant.getName(bean.getSupervisorAttribute());
				transferRepository.setEntryNature(entryNature==null?" ":entryNature);
				transferRepository.setSupervisorSource(bean.getSupervisorSource());
				transferService.addDealerSupervisor(transferRepository);
			}catch(Exception e){
				e.printStackTrace();
			}
			//TODO 发布通知
			
		}else if(bean.getNextApproval() == RoleConstants.BUSINESS_COMMISSIONER.getCode()&&
				RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole)){
			bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
			//TODO 发布通知
			
			dealer.setCooperationState(DealerContant.COOPERATIONSTATE_IN.getCode());
			dao.update(dealer);
			
			//发送通知公告
			try {
				
				String ids = userDao.findAllIdByClientType(ClientTypeConstants.getIds(ClientTypeConstants.SUPERVISORY.getCode()));
				if(ids!=null){
					String[] id = ids.split(",");
					List<NoticeVO> list = new ArrayList<NoticeVO>();
					for (String string : id) {
						NoticeVO notice = new NoticeVO();
						notice.setId(Util.getID(NoticeVO.class));
						notice.setObjectId(bean.getId());
						notice.setType(NoticeTypeContant.URL.getCode());
						notice.setUrl("/market/businessTransfer.do?method=detailPage&&business.id="+bean.getId());
						notice.setUserId(Integer.parseInt(string));
						notice.setTitle("业务进店流转："+dealer.getDealerName()+",进驻时间"+DateUtil.getStringFormatByDate(bean.getInStoreDate(), "yyyy年MM月dd日"));
						list.add(notice);
					}
					noticeDao.addBatch(list);
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dao.update(bean); 
		//记录流程
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalPerson(user.getUser().getUserName());
		approval.setApprovalObjectId(bean.getId());
		approval.setApprovalResult(ApprovalContant.APPROVAL_PASS.getCode());
		approval.setApprovalType(ApprovalTypeContant.BUSINESSRANSFER.getCode());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		approval.setCreateDate(new Date());
		dao.add(approval);
		
		//发送消息
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "业务流转单:"+dealer.getDealerName()+"请填写", 
						"/market/businessTransfer.do?method=findList", 1, 
						MessageTypeContant.BUSINESSTRANSFER.getCode(), bean.getCreateUserId());
			}
			if (bean.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()) {
				
				try {
					//给所有人发送消息提醒 除监管员，银行，品牌集团，经销商集团
					/*for(BusinessTransferContant approvalRole:BusinessTransferContant.values()){
						String userid = userRoleDao.findUsingUserIdByRole(approvalRole.getCode()+"");
						if(StringUtil.isNotEmpty(userid)){
							String[] userids = userid.split(",");
							for (String uid : userids) {
								MessageUtil.addMsg(Integer.parseInt(uid), "业务流转单:"+dealer.getDealerName()+"审批已通过，请查看",
										 "/market/businessTransfer.do?method=findList&query.pageType=2",1, MessageTypeContant.BUSINESSTRANSFER.getCode(),bean.getCreateUserId());
							}
							
						}
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
										MessageUtil.addMsg(Integer.parseInt(uid), 1+"",
												 "/market/businessTransfer.do?method=findList&query.pageType=2",1, MessageTypeContant.BUSINESSTRANSFER.getCode(),bean.getCreateUserId());
									}
								}
								
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "业务流转单:"+dealer.getDealerName()+"审批未通过，请查看", "/market/businessTransfer.do?method=findList&query.pageType=2",1, MessageTypeContant.BUSINESSTRANSFER.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "业务流转单:"+dealer.getDealerName()+"审批已通过，请查看", "/market/businessTransfer.do?method=findList",1, MessageTypeContant.BUSINESSTRANSFER.getCode(),bean.getCreateUserId());
				//业务进驻流转单（新改造的）
				typeService.businessIn(bean.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 根据储备库Id获取有效的监管员信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SupervisorJsonBean getSupervisorByRepositoryId(int id) throws Exception{
		return dao.getSupervisorByRepositoryId(id);
	}
}

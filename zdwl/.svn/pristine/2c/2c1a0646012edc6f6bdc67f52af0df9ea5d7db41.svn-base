package com.zd.csms.supervisorymanagement.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.CollectionUtils;

import com.zd.core.ServiceSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.BusinessTransferContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryReason;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.AssetsTypeContant;
import com.zd.csms.supervisorymanagement.contants.HandoverLogApprovalRoleContants;
import com.zd.csms.supervisorymanagement.contants.HandoverNatureContants;
import com.zd.csms.supervisorymanagement.contants.ReceiveNatureContants;
import com.zd.csms.supervisorymanagement.dao.IHandoverLogDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.model.ElectronicTextVO;
import com.zd.csms.supervisorymanagement.model.ExtHandoverLogVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.model.HandoverBookVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogPicVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.supervisorymanagement.model.OfficeEquipmentVO;
import com.zd.csms.supervisorymanagement.model.OtherDataVO;
import com.zd.csms.supervisorymanagement.model.PaperTextVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.warning.SupervisoryManagementWarning;
import com.zd.csms.util.Util;
import com.zd.csms.windcontrol.dao.IInspectionDAO;
import com.zd.csms.windcontrol.dao.WindControlDAOFactory;
import com.zd.csms.windcontrol.model.Image;
import com.zd.tools.StringUtil;
import com.zd.tools.file.FileUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class HandoverLogService extends ServiceSupport {

	private IHandoverLogDAO dao = SupervisoryManagementDAOFactory.getHandoverLogDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private UserService userService=new UserService();
	private SupervisoryManagementWarning warning=new SupervisoryManagementWarning();
	private DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
	RosterService rosterService=new RosterService();
	public boolean add(HandoverLogVO vo) throws Exception{
		vo.setId(Util.getID(HandoverLogVO.class));
		boolean flag=dao.add(vo);
		if(flag){
			HandoverLogPicVO handoverLogPic=new HandoverLogPicVO();
			handoverLogPic.setId(Util.getID(HandoverLogPicVO.class));
			handoverLogPic.setApproveStatus(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			handoverLogPic.setHandoverLogId(vo.getId());
			flag=dao.add(handoverLogPic);
		}
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public boolean update(HandoverLogVO vo) throws Exception{
		boolean flag=dao.update(vo);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}
	
	
	public boolean delete(int id) throws Exception{
		HandoverLogVO log=load(id);
		if(log==null){
			this.setExecuteMessage("该数据不存在！");
			return false;
		}
		boolean flag=true;
		HandoverLogPicVO pic=searchPicsByHandoverLogId(log.getId());
		if(pic!=null){
			flag =dao.delete(HandoverLogPicVO.class, pic.getId());
		}
		ElectronicTextVO electronicText=dao.getETextBySupervisorId(log.getDeliverer());
		if(flag && electronicText!=null){
			flag=dao.delete(ElectronicTextVO.class, electronicText.getId());
		}
		OtherDataVO otherData=dao.getODataBySupervisorId(log.getDeliverer());
		if(flag && otherData!=null){
			flag=dao.delete(OtherDataVO.class, otherData.getId());
		}
		OfficeEquipmentVO officeEquipment=dao.getOfficeEquipmentBySupervisorId(log.getDeliverer());
		if(flag && officeEquipment!=null){
			flag=dao.delete(OfficeEquipmentVO.class, officeEquipment.getId());
		}
		PaperTextVO paperText=dao.getpTextBySupervisorId(log.getDeliverer());
		if(flag && paperText!=null){
			flag=dao.delete(PaperTextVO.class, paperText.getId());
		}
		if(flag){
			flag=dao.delete(HandoverLogVO.class, id);
		}
		
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
	}
	
	public HandoverLogVO load(int id) throws Exception{
		return dao.get(HandoverLogVO.class, id,  new BeanPropertyRowMapper(HandoverLogVO.class));
	}
	
	public List<HandoverLogVO> searchHandoverLogList(HandoverLogQueryVO query){
		return dao.searchHandoverLogList(query);
	}
	
	public List<HandoverLogVO> searchHandoverLogListByPage(HandoverLogQueryVO query,IThumbPageTools tools){
		return dao.searchHandoverLogListByPage(query, tools);
	}

	public HandoverLogPicVO searchPicsByHandoverLogId(int handoverLogId) {
		return dao.searchPicsByHandoverLogId(handoverLogId);
	}
	
	/* 根据监管员ID查找花名册VO added by LSF 20170513 */
	public RosterVO loadRosterBySupervisorId(int supervisorId){
		return dao.loadRosterBySupervisorId(supervisorId);
	}
	
	/* 根据花名册ID找出最后一条流水 */
	public PostChangeVO loadLastPostChangeByRosterId(int rosterid){
		return dao.loadLastPostChangeByRosterId(rosterid);
	}
	
	/* 根据一些id找出最后一个在职的监管员的记录 */
	public TransferRepositoryVO loadLastTransferRepositoryByIds(int dealerid, int repositoryid){
		return dao.loadLastTransferRepositoryByIds(dealerid,repositoryid);
	}
	
	/* 更新离岗时间和离岗性质 */
	public boolean updatePostChange(PostChangeVO pc, int flag){
		return dao.updatePostChange(pc, flag);
	}
	
	/* 更新另外的离岗时间和离岗性质 */
	public boolean updateTransferRepository(TransferRepositoryVO pc, int flag){
		return dao.updateTransferRepository(pc, flag);
	}

	public boolean updateHandoverLogPic(HandoverLogPicVO handoverLogPic) {
		boolean flag=false;
		if(handoverLogPic.getId()>0){
			flag=dao.update(handoverLogPic);
		}else{
			try {
				handoverLogPic.setId(Util.getID(HandoverLogPicVO.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			flag=dao.add(handoverLogPic);
		}
		if(flag){
			this.setExecuteMessage("备注保存成功！");
		}else{
			this.setExecuteMessage("备注保存失败！");
		}
		return flag;
	}
	/**
	 * 提交图片，进入审批流程
	 * @param hpic
	 * @return
	 */
	public boolean updPicEditStatus(HandoverLogPicVO hpic) {
		boolean flag=dao.updPicEditStatus(hpic);
		if(flag){
			this.setExecuteMessage("提交成功，进入审批流程");
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}

	public HandoverLogPicVO loadHandoverLogPic(int id) {
		return dao.get(HandoverLogPicVO.class, id,  new BeanPropertyRowMapper(HandoverLogPicVO.class));
	}
	public boolean approval(HandoverLogQueryVO query, UserSession user) throws Exception {
		HandoverLogVO bean =load(query.getId());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrentRole();
		
		if(currRole == RoleConstants.SR.getCode()){
			currRole = bean.getNextApproval();
		}
		boolean flag=false;
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApproveStatus(ApprovalContant.APPROVAL_NOPASS.getCode());
			bean.setNextApproval(0);
			bean.setIsEdit(0);
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务部经理
				bean.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.BUSINESS_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole)){
				//监管员管理部综合专员
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(), currRole)){
				//监管员管理部经理
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole)){
				//运营管理部部长
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			}else if(bean.getNextApproval()==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)){
				
				bean.setApproveStatus(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				bean.setNextApproval(0);
				
				//给所有人发送消息提醒
				/*for(HandoverLogApprovalRoleContants approvalRole:HandoverLogApprovalRoleContants.values()){
					String userid = userRoleDao.findUsingUserIdByRole(approvalRole.getCode()+"");
					if(StringUtil.isNotEmpty(userid)){
						String[] userids = userid.split(",");
						for (String uid : userids) {
							MessageUtil.addMsg(Integer.parseInt(uid), "交接申请通过"+RoleConstants.getName(currRole)+"审批通过，请查看", "/handoverLog.do?method=handoverLogPageList&query.pageType=2",1, MessageTypeContant.HANDOVERLOG.getCode(),bean.getCreateUserId());
						}
						
					}
				}*/
				try {
					//给所有人发送消息提醒 除监管员，银行，品牌集团，经销商集团
					for(HandoverLogApprovalRoleContants approvalRole:HandoverLogApprovalRoleContants.values()){
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
											"/handoverLog.do?method=handoverLogPageList&query.pageType=2",1, MessageTypeContant.HANDOVERLOG.getCode(),bean.getCreateUserId());
								}
							}
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		flag=dao.update(bean);
		if(flag){
			//审批记录表
			MarketApprovalVO approval = new MarketApprovalVO();
			try {
				approval.setId(Util.getID(MarketApprovalVO.class));
				approval.setApprovalObjectId(query.getId());
				approval.setApprovalPerson(userVO.getUserName());
				approval.setApprovalType(ApprovalTypeContant.HANDOVERLOG.getCode());
				approval.setCreateDate(new Date());
				approval.setRemark(query.getRemark());
				approval.setApprovalResult(query.getApprovalState());
				approval.setApprovalUserId(user.getUser().getId());
				approval.setApprovalUserRole(currRole);
				flag=dao.add(approval);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/* 审批完成后添加记录 
			 * 需要添加原来人员的离岗时间和离岗性质
			 * 需要添加新人员的上岗时间和上岗性质*/
			if(bean.getApproveStatus() == ApprovalContant.APPROVAL_PASS.getCode()){
				RosterVO roster1 = new RosterVO();
				RosterVO roster2 = new RosterVO();
				PostChangeVO pc1;
				PostChangeVO pc2;
				TransferRepositoryVO tr1;
				TransferRepositoryVO tr2;
				
				if(bean.getDeliverer()!=0){
					roster1 = loadRosterBySupervisorId(bean.getDeliverer());
				}
				if(bean.getReceiver()!=0){
					roster2 = loadRosterBySupervisorId(bean.getReceiver());
				}
				
				//如果有递交人员，需要更新离职信息
				if(roster1 != null){
					pc1 = loadLastPostChangeByRosterId(roster1.getId());
					if(pc1!=null){
						pc1.setDimissionDate(new Date());
						if(bean.getHandoverNature()==1){
							pc1.setDimissionNature("辞职");
						}else if(bean.getHandoverNature()==2){
							pc1.setDimissionNature("辞退");
						}else if(bean.getHandoverNature()==3){
							pc1.setDimissionNature("正常轮岗");
						}else{
							pc1.setDimissionNature("投诉轮岗");
						}
						updatePostChange(pc1,0);
					}
					
					tr1 = loadLastTransferRepositoryByIds(bean.getDealerId(), roster1.getRepositoryId());
					if(tr1 != null){
						tr1.setLeaveTime(new Date());
						if(bean.getHandoverNature()==1){
							tr1.setLeaveNature("辞职");
						}else if(bean.getHandoverNature()==2){
							tr1.setLeaveNature("辞退");
						}else if(bean.getHandoverNature()==3){
							tr1.setLeaveNature("正常轮岗");
						}else{
							tr1.setLeaveNature("投诉轮岗");
						}
						updateTransferRepository(tr1,0);
					}
				}

				//如果有上岗人员，需要更新上岗信息
				if(roster2 != null){
					pc2 = new PostChangeVO();
					pc2.setMissionDate(new Date());
					pc2.setRosterId(roster2.getId());
					pc2.setId(bean.getDealerId());
					if(bean.getReceiveNature()==1){
						pc2.setMissionNature("轮岗");
					}else if(bean.getReceiveNature()==2){
						pc2.setMissionNature("新入职");
					}else{
						pc2.setMissionNature("二次上岗");
					}
					updatePostChange(pc2,1);
					
					tr2 = new TransferRepositoryVO();
					tr2.setDealerId(bean.getDealerId());
					tr2.setEntryTime(new Date());
					if(bean.getReceiver()==1){
						tr2.setEntryNature("轮岗");
					}else if(bean.getReceiver()==2){
						tr2.setEntryNature("新入职");
					}else{
						tr2.setEntryNature("二次上岗");
					}
					tr2.setRepositoryId(roster2.getRepositoryId());
					tr2.setSupervisorSource(bean.getAfterHandoverNature());
					updateTransferRepository(tr2,1);
				}
				
			}
			
		}
		if(flag){
			this.setExecuteMessage("审批操作成功!");
			SupervisoryService supervisoryService=new SupervisoryService();
			RepositoryService  repositoryService=new RepositoryService();
			SupervisorBaseInfoVO delivery=supervisoryService.getBaseInfo(bean.getDeliverer());
			SupervisorBaseInfoVO receiver=supervisoryService.getBaseInfo(bean.getReceiver());
			//交付人与接收人的储备库对象
			RepositoryVO deliveryRepositoryVO=repositoryService.loadRepositoryBySupervisorId(bean.getDeliverer());
			if(deliveryRepositoryVO==null){
				deliveryRepositoryVO=new RepositoryVO();
			}
			RepositoryVO receiverRepositoryVO=repositoryService.loadRepositoryBySupervisorId(bean.getReceiver());
			if(receiverRepositoryVO==null){
				receiverRepositoryVO=new RepositoryVO();
			}
			RepositoryTrainVO receiverTrain=repositoryService.loadRepositoryTrainByRepositoryId(receiverRepositoryVO.getId());
			try {
				if(bean.getApproveStatus()==ApprovalContant.APPROVAL_WAIT.getCode()){
					//发送消息
					try {
						if(bean.getApproveStatus()==ApprovalContant.APPROVAL_WAIT.getCode()){
							MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, delivery.getName()+" 与 "+receiver.getName()+" 交接申请,请填写",
									"/handoverLog.do?method=handoverLogPageList", 1, MessageTypeContant.HANDOVERLOG.getCode(), bean.getCreateUserId());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					//如果下一审批人是监管员管理部综合专员则给监管员发送消息，上传交接书图片
					try {
						if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){
							List<UserVO> userList=userService.findUserListByClientTypeAndClientId(ClientTypeConstants.SUPERVISORY.getCode(), deliveryRepositoryVO.getId());
							if(userList!=null && userList.size()>0){
								for(UserVO u:userList){
									MessageUtil.addMsg(u.getId(), delivery.getName()+" 与 "+receiver.getName()+" 交接申请已致监管员管理部综合专员审批,请上传交接书图片", "/handoverLog.do?method=handoverLogPageList",1, MessageTypeContant.HANDOVERLOG.getCode(),bean.getCreateUserId());
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}else if(bean.getApproveStatus()==ApprovalContant.APPROVAL_NOPASS.getCode()){
					//修改完成时间
					try {
						dao.updateFinishTime(bean.getId(),new Date());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try{
						//除点击审批不通过的当事人和监管员，其他人都要受到提醒。
						for(HandoverLogApprovalRoleContants approvalRole:HandoverLogApprovalRoleContants.values()){
							if(currRole==approvalRole.getCode()){
								continue;
							}
							String userid = userRoleDao.findUsingUserIdByRole(approvalRole.getCode()+"");
							if(StringUtil.isNotEmpty(userid)){
								String[] userids = userid.split(",");
								for (String uid : userids) {
									MessageUtil.addMsg(Integer.parseInt(uid), delivery.getName()+" 与 "+receiver.getName()+" 交接申请,"+RoleConstants.getName(currRole)+"审批未通过，请查看", "/handoverLog.do?method=handoverLogPageList&query.pageType=2",1, MessageTypeContant.HANDOVERLOG.getCode(),bean.getCreateUserId());
								}
								
							}
						}
						//给当前监管员发送提醒
						UserQueryVO userQuery=new UserQueryVO();
						userQuery.setClienttype(ClientTypeConstants.SUPERVISORY.getCode());
						userQuery.setClientId(deliveryRepositoryVO.getId());
						userQuery.setStates(new Integer[]{StateConstants.USING.getCode()});
						List<UserVO> userList=userService.searchUserList(userQuery);
						for(UserVO u:userList){
							MessageUtil.addMsg(u.getId(), delivery.getName()+" 与 "+receiver.getName()+" 交接申请,"+RoleConstants.getName(currRole)+"审批未通过，请查看", "/handoverLog.do?method=handoverLogPageList&query.pageType=2",1, MessageTypeContant.HANDOVERLOG.getCode(),bean.getCreateUserId());
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}else if(bean.getApproveStatus()==ApprovalContant.APPROVAL_PASS.getCode()){
					//轮岗交接修改经销商和监管员绑定关系
					try {
						if(bean.getHandoverType()==2){
							DealerSupervisorService dealerSupervisorService=new DealerSupervisorService();
							DealerSupervisorQueryVO dealerSupervisorQuery=new DealerSupervisorQueryVO();
							//交付人
							dealerSupervisorQuery.setDealerId(bean.getDealerId());
							dealerSupervisorQuery.setSupervisorId(deliveryRepositoryVO.getId());
							List<DealerSupervisorVO> delivererSupervisorList=dealerSupervisorService.searchDealerSupervisorList(dealerSupervisorQuery);
							
							//修改交付人为接收人
							if(delivererSupervisorList!=null && delivererSupervisorList.size()>0){
								for(DealerSupervisorVO dealerSupervisor:delivererSupervisorList){
									dealerSupervisor.setRepositoryId(receiverRepositoryVO.getId());
									dealerSupervisorService.update(dealerSupervisor);
								}
							}
							try{
								TransferService transferService=new TransferService();
								//轮岗交接， 新增经销商监管员记录
								TransferRepositoryVO transferRepository=new TransferRepositoryVO();
								transferRepository.setDealerId(bean.getDealerId());
								transferRepository.setRepositoryId(receiverRepositoryVO.getId());
								transferRepository.setEntryTime(new Date());
								String entryNature=ReceiveNatureContants.getNameByCode(bean.getReceiveNature());
								transferRepository.setEntryNature(entryNature==null?" ":entryNature);
								transferRepository.setSupervisorSource(bean.getAfterHandoverNature());
								transferService.addDealerSupervisor(transferRepository);
							}catch(Exception e){
								e.printStackTrace();
							}
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					//修改电脑、保险柜使用人
					try {
						FixedAssetsService fixedAssetsService=new FixedAssetsService();
						FixedAssetsQueryVO fixedAssetsQuery=new FixedAssetsQueryVO();
						fixedAssetsQuery.setSendee(bean.getDeliverer());
						fixedAssetsQuery.setAsset_type(AssetsTypeContant.ELECTRONICS.getCode());
						//电脑
						List<FixedAssetsVO> computers=fixedAssetsService.searchFixedAssets(fixedAssetsQuery);
						if(computers!=null && computers.size()>0){
							for(FixedAssetsVO computer:computers){
								fixedAssetsService.updateFixedAssetsSendee(computer.getId(), bean.getReceiver());
							}
						}
						//保险柜
						fixedAssetsQuery.setAsset_type(AssetsTypeContant.WORK.getCode());
						List<FixedAssetsVO> safetyBoxs=fixedAssetsService.searchFixedAssets(fixedAssetsQuery);
						if(safetyBoxs!=null && safetyBoxs.size()>0){
							for(FixedAssetsVO safetyBox:safetyBoxs){
								fixedAssetsService.updateFixedAssetsSendee(safetyBox.getId(), bean.getReceiver());
							}
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					//修改资料保管人
					try {
						ElectronicTextVO electronicText=dao.getETextBySupervisorId(bean.getDeliverer());
						if(electronicText!=null){
							dao.updateETextSupervisorId(electronicText.getId(), bean.getReceiver());
						}
						OtherDataVO otherData=dao.getODataBySupervisorId(bean.getDeliverer());
						if(otherData!=null){
							dao.updateODataSupervisorId(otherData.getId(), bean.getReceiver());
						}
						OfficeEquipmentVO officeEquipment=dao.getOfficeEquipmentBySupervisorId(bean.getDeliverer());
						if(officeEquipment!=null){
							dao.updateOfficeEquipmentSupervisorId(officeEquipment.getId(), bean.getReceiver());
						}
						PaperTextVO paperText=dao.getpTextBySupervisorId(bean.getDeliverer());
						if(paperText!=null){
							dao.updatepTextSupervisorId(paperText.getId(), bean.getReceiver());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					//修改完成时间
					try {
						dao.updateFinishTime(bean.getId(),new Date());
					} catch (Exception e) {
						e.printStackTrace();
					}
					//修改储备库状态
					try{
						RepositoryVO deliverer=repositoryService.loadRepositoryBySupervisorId(bean.getDeliverer());
						if(deliverer!=null){
							if(bean.getHandoverNature()==HandoverNatureContants.RESIGN.getCode() ){
								repositoryService.updateRepositoryStatus(deliverer.getId(), RepositoryStatus.INVALID.getCode(),RepositoryReason.RESIGN.getCode());
							}else if(bean.getHandoverNature()==HandoverNatureContants.DISMISS.getCode()){
								repositoryService.updateRepositoryStatus(deliverer.getId(), RepositoryStatus.INVALID.getCode(),RepositoryReason.DISMISS.getCode());
							}else if(bean.getHandoverNature()==HandoverNatureContants.NORMAL_ROTATE_POSTSOCIETY.getCode() || bean.getHandoverNature()==HandoverNatureContants.COMPLAIN_RECRUITMENT.getCode()){
								repositoryService.updateRepositoryStatus(deliverer.getId(), RepositoryStatus.VALID.getCode(),0);
							}
						}
						RepositoryVO receiverer=repositoryService.loadRepositoryBySupervisorId(bean.getReceiver());
						if(receiverer!=null){
							if(bean.getReceiveNature()==ReceiveNatureContants.ENTRY.getCode()){
								//新增花名册
								try {
									List<RosterVO> rosters=rosterService.searchRosterByRepositoryId(receiverer.getId());
									if(CollectionUtils.isEmpty(rosters)){
										RosterVO roster = new RosterVO();
										roster.setId(Util.getID(RosterVO.class));
										String staffNo=new DecimalFormat("000000").format(roster.getId());;
										roster.setStaffNo(staffNo);
										roster.setRepositoryId(receiverer.getId());
										roster.setSupervisorId(receiverer.getSupervisorId());
										roster.setCreateTime(new Date());
										roster.setCreateUser(user.getUser().getId());
										flag=rosterService.add(roster);
										if(flag){
											repositoryService.updateRepositoryStatus(receiverer.getId(),RepositoryStatus.ALREADYPOST.getCode(),0);
											supervisoryService.updateSupervisorEntryDate(receiverer.getSupervisorId(),new Date());
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}else{
								repositoryService.updateRepositoryStatus(receiverer.getId(),RepositoryStatus.ALREADYPOST.getCode(),0);
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					try{
						DealerService dealerService=new DealerService();
						DealerVO dealer=dealerService.loadDealerById(bean.getDealerId());
						StringBuffer message=new StringBuffer();
						if(dealer!=null){
							message.append(dealer.getDealerName());
						}
						message.append( delivery.getName()+" 与 "+receiver.getName()+" 交接申请,"+RoleConstants.getName(currRole)+"审批通过，请查看");
						
						//给当前监管员发送提醒
						UserQueryVO userQuery=new UserQueryVO();
						userQuery.setClienttype(ClientTypeConstants.SUPERVISORY.getCode());
						userQuery.setClientId(deliveryRepositoryVO.getId());
						userQuery.setStates(new Integer[]{StateConstants.USING.getCode()});
						List<UserVO> userList=userService.searchUserList(userQuery);
						for(UserVO u:userList){
							MessageUtil.addMsg(u.getId(), delivery.getName()+" 与 "+receiver.getName()+" 交接申请,"+RoleConstants.getName(currRole)+"审批通过，请查看", "/handoverLog.do?method=handoverLogPageList&query.pageType=2",1, MessageTypeContant.HANDOVERLOG.getCode(),bean.getCreateUserId());
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					//MessageUtil.addMsg(bean.getCreateUserId(),delivery.getName()+" 与 "+receiver.getName()+" 交接申请,审批已通过，请查看", "/handoverLog.do?method=handoverLogPageList&query.pageType=2",1, MessageTypeContant.HANDOVERLOG.getCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.setExecuteMessage("审批操作失败!");
		}
		return flag;
	}

	public boolean updHandoverLogEditStatus(HandoverLogVO handoverLog) {
		boolean flag=dao.updHandoverLogEditStatus(handoverLog);
		if(flag){
			SupervisoryService supervisoryService=new SupervisoryService();
			SupervisorBaseInfoVO delivery=supervisoryService.getBaseInfo(handoverLog.getDeliverer());
			SupervisorBaseInfoVO receiver=supervisoryService.getBaseInfo(handoverLog.getReceiver());
			//发送消息
			try {
				if(handoverLog.getApproveStatus()==ApprovalContant.APPROVAL_WAIT.getCode()){
					MessageUtil.sendMsg(new String[]{handoverLog.getNextApproval()+""}, delivery.getName()+" 与 "+receiver.getName()+" 交接申请,请填写",
							"/handoverLog.do?method=handoverLogPageList", 1, MessageTypeContant.HANDOVERLOG.getCode(), handoverLog.getCreateUserId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setExecuteMessage("提交成功，进入审批流程！");
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}

	public List<HandoverLogVO> getHandoverLogByDealerId(int dealerId) {
		return dao.getHandoverLogByDealerId(dealerId);
	}

	public HandoverBookVO getHandoverBookBySupervisorId(int id) {
		
		 new FixedAssetsService();
		new FixedAssetListService();
		HandoverBookVO book=new HandoverBookVO();
		ElectronicTextVO electronicText=dao.getETextBySupervisorId(id);
		if(electronicText==null){
			electronicText=new ElectronicTextVO();
		}
		OtherDataVO otherData=dao.getODataBySupervisorId(id);
		if(otherData==null){
			otherData=new OtherDataVO();
		}
		OfficeEquipmentVO officeEquipment=dao.getOfficeEquipmentBySupervisorId(id);
		
		
		
		if(officeEquipment==null){
			officeEquipment=new OfficeEquipmentVO();
		}
			List<FixedAssetListVO> list = new FixedAssetListService().searchfixedAssetListByUserName(id);
			for (FixedAssetListVO fixedAssetListVO : list) {
				
				try {
					FixedAssetsVO fal = new FixedAssetsService().loadFixedAssetsById(fixedAssetListVO.getFid());
					if(fal.getAsset_type() == 1){
						FixedAssetsVO fixedAssets = new FixedAssetsService().loadFixedAssetsById(fal.getId());
						officeEquipment.setComputerAssetsNTT(fixedAssets);
					}else if(fal.getAsset_type() == 2){
						FixedAssetsVO fixedAssets = new FixedAssetsService().loadFixedAssetsById(fal.getId());
						officeEquipment.setBoxesAssetsNTT(fixedAssets);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		PaperTextVO paperText=dao.getpTextBySupervisorId(id);
		if(paperText==null){
			paperText=new PaperTextVO();
		}
		book.seteText(electronicText);
		book.setOData(otherData);
		book.setOfficeEquipment(officeEquipment);
		book.setpText(paperText);
		return book;
	}

	public boolean updElectronicText(ElectronicTextVO eText) {
		boolean flag=false;
		if(eText.getId()>0){
			flag=dao.update(eText);
		}else{
			try {
				eText.setId(Util.getID(ElectronicTextVO.class));
				flag=dao.add(eText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(flag){
			this.setExecuteMessage("保存成功！");
		}else{
			this.setExecuteMessage("保存失败！");
		}
		return flag;
	}
	public boolean updOfficeEquipment(OfficeEquipmentVO officeEquipment) {
		boolean flag=false;
		if(officeEquipment.getId()>0){
			flag=dao.update(officeEquipment);
		}else{
			try {
				officeEquipment.setId(Util.getID(OfficeEquipmentVO.class));
				flag=dao.add(officeEquipment);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(flag){
			this.setExecuteMessage("保存成功！");
		}else{
			this.setExecuteMessage("保存失败！");
		}
		return flag;
	}
	public boolean updOtherData(OtherDataVO OData) {
		boolean flag=false;
		if(OData.getId()>0){
			flag=dao.update(OData);
		}else{
			try {
				OData.setId(Util.getID(OtherDataVO.class));
				flag=dao.add(OData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(flag){
			this.setExecuteMessage("保存成功！");
		}else{
			this.setExecuteMessage("保存失败！");
		}
		return flag;
	}
	public boolean updPaperText(PaperTextVO pText) {
		boolean flag=false;
		if(pText.getId()>0){
			flag=dao.update(pText);
		}else{
			try {
				pText.setId(Util.getID(PaperTextVO.class));
				flag=dao.add(pText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(flag){
			this.setExecuteMessage("保存成功！");
		}else{
			this.setExecuteMessage("保存失败！");
		}
		return flag;
	}
	 /*
	  * 导出交接记录管理台账 -- 需求59
	  * @time 20170517
	 */
	public List<ExtHandoverLogVO> ExtHandoverLog(HandoverLogQueryVO handoverLogQuery) {
		return dao.ExtHandoverLog(handoverLogQuery);
	}
	
	public boolean deleteHandoverPicPicture(int fileId) throws Exception {
		UploadfileService ufService = new UploadfileService();
		UploadfileVO file = ufService.loadUploadfileById(fileId);
		String filePath = file.getFile_path();
		filePath = FileUtil.getFileFolder() + File.separator + filePath;
		boolean fag = false;
		// 根据图片Id删除对应的图片
		if (FileUtil.delFile(filePath)) {
			try {
				// 根据图片Id删除对应的图片信息
				if (dao.delete(UploadfileVO.class, fileId)){
					// 根据图片Id删除对应的巡检报告-店面图片记录
					fag = dao.deleteHandoverPicPicture(fileId);
				}
			} catch (Exception e) {
				fag=false ;
				e.printStackTrace();
			}

		}
		return fag;
	}

	public Image createHandoverPicImageList(int id,int hpicId) {
		UploadfileVO uf = null;
		UploadfileService ufService = new UploadfileService();;
		Image image = null;
		try {
			uf = ufService.loadUploadfileById(id);
			image = new Image();
			image.setDeleteUrl("/handoverLog.do?method=deleteHandoverPicPicture&fileId="
					+ id+"&hpicId="+hpicId);

			try {
				image.setUrl("/showImg.do?method=downLoadFile&filePath="
						+ uf.getFile_path() + "&fileName="
						+ uf.getFile_name());// 下载地址
				// 上传完成后的缩略图地址
				image.setThumbnailUrl("/showImg.do?method=showThumbnailImg&filePath="
						+ uf.getFile_path());
				image.setName(uf.getFile_name());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 删除图片的地址
			image.setDeleteType("GET");// 提交方式
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}

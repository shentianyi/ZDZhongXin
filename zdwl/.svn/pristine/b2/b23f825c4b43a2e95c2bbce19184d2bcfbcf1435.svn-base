package com.zd.csms.messagequartz.quartz;

import java.util.Date;
import java.util.List;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.contants.MsgUrlContant;
import com.zd.csms.messagequartz.model.SupervisorVO;
import com.zd.csms.messagequartz.model.UnInspectMes;
import com.zd.csms.messagequartz.model.UnInspectVO;
import com.zd.csms.messagequartz.model.UnInspectionPlanVO;
import com.zd.csms.messagequartz.service.MessageQuartzService;
import com.zd.csms.messagequartz.service.UnInspectService;
import com.zd.csms.messagequartz.service.UnInspectionPlanService;
import com.zd.csms.planandreport.model.InspectionPlanVO;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;

/**
 * 监管员定时任务监管员生日提醒
 *
 */
public class MessageQuartz {
	private static int[] role = new int[] { 
		RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
		RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
		RoleConstants.WINDCONRTOL_DATA.getCode(),
		RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
	};
	
	public void run() throws Exception {
	    birthdayMessage();//监管员生日提醒
		yearAndDay();//监管员入职满一年提醒
		UninspectionMessage();//3)	未按风控巡检计划执行提醒
		UninspectMessage();//4)	巡店报告上传3日未上传新报告提醒
	}
	
	/**
	 * 监管员生日提醒统计
	 */
	public void birthdayMessage(){
		MessageQuartzService service = new MessageQuartzService();
	 	MessageService mService = new MessageService();
	
	 	boolean flag = false;
	    List<SupervisorVO> list = service.findList();
	    if (list != null && list.size() > 0) {
	    
	    	for (int i = 0; i < list.size(); i++) {
	    	MessageVO msVo = null;
			try {
				msVo = mService.loadMsgByUserAndType(list.get(i).getId(), MessageTypeContant.SUPSERVISORBIRTHDAY.getCode());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (msVo != null) {
				//该用户该类型提醒信息数量增加1
				msVo.setName(String.valueOf(Integer.parseInt(msVo.getName())+1));//数量增加1
				if (msVo.getIsread()==2) {
					msVo.setIsread(1);
				}
				try {
					flag = mService.updMessage(msVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (flag) {
					SupervisorVO supervisorVO = new SupervisorVO();	
					supervisorVO.setMessageId(msVo.getId());
					supervisorVO.setSupervisorId(list.get(i).getId());
					supervisorVO.setIsread(1);
					supervisorVO.setDepartment(ClientTypeConstants.SUPERVISORY.getName());
					supervisorVO.setMessagetype(MessageTypeContant.SUPSERVISORBIRTHDAY.getCode());
					supervisorVO.setGreetings("生日快乐！！！！！");
					service.add(supervisorVO);
				}
			}else {
					SupervisorVO supervisorVO = new SupervisorVO();	
				    //用户id - 消息名称  - url(暂时不用) - 是否已读  - 消息类型:1.信息2.预警   - 消息类别 分辨不同的消息类别 - 部门名称
				    MessageVO mvo = new MessageVO(list.get(i).getId(),1 + "",MsgUrlContant.MSUPERVISORBIRTHDAY.getName(),
							MsgIsContants.NOREAD.getCode(),MsgIsContants.INFO.getCode(),MessageTypeContant.SUPSERVISORBIRTHDAY.getCode(),
							ClientTypeConstants.SUPERVISORY.getName());
					int mvoId = 0;
					try {
						mvoId = mService.addMessages(mvo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					    //插入到监管员生日提醒表
					supervisorVO.setMessageId(mvoId);
					supervisorVO.setSupervisorId(list.get(i).getId());
					supervisorVO.setIsread(1);
					supervisorVO.setDepartment(ClientTypeConstants.SUPERVISORY.getName());
					supervisorVO.setMessagetype(MessageTypeContant.SUPSERVISORBIRTHDAY.getCode());
					supervisorVO.setGreetings("生日快乐！！！！！");
					service.add(supervisorVO);
					}
			}
	}
	    	
	}
	/**
	 *  未按风控巡检计划执行提醒
	 */
	public void UninspectionMessage(){
	
		
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		for (int i = 0; i < role.length; i++) {
			urquery.setRole_id(role[i]);
			innerUninspectionMessage(urquery);
		}
		
	}
	
	/**
	 * 巡店报告上传3日未上传新报告提醒
	 */
	public void UninspectMessage(){
		
		
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		for (int i = 0; i < role.length; i++) {
			urquery.setRole_id(role[i]);
			innerUninspectMessage(urquery);
		}
		
	}
	
public void innerUninspectMessage(UserRoleQueryVO urquery){
		
		UnInspectService service = new UnInspectService();
		MessageService mService = new MessageService();
		List<UnInspectMes> list = service.findList();
	 	RoleService rs = new RoleService();
	 	UserService userService = new UserService();
	 	UserVO userVO = null;
		List<UserRoleVO> urList;
		boolean flag = false;
		int mvoId = 0;
		try {
			urList = rs.searchUserRoleList(urquery);
			
			if (list != null && list.size() >0 ) {
				for (UnInspectMes unInspectMes : list) {
				  if(urList != null && urList.size()>0){
					for(UserRoleVO ur : urList){
						userVO = userService.get(ur.getUser_id());
						if (userVO != null && userVO.getState() == 1) {
							MessageVO msVo = mService.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.UNINSPECTI.getCode());
							
								if (msVo != null) {
									//该用户该类型提醒信息数量增加1
									msVo.setName(String.valueOf(Integer.parseInt(msVo.getName())+1));//数量增加1
									mvoId = msVo.getId();
									if (msVo.getIsread()==2) {
										msVo.setIsread(1);
									}
									try {
										flag = mService.updMessage(msVo);
									} catch (Exception e) {
										e.printStackTrace();
									}
								
							}else {
								//用户id - 消息名称  - url(暂时不用) - 是否已读  - 消息类型:1.信息2.预警   - 消息类别 分辨不同的消息类别 - 部门名称
								//将消息添加到t_message表中
								MessageVO mvo = new MessageVO(ur.getUser_id(),String.valueOf(1),MsgUrlContant.UNINSPECT.getName(),
										MsgIsContants.NOREAD.getCode(),MsgIsContants.INFO.getCode(),MessageTypeContant.UNINSPECTI.getCode(),ClientTypeConstants.WINDCONRTOL.getName());
							    mvoId = mService.addMessages(mvo);
							}
							UnInspectVO unInspectVO = new UnInspectVO();
							unInspectVO.setCreateDate(new Date());
							unInspectVO.setUserId(ur.getUser_id());
							unInspectVO.setMessageId(mvoId);
							unInspectVO.setIsread(1);
							unInspectVO.setMessagetype(MessageTypeContant.UNINSPECTI.getCode());
							unInspectVO.setAddress(unInspectMes.getAddress());
							unInspectVO.setLastModified(unInspectMes.getModify_time());
							unInspectVO.setDirector(unInspectMes.getOutControlUserName());
							
							service.addUnInspectMessage(unInspectVO);
						}
					}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void innerUninspectionMessage(UserRoleQueryVO urquery){
		
		UnInspectionPlanService service = new UnInspectionPlanService();
		MessageService mService = new MessageService();
		UserService uservice = new UserService();
		DealerService dealerService = new DealerService();
		BankService bankService = new BankService();
		BrandService brandService = new BrandService();
		UserService userService = new UserService();
	 	UserVO userVO = null;
		List<InspectionPlanVO> list = service.findList();
	 	RoleService rs = new RoleService();
		List<UserRoleVO> urList;
		int mvoId = 0;
		try {
			if (list != null && list.size() >0) {
				urList = rs.searchUserRoleList(urquery);
				for (InspectionPlanVO inspectionPlanVO : list) {
				  if(urList != null && urList.size()>0){
					for(UserRoleVO ur : urList){
						userVO = userService.get(ur.getUser_id());
						if (userVO != null && userVO.getState() == 1) {
						MessageVO msVo = mService.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.UNINSPECTIONVID.getCode());
						if (msVo != null) {
							msVo.setName(Integer.parseInt(msVo.getName())+1+"");
							mvoId = msVo.getId();
							if (msVo.getIsread()==2) {
								msVo.setIsread(1);
							}
							mService.updMessage(msVo);
						}else {
							//用户id - 消息名称  - url(暂时不用) - 是否已读  - 消息类型:1.信息2.预警   - 消息类别 分辨不同的消息类别 - 部门名称
							//将消息添加到t_message表中
							MessageVO mvo = new MessageVO(ur.getUser_id(),String.valueOf(1),MsgUrlContant.UNINSPECTION.getName(),
									MsgIsContants.NOREAD.getCode(),MsgIsContants.INFO.getCode(),MessageTypeContant.UNINSPECTIONVID.getCode(),ClientTypeConstants.WINDCONRTOL.getName());
							mvoId = mService.addMessages(mvo);
							
						}
						UnInspectionPlanVO unInspectionPlanVO = new UnInspectionPlanVO();
						unInspectionPlanVO.setCreateDate(new Date());
						unInspectionPlanVO.setUserId(ur.getUser_id());
						unInspectionPlanVO.setMessageId(mvoId);
						unInspectionPlanVO.setIsread(1);
						unInspectionPlanVO.setMessagetype(MessageTypeContant.UNINSPECTIONVID.getCode());
						unInspectionPlanVO.setNum(inspectionPlanVO.getPlanCode());
						unInspectionPlanVO.setPlandate(inspectionPlanVO.getPredictBeginDate());
						//外控专员Id
						if(inspectionPlanVO.getOutControlUserId() != null){
							
							int ins = inspectionPlanVO.getOutControlUserId();
							UserVO user = uservice.get(ins);
							if (user != null) {
								unInspectionPlanVO.setDirector(user.getUserName());
							}
						}
						//经销商id
						if (inspectionPlanVO.getDealerId() != null) {
							int delar = inspectionPlanVO.getDealerId();
							DealerVO dealerVO = dealerService.loadDealerById(delar);
							BrandVO brandVO = null;
							if (dealerVO != null) {
								unInspectionPlanVO.setMerchantname(dealerVO.getDealerName());
								//品牌
								brandVO = brandService.loadBrandById(dealerVO.getBrandId());
							}
							if (brandVO != null) {
								unInspectionPlanVO.setBrandname(brandVO.getName());
							}
						}
						//金融结构
						DealerQueryVO query = new DealerQueryVO();
						query.setIds(new Integer[]{inspectionPlanVO.getDealerId()});
						List<DealerBankVO> banks = dealerService.findBankListByIds(query);
						StringBuffer bankName=new StringBuffer();
						if(banks!=null){
							for (DealerBankVO dealerBankVO : banks) {
								BankVO bank = bankService.get(dealerBankVO.getBankId());
								if(bank!=null){
									bankName.append(bank.getBankFullName()+"/");
								}
							}
							unInspectionPlanVO.setBankname(bankName.toString());
						}
						
						
						service.addUnInspectionPlanMessage(unInspectionPlanVO);
					}
			      }
				}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 监管员入职满一年提醒
	 */
	
	public void yearAndDay(){
		
		MessageQuartzService service = new MessageQuartzService();
	 	MessageService mService = new MessageService();

	 	List<SupervisorVO> list = service.findListinYear();
	 	boolean flag = false;
	 	if (list != null && list.size() > 0) {
	     for (int i = 0; i < list.size(); i++) {
	    	MessageVO msVo = null;
			try {
				msVo = mService.loadMsgByUserAndType(list.get(i).getId(), MessageTypeContant.SUPSERVISORBIRTHDAYYEARANDDAY.getCode());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (msVo != null) {
				//该用户该类型提醒信息数量增加1
				msVo.setName(String.valueOf(Integer.parseInt(msVo.getName())+1));//数量增加1
				if (msVo.getIsread()==2) {
					msVo.setIsread(1);
				}
				try {
					flag = mService.updMessage(msVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (flag) {
					SupervisorVO supervisorVO = new SupervisorVO();	
					supervisorVO.setMessageId(msVo.getId());
					supervisorVO.setSupervisorId(list.get(i).getId());
					supervisorVO.setIsread(MsgIsContants.NOREAD.getCode());
					supervisorVO.setDepartment(ClientTypeConstants.SUPERVISORY.getName());
					supervisorVO.setMessagetype(MessageTypeContant.SUPSERVISORBIRTHDAYYEARANDDAY.getCode());
					supervisorVO.setGreetings("恭喜入职满一年");
					service.add(supervisorVO);
				}
				}else {
					
				    //用户id - 消息名称  - url(暂时不用) - 是否已读  - 消息类型:1.信息2.预警   - 消息类别 分辨不同的消息类别 - 部门名称
					  MessageVO mvo = new MessageVO(list.get(i).getId(),1 + "",MsgUrlContant.MSUPERVISORBIRTHDAYYEAR.getName(),
								MsgIsContants.NOREAD.getCode(),MsgIsContants.INFO.getCode(),MessageTypeContant.SUPSERVISORBIRTHDAYYEARANDDAY.getCode(),
								ClientTypeConstants.SUPERVISORY.getName());
					int mvoId = 0;
					try {
						mvoId = mService.addMessages(mvo);
					} catch (Exception e) {
						e.printStackTrace();
					}//将消息添加到t_message表中
					SupervisorVO supervisorVO = new SupervisorVO();
					//插入到监管员
					if (flag) {
						supervisorVO.setMessageId(mvoId);
						supervisorVO.setSupervisorId(list.get(i).getId());
						supervisorVO.setIsread(MsgIsContants.NOREAD.getCode());
						supervisorVO.setDepartment(ClientTypeConstants.SUPERVISORY.getName());
						supervisorVO.setMessagetype(MessageTypeContant.SUPSERVISORBIRTHDAYYEARANDDAY.getCode());
						supervisorVO.setGreetings("恭喜入职满一年");
						flag = service.add(supervisorVO);
						}
				}
		}
	 	}
	}
	
}

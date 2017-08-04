package com.zd.csms.supervisory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.contants.MsgUrlContant;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.dao.IRepairCostDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.dao.mapper.RepairCostMapper;
import com.zd.csms.supervisory.model.ExtRepairCostVO;
import com.zd.csms.supervisory.model.RepairCostQueryVO;
import com.zd.csms.supervisory.model.RepairCostVO;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageVO;
import com.zd.csms.supervisory.service.repairecostms.SupervisorRepairCostMessageService;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetsService;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class RepairCostService extends ServiceSupport {

	private IRepairCostDAO dao = SupervisorDAOFactory.getRepairCostDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	
	public boolean addRepairCost(RepairCostVO vo) throws Exception {
		vo.setId(Util.getID(RepairCostVO.class));
		boolean flag =dao.add(vo);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public boolean updRepairCost(RepairCostVO vo) throws Exception {
		RepairCostVO bean = this.loadRepairCostById(vo.getId());
		bean.setPromoter(vo.getPromoter());
		bean.setRepair_project(vo.getRepair_project());
		bean.setProblem(vo.getProblem());
		bean.setMoney(vo.getMoney());
		bean.setCredatetime(vo.getCredatetime());
		bean.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		boolean flag=dao.update(vo);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}	
		return flag;
	}
	
	public boolean deleteRepairCost(int id) throws Exception {
		boolean flag=dao.delete(RepairCostVO.class, id);
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
    }
	
	public RepairCostVO loadRepairCostById(int id) throws Exception{
		RepairCostVO repairCost = dao.get(RepairCostVO.class, id,new RepairCostMapper());
		return repairCost;
	}
	
	public List<RepairCostVO> searchRepairCostList(RepairCostQueryVO query){
		return dao.searchRepairCostList(query);
	}
	
	
	/**
	 * 
	 * @number:		63
	 * @author:		sxs
	 * @describe:
	 * @param:
	 * @return:
	 */
	public List<RepairCostVO> searchRepairCostListByPage(RepairCostQueryVO vo, IThumbPageTools tools){
		List<RepairCostVO> list = dao.searchRepairCostListByPage(vo, tools);
		if(list == null){
			list = new ArrayList<RepairCostVO>();
		}
		for (RepairCostVO repairCostVO : list) {
			if(repairCostVO.getRepair_project() != null && repairCostVO.getRepair_project().trim().length() != 0){
				int value = StringUtil.intValue(repairCostVO.getRepair_project(), -1);
				if(value > 0){
					FixedAssetsVO f;
					
					try {
						f = new FixedAssetsService().loadFixedAssetsById(value);
						if(f==null){
							continue;
						}
						repairCostVO.setRepair_project(f.getAsset_name());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}
	
	//查询维修台账
	public List<RepairCostVO> searchRepairCostListByPage(RepairCostQueryVO vo, IThumbPageTools tools,HttpServletRequest request){
		List<RepairCostVO> list = dao.searchRepairCostListByPage(vo, tools,request);
		for (RepairCostVO repairCostVO : list) {
			if(repairCostVO.getRepair_project() != null && repairCostVO.getRepair_project().trim().length() != 0){
				int value = StringUtil.intValue(repairCostVO.getRepair_project(), -1);
				if(value > 0){
					FixedAssetsVO f;
					
					try {
						f = new FixedAssetsService().loadFixedAssetsById(value);
						if(f==null){
							continue;
						}
						repairCostVO.setRepair_project(f.getAsset_name());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean approval(RepairCostQueryVO query,UserSession user) throws Exception{
		RepairCostVO bean = dao.get(RepairCostVO.class, query.getId(),new RepairCostMapper());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		boolean flag=false;
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
			bean.setNextApproval(0);
			try {
				MessageUtil.addOrUpdateMeg(bean.getPromoter(), "设备维修申请审批不通过", "/repaircost.do?method=findList&repaircostquery.pageType=2", 1,MessageTypeContant.REPAIRCOST.getCode(),bean.getCreateuserid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(), currRole) ){
				//监管员管理部综合专员
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()+""}, "设备维修申请,请审批", 
						"/repaircost.do?method=findList", 1,MessageTypeContant.REPAIRCOST.getCode(),bean.getCreateuserid());

			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(), currRole)){
				//监管员管理部薪酬专员
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+""}, "设备维修申请,请审批", 
						"/repaircost.do?method=findList", 1,MessageTypeContant.REPAIRCOST.getCode(),bean.getCreateuserid());

			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole)){
				//监管员管理部经理
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+""}, "设备维修申请,请审批", 
						"/repaircost.do?method=findList", 1,MessageTypeContant.REPAIRCOST.getCode(),bean.getCreateuserid());

			}else if(bean.getNextApproval()==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)){
				//运营管理部部长
				bean.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""}, "设备维修申请,请审批", 
						"/repaircost.do?method=findList", 1,MessageTypeContant.REPAIRCOST.getCode(),bean.getCreateuserid());

			}else if(bean.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//总监
				bean.setNextApproval(0);
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				try {
					MessageUtil.addOrUpdateMeg(bean.getPromoter(), "设备维修申请审批通过", "/repaircost.do?method=findList&repaircostquery.pageType=2", 1,MessageTypeContant.REPAIRCOST.getCode(),bean.getCreateuserid());
					MessageUtil.addOrUpdateMeg(RoleConstants.FINANCE_ACCOUNTING.getCode(), "设备维修申请审批通过", "/repaircost.do?method=findList&repaircostquery.pageType=2", 1,MessageTypeContant.REPAIRCOST.getCode(),bean.getCreateuserid());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		flag=dao.update(bean);
		if(flag){
			//审批记录表
			MarketApprovalVO approval = new MarketApprovalVO();
			approval.setId(Util.getID(MarketApprovalVO.class));
			approval.setApprovalObjectId(query.getId());
			approval.setApprovalPerson(userVO.getUserName());
			approval.setApprovalType(ApprovalTypeContant.REPAIRCOST.getCode());
			approval.setCreateDate(new Date());
			approval.setRemark(query.getRemark());
			approval.setApprovalResult(query.getApprovalState());
			approval.setApprovalUserId(user.getUser().getId());
			approval.setApprovalUserRole(currRole);
			flag=dao.add(approval);
		}
		if(flag){
			this.setExecuteMessage("审批操作成功！");
		}else{
			this.setExecuteMessage("审批操作失败！");
		}
		return flag;
	}
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submit(int id) throws Exception{
		
		RepairCostVO bean =  loadRepairCostById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.REPAIRCOST.getCode());
		bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()+""}, "设备维修申请", 
				"/repaircost.do?method=findList", 1,MessageTypeContant.REPAIRCOST.getCode(),bean.getCreateuserid());

		boolean flag=dao.update(bean);
		if(flag){
			this.setExecuteMessage("提交成功！");
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
		
	
	}
	
	/**
	 * 提交的时候  新增设备申请提醒信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submitAndAddRepairCost(int id,SupervisorRepairCostMessageVO srcmvo) throws Exception{
		
		RepairCostVO bean =  loadRepairCostById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.REPAIRCOST.getCode());
		bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		
		boolean flag = false;
		RoleService rs = new RoleService();
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		//综合专员
		urquery.setRole_id(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
		List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
        flag = addSRCMmessage(urList, srcmvo);
        //监管员管理部经理
        if (flag) {
        	urquery.setRole_id(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
    		urList = rs.searchUserRoleList(urquery);
            flag = addSRCMmessage(urList, srcmvo);
		}
        //财务部经理
        if (flag) {
        	urquery.setRole_id(RoleConstants.FINANCE_AMALDAR.getCode());
    		urList = rs.searchUserRoleList(urquery);
            flag = addSRCMmessage(urList, srcmvo);
		}
        //会计
        if (flag) {
        	urquery.setRole_id(RoleConstants.FINANCE_ACCOUNTING.getCode());
    		urList = rs.searchUserRoleList(urquery);
            flag = addSRCMmessage(urList, srcmvo);
		}
        
        if (flag) {
        	flag=dao.update(bean);
		}
        if(flag){
			this.setExecuteMessage("提交成功！");
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}
	
	//新增设备申请信息提醒
	private boolean addSRCMmessage(List<UserRoleVO> urList,SupervisorRepairCostMessageVO srcmvo) throws Exception{
		MessageService ms = new MessageService();
		SupervisorRepairCostMessageService srcmService = new SupervisorRepairCostMessageService();
		UserService userService = new UserService();
		UserVO userVO = null;
		boolean flag = false;
		if(urList != null && urList.size()>0){
			for(UserRoleVO ur : urList){
				userVO = userService.get(ur.getUser_id());
				if (userVO != null && userVO.getState() == 1) {
					MessageVO msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVISREPAIRCOST.getCode());
					if (msVo != null) {
						//该用户该类型提醒信息数量增加1
						msVo.setName(String.valueOf(Integer.parseInt(msVo.getName())+1));//数量增加1
						if (msVo.getIsread()==2) {
							msVo.setIsread(1);
						}
						flag = ms.updMessage(msVo);
						if (flag) {
							srcmvo.setUserId(ur.getUser_id());
							srcmvo.setMessageId(msVo.getId());
							flag = srcmService.addRepairCostMessage(srcmvo);
						}else {
							this.setExecuteMessage("提交失败");
						}
					}else {
					    MessageVO mvo = new MessageVO(ur.getUser_id(),1 + "",MsgUrlContant.REPAIRCOST.getName(),
								MsgIsContants.NOREAD.getCode(),MsgIsContants.INFO.getCode(),MessageTypeContant.SUPSERVISREPAIRCOST.getCode(),
								ClientTypeConstants.SUPERVISORY.getName());
					    int mvoId = 0;
						try {
							mvoId = ms.addMessages(mvo);
						} catch (Exception e) {
							e.printStackTrace();
						}
						srcmvo.setUserId(ur.getUser_id());
						srcmvo.setMessageId(mvoId);
						flag = srcmService.addRepairCostMessage(srcmvo);
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * number:		63
	 * author:		sxs
	 * describe:	查询维修项目历史记录
	 * params:
	 * return:
	 */
	public List<RepairCostVO> repairProjectHistoricalRecord(RepairCostQueryVO query,IThumbPageTools tools){
		if(query.getRepair_project()==null || query.getRepair_project().trim().length()==0 || query.getRepair_project().equals("-1")){
			return new ArrayList<RepairCostVO>();
		}
		List<RepairCostVO> list = dao.searchRepairCostAllListByPage(query, tools);
		for (RepairCostVO repairCostVO : list) {
			if(repairCostVO.getRepair_project() != null && repairCostVO.getRepair_project().trim().length() != 0){
				int value = StringUtil.intValue(repairCostVO.getRepair_project(), -1);
				if(value > 0){
					FixedAssetsVO f;
					try {
						f = new FixedAssetsService().loadFixedAssetsById(value);
						if(f==null){
							continue;
						}
						repairCostVO.setRepair_project(f.getAsset_name());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}

	/*
	 * 需求38
	 * 导出设备维修费用申请
	*/
	public List<ExtRepairCostVO> ExtRepairCostLedger(RepairCostQueryVO query) {
		return dao.ExtRepairCostLedger(query);
	}
}

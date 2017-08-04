package com.zd.csms.messagequartz.quartz;

import java.util.Date;
import java.util.List;

import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.contants.MsgUrlContant;
import com.zd.csms.messagequartz.model.InspectionVO;
import com.zd.csms.messagequartz.service.InspectionSupervisorService;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;

/**
 *
 */
public class InspectionQuartz {

	
	
	public void run() {
		inspectionMessage();//巡店检查计划提醒
		inspectionVideo();//提交视频检查计划提醒
		    //未按风控巡检计划执行提醒
	}
	
	private static int[] approvalRole = new int[] { 
		RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
		RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
		RoleConstants.WINDCONRTOL_DATA.getCode(),
		RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
	};
	private static int[] role = new int[] { 
		RoleConstants.VIDEO_COMMISSIONER.getCode(),
		RoleConstants.VIDEO_AMALDAR.getCode(),
		RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
	};
	
	
	/**
	 * 巡店检查计划提醒统计
	 */
	public void inspectionMessage(){
		
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		for (int i = 0; i < approvalRole.length; i++) {
			urquery.setRole_id(approvalRole[i]);
			innerMassage(urquery);
		}
	    
	}
	
	private void innerMassage(UserRoleQueryVO urquery){
		InspectionSupervisorService isService = new InspectionSupervisorService();
	 	MessageService mService = new MessageService();
	 	RoleService rs = new RoleService();
	 	UserService userService = new UserService();
	 	UserVO userVO = null;
		List<UserRoleVO> urList;
		int mvoId = 0;
		try {
			urList = rs.searchUserRoleList(urquery);
			if(urList != null && urList.size()>0){
				for(UserRoleVO ur : urList){
					userVO = userService.get(ur.getUser_id());
					if (userVO != null && userVO.getState() == 1) {
						MessageVO msVo = mService.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.INSPECTIONSU.getCode());
						if (msVo != null) {
							msVo.setName(Integer.parseInt(msVo.getName())+1+"");
							if (msVo.getIsread() == 2) {
								msVo.setIsread(1);
							}
							mService.updMessage(msVo);
							mvoId = msVo.getId();
						}else {
							//用户id - 消息名称  - url(暂时不用) - 是否已读  - 消息类型:1.信息2.预警   - 消息类别 分辨不同的消息类别 - 部门名称
							//将消息添加到t_message表中
							MessageVO mvo = new MessageVO(ur.getUser_id(),String.valueOf(1),MsgUrlContant.INSPECTION.getName(),
									MsgIsContants.NOREAD.getCode(),MsgIsContants.INFO.getCode(),MessageTypeContant.INSPECTIONSU.getCode(),ClientTypeConstants.WINDCONRTOL.getName());
							mvoId = mService.addMessages(mvo);
						}
						InspectionVO inspectionVO = new InspectionVO();
						inspectionVO.setCreateDate(new Date());
						inspectionVO.setUserId(ur.getUser_id());
						inspectionVO.setMessageId(mvoId);
						inspectionVO.setIsread(1);
						inspectionVO.setMessagetype(MessageTypeContant.INSPECTIONSU.getCode());
						inspectionVO.setGreetings("提交巡店检查计划");
						isService.add(inspectionVO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 */
	
	public void inspectionVideo(){
	
	 	
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		for (int i = 0; i < role.length; i++) {
			urquery.setRole_id(role[i]);
			innerVideoMessage(urquery);
		}
		
	}
	
	public void innerVideoMessage(UserRoleQueryVO urquery){
		InspectionSupervisorService isService = new InspectionSupervisorService();
	 	MessageService mService = new MessageService();
	 	UserService userService = new UserService();
	 	UserVO userVO = null;
	 	RoleService rs = new RoleService();
		List<UserRoleVO> urList;
		int mvoId = 0;
		try {
			urList = rs.searchUserRoleList(urquery);
			if(urList != null && urList.size()>0){
				for(UserRoleVO ur : urList){
					userVO = userService.get(ur.getUser_id());
					if (userVO != null && userVO.getState() == 1) {
						MessageVO msVo = mService.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.INSPECTIONVIDEO.getCode());
						if (msVo != null) {
							msVo.setName(Integer.parseInt(msVo.getName())+1+"");
							if (msVo.getIsread() == 2) {
								msVo.setIsread(1);
							}
							mService.updMessage(msVo);
							mvoId = msVo.getId();
						}else {
							//用户id - 消息名称  - url(暂时不用) - 是否已读  - 消息类型:1.信息2.预警   - 消息类别 分辨不同的消息类别 - 部门名称
							//将消息添加到t_message表中
							MessageVO mvo = new MessageVO(ur.getUser_id(),String.valueOf(1),MsgUrlContant.INSPECTIONVIDEO.getName(),
									MsgIsContants.NOREAD.getCode(),MsgIsContants.INFO.getCode(),MessageTypeContant.INSPECTIONVIDEO.getCode(),ClientTypeConstants.WINDCONRTOL.getName());
							mvoId = mService.addMessages(mvo);
						}
						InspectionVO inspectionVO = new InspectionVO();
						inspectionVO.setCreateDate(new Date());
						inspectionVO.setUserId(ur.getUser_id());
						inspectionVO.setMessageId(mvoId);
						inspectionVO.setIsread(1);
						inspectionVO.setMessagetype(MessageTypeContant.INSPECTIONVIDEO.getCode());
						inspectionVO.setGreetings("提交视频检查计划提醒");
						isService.add(inspectionVO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}

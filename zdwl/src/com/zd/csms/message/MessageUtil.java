package com.zd.csms.message;

import java.util.List;

import com.zd.csms.message.model.MessageQueryVO;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;

public class MessageUtil {
	private static MessageService ms = new MessageService();
	private static IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private static UserService userService=new UserService();
	/**
	 * 批量新增
	 * @param list
	 */
	public static void addMsg(List<MessageVO> list){
		try {
			for (MessageVO messageVO : list) {
				messageVO.setId(Util.getID(MessageVO.class));
				messageVO.setIsread(1);
			}
			ms.addBatch(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean addOrUpdateMeg(int userid, String name,String url, int msgtype, int type,int createUserId){
		boolean flag = false;
					try {
						MessageVO msg = ms.loadMsgByUserAndType(userid, type);
						if(msg == null){
							name = 1+"";
							addMsg(userid,name,url,msgtype,type,createUserId);
							flag = true;
						}else{
							if(StringUtil.isNumber(msg.getName())){
								int num = Integer.parseInt(msg.getName())+1;
								msg.setName(num+"");
								msg.setIsread(1);
								flag = ms.updMessage(msg);
							}
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
		return flag;
	}
	
	public static boolean addOrUpdateMeg(int userid, String name,String url, int isRead,int msgType, int type,String deptName){
		boolean flag = false;
					try {
						MessageVO msg = ms.loadMsgByUserAndType(userid, type);
						if(msg == null){
							if(name == null || name.equals("")){
								name = 1+"";
							}
							addMsg(userid, name, url, isRead, msgType, type, deptName);
							flag = true;
						}else{
							if(StringUtil.isNumber(msg.getName())){
								int num = Integer.parseInt(msg.getName())+1;
								msg.setName(num+"");
								msg.setDeptName(deptName);
								msg.setMsgtype(msgType);
								msg.setType(type);
								msg.setIsread(isRead);
								msg.setUrl(url);
								msg.setUserid(userid);
								flag = ms.updMessage(msg);
							}
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
		return flag;
	}
	
	public static boolean sendOrUpdateMeg(String[] roleIds, String name,String url, int msgtype, int type,int createUserId){
		String userid = userRoleDao.findUsingUserIdByRole(roleIds);
		boolean flag = false;
		if(StringUtil.isNotEmpty(userid)){
			String[] userids = userid.split(",");
			for (String uid : userids) {
					try {
						MessageVO msg = ms.loadMsgByUserAndType(Integer.parseInt(uid), type);
						if(msg == null){
							int num = 0;
							if(name == null || name.trim().equals("")){
								num = 1;
							}
							addMsg(Integer.parseInt(uid),num+"",url,msgtype,type,createUserId);
							flag = true;
						}else{
							int num = 0;
							if(StringUtil.isNumber(msg.getName())){
								
								if(StringUtil.isNumber(name)){
									num = (Integer.parseInt(msg.getName())+Integer.parseInt(name));
								}else{
									num = (Integer.parseInt(msg.getName())+1);
								}
								msg.setName(num+"");
								msg.setIsread(1);
							}else{
								msg.setName(name);
								msg.setIsread(1);
							}
							String deptName="";
							try {
								UserVO user=userService.loadUserById(createUserId);
								if(user!=null){
									deptName=ClientTypeConstants.getName(user.getClient_type());
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							msg.setDeptName(deptName);
							flag = ms.updMessage(msg);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		return flag;
	}
	
	public static int getUserId(String roleIds){
		String userid = userRoleDao.findUsingUserIdByRole(roleIds);
		return Integer.valueOf(userid);
	}
	
	/**
	 * 根据用户ID发送信息
	 * @param userid
	 * @param name
	 * @param url
	 * @param msgtype
	 * @param type
	 * @param createUserId 用于获取发送部门
	 */
	public static void addMsg(int userid, String name,String url, int msgtype, int type,int createUserId){
		if(userid >0 && !StringUtil.isEmpty(name) && msgtype > 0){
			try {
				MessageVO mvo = new MessageVO();
				mvo.setUserid(userid);
				mvo.setName(name);
				mvo.setUrl(url);
				mvo.setMsgtype(msgtype);
				mvo.setIsread(1);
				mvo.setType(type);
				String deptName="";
				try {
					UserVO user=userService.loadUserById(createUserId);
					if(user!=null){
						deptName=ClientTypeConstants.getName(user.getClient_type());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				mvo.setDeptName(deptName);
				ms.addMessage(mvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据角色发送信息
	 * @param roleIds
	 * @param name
	 * @param url
	 * @param msgtype
	 * @param type
	 * @param createUserId 用于获取发送部门
	 */
	public static void sendMsg(String[] roleIds, String name,String url, int msgtype, int type,int createUserId){
		String userid = userRoleDao.findUsingUserIdByRole(roleIds);
		if(StringUtil.isNotEmpty(userid)){
			String[] userids = userid.split(",");
			for (String uid : userids) {
				addMsg(Integer.parseInt(uid),name,url,msgtype,type,createUserId);
			}
			
		}
	}
	public static void addMsg(int userid, String name,String url, int isRead,int msgType, int type,String deptName){
		if(userid >0 && !StringUtil.isEmpty(name) && msgType > 0){
			try {
				MessageVO mvo = new MessageVO();
				mvo.setUserid(userid);
				mvo.setName(name);
				mvo.setUrl(url);
				mvo.setIsread(1);
				mvo.setMsgtype(msgType);
				mvo.setType(type);
				mvo.setDeptName(deptName);
				ms.addMessage(mvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static int num(int userid, int msgtype){
		int i = 0;
		MessageService ms = new MessageService();
		MessageQueryVO mq = new MessageQueryVO();
		mq.setUserid(userid);
		mq.setMsgtype(msgtype);
		mq.setIsread(1);
		List<MessageVO> mList = ms.searchMessageList(mq);
		if(mList != null && mList.size()>0){
			i = mList.size();
		}
		return i;
	}
	

}

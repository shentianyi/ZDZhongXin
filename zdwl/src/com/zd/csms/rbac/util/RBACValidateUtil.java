package com.zd.csms.rbac.util;

import java.util.ArrayList;
import java.util.List;

import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;


/**
 * 
* <p>版权所有:北京科码先锋软件技术有限公司</p>
* @作者: JIANGYE
* @日期: 2013-7-3 下午01:48:49
* @描述: [RBACValidateUtil]
* 可分配管理员权限的用户类型和各类管理员可以管理的用户。
* 可扩展用户、角色权限等修改的验证功能
 */
public class RBACValidateUtil{
	/*
	 * 暂时只支持银行管理银行，监管机构管理监管员，平台管理所有用户
	 * 如果要修改银行或监管机构管理员管理用户的范围。管理用户功能需调整为多个查询，每个client_type对应一个查询。
	 */
	
	//平台管理员可管理的用户类型
	public static final RoleConstants[] PLATFORM_ADMIN = RoleConstants.values();
	//监管员管理部可管理的用户类型
	public static final RoleConstants[] SUPERVISORYMANAGEMENT_ADMIN=
	{RoleConstants.SUPERVISORYMANAGEMENT_DATA,RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE,RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT,RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY,RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT,RoleConstants.SUPERVISORYMANAGEMENT_TRAIN,RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE,RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR,RoleConstants.SUPERVISORY};
	//市场部管理员可管理的用户类型
	public static final RoleConstants[] MARKET_ADMIN=
	{RoleConstants.MARKET_COMMISSIONER,RoleConstants.MARKET_DATA,RoleConstants.MARKET_AMALDAR};
	//业务部管理员可管理的用户类型
	public static final RoleConstants[] BUSINESS_ADMIN=
	{RoleConstants.BUSINESS_COMMISSIONER,RoleConstants.BUSINESS_DATA,RoleConstants.BUSINESS_AMALDAR};
	//风控部管理员可管理的用户类型
	public static final RoleConstants[] WINDCONRTOL_ADMIN=
	{RoleConstants.WINDCONRTOL_DATA,RoleConstants.WINDCONRTOL_INTERNAL,RoleConstants.WINDCONRTOL_EXTERNAL,RoleConstants.WINDCONRTOL_AMALDAR};
	//视频部可管理的用户类型
	public static final RoleConstants[] VIDEO_ADMIN=
	{RoleConstants.VIDEO_COMMISSIONER,RoleConstants.VIDEO_DATA,RoleConstants.VIDEO_AMALDAR};
	//财务部可管理的用户类型
	public static final RoleConstants[] FINANCE_ADMIN=
	{RoleConstants.FINANCE_ACCOUNTING,RoleConstants.FINANCE_AMALDAR};
	//运营管理部可管理的用户类型
	public static final RoleConstants[] LOGISTICSFINANCECENTER_ADMIN=
	{RoleConstants.RISKMANAGEMENT_MINISTER,RoleConstants.MARKETMANAGEMENT_MINISTER,RoleConstants.OPERATIONMANAGEMENT_MINISTER,RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO};
	//管理员用户类型集合
	public static final RoleConstants[] ADMIN_CLIENT_TYPE=
	{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR,RoleConstants.MARKET_AMALDAR,RoleConstants.BUSINESS_AMALDAR,RoleConstants.WINDCONRTOL_AMALDAR,RoleConstants.VIDEO_AMALDAR,RoleConstants.FINANCE_AMALDAR,RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO};
	//未分配角色的用户
	public static final String NO_ROLE_USER="id not in (select user_id from t_rbac_user_role)";
	
	/**
	 * 
	* <p>方法名称: getQueryClientType|描述: 获得管理员可管理的用户类型ID集合.</p>
	* @param userVO
	* @return
	* @作者: JIANGYE
	* @日期: 2013-7-2 下午05:43:56
	*
	* @描述: 方法用户获得UserQUeryVO中clienttype的查询条件。
	* 		   当管理员可管理全部用户时，集合为空。当用户类型非管理员类型时，返回false
	 */
	public static boolean queryClientType(UserVO userVO,UserQueryVO query){
		boolean flag=true;
		Integer[] clientTypes=null;
		if(userVO!=null){
			if(userVO.getClient_type()==RoleConstants.ADMINISTRATOR.getCode()){
				clientTypes = getQueryClientType(PLATFORM_ADMIN);
			}else if(userVO.getClient_type()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
				//监管员管理部
				clientTypes = getQueryClientType(SUPERVISORYMANAGEMENT_ADMIN);
				query.setClientId(userVO.getClient_id());
			}else if(userVO.getClient_type()==RoleConstants.MARKET_AMALDAR.getCode()){
				clientTypes = getQueryClientType(MARKET_ADMIN);
				//市场部
				query.setClientId(userVO.getClient_id());
			}else if(userVO.getClient_type()==RoleConstants.BUSINESS_AMALDAR.getCode()){
				//业务部
				clientTypes = getQueryClientType(BUSINESS_ADMIN);
				query.setClientId(userVO.getClient_id());
			}else if(userVO.getClient_type()==RoleConstants.WINDCONRTOL_AMALDAR.getCode()){
				//风控部
				clientTypes = getQueryClientType(WINDCONRTOL_ADMIN);
				query.setClientId(userVO.getClient_id());
			}else if(userVO.getClient_type()==RoleConstants.VIDEO_AMALDAR.getCode()){
				//视频部
				clientTypes = getQueryClientType(VIDEO_ADMIN);
				query.setClientId(userVO.getClient_id());
			}else if(userVO.getClient_type()==RoleConstants.FINANCE_AMALDAR.getCode()){
				//财务部
				clientTypes = getQueryClientType(FINANCE_ADMIN);
				query.setClientId(userVO.getClient_id());
			}else if(userVO.getClient_type()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()){
				//运营管理部部长
				clientTypes = getQueryClientType(LOGISTICSFINANCECENTER_ADMIN);
				query.setClientId(userVO.getClient_id());
			}else{
				flag=false;
			}
		}
		return flag;
	}
	
	/**
	 * 
	* <p>方法名称: clientTypeOptions|描述: 获得管理员可管理的用户类型下拉列表</p>
	* @param userVO
	* @return
	* @作者: JIANGYE
	* @日期: 2013-7-2 下午05:50:14
	*
	* @描述: 简要描述
	 */
	public static List<OptionObject> clientTypeOptions(UserVO userVO){
		List<OptionObject> options =null;
		if(userVO!=null){
			if(userVO.getClient_type()==RoleConstants.ADMINISTRATOR.getCode()){
				options = getClientTypeOptions(PLATFORM_ADMIN);
			}else if(userVO.getClient_type()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
				options = getClientTypeOptions(SUPERVISORYMANAGEMENT_ADMIN);
			}else if(userVO.getClient_type()==RoleConstants.MARKET_AMALDAR.getCode()){
				options = getClientTypeOptions(MARKET_ADMIN);
			}else if(userVO.getClient_type()==RoleConstants.BUSINESS_AMALDAR.getCode()){
				options = getClientTypeOptions(BUSINESS_ADMIN);
			}else if(userVO.getClient_type()==RoleConstants.WINDCONRTOL_AMALDAR.getCode()){
				options = getClientTypeOptions(WINDCONRTOL_ADMIN);
			}else if(userVO.getClient_type()==RoleConstants.VIDEO_AMALDAR.getCode()){
				options = getClientTypeOptions(VIDEO_ADMIN);
			}else if(userVO.getClient_type()==RoleConstants.FINANCE_AMALDAR.getCode()){
				options = getClientTypeOptions(FINANCE_ADMIN);
			}else if(userVO.getClient_type()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()){
				options = getClientTypeOptions(LOGISTICSFINANCECENTER_ADMIN);
			}
		}
		return options;
	}
	
	/**
	 * 
	* <p>方法名称: validateUserUpdate|描述: 检查登陆用户是否可以操作此用户</p>
	* @param updateUser
	* @param loginUser
	* @return
	* @作者: JIANGYE
	* @日期: 2013-7-2 下午05:54:34
	*
	* @描述: 当对用户做增删改查操作时，验证登陆用户是否管理员，是否可以管理操作用户的类型
	 */
	public static boolean validateUserUpdate(UserVO updateUser,UserVO loginUser){
		boolean flag=false;
		//验证用户是否管理员
		for(RoleConstants clientTypeConstant: ADMIN_CLIENT_TYPE){
			if(loginUser.getClient_type()==clientTypeConstant.getCode()){
				flag=true;
				break;
			}
		}
		if(flag){
			flag=false;
			//验证管理员是否可以维护此类用户
			if(loginUser.getClient_type()==RoleConstants.ADMINISTRATOR.getCode()){
				flag = checkClientType(PLATFORM_ADMIN,updateUser.getClient_type());
			}else if(loginUser.getClient_type()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
				flag = checkClientType(SUPERVISORYMANAGEMENT_ADMIN,updateUser.getClient_type());
			}else if(loginUser.getClient_type()==RoleConstants.MARKET_AMALDAR.getCode()){
				flag = checkClientType(MARKET_ADMIN,updateUser.getClient_type());
			}else if(loginUser.getClient_type()==RoleConstants.BUSINESS_AMALDAR.getCode()){
				flag = checkClientType(BUSINESS_ADMIN,updateUser.getClient_type());
			}else if(loginUser.getClient_type()==RoleConstants.WINDCONRTOL_AMALDAR.getCode()){
				flag = checkClientType(WINDCONRTOL_ADMIN,updateUser.getClient_type());
			}else if(loginUser.getClient_type()==RoleConstants.VIDEO_AMALDAR.getCode()){
				flag = checkClientType(VIDEO_ADMIN,updateUser.getClient_type());
			}else if(loginUser.getClient_type()==RoleConstants.FINANCE_AMALDAR.getCode()){
				flag = checkClientType(FINANCE_ADMIN,updateUser.getClient_type());
			}else if(loginUser.getClient_type()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()){
				flag = checkClientType(LOGISTICSFINANCECENTER_ADMIN,updateUser.getClient_type());
			}
		}
		return flag;
	}
	
	private static Integer[] getQueryClientType(RoleConstants[] clientTypeConstants){
		Integer[] clientTypes=new Integer[clientTypeConstants.length];
		if(clientTypeConstants.length==RoleConstants.values().length){
			//当管理的用户类型为所有时，返回空
			return null;
		}
		for(int i=0;i<clientTypeConstants.length;i++){
			clientTypes[i]=clientTypeConstants[i].getCode();
		}
		return clientTypes;
	}
	
	private static List<OptionObject> getClientTypeOptions(RoleConstants[] clientTypeConstants){
		List<OptionObject> options = new ArrayList<OptionObject>();
		OptionObject option;
		for(RoleConstants item : clientTypeConstants){
			option = new OptionObject(item.getCode(),item.getName());
			options.add(option);
		}
		return options;
	}
	
	private static boolean checkClientType(RoleConstants[] clientTypeConstants,int clienttype){
		boolean flag=false;
		for(RoleConstants clientTypeConstant: clientTypeConstants){
			if(clienttype==clientTypeConstant.getCode()){
				flag=true;
				break;
			}
		}
		return flag;
	}
}

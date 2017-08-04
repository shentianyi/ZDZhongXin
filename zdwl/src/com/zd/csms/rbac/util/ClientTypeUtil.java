package com.zd.csms.rbac.util;

import com.zd.csms.rbac.constants.RoleConstants;

public class ClientTypeUtil {

	public static String clienttype(int clienttype){
		
		String name = "";
		if(clienttype==RoleConstants.ADMINISTRATOR.getCode()){
			name = "超级管理员";
		} else if(clienttype==RoleConstants.SUPERVISORYMANAGEMENT_DATA.getCode()){
			name = "监管员管理部数据专员";
		} else if(clienttype==RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){
			name = "监管员管理部综合专员";
		} else if(clienttype==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()){
			name = "监管员管理部招聘专员";
		} else if(clienttype==RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()){
			name = "监管员管理部调配专员";
		} else if(clienttype==RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()){
			name = "监管员管理部薪酬专员";
		} else if(clienttype==RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode()){
			name = "监管员管理部培训专员";
		} else if(clienttype==RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()){
			name = "监管员管理部考勤专员";
		} else if(clienttype==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
			name = "监管员管理部经理";
		} else if(clienttype==RoleConstants.SUPERVISORY.getCode()){
			name = "监管员";
		} else if(clienttype==RoleConstants.MARKET_COMMISSIONER.getCode()){
			name = "市场部专员";
		} else if(clienttype==RoleConstants.MARKET_DATA.getCode()){
			name = "市场部数据专员";
		} else if(clienttype==RoleConstants.MARKET_AMALDAR.getCode()){
			name = "市场部经理";
		} else if(clienttype==RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			name = "业务部专员";
		} else if(clienttype==RoleConstants.BUSINESS_DATA.getCode()){
			name = "业务部数据专员";
		} else if(clienttype==RoleConstants.BUSINESS_AMALDAR.getCode()){
			name = "业务部经理";
		} else if(clienttype==RoleConstants.WINDCONRTOL_DATA.getCode()){
			name = "风控部数据专员";
		} else if(clienttype==RoleConstants.WINDCONRTOL_INTERNAL.getCode()){
			name = "风控部内控专员";
		} else if(clienttype==RoleConstants.WINDCONRTOL_EXTERNAL.getCode()){
			name = "风控部外控专员";
		} else if(clienttype==RoleConstants.WINDCONRTOL_AMALDAR.getCode()){
			name = "风控部经理";
		} else if(clienttype==RoleConstants.VIDEO_COMMISSIONER.getCode()){
			name = "视频部专员";
		} else if(clienttype==RoleConstants.VIDEO_DATA.getCode()){
			name = "视频部数据专员";
		} else if(clienttype==RoleConstants.VIDEO_AMALDAR.getCode()){
			name = "视频部经理";
		} else if(clienttype==RoleConstants.FINANCE_ACCOUNTING.getCode()){
			name = "财务部会计";
		} else if(clienttype==RoleConstants.FINANCE_AMALDAR.getCode()){
			name = "财务部经理";
		} else if(clienttype==RoleConstants.RISKMANAGEMENT_MINISTER.getCode()){
			name = "风险管理部部长";
		} else if(clienttype==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()){
			name = "市场管理部部长";
		} else if(clienttype==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()){
			name = "运营管理部部长";
		} else if(clienttype==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()){
			name = "物流金融中心总监";
		}
		return name;
	}
}

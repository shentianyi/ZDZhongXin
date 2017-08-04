package com.zd.csms.supervisorymanagement.web.excel;

import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.web.jsonaction.SupervisorJsonAction;
import com.zd.csms.supervisorymanagement.contants.AfterHandoverNatureContants;
import com.zd.csms.supervisorymanagement.contants.HandoverNatureContants;
import com.zd.csms.supervisorymanagement.contants.ReceiveNatureContants;
import com.zd.csms.supervisorymanagement.contants.ResignReasonContants;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.util.DateUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class HandoverLogRowMapper implements IImportRowMapper {

	@Override
	public String[] exportRow(Object obj) {
		
		HandoverLogVO handoverLog=(HandoverLogVO) obj;
		if(handoverLog==null){
			handoverLog=new HandoverLogVO();
		}
		//经销商
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		DealerQueryBean dealer=null;
		String headBank="";
		String subBank="";
		String branchBank="";
		try {
			dealer=dealerByIdJsonAction.getDealer(handoverLog.getDealerId());
			if(dealer!=null){
				String bank=dealer.getBankName();
				String[] banks=bank.split("/");
				if(banks!=null && banks.length==3){
					headBank=banks[0];
					subBank=banks[1];
					branchBank=banks[2];
				}else if(banks!=null && banks.length==2){
					headBank=banks[0];
					subBank=banks[1];
				}else if(banks!=null && banks.length==1){
					headBank=banks[0];
				}
			}else{
				dealer=new DealerQueryBean();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//交付人基本信息
		SupervisorJsonAction supervisorJsonAction=new SupervisorJsonAction();
		SupervisorBaseInfoJsonVO delivery=supervisorJsonAction.getSupervisorBaseInfoJsonVOById(handoverLog.getDeliverer());
		if(delivery==null){
			delivery=new SupervisorBaseInfoJsonVO();
		}
		//接收人基本信息
		SupervisorBaseInfoJsonVO receiver=supervisorJsonAction.getSupervisorBaseInfoJsonVOById(handoverLog.getReceiver());
		if(receiver==null){
			receiver=new SupervisorBaseInfoJsonVO();
		}
		UserService userService=new UserService();
		String deploy="";
		UserVO u=userService.get(handoverLog.getDeployId());
		if(u!=null){
			deploy=u.getUserName();
		}
		String resignReason="";
		ResignReasonContants resignReasonContants=ResignReasonContants.getByCode(handoverLog.getResignReason());
		if(resignReasonContants!=null){
			resignReason=resignReasonContants.getName();
		}
		String[] result=new String[]{dealer.getDealerName(),headBank,subBank,branchBank,dealer.getProvince(),dealer.getCity(),
				dealer.getBrand(),dealer.getAddress(),handoverLog.getMerchantDemand(),handoverLog.getBindMerchant(),handoverLog.getBindBank(),
				delivery.getName(),delivery.getGender(),delivery.getStaffNo(),delivery.getEntryTimeStr(),delivery.getSelfTelephone(),
				DateUtil.getStringFormatByDate(handoverLog.getDelivererApplicationDate(),"yyyy-MM-dd"),
				DateUtil.getStringFormatByDate(handoverLog.getExpectedDimissionDate(),"yyyy-MM-dd"),
				HandoverNatureContants.getByCode(handoverLog.getHandoverNature()).getName(),resignReason,
				DateUtil.getStringFormatByDate(handoverLog.getDimissionDate(),"yyyy-MM-dd"),
				receiver.getName(),receiver.getGender(),receiver.getStaffNo(),receiver.getSelfTelephone(),
				ReceiveNatureContants.getByCode(handoverLog.getReceiveNature()).getName(),
				AfterHandoverNatureContants.getByCode(handoverLog.getAfterHandoverNature()).getName(),
				DateUtil.getStringFormatByDate(handoverLog.getMissionDate(),"yyyy-MM-dd"),
				DateUtil.getStringFormatByDate(handoverLog.getHandoverDate(),"yyyy-MM-dd"),
				DateUtil.getStringFormatByDate(handoverLog.getFinishTime(),"yyyy-MM-dd"),
				deploy,handoverLog.getFallowStatus(),handoverLog.getSituationExplain()};
		return result;
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"经销商","合作银行（总）","合作银行（分）","合作银行（支）","省","市","品牌","地址","店方要求","绑定店","绑定行",
				"交付人","性别","员工工号","调入时间","联系方式","申请时间","预计离岗时间","交接性质","辞职原因","离岗时间",
				"接收人","性别","员工工号","联系方式","接收性质","交接后属性","上岗时间","交接时间","完成时间","调配专员","跟进情况","情况说明"};
	}

	@Override
	public Object importRow(String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

package com.zd.csms.messageAndWaring.message.dao.business;
import java.util.List;
import java.util.Set;

import com.zd.csms.messageAndWaring.message.model.business.BusinMessageInfoVO;
import com.zd.csms.messageAndWaring.message.model.business.BusinWaringInfoVO;
import com.zd.csms.messageAndWaring.message.queryBean.business.BusinMessageQueryBean;
import com.zd.csms.messageAndWaring.message.queryBean.business.BusinWaringQueryBean;
import com.zd.csms.messageAndWaring.message.web.form.business.BusinessMessageForm;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IBunisessMessageTypeDAO{
	//-----------------------信息提醒----------------------------
	List<BusinMessageQueryBean> findMessageList(BusinessMessageForm remindForm,IThumbPageTools tools);
	// 银行释放审批提醒
	List<BusinMessageInfoVO> findBankOutStorage(List<Integer> ids, int state);
	//银行移动审批提醒
	List<BusinMessageInfoVO> findBankMoveStorage(List<Integer> ids, int state);
	
	//开票十个工作日未到车提醒
	List<BusinMessageInfoVO> findBillNoCarTen();
	//开票金额
	BusinMessageInfoVO findBillingMoneyForMsg(String draftNum);
	//开票三十天未押满-提醒
	List<BusinMessageInfoVO> findBillThirtyNoFull();
	//汇票到期前7天未清票提醒
	List<BusinMessageInfoVO> findBillNoCarSeven();
	//零库存零汇票提醒 
	List<BusinMessageInfoVO> findZeroSkuDraft();
	
	//连续三天无业务发生提醒
	List<BusinMessageInfoVO> findThreeDayNoBusiness();
	//最后一笔业务提醒
	List<BusinMessageInfoVO> findLastBusiness(Set<String> draftNums);
	
	//移动车辆超过25天未处理提醒
	List<BusinMessageInfoVO> findCarInfoTwentyFive();
	
	//移动车辆超过总库存20%提醒
	List<BusinMessageInfoVO> findMoveCarInfoTwenty();
	
	//异常车辆超过总库存15%提醒
	List<BusinMessageInfoVO> findCarInfoFifteen();
	
	//-----------------------信息预警---------------------------
	List<BusinWaringQueryBean> findWaringList(BusinessMessageForm form,
			IThumbPageTools tools);
	
	//DOTO 银行释放审批一天未出库预警(需求不明确)
	//DOTO 银行移动审批一天未出库预警(需求不明确)
	
	//开票15个工作日未到车预警
	List<BusinWaringInfoVO> findBillNoCarFifteen();
	
	// 开票四十五天未押满-预警
	List<BusinWaringInfoVO> findBillFortyFiveNoFull();
	
	//汇票到期当天未清票预警
	List<BusinWaringInfoVO> findBillNoDraftNowDate();
	
	//DOTO 零库存零汇票延续7天预警
	List<BusinWaringInfoVO> findZeroSkuDraftSeven();

    //连续五天无业务发生预警
	List<BusinWaringInfoVO> findFiveDayNoBusiness();
	
	//移动车辆超过30天未处理预警
	List<BusinWaringInfoVO> findCarInfoThirty();
	
    //异常车辆超过总库存20%预警
	List<BusinWaringInfoVO> findCarInfoTwenty();
	
	//开票金额
	BusinWaringInfoVO findBillingMoneyForWaring(String draftNum);
	

}

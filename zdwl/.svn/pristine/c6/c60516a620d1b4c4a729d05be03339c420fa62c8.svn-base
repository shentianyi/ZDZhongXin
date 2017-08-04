package com.zd.csms.messageAndWaring.message.dao.market;
import java.util.List;
import com.zd.csms.messageAndWaring.message.model.market.MarketMessageInfoVO;
import com.zd.csms.messageAndWaring.message.model.market.MarketWaringInfoVO;
import com.zd.csms.messageAndWaring.message.queryBean.market.MarketMessageQueryBean;
import com.zd.csms.messageAndWaring.message.queryBean.market.MarketWaringQueryBean;
import com.zd.csms.messageAndWaring.message.web.form.market.MarkrtMessageManagerForm;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IMarketMessageTypeDAO  {
   
	// 进驻7天未交监管费提醒
	List<MarketMessageInfoVO> sevenDaysNoFee();
	
	//监管费到期前30天进行提醒
	List<MarketMessageInfoVO> feeExpire30DaysAgo();
	
	// 监管费收取3天后未开票提醒
	List<MarketMessageInfoVO> threeDaysAfterNoBilling();
	
	//项目进驻当天协议未收回提醒
	List<MarketMessageInfoVO> agreementNoRecovered();
	
	// 监管协议到期前30天提醒
    List<MarketMessageInfoVO> agreementExpire30DaysAgo();
    
    //  监管协议签署10天未收回提醒
    List<MarketMessageInfoVO> signing10DayNoRecovered();
    
    // 协议签署超15天未发进店流转单提醒
    List<MarketMessageInfoVO> signing15DayNoTransfer();
    
    //业务进驻流转单进驻前3天未匹配人员提醒
   	List<MarketMessageInfoVO> businessInThreeDayNOSupervise();
    
    //项目进驻流转单
    List<MarketMessageInfoVO>  projectIn(int id);
    
    //业务进驻流转单
    List<MarketMessageInfoVO>  businessIn(int id);
    //项目撤出流转单
    List<MarketMessageInfoVO>  projectOut(int id);
    //项目绑定流转单
    List<MarketMessageInfoVO>  projectBinding(int id);
    
    //项目解绑流转单
    List<MarketMessageInfoVO>  projectUnBinding(int id);
    
    
    //----------------------------信息预警----------------------------------
    // 进驻15天未交监管费预警
	List<MarketWaringInfoVO> fifteenDaysNoFee();
	
	// 监管费到期前10天进行预警
	List<MarketWaringInfoVO> feeExpire10DaysAgo();
	
	//监管费收取后7天未开票预警
	List<MarketWaringInfoVO> sevenDaysAfterNoBilling();
	
	// 监管费到期日未收费预警
	List<MarketWaringInfoVO> expireNoFee();
	
	// 项目进驻15天协议未收回预警
	List<MarketWaringInfoVO> fifteenAgreementNoRecovered();
	
	//  监管协议到期前15天预警
	List<MarketWaringInfoVO> agreement15DaysAgo();
	
	// 协议到期未续签预警
	List<MarketWaringInfoVO> agreementNoSigning();
	
	//业务进驻流转单进驻前1天未匹配人员预警
	List<MarketWaringInfoVO> businessInONEDayNOSupervise();

    List<MarketMessageQueryBean> findMessageList(MarkrtMessageManagerForm form,
			IThumbPageTools tools);
	

	 List<MarketWaringQueryBean> findWaringList(MarkrtMessageManagerForm form,
			IThumbPageTools tools);

	
}

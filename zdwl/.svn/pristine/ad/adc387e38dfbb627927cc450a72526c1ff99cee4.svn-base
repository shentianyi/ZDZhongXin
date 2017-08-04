package com.zd.csms.messageAndWaring.message.quartz;
import com.zd.core.ServiceSupport;
import com.zd.csms.messageAndWaring.message.service.market.MarketMessageTypeService;
/**
 * 市场部-信息提醒和预警定时任务
 *
 */
public class MarketMessageAndWaring  extends ServiceSupport{
	    //--------------------------------提醒-----------------------------------
	
	    // 进驻7天未交监管费提醒
		public void sevenDaysNoFee() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.sevenDaysNoFee();
		}

		// 监管费到期前30天进行提醒
		public void feeExpire30DaysAgo() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.feeExpire30DaysAgo();
		}

		//监管费收取3天后未开票提醒
		public void threeDaysAfterNoBilling() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.threeDaysAfterNoBilling();
		}

		// 项目进驻当天协议未收回提醒
		public void agreementNoRecovered() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.agreementNoRecovered();
		}

		// 监管协议到期前30天提醒
		public void agreementExpire30DaysAgo() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.agreementExpire30DaysAgo();
		}

		// 监管协议签署10天未收回提醒
		public void signing10DayNoRecovered() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.signing10DayNoRecovered();
		}

		// 协议签署超15天未发进店流转单提醒
		public void signing15DayNoTransfer() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.signing15DayNoTransfer();
		}
		
	
		//业务进驻流转单进驻前3天未匹配人员提醒
		public void businessInThreeDayNOSupervise() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.businessInThreeDayNOSupervise();
		}
		
		
		//TODO 1、4、7、10月5日中信银行监管费明细提醒(需求不明确)
		
		//--------------------------------预警-----------------------------------
		// 进驻15天未交监管费预警
		public void fifteenDaysNoFee() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.fifteenDaysNoFee();
		}

		// 监管费到期前10天进行预警
		public void feeExpire10DaysAgo() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.feeExpire10DaysAgo();
		}

		// 监管费收取后10天未开票预警
		public void sevenDaysAfterNoBilling() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.sevenDaysAfterNoBilling();
		}

		// 监管费到期日未收费预警
		public void expireNoFee() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.expireNoFee();
		}

		// 项目进驻15天协议未收回预警
		public void fifteenAgreementNoRecovered() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.fifteenAgreementNoRecovered();
		}
		

		// 监管协议到期前15天预警
		public void agreement15DaysAgo() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.agreement15DaysAgo();
		}

		// 协议到期未续签预警
		public void agreementNoSigning() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.agreementNoSigning();
		}
		
		//业务进驻流转单进驻前1天未匹配人员预警
		public void businessInONEDayNOSupervise() throws Exception {
			MarketMessageTypeService typeService=new MarketMessageTypeService() ;
			typeService.businessInONEDayNOSupervise();
		}
}

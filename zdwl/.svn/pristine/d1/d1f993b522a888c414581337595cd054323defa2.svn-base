package com.zd.csms.messageAndWaring.message.service.market;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.zd.core.ServiceSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messageAndWaring.message.dao.MessageDAOFactory;
import com.zd.csms.messageAndWaring.message.dao.market.IMarketMessageTypeDAO;
import com.zd.csms.messageAndWaring.message.model.market.MarketMessageInfoVO;
import com.zd.csms.messageAndWaring.message.model.market.MarketWaringInfoVO;
import com.zd.csms.messageAndWaring.message.service.MessageReceiveService;
import com.zd.csms.messageAndWaring.message.service.MessageSendService;
/**
 * 市场部-信息提醒和预警触发逻辑
 * @author zhangzhicheng
 *
 */
public class MarketMessageTypeService extends ServiceSupport{
	private IMarketMessageTypeDAO daoType = MessageDAOFactory.getMarketMessageTypeDAO();
	private MessageReceiveService  receiveService=new MessageReceiveService() ;
	private MessageSendService service ;
	
	
	public MarketMessageTypeService() {
		super();
		service=new MessageSendService(MarketMessageInfoVO.class,MarketWaringInfoVO.class) ;
	}

	// 进驻7天未交监管费提醒
	public void sevenDaysNoFee() throws Exception {
		List<MarketMessageInfoVO> infos= daoType.sevenDaysNoFee();
		addMessage(infos, MessageTypeContant.SEVENDAYSNONREGULATORYFEE.getCode());
	}

	// 监管费到期前30天进行提醒
	public void feeExpire30DaysAgo() throws Exception {
		List<MarketMessageInfoVO> infos=daoType.feeExpire30DaysAgo();
		addMessage(infos, MessageTypeContant.SUPERVISIONFEE30DAYSAGO.getCode());
	}

	// 监管费收取3天后未开票提醒
	public void threeDaysAfterNoBilling() throws Exception {
		List<MarketMessageInfoVO> infos=daoType.threeDaysAfterNoBilling();
		addMessage(infos, MessageTypeContant.THREEDAYSAFTERNOBILLING.getCode());
	}

	// 项目进驻当天协议未收回提醒
	public void agreementNoRecovered() throws Exception {
		List<MarketMessageInfoVO> infos=daoType.agreementNoRecovered();
		addMessage(infos, MessageTypeContant.PROJECTINAGREEMENTNOTRECOVERED.getCode());
	}

	// 监管协议到期前30天提醒
	public void agreementExpire30DaysAgo() throws Exception {
		List<MarketMessageInfoVO> infos= daoType.agreementExpire30DaysAgo();
		addMessage(infos, MessageTypeContant.SUPERVISIONAGREEMENT30DAYSAGO.getCode());
	}

	// 监管协议签署10天未收回提醒
	public void signing10DayNoRecovered() throws Exception {
		List<MarketMessageInfoVO> infos=daoType.signing10DayNoRecovered();
		addMessage(infos, MessageTypeContant.AGREEMENTSIGNING10DAYNOTRECOVERED.getCode());
	}

	// 协议签署超15天未发进店流转单提醒
	public void signing15DayNoTransfer() throws Exception {
		List<MarketMessageInfoVO> infos=daoType.signing15DayNoTransfer();
		addMessage(infos, MessageTypeContant.AGREEMENTSIGNING15DAYNOTRANSFER.getCode());
	}
	
	
	 //项目进驻流转单
	public void  projectIn(int id) throws Exception{
		List<MarketMessageInfoVO> infos=daoType.projectIn(id);
		addMessage(infos, MessageTypeContant.PROJECTIN.getCode());
	}
    
    //业务进驻流转单
	public void  businessIn(int id) throws Exception{
		List<MarketMessageInfoVO> infos=daoType.businessIn(id);
		addMessage(infos, MessageTypeContant.BUSINESSIN.getCode());	
	}
	
    //项目撤出流转单
	public void  projectOut(int id) throws Exception{
		List<MarketMessageInfoVO> infos=daoType.projectOut(id);
		addMessage(infos, MessageTypeContant.PROJECTOUT.getCode());
	}
	
    //项目绑定流转单
	public void projectBinding(int id) throws Exception{
		List<MarketMessageInfoVO> infos=daoType.projectBinding(id);
		addMessage(infos, MessageTypeContant.PROJECTBINDING.getCode());
	}
	
    
    //项目解绑流转单
	public void  projectUnBinding(int id) throws Exception{
		List<MarketMessageInfoVO> infos=daoType.signing15DayNoTransfer();
		addMessage(infos, MessageTypeContant.PROJECTUNBUNDLING.getCode());
	}

	//业务进驻流转单进驻前3天未匹配人员提醒
	public void businessInThreeDayNOSupervise(){
		List<MarketMessageInfoVO> infos=daoType.businessInThreeDayNOSupervise();
		addMessage(infos, MessageTypeContant.BUSINESSIN3DAYSAGONOTPERSONNEL.getCode());
	}
	
	//TODO 1、4、7、10月5日中信银行监管费明细提醒(需求不明确)
	
	//--------------------------------预警-----------------------------------
	// 进驻15天未交监管费预警
	public void fifteenDaysNoFee() throws Exception {
		List<MarketWaringInfoVO> infos= daoType.fifteenDaysNoFee();
		addWaring(infos, MessageTypeContant.FIFTEENDAYSNONREGULATORYFEE.getCode());
	}

	// 监管费到期前10天进行预警
	public void feeExpire10DaysAgo() throws Exception {
		List<MarketWaringInfoVO> infos=daoType.feeExpire10DaysAgo();
		addWaring(infos, MessageTypeContant.SUPERVISIONFEE10DAYSAGO.getCode());
	}

	// 监管费收取后7天未开票预警
	public void sevenDaysAfterNoBilling() throws Exception {
		List<MarketWaringInfoVO> infos= daoType.sevenDaysAfterNoBilling();
		addWaring(infos, MessageTypeContant.TENDAYSAFTERNOBILLING.getCode());
	}

	// 监管费到期日未收费预警
	public void expireNoFee() throws Exception {
		List<MarketWaringInfoVO> infos= daoType.expireNoFee();
		addWaring(infos, MessageTypeContant.EXPIRENOFEE.getCode());
	}

	// 项目进驻15天协议未收回预警
	public void fifteenAgreementNoRecovered() throws Exception {
		List<MarketWaringInfoVO> infos=daoType.fifteenAgreementNoRecovered();
		addWaring(infos, MessageTypeContant.FIFTEENDAYSAGREEMENTNOTRECOVERED.getCode());
	}

	// 监管协议到期前15天预警
	public void agreement15DaysAgo() throws Exception {
		List<MarketWaringInfoVO> infos= daoType.agreement15DaysAgo();
		addWaring(infos, MessageTypeContant.AGREEMENT15DAYSAGO.getCode());
	}

	// 协议到期未续签预警
	public void agreementNoSigning() throws Exception {
		List<MarketWaringInfoVO> infos= daoType.agreementNoSigning();
		addWaring(infos, MessageTypeContant.AGREEMENTNOSIGNING.getCode());
	}

	//业务进驻流转单进驻前1天未匹配人员预警
	public void businessInONEDayNOSupervise(){
		List<MarketWaringInfoVO> infos=daoType.businessInONEDayNOSupervise();
		addWaring(infos, MessageTypeContant.BUSINESSINONEDAYSAGONOTPERSONNEL.getCode());
	}
	
	 private void addMessage(List<MarketMessageInfoVO> infos,int msgType){
		 if(CollectionUtils.isEmpty(infos))
		   return ;
		 Set<Integer> dealerIds=new HashSet<Integer>();
		 try {
			 for(MarketMessageInfoVO info: infos){
	        	 dealerIds.add(info.getDealerId()) ;
			 }
			 Map<String,List<Integer>> relationUserIds=receiveService.findMarketIdsByDealerIds(dealerIds);
			 service.setReceiveUsers(receiveService.findMsgReceive(msgType));
			 service.setReceiveUserMaps(receiveService.findMsgReceive(service.getReceiveUsers().get(0),relationUserIds));
			 service.addMessage(infos, msgType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	 private void addWaring(List<MarketWaringInfoVO> infos,int msgType){
		 if(CollectionUtils.isEmpty(infos))
			  return ;
		 Set<Integer> dealerIds=new HashSet<Integer>();
		 try {
			 for(MarketWaringInfoVO info: infos){
	        	 dealerIds.add(info.getDealerId()) ;
			 }
			 Map<String,List<Integer>> relationUserIds=receiveService.findMarketIdsByDealerIds(dealerIds);
			 service.setReceiveUsers(receiveService.findMsgReceive(msgType));
			 service.setReceiveUserMaps(receiveService.findMsgReceive(service.getReceiveUsers().get(0),relationUserIds));
			 service.addWaring(infos, msgType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
}

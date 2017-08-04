package com.zd.csms.messageAndWaring.message.service.business;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.util.CollectionUtils;
import com.zd.core.ServiceSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messageAndWaring.message.dao.MessageDAOFactory;
import com.zd.csms.messageAndWaring.message.dao.business.IBunisessMessageTypeDAO;
import com.zd.csms.messageAndWaring.message.model.business.BusinMessageInfoVO;
import com.zd.csms.messageAndWaring.message.model.business.BusinWaringInfoVO;
import com.zd.csms.messageAndWaring.message.service.MessageReceiveService;
import com.zd.csms.messageAndWaring.message.service.MessageSendService;
/**
 * 业务部-信息提醒和预警触发逻辑
 * @author zhangzhicheng
 *
 */
public class BusinessMessageTypeService extends ServiceSupport{
	private IBunisessMessageTypeDAO daoType = MessageDAOFactory.getBusinessMessageTypeDAO();
	private MessageReceiveService  receiveService=new MessageReceiveService() ;
	private MessageSendService messageSendService ;
	
	//------------------------------信息提醒-------------------------------------
	public BusinessMessageTypeService() {
		super();
		messageSendService = new MessageSendService(BusinMessageInfoVO.class,BusinWaringInfoVO.class);
	}

	/**
	 *  银行释放审批提醒
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void msgBankOutStorage(List<Integer> ids,int state) throws Exception{
		if(!CollectionUtils.isEmpty(ids)){
			List<BusinMessageInfoVO> businMessages=daoType.findBankOutStorage(ids,state);
			int msgType=MessageTypeContant.BANKOUTSTORAGEREMIND.getCode() ;
			if (!CollectionUtils.isEmpty(businMessages)) {
				Set<Integer> dealerIds = getDealerIds(businMessages);
				Map<String,List<Integer>> relationUserIds=null;
				if (!CollectionUtils.isEmpty(dealerIds)) {
					relationUserIds=receiveService.findUserIdsByDealerIds(dealerIds);
					Map<String,List<Integer>> relationSuperviseIds=receiveService.findSuperviseIdsByDealerIds(dealerIds) ;
					relationUserIds=relationUserIdMap(relationUserIds,relationSuperviseIds);
					 messageSendService.setReceiveUsers(receiveService.findMsgReceive(msgType));
					 messageSendService.setReceiveUserMaps(receiveService.findMsgReceive(messageSendService.getReceiveUsers().get(0),relationUserIds));
					 messageSendService.addMessage(businMessages, msgType);
				}
			 }
		}
		
	}
	
	/**
	 *  银行移动审批提醒
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void msgBankMoveStorage(List<Integer> ids ,int state) throws Exception{
		if(!CollectionUtils.isEmpty(ids)){
			List<BusinMessageInfoVO> businMessages =daoType.findBankMoveStorage(ids , state) ;
			int msgType=MessageTypeContant.BANKMOVESTORAGEREMIND.getCode() ;
			if (!CollectionUtils.isEmpty(businMessages)) {
				Set<Integer> dealerIds = getDealerIds(businMessages);
				Map<String,List<Integer>> relationUserIds=null;
				if (!CollectionUtils.isEmpty(dealerIds)) {
					relationUserIds=receiveService.findUserIdsByDealerIds(dealerIds);
					Map<String,List<Integer>> relationSuperviseIds=receiveService.findSuperviseIdsByDealerIds(dealerIds) ;
					relationUserIds=relationUserIdMap(relationUserIds,relationSuperviseIds);
					 messageSendService.setReceiveUsers(receiveService.findMsgReceive(msgType));
					 messageSendService.setReceiveUserMaps(receiveService.findMsgReceive(messageSendService.getReceiveUsers().get(0),relationUserIds));
					 messageSendService.addMessage(businMessages, msgType);
				}
			 }
		}
	}
	
	/**
	 * 开票十个工作日未到车提醒
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void msgBillNoCar() throws Exception{
		List<BusinMessageInfoVO> list = daoType.findBillNoCarTen();
		if (!CollectionUtils.isEmpty(list)) {
			List<BusinMessageInfoVO> businMessages =(List<BusinMessageInfoVO>)noMortgagecarMoneyForTen(list);
			send(MessageTypeContant.MSGBILLNOCARREMIND.getCode(),businMessages);
		}
	}
	
	
	/**
	 * 开票三十天未押满-提醒
	 * @throws Exception 
	 */  
	@SuppressWarnings("unchecked")
	public void msgBillNoFull() throws Exception{
		List<BusinMessageInfoVO> list = daoType.findBillThirtyNoFull();
		if (!CollectionUtils.isEmpty(list)){
			List<BusinMessageInfoVO> businMessages =(List<BusinMessageInfoVO>)noMortgageCarMoneyForThirty(list);
			send(MessageTypeContant.MSGBILLNOFULLREMIND.getCode(),businMessages);
		}	
	}
	
	/**
	 * 汇票到期前7天未清票提醒
	 * @throws Exception 
	 */
	public void msgBillNoDraft() throws Exception{
		List<BusinMessageInfoVO> businMessages = daoType.findBillNoCarSeven();
		if (!CollectionUtils.isEmpty(businMessages)) {
			noMortgageCarMoneyForSeven(businMessages);
			send(MessageTypeContant.MSGBILLNODRAFTREMIND.getCode(),businMessages);
		 }
	}
	
	
	/**
	 * 零库存零汇票提醒 
	 * @throws Exception 
	 */
	public void msgZeroSkuDraft() throws Exception{
		List<BusinMessageInfoVO> businMessages = daoType.findZeroSkuDraft();
		if (!CollectionUtils.isEmpty(businMessages)) {
			if(!CollectionUtils.isEmpty(businMessages)){
				send2(MessageTypeContant.MSGZEROSKUZERODRAFTREMIND.getCode(),businMessages);
			}
		}
	}
	
	
	/**
	 *  最后一笔业务提醒
	 * @throws Exception
	 */
	public void msgLastBusiness(Set<String> draftNums) throws Exception{
		if(!CollectionUtils.isEmpty(draftNums)){
			List<BusinMessageInfoVO> businMessages=daoType.findLastBusiness(draftNums);
			if (!CollectionUtils.isEmpty(businMessages)) {
					send2(MessageTypeContant.MSGLASTBUSINESSREMIND.getCode(),businMessages);
			 }
		}
		
	}
	
	
	/**
	 * 连续三天无业务发生提醒
	 * @throws Exception 
	 */
	public void msgThreeDayNoBusiness() throws Exception{
		List<BusinMessageInfoVO> businMessages = daoType.findThreeDayNoBusiness();
		if (!CollectionUtils.isEmpty(businMessages)) {
			send(MessageTypeContant.MSGTHREEDAYNOBUSINESSREMIND.getCode(),businMessages);
		}
	}
	
	
	/**
	 * 移动车辆超过25天未处理提醒
	 * @throws Exception 
	 */
	public void msgCarInfoTwentyFive() throws Exception{
		List<BusinMessageInfoVO> businMessages = daoType.findCarInfoTwentyFive();
		if (!CollectionUtils.isEmpty(businMessages)) {
			send(MessageTypeContant.MSGMOVEEXCEEDTWENTYFIVENOPROCESSREMIND.getCode(),businMessages);
		}
	}
	
	
	/**
	 * 移动车辆超过总库存20%提醒
	 * @throws Exception 
	 */
	public void msgCarInfoTwenty() throws Exception{
		List<BusinMessageInfoVO> businMessages = daoType.findMoveCarInfoTwenty();
		if (!CollectionUtils.isEmpty(businMessages)){
			send(MessageTypeContant.MSGMOVECARTEXCEEDKURTWENTYEMIND.getCode(),businMessages);
		}
	}
	
	
	/**
	 * 异常车辆超过总库存15%提醒
	 * @throws Exception 
	 */
	public void msgCarInfoFifteen() throws Exception{
		List<BusinMessageInfoVO> businMessages = daoType.findCarInfoFifteen();
		if (!CollectionUtils.isEmpty(businMessages)) {
			if (!CollectionUtils.isEmpty(businMessages)){
				send(MessageTypeContant.MSGEXCEPTIONCAREXCEEDSKUFIFTEENREMIND.getCode(),businMessages);
			}
		}
	}
	
	//------------------------------信息预警------------------------------
	
	//DOTO 银行释放审批一天未出库预警(需求不明确)
	//DOTO 银行移动审批一天未出库预警(需求不明确)
	
	/**
	 * 开票15个工作日未到车预警
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void billNoCarFifteen() throws Exception{
		//当前日期大于开票日15天及以上，如果汇票下不存在车辆，触发提醒
		List<BusinWaringInfoVO> list = daoType.findBillNoCarFifteen();
		if (!CollectionUtils.isEmpty(list)) {
			List<BusinWaringInfoVO> businWarings =(List<BusinWaringInfoVO>)noMortgagecarMoneyForTen(list);
			send(MessageTypeContant.BILLNOCARFIFTEENWARING.getCode(),businWarings);
		}
		    
	}
	
	
	/**
	 * 开票四十五天未押满-预警
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void billFortyFiveNoFull() throws Exception{
		//当前日期大于开票日45天及以上，押车金额不足开票金额则预警
		List<BusinWaringInfoVO> list = daoType.findBillFortyFiveNoFull();
		if (!CollectionUtils.isEmpty(list)){
			List<BusinWaringInfoVO> businWarings =(List<BusinWaringInfoVO>)noMortgageCarMoneyForThirty(list);
			send(MessageTypeContant.BILLFORTYFIVENOFULLWARING.getCode(),businWarings);
		}
		
	}
	
	
	/**
	 * 汇票到期当天未清票预警
	 * @throws Exception 
	 */
	public void billNoDraftNowDate() throws Exception{
		//查询汇票到期日大于当前日期的且状态为未清票的汇票信息 
		List<BusinWaringInfoVO> businWarings = daoType.findBillNoDraftNowDate();
		if (!CollectionUtils.isEmpty(businWarings)) {
			noMortgageCarMoneyForSeven(businWarings);
			send(MessageTypeContant.BILLNODRAFTNOWDATEWARING.getCode(),businWarings);
		}
	
	}
	
	
	/**
	 * 零库存零汇票延续7天预警 
	 * @throws Exception 
	 */
	public void zeroSkuDraft() throws Exception{
		//查询当前日期减去进驻日期大于7天且不存在未清票的汇票的经销商
		List<BusinWaringInfoVO> businWarings = daoType.findZeroSkuDraftSeven();
		if (!CollectionUtils.isEmpty(businWarings)) {
			if(!CollectionUtils.isEmpty(businWarings)){
				send2(MessageTypeContant.ZEROSKUZERODRAFTWARING.getCode(),businWarings);
			}
		}
	}
	
	
	/**
	 * 连续五天无业务发生预警
	 * @throws Exception 
	 */
	public void fiveDayNoBusiness() throws Exception{
		//查询经销商最后一辆出库车辆的出库时间小于当前日期5天的
		List<BusinWaringInfoVO> businWarings = daoType.findFiveDayNoBusiness();
		if (!CollectionUtils.isEmpty(businWarings)) {
			send(MessageTypeContant.FIVEDAYNOBUSINESSWARING.getCode(),businWarings);
		}
	}
	
	/**
	 * 移动车辆超过30天未处理预警
	 * @throws Exception 
	 */
	public void carInfoThirty() throws Exception{
		//查询车辆移动时间小于当前时间30天以上的车辆信息
		List<BusinWaringInfoVO> businWarings = daoType.findCarInfoThirty();
		if (!CollectionUtils.isEmpty(businWarings)){
			send(MessageTypeContant.MOVEEXCEEDTHIRTYNOPROCESSWARING.getCode(),businWarings);
		}
	}
	
	/**
	 * 异常车辆超过总库存20%预警
	 * @throws Exception 
	 */
	public void carInfoTwenty() throws Exception{
		//查询当前异常状态（包含私自移动、私自售卖、销售未还款）的车辆占总在库车辆的20%以上的经销商
		List<BusinWaringInfoVO> businWarings = daoType.findCarInfoTwenty();
		if (!CollectionUtils.isEmpty(businWarings)) {
			send(MessageTypeContant.EXCEPTIONCAREXCEEDSKUTWENTYWARING.getCode(),businWarings);
		}
	}
	
	// 未押车金额
	private List<?> noMortgagecarMoneyForTen(List<?> list) {
		String draftNum=null ;
		if(list.get(0).getClass()==BusinMessageInfoVO.class){
			List<BusinMessageInfoVO> businMessages = new ArrayList<BusinMessageInfoVO>();
			for (Object businMessageInfoVO : list) {
				draftNum=((BusinMessageInfoVO)businMessageInfoVO).getDraft_num() ;
				BusinMessageInfoVO vo = daoType.findBillingMoneyForMsg(draftNum);
				if (vo != null) {
					vo.noMortgageCarMoneyForTen();
					businMessages.add(vo);
				}

			}
			return  businMessages;
		}else{
			List<BusinWaringInfoVO> businWarings = new ArrayList<BusinWaringInfoVO>();
			for (Object businWaringInfoVO : list) {
				draftNum=((BusinWaringInfoVO)businWaringInfoVO).getDraft_num() ;
				BusinWaringInfoVO vo = daoType.findBillingMoneyForWaring(draftNum);
				if (vo != null) {
					vo.noMortgageCarMoneyForTen();
					businWarings.add(vo);
				}

			}
			return businWarings;
		}
		
	}
	

	// 未押车金额
	private List<?> noMortgageCarMoneyForThirty(List<?> list) {
		String draftNum=null;
		if(list.get(0).getClass()==BusinMessageInfoVO.class){
			List<BusinMessageInfoVO> businMessages = new ArrayList<BusinMessageInfoVO>();
			for (Object businMessageInfoVO : list) {
				draftNum=((BusinMessageInfoVO)businMessageInfoVO).getDraft_num() ;
				BusinMessageInfoVO vo = daoType.findBillingMoneyForMsg(draftNum);
				if (vo!=null) {
				  if(vo.noMortgageCarMoneyForThirty()){
					  businMessages.add(vo);
				  }
				}
			}
			return  businMessages;
		}else{
			List<BusinWaringInfoVO> businWarings = new ArrayList<BusinWaringInfoVO>();
			for (Object businWaringInfoVO : list) {
				draftNum=((BusinWaringInfoVO)businWaringInfoVO).getDraft_num() ;
				BusinWaringInfoVO vo = daoType.findBillingMoneyForWaring(draftNum);
				if (vo!=null) {
				   if(vo.noMortgageCarMoneyForThirty()){
					   businWarings.add(vo);
					}
				}

			}
			return businWarings;
		}

	}
	
	 // 未押车金额
	  private void noMortgageCarMoneyForSeven(List<?> list) {
			if(list.get(0).getClass()==BusinMessageInfoVO.class){
				for (Object businMessageInfoVO : list) {
					((BusinMessageInfoVO)businMessageInfoVO).noMortgageCarMoneyForSeven(); 
					
				}
			}else{
				for (Object businWaringInfoVO : list) {
					((BusinWaringInfoVO)businWaringInfoVO).noMortgageCarMoneyForSeven();

				}
			}

		}
		
	
	 private Set<Integer> getDealerIds(List<?> objs) throws Exception{
		if (CollectionUtils.isEmpty(objs))
			return null;
		Set<Integer> dealerIds = new HashSet<Integer>();
		if (objs.get(0).getClass() == BusinMessageInfoVO.class) {
			for (Object obj : objs) {
				if(((BusinMessageInfoVO) obj).getDealerId()>0){
					dealerIds.add(((BusinMessageInfoVO) obj).getDealerId());
				}
				
			}
		} else {
			for (Object obj : objs) {
				if(((BusinWaringInfoVO) obj).getDealerId()>0){
					dealerIds.add(((BusinWaringInfoVO) obj).getDealerId());
				}
				
			}
		}
		return dealerIds;
	}
	 
	

	@SuppressWarnings("unchecked")
	private void send(int msgType ,List<?> businMessages) throws Exception{
		Set<Integer> dealerIds = getDealerIds(businMessages);
		Map<String,List<Integer>> relationUserIds=null;
		if (!CollectionUtils.isEmpty(dealerIds)) {
			relationUserIds = receiveService.findUserIdsByDealerIds(dealerIds);
			Map<String,List<Integer>> relationSuperviseIds=receiveService.findSuperviseIdsByDealerIds(dealerIds);
			Map<String,List<Integer>> relationBankMangers=receiveService.findBankMangersByDealerIds(dealerIds);
			relationUserIds=relationUserIdMap(relationUserIds,relationSuperviseIds,relationBankMangers);
		}
        
		if(!CollectionUtils.isEmpty(relationUserIds)){
			if (businMessages.get(0).getClass() == BusinMessageInfoVO.class) {
				 messageSendService.setReceiveUsers(receiveService.findMsgReceive(msgType));
				 messageSendService.setReceiveUserMaps(receiveService.findMsgReceive(messageSendService.getReceiveUsers().get(0),relationUserIds));
				 messageSendService.addMessage((List<BusinMessageInfoVO>)businMessages, msgType);
			} else {
				 messageSendService.setReceiveUsers(receiveService.findMsgReceive(msgType));
				 messageSendService.setReceiveUserMaps(receiveService.findMsgReceive(messageSendService.getReceiveUsers().get(0),relationUserIds));
				 messageSendService.addWaring((List<BusinWaringInfoVO>)businMessages, msgType);
			}
		}
		
	 }
	
	
	@SuppressWarnings("unchecked")
	private void send2(int msgType ,List<?> businMessages) throws Exception{
		Set<Integer> dealerIds = getDealerIds(businMessages);
		Map<String,List<Integer>> relationUserIds=null;
		if (!CollectionUtils.isEmpty(dealerIds)) {
			relationUserIds=receiveService.findUserIdsByDealerIds(dealerIds);
			Map<String,List<Integer>> relationSuperviseIds=receiveService.findSuperviseIdsByDealerIds(dealerIds) ;
			Map<String,List<Integer>> relationMarketIds=receiveService.findMarketIdsByDealerIds(dealerIds) ;
			Map<String,List<Integer>> relationBankMangers=receiveService.findBankMangersByDealerIds(dealerIds);
			relationUserIds=relationUserIdMap(relationUserIds,relationSuperviseIds,relationMarketIds,relationBankMangers);
		}
        
		if(!CollectionUtils.isEmpty(relationUserIds)){
			if (businMessages.get(0).getClass() == BusinMessageInfoVO.class) {
				 messageSendService.setReceiveUsers(receiveService.findMsgReceive(msgType));
				 messageSendService.setReceiveUserMaps(receiveService.findMsgReceive(messageSendService.getReceiveUsers().get(0),relationUserIds));
				 messageSendService.addMessage((List<BusinMessageInfoVO>)businMessages, msgType);
			} else {
				 messageSendService.setReceiveUsers(receiveService.findMsgReceive(msgType));
				 messageSendService.setReceiveUserMaps(receiveService.findMsgReceive(messageSendService.getReceiveUsers().get(0),relationUserIds));
				 messageSendService.addWaring((List<BusinWaringInfoVO>)businMessages, msgType);
			}
		}
		
	 }
	
	private Map<String,List<Integer>> relationUserIdMap(Map<String,List<Integer>>... relationUserIds){
		if(relationUserIds==null){
			return null ;
		}
		
		for(int i=1;i<relationUserIds.length;i++){
			if(relationUserIds[i]!=null){
				for(String key:relationUserIds[i].keySet()){
					List<Integer> ids1=relationUserIds[0].get(key);
					List<Integer> ids2=relationUserIds[i].get(key);
					ids1.addAll(ids2);
					relationUserIds[0].put(key, ids1);
				}
			}
		}
		return relationUserIds[0] ;
	}
}

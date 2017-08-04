package com.zd.csms.messageAndWaring.message.dao;
import java.util.List;
import java.util.Map;
import com.zd.core.IDAO;
import com.zd.csms.messageAndWaring.message.queryBean.MessageReceiveQueryBean;
public interface IMessageReceiveDAO extends IDAO {

	// 获取消息提醒和预警的接收用户
	List<MessageReceiveQueryBean> findMsgReceive(int msgType);
	
	Map<String, String> findMessageUser(int msgType);
	
	Map<String, String> findWaringUser(int msgType);
	
	//业务专员
	Map<String,List<Integer>> findUserIdsByDealerIds(String dealerIdStr);
	//监管员
	Map<String,List<Integer>> findSuperviseIdsByDealerIds(String dealerIdStr);
  
	//市场专员
	Map<String,List<Integer>> findMarketIdsByDealerIds(String dealerIdStr);
    //金融机构负责的客户经理
	Map<String, List<Integer>> findBankMangersByDealerIds(String string);
}

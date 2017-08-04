package com.zd.csms.messageAndWaring.message.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import com.zd.core.ServiceSupport;
import com.zd.csms.messageAndWaring.message.dao.IMessageReceiveDAO;
import com.zd.csms.messageAndWaring.message.dao.MessageDAOFactory;
import com.zd.csms.messageAndWaring.message.queryBean.MessageReceiveQueryBean;

/**
 * 信息提醒和预警接收人(负责)
 * @author zhangzhicheng
 *
 */
public class MessageReceiveService extends ServiceSupport {
    private IMessageReceiveDAO receiveDao=MessageDAOFactory.getMessageReceiveDAO();
   
	/**
	 * 业务专员
	 * @param dealerId
	 * @return
	 */
	public  Map<String,List<Integer>> findUserIdsByDealerIds(Set<Integer> dealerIds){
		if(dealerIds == null || dealerIds.size()<=0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (Integer id : dealerIds) {
			sb.append(id+",");
		}
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		return  receiveDao.findUserIdsByDealerIds(sb.toString());
	}
	
	
	/**
	 * 监管员
	 * @param dealerId
	 * @return
	 */
	public  Map<String,List<Integer>> findSuperviseIdsByDealerIds(Set<Integer> dealerIds){
		if(dealerIds == null || dealerIds.size()<=0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (Integer id : dealerIds) {
			sb.append(id+",");
		}
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		return receiveDao.findSuperviseIdsByDealerIds(sb.toString());
	}
	
	
	/**
	 * 市场专员
	 * @param dealerIds
	 * @return
	 */
	public  Map<String,List<Integer>> findMarketIdsByDealerIds(Set<Integer> dealerIds){
		if(dealerIds == null || dealerIds.size()<=0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (Integer id : dealerIds) {
			sb.append(id+",");
		}
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		return receiveDao.findMarketIdsByDealerIds(sb.toString());
	}
	
	
	
	/**
	 * 金融机构负责的客户经理
	 * @param dealerIds
	 * @return
	 */
	public  Map<String,List<Integer>> findBankMangersByDealerIds(Set<Integer> dealerIds){
		if(dealerIds == null || dealerIds.size()<=0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (Integer id : dealerIds) {
			sb.append(id+",");
		}
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		return receiveDao.findBankMangersByDealerIds(sb.toString());
	}
	
	//获取接收人
	public List<MessageReceiveQueryBean> findMsgReceive(int msgType) {
		List<MessageReceiveQueryBean> receives=receiveDao.findMsgReceive(msgType);
		if(CollectionUtils.isEmpty(receives))
			return null;
		
		/*Map<String,String> msgUsers ;
		if(type==MsgAttributesContants.INFO.getCode()){
			msgUsers=receiveDao.findMessageUser(msgType);
		}else{
			msgUsers=receiveDao.findWaringUser(msgType);
		}
		
		if(!CollectionUtils.isEmpty(msgUsers)){
			for(MessageReceiveQueryBean receive :receives){
				String signIdStr=msgUsers.get(receive.getUserId()+"") ;
				if(StringUtil.isNotEmpty(signIdStr)){
					receive.setSignIdStr(signIdStr);
				}
			}
		}*/
		return receives ;
	  }
	
	public Map<String,List<MessageReceiveQueryBean>> findMsgReceive(MessageReceiveQueryBean receiveBean,Map<String,List<Integer>> relationUserIds) {
		if(receiveBean==null || CollectionUtils.isEmpty(relationUserIds))
			return null;
		Map<String,List<MessageReceiveQueryBean>> receiveMap=new HashMap<String, List<MessageReceiveQueryBean>>() ;
		List<MessageReceiveQueryBean> receiveList=new ArrayList<MessageReceiveQueryBean>();
		MessageReceiveQueryBean addreceive ;
		for(String key: relationUserIds.keySet()){
			for(Integer userId:relationUserIds.get(key)){
				addreceive=new MessageReceiveQueryBean();
				BeanUtils.copyProperties(receiveBean, addreceive);
				addreceive.setUserId(userId);
				receiveList.add(addreceive);
			}
			
			receiveMap.put(key,receiveList) ;
		}
		
		return receiveMap ;
	  }
	

}

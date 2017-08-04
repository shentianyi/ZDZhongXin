package com.zd.csms.messageAndWaring.message.dao.oracle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.messageAndWaring.message.contant.MsgAttributesContants;
import com.zd.csms.messageAndWaring.message.dao.IMessageReceiveDAO;
import com.zd.csms.messageAndWaring.message.model.MessageInfoUserVO;
import com.zd.csms.messageAndWaring.message.model.WaringInfoUserVO;
import com.zd.csms.messageAndWaring.message.queryBean.MessageReceiveQueryBean;
import com.zd.csms.rbac.constants.ClientTypeConstants;
public  class MessageReceiveDAOImpl extends DAOSupport implements
		IMessageReceiveDAO {

	public MessageReceiveDAOImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	
	/**
	 * 根据经销商id获取业务专员id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,List<Integer>> findUserIdsByDealerIds(String dealerIdStr) {
		String sql="select md.id , tyw.userid  as  createuserid  from market_dealer md  "
				+ " inner join market_dealer_supervisor mds on md.id = mds.dealerid "
				+ " inner join t_yw_bank tyw on tyw.bankid = mds.bankid  ";
		
		if(dealerIdStr.contains(",")){
			 sql+= " where md.id IN ("+dealerIdStr+" )";
		 }else{
			 sql+= " where md.id ="+Integer.valueOf(dealerIdStr) ;
		 }
		List<DealerVO> dealers=this.getJdbcTemplate().query(sql, new Object[]{}, new BeanPropertyRowMapper(DealerVO.class));
		Map<String,List<Integer>> maps=new HashMap<String,List<Integer>>() ;
		for(DealerVO  vo: dealers){
			if(maps.get(vo.getId()+"")==null){
				List<Integer> ids=new ArrayList<Integer>();
				ids.add(vo.getCreateUserid());
				maps.put(vo.getId()+"", ids);
			}
			List<Integer> addIds=maps.get(vo.getId()+"") ;
			addIds.add(vo.getCreateUserid());
			maps.put(vo.getId()+"", addIds);
		}
		return maps ;
	}
	
	/**
	 * 根据经销商id获取监管员id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,List<Integer>> findSuperviseIdsByDealerIds(String dealerIdStr) {
		String sql="select  md.id , ru.id  as createuserid  from market_dealer md  "
				+ " inner join market_dealer_supervisor mds on md.id = mds.dealerid  "
				+ " inner join t_rbac_user ru on ru.client_id = mds.repositoryid  "
				+ " where  ru.client_type = " + ClientTypeConstants.SUPERVISORY.getCode()
		        +" and  " ;
		
		if(dealerIdStr.contains(",")){
			 sql+= " md.id  IN ("+dealerIdStr+" ) ";
		 }else{
			 sql+= "md.id ="+Integer.valueOf(dealerIdStr) ;
		 }
		List<DealerVO> dealers=this.getJdbcTemplate().query(sql, new Object[]{}, new BeanPropertyRowMapper(DealerVO.class));
		Map<String,List<Integer>> maps=new HashMap<String,List<Integer>>() ;
		for(DealerVO  vo: dealers){
			if(maps.get(vo.getId()+"")==null){
				List<Integer> ids=new ArrayList<Integer>();
				ids.add(vo.getCreateUserid());
				maps.put(vo.getId()+"", ids);
			}
			List<Integer> addIds=maps.get(vo.getId()+"") ;
			addIds.add(vo.getCreateUserid());
			maps.put(vo.getId()+"", addIds);
		}
		return maps ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,List<Integer>> findMarketIdsByDealerIds(String dealerIds) {
		String sql="select md.id , md.createuserid  from market_dealer md  ";
		if(dealerIds.contains(",")){
			 sql+= " where md.id in ("+dealerIds+" ) ";
		 }else{
			 sql+= " where md.id="+Integer.valueOf(dealerIds) ;
		 }
		List<DealerVO> dealers=this.getJdbcTemplate().query(sql, new Object[]{}, new BeanPropertyRowMapper(DealerVO.class));
		Map<String,List<Integer>> maps=new HashMap<String,List<Integer>>() ;
		for(DealerVO  vo: dealers){
			if(maps.get(vo.getId()+"")==null){
				List<Integer> ids=new ArrayList<Integer>();
				ids.add(vo.getCreateUserid());
				maps.put(vo.getId()+"", ids);
			}
			List<Integer> addIds=maps.get(vo.getId()+"") ;
			addIds.add(vo.getCreateUserid());
			maps.put(vo.getId()+"", addIds);
		}
		return maps ;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<Integer>> findBankMangersByDealerIds(String dealerIds) {
		String sql="select md.id , md.bankManagerId  from market_dealer md  ";
		if(dealerIds.contains(",")){
			 sql+= " where md.id in ("+dealerIds+" ) ";
		 }else{
			 sql+= " where md.id="+Integer.valueOf(dealerIds) ;
		 }
		List<DealerVO> dealers=this.getJdbcTemplate().query(sql, new Object[]{}, new BeanPropertyRowMapper(DealerVO.class));
		Map<String,List<Integer>> maps=new HashMap<String,List<Integer>>() ;
		for(DealerVO  vo: dealers){
			if(maps.get(vo.getId()+"")==null){
				List<Integer> ids=new ArrayList<Integer>();
				ids.add(vo.getBankManagerId());
				maps.put(vo.getId()+"", ids);
			}
			List<Integer> addIds=maps.get(vo.getId()+"") ;
			addIds.add(vo.getBankManagerId());
			maps.put(vo.getId()+"", addIds);
		}
		return maps ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageReceiveQueryBean> findMsgReceive(int msgType){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MSGR.* , U.ID  AS USERID  FROM  T_MSG_RECEIVE  MSGR ");
		sql.append(" INNER JOIN  T_RBAC_USER_ROLE  USR ON USR.ROLE_ID=MSGR.ROLEID  ");
		sql.append(" INNER JOIN  T_RBAC_USER U ON U.ID=USR.USER_ID ");
		sql.append(" WHERE  U.STATE=?  AND  MSGR.MSGTYPE=?  AND MSGR.RECEIVETYPE="+MsgAttributesContants.RECEIVEROLE.getCode() );
		sql.append(" UNION ALL  ");
		sql.append(" SELECT MSGR.* , U.ID  AS USERID  FROM  T_MSG_RECEIVE  MSGR  ");
		sql.append(" INNER JOIN  T_RBAC_USER U ON U.CLIENT_TYPE=MSGR.DEPID  ");
		sql.append(" WHERE  U.STATE=?  AND  MSGR.MSGTYPE=?  AND MSGR.RECEIVETYPE="+MsgAttributesContants.RECEIVEDEP.getCode() );
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {StateConstants.USING.getCode(), msgType,StateConstants.USING.getCode(), msgType },
				new BeanPropertyRowMapper(MessageReceiveQueryBean.class));
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> findMessageUser(int msgType) {
		String sql = " SELECT  USERID ,MSGTYPE, SIGNID  FROM  T_MSG_INFO_USER  WHERE  MSGTYPE=? "  ;
		List<MessageInfoUserVO> msgUsers= getJdbcTemplate().query(sql,new Object[] {msgType },new BeanPropertyRowMapper(MessageInfoUserVO.class));
		Map<String, String> map=new HashMap<String, String>() ;
		for(MessageInfoUserVO  msgU: msgUsers){
			String key=msgU.getUserId()+"" ;
			if(msgU.getSignId()!=null){
        		String value=msgU.getSignId()+",";
        		String oldValue=map.get(key);
    			map.put(key,oldValue+value);
        	}
		}
	    return map ;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> findWaringUser(int msgType) {
		String sql = " SELECT  USERID , MSGTYPE,SIGNID  FROM  T_WARING_INFO_USER  WHERE   MSGTYPE=? AND SHIELD=? "  ;
		List<WaringInfoUserVO> warings=  getJdbcTemplate().query(sql,new Object[] {msgType,MsgAttributesContants.SHIELD.getCode()},new BeanPropertyRowMapper(WaringInfoUserVO.class));
		Map<String, String> map=new HashMap<String, String>() ;
        for(WaringInfoUserVO  waringU: warings){
        	String key=waringU.getUserId()+"" ;
        	if(waringU.getSignId()!=null){
        		String value=waringU.getSignId()+",";
        		String oldValue=map.get(key);
    			map.put(key,oldValue+value);
        	}
		}
		return map ;
	}



}

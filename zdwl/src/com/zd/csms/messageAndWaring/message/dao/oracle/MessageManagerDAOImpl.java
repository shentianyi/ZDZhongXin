package com.zd.csms.messageAndWaring.message.dao.oracle;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.DAOSupport;
import com.zd.core.util.ObjectSQLUtil;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.messageAndWaring.message.contant.MsgAttributesContants;
import com.zd.csms.messageAndWaring.message.dao.IMessageManagerDAO;
import com.zd.csms.messageAndWaring.message.model.MessageInfoUserVO;
import com.zd.csms.messageAndWaring.message.model.WaringInfoUserVO;
public  class MessageManagerDAOImpl extends DAOSupport implements
		IMessageManagerDAO {

	public MessageManagerDAOImpl(String dataSourceName) {
		super(dataSourceName);
	}

	
	@Override
	public MessageVO getMessageGroupByUserId(int userId, int msgType) {
		String sql = " SELECT * FROM T_MESSAGE  WHERE USERID=? AND TYPE=? "  ;
		return  (MessageVO) getJdbcTemplate().queryForObject(sql, new Object[] { userId,msgType },
				new BeanPropertyRowMapper(MessageVO.class));
	}


	@Override
	public boolean isAllReadMessage(int userId, int msgType) {
		String sql = " SELECT COUNT(USERID) FROM  T_MSG_INFO_USER  WHERE USERID=? AND MSGTYPE=?  AND  READ=? "  ;
		return getJdbcTemplate().queryForInt(sql, new Object[]{userId,msgType,MsgAttributesContants.NOREAD.getCode()})==0;
	}
	
	@Override
	public boolean isAllReadWaring(int userId, int msgType) {
	    String sql = " SELECT COUNT(USERID) FROM  T_WARING_INFO_USER  WHERE USERID=? AND MSGTYPE=?  AND  READ=? "  ;
		return getJdbcTemplate().queryForInt(sql, new Object[]{userId,msgType,MsgAttributesContants.NOREAD.getCode()})==0;
	}

	@Override
	public boolean readMessage(int useId, String msgInfoIds) {
		String sql = " UPDATE T_MSG_INFO_USER  SET READ=?  WHERE USERID=?  AND  "  ;
		if(msgInfoIds.contains(",")){
			sql +="  MSGINFOID IN ( "+msgInfoIds+" ) " ;
		}else{
			sql +="  MSGINFOID="+Integer.valueOf(msgInfoIds) ;
		}
		
		return getJdbcTemplate().update(sql, new Object[]{MsgAttributesContants.READ.getCode() ,useId}) >0;
	}

	
	@Override
	public boolean readWaring(int useId, String msgInfoIds) {
		String sql = " UPDATE T_WARING_INFO_USER  SET READ=?  WHERE USERID=? AND "  ;
		if(msgInfoIds.contains(",")){
			sql +="  MSGINFOID IN ( "+msgInfoIds+" ) " ;
		}else{
			sql +="  MSGINFOID="+Integer.valueOf(msgInfoIds) ;
		}
		return getJdbcTemplate().update(sql, new Object[]{MsgAttributesContants.READ.getCode() ,useId}) >0;
	}

	
	@Override
	public boolean shieldWaring(int useId, String msgInfoIds) {
		String sql = " UPDATE T_WARING_INFO_USER  SET SHIELD=?  WHERE USERID=?  AND  "  ;
		if(msgInfoIds.contains(",")){
			sql +="  MSGINFOID IN ( "+msgInfoIds+" ) " ;
		}else{
			sql +="  MSGINFOID="+msgInfoIds ;
		}
		return getJdbcTemplate().update(sql, new Object[]{MsgAttributesContants.SHIELD.getCode() ,useId}) >0;
	}


	@Override
	public boolean addMessageUserBatch(final List<MessageInfoUserVO> msgUsers) {
		try {
			String sql ="insert into t_msg_info_user (userId,msgInfoId,msgType,read,signId) values(?,?,?,?,?)";
			BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
						ps.setInt(1, msgUsers.get(arg1).getUserId());
						ps.setInt(2, msgUsers.get(arg1).getMsgInfoId());
						ps.setInt(3, msgUsers.get(arg1).getMsgType());
						ps.setInt(4, msgUsers.get(arg1).getRead());
						ps.setInt(5, msgUsers.get(arg1).getSignId());
						
				}
				
				@Override
				public int getBatchSize() {
					return msgUsers.size();
				}
			};
			getJdbcTemplate().batchUpdate(sql,bpss);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean addWaringUserBatch(final List<WaringInfoUserVO> waringUsers) {
		try {
			String sql ="insert into t_msg_info_user (userId,msgInfoId,msgType,read,shield,signId) values(?,?,?,?,?,?)";
			BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
						ps.setInt(1, waringUsers.get(arg1).getUserId());
						ps.setInt(2, waringUsers.get(arg1).getMsgInfoId());
						ps.setInt(3, waringUsers.get(arg1).getMsgType());
						ps.setInt(4, waringUsers.get(arg1).getRead());
						ps.setInt(5, waringUsers.get(arg1).getShield());
						ps.setInt(6, waringUsers.get(arg1).getSignId());
						
				}
				
				@Override
				public int getBatchSize() {
					return waringUsers.size();
				}
			};
			getJdbcTemplate().batchUpdate(sql,bpss);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean addMessageBath(List<?> infoVOs) {
		try {
			String sql=ObjectSQLUtil.getAdd(infoVOs.get(0));
			final List<Object[]> paramList=new ArrayList<Object[]>();
			for(Object vo:infoVOs){
				Object[] objs=ObjectSQLUtil.getAddParameters(vo) ;
				formateParams(objs);
				paramList.add(objs) ;
			}
			
			BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
					Object[] params=paramList.get(arg1);
					for (int i = 0; i < params.length; i++) {
						ps.setObject(i + 1, params[i]);
					}
					
				}
				
				@Override
				public int getBatchSize() {
					return paramList.size();
				}
			};
			getJdbcTemplate().batchUpdate(sql,bpss);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	private void formateParams(Object[] params) {
		for(int i=0;i<params.length;i++){
			if(params[i] instanceof Date){
				Date date = (Date)params[i];
				if(date.getTime()==0){
					params[i] = null;
				}else{
					params[i] = new Timestamp(date.getTime());
				}
			}
		}
	}

	
}

package com.zd.csms.message.dao.oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.dao.IMessageDAO;
import com.zd.csms.message.dao.mapper.CountLastMessageMapper;
import com.zd.csms.message.dao.mapper.MessageMapper;
import com.zd.csms.message.model.CountLastMessageVO;
import com.zd.csms.message.model.MessageQueryVO;
import com.zd.csms.message.model.MessageVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class MessageOracleDAO extends DAOSupport implements IMessageDAO {

	public MessageOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_message = "select * from t_message";

	private boolean formatMessageWhereSQL(MessageQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getUserid()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("userid=?");
			params.add(vo.getUserid());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getName())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("name like ?");
			params.add("%"+vo.getName()+"%");
			queryFlag = true;
		}
		if(vo.getIsread()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("isread=?");
			params.add(vo.getIsread());
			queryFlag = true;
		}
		if(vo.getMsgtype()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("msgtype=?");
			params.add(vo.getMsgtype());
			queryFlag = true;
		}
		if(vo.getType()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("type=?");
			params.add(vo.getType());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getTypeName())){
			StringBuffer sb = new StringBuffer("");
			MessageTypeContant[] values = MessageTypeContant.values();
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			if (null != values && values.length > 0) {
				for (MessageTypeContant msgTypeContant : values) {
					if (null != msgTypeContant && StringUtil.isNotEmpty(msgTypeContant.getName())
							&& msgTypeContant.getName().contains(vo.getTypeName())) {
						sb.append(msgTypeContant.getCode()+",");
					}
				}
			}
			if (sb.length() > 0) {
				String num = sb.substring(0, sb.length()-1).toString();
				sql.append("type in "
						+ "(select regexp_substr('"+num+"','[^,]+', 1, level) from dual "
						+ "connect by regexp_substr('"+num+"', '[^,]+', 1, level) is not null)");
			}
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getDeptName())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("deptname like ?");
			params.add("%"+vo.getDeptName()+"%");
			queryFlag = true;
		}
		return !queryFlag;
	}

	@Override
	public List<MessageVO> searchMessageList(MessageQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(MessageOracleDAO.select_message);
		formatMessageWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<MessageVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new MessageMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	public boolean addBatch(final List<MessageVO> list){
		try {
			String sql ="insert into t_message(id,userid,name,url,isread,msgtype,type,deptName) values(?,?,?,?,?,?,?,?)";
			BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
						ps.setInt(1, list.get(arg1).getId());
						ps.setInt(2, list.get(arg1).getUserid());
						ps.setString(3, list.get(arg1).getName());
						ps.setString(4, list.get(arg1).getUrl());
						ps.setInt(5, list.get(arg1).getIsread());
						ps.setInt(6, list.get(arg1).getMsgtype());
						ps.setInt(7, list.get(arg1).getType());
						ps.setString(8, list.get(arg1).getDeptName());
				}
				
				@Override
				public int getBatchSize() {
					return list.size();
				}
			};
			getJdbcTemplate().batchUpdate(sql,bpss);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	public boolean addMessageBatch(final List<MessageVO> list){
		try {
			String sql ="insert into t_message(id,userid,name,url,isread,msgtype,type,deptName) values(?,?,?,?,?,?,?,?)";
			BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int arg1) throws SQLException {
						ps.setInt(1, list.get(arg1).getId());
						ps.setInt(2, list.get(arg1).getUserid());
						ps.setString(3, list.get(arg1).getName());
						ps.setString(4, list.get(arg1).getUrl());
						ps.setInt(5, list.get(arg1).getIsread());
						ps.setInt(6, list.get(arg1).getMsgtype());
						ps.setInt(7, list.get(arg1).getType());
						ps.setString(8, list.get(arg1).getDeptName());
						
				}
				@Override
				public int getBatchSize() {
					return list.size();
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
	public List<CountLastMessageVO> searchLastMessageAndCountByType(MessageQueryVO vo, IThumbPageTools tools){
		
		
		StringBuffer temp = new StringBuffer();
		if(vo.getTypeName() != null && vo.getTypeName().trim().length()!=0){
			MessageTypeContant[] values = MessageTypeContant.values();
			for (MessageTypeContant messageTypeContant : values) {
				if(messageTypeContant.getCode() == 34)
						System.out.println();
				Pattern compile = Pattern.compile(vo.getTypeName().trim());
				Matcher matcher = compile.matcher(messageTypeContant.getName());
				if(matcher.find()){
					temp.append(messageTypeContant.getCode()+",");
				}
			}
			if(temp.length()!= 0){
				temp.deleteCharAt(temp.length()-1);
			}
		}
		
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t1.num,t2.* from  ");
		sql.append("(select count(id) as num,type as typenum from t_message where userid = ?  and msgtype = ?  ");
		params.add(vo.getUserid());
//		params.add(vo.getIsread());
		params.add(vo.getMsgtype());
		
		if(vo.getDeptName()!=null && vo.getDeptName().trim().length()!=0){
			sql.append(" and deptname like ?");
			params.add("%"+vo.getDeptName()+"%");
		}
		
		if(vo.getTypeName() != null && vo.getTypeName().trim().length()!=0){
			sql.append(" and type in ("+temp.toString()+")");
		}
		
		
		sql.append(" group by type) t1 ");
		sql.append("join ");
		sql.append("(select * from t_message t1 where (id,type) in (select max(id),type from t_message where userid = ?  and msgtype = ?  ");
		params.add(vo.getUserid());
		params.add(vo.getMsgtype());
		

		if(vo.getDeptName()!=null && vo.getDeptName().trim().length()!=0){
			sql.append(" and deptname like ?");
			params.add("%"+vo.getDeptName()+"%");
		}
		
		if(vo.getTypeName() != null && vo.getTypeName().trim().length()!=0){
			sql.append(" and type in ("+temp.toString()+")");
		}
		
		sql.append(" group by type)) t2 ");
		
		sql.append("on t1.typenum = t2.type ");
		
		
		
		
		List<CountLastMessageVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new CountLastMessageMapper());
			System.out.println("CountLastMessageMapper:"+sql.toString());
			System.out.println("CountLastMessageMapper:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
		
		
	}

	
	@Override
	public List<MessageVO> searchMessageListByPage(MessageQueryVO vo, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(vo.getUserid() != -1){
			sql.append(MessageOracleDAO.select_message);
		}else{
			sql.append("select max(id) as id, 0 as userid ,name,url,min(isread) as isread,msgtype,type,deptname from t_message ");
		}
		formatMessageWhereSQL(vo,sql,params);
		if(vo.getUserid() == -1){
			sql.append(" group by name,url,msgtype,type,deptname");
		}
		sql.append(" order by isread,id desc ");
		List<MessageVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new MessageMapper());
			System.out.println("searchMessageListByPage sql:"+sql.toString());
			System.out.println("searchMessageListByPage params"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public MessageVO loadMsgByUserAndType(int id, int type) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.* from t_message t where t.userid = ? and t.type = ? ");
		return (MessageVO)getJdbcTemplate().queryForObject(sql.toString(), new Object[] {id,type}, new BeanPropertyRowMapper(MessageVO.class));
	}
	
	//标记已读 -- 需求28
	public int signReadMessageById(String ids){
		try {
			String sql = "update t_message SET  isread = 2, SET name = 0 WHERE id IN("+ids+")";
			int result = getJdbcTemplate().update(sql);
			if (result > 0) {
				System.out.println("标记已读成功！");
				System.out.println("signReadMessageById sql:"+sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int signReadMessageByAllIsNotRead(MessageQueryVO mQuery) {
		try {
			String sql = "update t_message SET  isread = 2 WHERE id IN(select id from t_message where userid="+mQuery.getUserid()+" and msgtype="+mQuery.getMsgtype()+" and isread=1)";
			int result = getJdbcTemplate().update(sql);
			if (result > 0) {
				System.out.println("标记已读成功！");
				System.out.println("signReadMessageByAllIsNotRead sql:"+sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}

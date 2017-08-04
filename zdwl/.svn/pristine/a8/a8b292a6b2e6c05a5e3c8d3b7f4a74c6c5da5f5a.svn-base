package com.zd.csms.message.dao.oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.NoticeTypeContant;
import com.zd.csms.message.dao.INoticeDAO;
import com.zd.csms.message.model.NoticeQueryVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class NoticeOracleDAO extends DAOSupport implements INoticeDAO {

	public NoticeOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_Notice = "select * from t_Notice";

	private boolean formatNoticeWhereSQL(NoticeQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getUserId()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("userid=?");
			params.add(vo.getUserId());
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
		if(!StringUtil.isEmpty(vo.getTitle())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("title like ?");
			params.add("%"+vo.getTitle()+"%");
			queryFlag = true;
		}
		return !queryFlag;
	}

	@Override
	public List<NoticeVO> searchNoticeAllList(NoticeQueryVO query) {
		query.setUserId(0);
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select max(id) as id,title,type,0 as userid,url,objectid ");
		sql.append(" from t_Notice group by title,type,url,objectid ");
		formatNoticeWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<NoticeVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(NoticeVO.class));
			System.out.println("我是公告SQL："+sql.toString());
			System.out.println("我是公告参数："+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@Override
	public List<NoticeVO> searchNoticeList(NoticeQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(NoticeOracleDAO.select_Notice);
		formatNoticeWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<NoticeVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(NoticeVO.class));
			System.out.println("我是公告SQL："+sql.toString());
			System.out.println("我是公告参数："+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@Override
	public List<NoticeVO> searchNoticeListByPage(NoticeQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(NoticeOracleDAO.select_Notice);
		formatNoticeWhereSQL(query, sql, params);
		sql.append(" order by id desc ");
		List<NoticeVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(NoticeVO.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
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
	 * @throws Exception
	 */
	public boolean addBatch(final List<NoticeVO> list) throws Exception{	
		String sql ="insert into t_notice(id,title,objectId,type,userId,url) values(?,?,?,?,?,?)";
		BatchPreparedStatementSetter bpss = new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int arg1) throws SQLException {
					ps.setInt(1, list.get(arg1).getId());
					ps.setString(2, list.get(arg1).getTitle());
					ps.setInt(3, list.get(arg1).getObjectId());
					ps.setInt(4, list.get(arg1).getType());
					ps.setInt(5, list.get(arg1).getUserId());
					ps.setString(6, list.get(arg1).getUrl());
			}
			
			@Override
			public int getBatchSize() {
				return list.size();
			}
		};
		getJdbcTemplate().batchUpdate(sql,bpss);
		return true;
	}
	
	/**
	 * 根据目标Id和类型删除
	 * @param objId
	 * @param type
	 * @return
	 */
	@Override
	public boolean deleteByObj(int objId,int type){
		String sql="delete from t_notice t where t.objectId=? and t.type=?";
		return super.update(sql, objId,type);
	}


	@Override
	public List<NoticeVO> systemList(NoticeQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select t.id,t.title,t.userid,t.url from t_notice t " +
						"inner join t_notice_content nc on nc.id = t.objectid " + 
						"where t.userid=0 ");
		if(query.getUserId()>0){
			sql.append(" and (nc.type = 1 or exists(select count(1) from t_content_department cd where cd.contentid = t.id and cd.CLIENTTYPEID = "+query.getUser().getClient_id()+" ))");
		}
		sql.append(" order by id desc");
		List<NoticeVO> resultList = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(NoticeVO.class));
		return resultList;
	}

}

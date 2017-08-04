package com.zd.csms.business.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.business.dao.IMailingDAO;
import com.zd.csms.business.dao.mapper.MailingMapper;
import com.zd.csms.business.model.MailingQueryVO;
import com.zd.csms.business.model.MailingVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class MailingOracleDAO extends DAOSupport implements IMailingDAO {

	public MailingOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_mailing = "select * from t_mailing";

	private boolean formatMailingWhereSQL(MailingQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getSuperviseid()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("superviseid=?");
			params.add(vo.getSuperviseid());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getMailnature())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("mailnature like ?");
			params.add("%"+vo.getMailnature()+"%");
			queryFlag = true;
		}
		if(vo.getMailtimebegin() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("mailtime >= ?");
			params.add(vo.getMailtimebegin());
			queryFlag = true;
		}
		if(vo.getMailtimeend() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("mailtime <= ?");
			params.add(vo.getMailtimeend());
			queryFlag = true;
		}
		return !queryFlag;
	}

	@Override
	public List<MailingVO> searchMailingList(MailingQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(MailingOracleDAO.select_mailing);
		formatMailingWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<MailingVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new MailingMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<MailingVO> searchMailingListByPage(MailingQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(MailingOracleDAO.select_mailing);
		formatMailingWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<MailingVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new MailingMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}

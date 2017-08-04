package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.dao.IActualAddressDAO;
import com.zd.csms.supervisory.dao.mapper.ActualAddressMapper;
import com.zd.csms.supervisory.model.ActualAddressQueryVO;
import com.zd.csms.supervisory.model.ActualAddressVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ActualAddressOracleDAO extends DAOSupport implements IActualAddressDAO {

	public ActualAddressOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_actual_address = "select * from t_actual_address ta ";

	private boolean formatActualAddressWhereSQL(ActualAddressQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = true;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getDistribid() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("ta.distribid = ?");
			params.add(vo.getDistribid());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getAgreement_address())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("ta.agreement_address like ?");
			params.add("%"+vo.getAgreement_address()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getActual_address())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("ta.actual_address like ?");
			params.add("%"+vo.getActual_address()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getRelationship())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("ta.relationship like ?");
			params.add("%"+vo.getRelationship()+"%");
			queryFlag = true;
		}
		if(vo.getIsreport() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("ta.isreport = ?");
			params.add(vo.getIsreport());
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<ActualAddressVO> searchActualAddressList(ActualAddressQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//监管员
		if(query.getType() == 1){
			sql.append(" select ta.* from t_actual_address ta "+
					" left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "+
					" where mds.repositoryid="+query.getUserid());
		}else if(query.getType() == 2){
			sql.append("select ta.* from t_actual_address ta "+
					" left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "+
					" left join t_yw_bank ty on ty.bankid = mds.bankid "+
					" where ty.userid=" + query.getUserid());
		}else{
			sql.append(ActualAddressOracleDAO.select_actual_address);
			sql.append(" where 1=1 ");
		}
		formatActualAddressWhereSQL(query,sql,params);
		
		List<ActualAddressVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new ActualAddressMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<ActualAddressVO> searchActualAddressListByPage(ActualAddressQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//监管员
		if(query.getType() == 1){
			sql.append(" select ta.* from t_actual_address ta "+
					" left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "+
					" where mds.repositoryid="+query.getUserid());
		}else if(query.getType() == 2){
			sql.append("select ta.* from t_actual_address ta "+
					" left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "+
					" left join t_yw_bank ty on ty.bankid = mds.bankid "+
					" where ty.userid=" + query.getUserid());
		}else{
			sql.append(ActualAddressOracleDAO.select_actual_address);
			sql.append(" where 1=1 ");
		}
		
		
		formatActualAddressWhereSQL(query,sql,params);
		
		List<ActualAddressVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new ActualAddressMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<String> findAllIds(UserVO user,int type) {
		StringBuffer sql = new StringBuffer();
		if(type == 1){
			sql.append("select mds.dealerid from market_dealer_supervisor mds where mds.repositoryid ="+user.getClient_id());
		}else{
			sql.append("select mds.dealerid from market_dealer_supervisor mds "+
                        " left join t_yw_bank ty on ty.bankid = mds.bankid "+
                        " where ty.userid="+user.getId());
		}
		return getJdbcTemplate().queryForList(sql.toString(), String.class);
	}

}

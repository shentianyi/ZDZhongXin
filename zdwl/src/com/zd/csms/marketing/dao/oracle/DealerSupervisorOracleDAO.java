package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.business.dao.mapper.DraftMapper;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.marketing.dao.IDealerSupervisorDAO;
import com.zd.csms.marketing.dao.mapper.DealerSupervisorMapper;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.querybean.DealerSupervisorBean;
import com.zd.tools.SqlUtil;

public class DealerSupervisorOracleDAO extends DAOSupport implements IDealerSupervisorDAO {

	public DealerSupervisorOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	//资源查询语句
	private static String select_market_dealer_supervisor = "select * from market_dealer_supervisor";
	
	private boolean formatDealerSupervisorWhereSQL(DealerSupervisorQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getDealerId()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("dealerId=?");
			params.add(vo.getDealerId());
			queryFlag = true;
		}
		if(vo.getSupervisorId()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("repositoryId=?");
			params.add(vo.getSupervisorId());
			queryFlag = true;
		}
		if(vo.getBankId()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("bankId=?");
			params.add(vo.getBankId());
			queryFlag = true;
		}
		
		return !queryFlag;
	}
	@Override
	public List<DealerSupervisorVO> searchDealerSupervisorList(DealerSupervisorQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(DealerSupervisorOracleDAO.select_market_dealer_supervisor);
		formatDealerSupervisorWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<DealerSupervisorVO> result;
		System.out.println("searchDealerSupervisorList sql:"+sql.toString());
		System.out.println("searchDealerSupervisorList params:"+params);
		
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerSupervisorVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@Override
	public List<DraftVO> searchDraftSupervisorList(int id) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
			sql.append("select td.* from t_draft td"+
					" left join market_dealer_supervisor md on td.distribid = md.dealerid"+
					" left join t_rbac_user tr on md.repositoryid = tr.client_id"+
					" where tr.id="+id);
		
		List<DraftVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new DraftMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * @param dealerId
	 * @param repoId
	 * @return
	 */
	public boolean updateRepoByDealerId(int dealerId,int repoId){
		StringBuffer sql = new StringBuffer();
		//变更监管员时需要更新绑定时间 added by lsf 20170522
		sql.append(" update market_dealer_supervisor t set t.repositoryid = ?,t.bindtime=? where t.dealerid = ? ");
		return getJdbcTemplate().update(sql.toString(),new Object[]{repoId,new Date(),dealerId})>0;
		
	}
	
	/**
	 * 删除关联
	 * @param dealerId
	 * @return
	 */
	public boolean deleteByRepoIdanddealerId(int dealerId){
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from market_dealer_supervisor t where t.dealerid = ? ");
		return getJdbcTemplate().update(sql.toString(),new Object[]{dealerId})>0;
		
	}
	
	public DealerSupervisorBean getSupervisorNameByDealerId(int dealerId){
		String sql=
				"select bi.name,bi.selftelephone from market_dealer_supervisor mds " +
						"left join t_repository tr on tr.id = mds.repositoryid " + 
						"left join t_supervisor_basic_information bi on tr.supervisorid = bi.id " + 
						"where mds.dealerid = ?";
		List<DealerSupervisorBean> list =  getJdbcTemplate().query(sql, new Object[]{dealerId}, new BeanPropertyRowMapper(DealerSupervisorBean.class));
		if(list !=null&&list.size()>0)
			return list.get(0);
		else
			return null;
	}
	

}

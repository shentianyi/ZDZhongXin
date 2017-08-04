package com.zd.csms.roster.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.roster.dao.IRosterDAO;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterQueryBean;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class RosterOracleDAO  extends DAOSupport implements IRosterDAO {

	public RosterOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PostChangeVO> getPostChangeByRosterId(int rosterId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_POST_CHANGE where rosterid=? order by id desc");
		List<Object> params = new ArrayList<Object>();
		params.add(rosterId);
		List<PostChangeVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PostChangeVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RosterVO> searchList(RosterQueryVO rosterQueryVO) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_ROSTER where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		formatWhereSQL(rosterQueryVO, sql, params);
		List<RosterVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RosterVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RosterVO> searchPageList(RosterQueryVO rosterQueryVO,
			IThumbPageTools tools) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_ROSTER where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		formatWhereSQL(rosterQueryVO, sql, params);
		List<RosterVO> list = tools.goPage(sql.toString(), params.toArray(),  new BeanPropertyRowMapper(RosterVO.class));
		return list;
	}
	
	private void formatWhereSQL(RosterQueryVO query, StringBuffer sql, List<Object> params){
		if(0!=query.getStaffNo()){
			sql.append(" and staffno = ? ");
			params.add(query.getStaffNo());
		}
		if(0<query.getSupervisorId()){
			sql.append(" and supervisorId = ? ");
			params.add(query.getSupervisorId());
		}
		if(query.getRepositoryId()>0){
			sql.append(" and repositoryid = ? ");
			params.add(query.getRepositoryId());
		}
	}
	
	/**
	 * 根据花名册的主键Id获取监管员信息
	 * @param rosterId
	 * @return
	 */
	public RosterQueryBean findRosterById(int rosterId){
		StringBuffer sql= new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select t.id, t.staffno, bi.name,bi.selfTelephone as \"phone\" " +
						"  from t_roster t " + 
						"  left join t_supervisor_basic_information bi " + 
						"    on t.repositoryId = bi.id " + 
						" where t.id = ?");
		params.add(rosterId);
		List<RosterQueryBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(RosterQueryBean.class));
		if(list != null&&list.size()>0)
			return list.get(0);
		else 
			return null;
		
	}
	
	/* 根据ID获取roster */
	public RosterVO loadById(int id){
		StringBuffer sql= new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				" select * from T_ROSTER where id = ? ");
		params.add(id);
		List<RosterVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(RosterVO.class));
		if(list != null&&list.size()>0)
			return list.get(0);
		else 
			return null;
	}
	
	/* 根据REPOSITORYID找出用户列表 */
	public String getLoginidList(int REPOSITORYID){
		StringBuffer sql= new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				" select to_char(wm_concat(loginid)) name from t_rbac_user where client_type = 3 and client_id = ? ");
		params.add(REPOSITORYID);
		List<RosterQueryBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(RosterQueryBean.class));
		if(list != null&&list.size()>0 &&list.get(0).getName()!=null)
			return list.get(0).getName();
		else 
			return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<RosterVO> searchRosterByRepositoryId(int repositoryId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_ROSTER where repositoryId=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryId);
		List<RosterVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RosterVO.class));
		return list;
	}

	@Override
	public RosterVO getRosterBySupervisorId(int supervisorId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_ROSTER where supervisorid=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(supervisorId);
		RosterVO list = (RosterVO) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RosterVO.class));
		return list;
	}
	
	@Override
	public BusinessTransferVO loadBusinessTransferByRId(int repositoryId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from market_businesstransfer where repositoryid = ?  order by lastmodifydate desc ");
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryId);
		List<BusinessTransferVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BusinessTransferVO.class));
		if(list==null||list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	@Override
	public DealerVO loadDealerByRId(int repositoryId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select t2.* from market_dealer_supervisor t1,market_dealer t2 where t1.dealerid=t2.id and t1.repositoryid=? order by t1.bindtime desc,t1.id desc ");
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryId);
		List<DealerVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerVO.class));
		if(list==null||list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

}

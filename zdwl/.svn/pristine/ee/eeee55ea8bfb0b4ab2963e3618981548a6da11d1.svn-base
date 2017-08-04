package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.supervisory.dao.ISupervisoryDao;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEducationVO;
import com.zd.csms.supervisory.model.SupervisorFamilyVO;
import com.zd.csms.supervisory.model.SupervisorQueryVO;
import com.zd.csms.supervisory.model.SupervisorRecommendVO;
import com.zd.csms.supervisory.model.SupervisorWorkExperienceVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupervisoryOracleDao extends DAOSupport implements ISupervisoryDao {

	public SupervisoryOracleDao(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SupervisorEducationVO> getEducationBySupervisorId(int SupervisorId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_SUPERVISOR_EDUCATION where supervisorid=? order by educationEndTime desc ");
		List<Object> params = new ArrayList<Object>();
		params.add(SupervisorId);
		List<SupervisorEducationVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupervisorEducationVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SupervisorFamilyVO> getFamilyBySupervisorId(int SupervisorId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_SUPERVISOR_FAMILY where supervisorid=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(SupervisorId);
		List<SupervisorFamilyVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupervisorFamilyVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SupervisorRecommendVO getRecommendBySupervisorId(int SupervisorId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_SUPERVISOR_RECOMMEND where supervisorid=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(SupervisorId);
		SupervisorRecommendVO list = (SupervisorRecommendVO) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupervisorRecommendVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SupervisorWorkExperienceVO> getWorkExperienceBySupervisorId(int SupervisorId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_SUPERVISOR_WORKEXPERIENCE where supervisorid=? order by workEndTime desc");
		List<Object> params = new ArrayList<Object>();
		params.add(SupervisorId);
		List<SupervisorWorkExperienceVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupervisorWorkExperienceVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SupervisorBaseInfoVO> searchList(SupervisorQueryVO supervisorQuery) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from t_supervisor_basic_information where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		formatWhereSQL(supervisorQuery, sql, params);
		List<SupervisorBaseInfoVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupervisorBaseInfoVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SupervisorBaseInfoVO> searchPageList(
			SupervisorQueryVO supervisoryQuery, IThumbPageTools tools) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from t_supervisor_basic_information where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		formatWhereSQL(supervisoryQuery, sql, params);
		sql.append(" order by id desc ");
		List<SupervisorBaseInfoVO> list = tools.goPage(sql.toString(), params.toArray(),  new BeanPropertyRowMapper(SupervisorBaseInfoVO.class));
		return list;
	}
	
	private void formatWhereSQL(SupervisorQueryVO query, StringBuffer sql, List<Object> params){
		if(StringUtils.isNotEmpty(query.getName())){
			sql.append(" and name like ? ");
			params.add("%"+query.getName()+"%");
		}
		if(StringUtils.isNotEmpty(query.getGender())){
			sql.append(" and gender = ? ");
			params.add(query.getGender());
		}
		if(StringUtils.isNotEmpty(query.getEducationBackground())){
			sql.append(" and educationBackground = ? ");
			params.add(query.getEducationBackground());
		}
		if(StringUtils.isNotEmpty(query.getJob())){
			sql.append(" and job = ? ");
			params.add(query.getJob());
		}
		if(query.getEntryTime()!=null){
			sql.append(" and entryTime = ? ");
			params.add(query.getEntryTime());
		}
		
	}

	@Override
	public boolean updateSupervisorEntryDate(int supervisorId, Date date) {
		StringBuffer sql= new StringBuffer();
		sql.append(" update t_supervisor_basic_information set entryTime = ? where id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		params.add(supervisorId);
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	
	}

	@Override
	public boolean deleteEducationBySupervisorId(int supervisorId) {
		String sql="delete from T_SUPERVISOR_EDUCATION where supervisorid = ? ";
		return getJdbcTemplate().update(sql.toString(),new Object[]{supervisorId})>=0;
	}

	@Override
	public boolean deleteFamilyBySupervisorId(int supervisorId) {
		String sql="delete from T_SUPERVISOR_FAMILY where supervisorid = ? ";
		return getJdbcTemplate().update(sql.toString(),new Object[]{supervisorId})>=0;
	}

	@Override
	public boolean deleteRecommendBySupervisorId(int supervisorId) {
		String sql="delete from T_SUPERVISOR_RECOMMEND where supervisorid = ? ";
		return getJdbcTemplate().update(sql.toString(),new Object[]{supervisorId})>=0;
	}

	@Override
	public boolean deleteWorkExperienceBySupervisorId(int supervisorId) {
		String sql="delete from T_SUPERVISOR_WORKEXPERIENCE where supervisorid = ? ";
		return getJdbcTemplate().update(sql.toString(),new Object[]{supervisorId})>=0;
	}

	@Override
	public boolean validateIdNumberUnique(int supervisorId, String idNumber) {
		String sql="";
		List<Object> params = new ArrayList<Object>();
		if(supervisorId>0){
			sql=" select count(1) from t_supervisor_basic_information where id != ? and  idNumber = ? ";
			params.add(supervisorId);
			params.add(idNumber);
		}else{
			sql=" select count(1) from t_supervisor_basic_information where idNumber = ? ";
			params.add(idNumber);
		}
		System.out.println(sql);
		return getJdbcTemplate().queryForInt(sql, params.toArray())<=0;
	}
}

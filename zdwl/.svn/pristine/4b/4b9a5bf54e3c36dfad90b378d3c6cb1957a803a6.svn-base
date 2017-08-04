package com.zd.csms.repository.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.dao.IRepositoryDAO;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.model.RepositoryQueryVO;
import com.zd.csms.repository.model.RepositorySelectBean;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class RepositoryOracleDAO extends DAOSupport implements IRepositoryDAO {

	public RepositoryOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RepositoryDispatchCityVO> getDispatchCityByRepositoryId(int repositoryId ) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from T_REPOSITORY_DISPATCHCITY where repositoryid=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryId);
		List<RepositoryDispatchCityVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RepositoryDispatchCityVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RepositoryVO> searchList(RepositoryQueryVO repositoryQueryVO) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select distinct r.* from T_REPOSITORY r "
				+ "left join t_supervisor_basic_information s on r.supervisorId=s.id "
				+ "left join T_REPOSITORY_TRAIN t on r.id=t.repositoryId  "
				+ "left join T_REPOSITORY_DISPATCHCITY d on r.id=d.repositoryId where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		formatWhereSQL(repositoryQueryVO, sql, params);
		List<RepositoryVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RepositoryVO.class));
		SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RepositoryVO> searchPageList(
			RepositoryQueryVO repositoryQueryVO, IThumbPageTools tools) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select distinct r.*,t.trainingcontent  from T_REPOSITORY r "
				+ "left join t_supervisor_basic_information s on r.supervisorId=s.id "
				+ "left join T_REPOSITORY_TRAIN t on r.id=t.repositoryId  "
				+ "left join T_REPOSITORY_DISPATCHCITY d on r.id=d.repositoryId where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		formatWhereSQL(repositoryQueryVO, sql, params);
		sql.append(" order by ( case  when r.status = 2 and t.trainingcontent is null then 1 ");
		sql.append(" when r.status = 2 and t.trainingcontent is not null then 2  ");
		sql.append(" when r.status = 1 and t.trainingcontent is null then 3 ");
		sql.append(" when r.status = 1 and t.trainingcontent is not null then 4 ");
		sql.append(" when r.status = 3 and t.trainingcontent is null then 5  ");
		sql.append(" when r.status = 3 and t.trainingcontent is not null then 6  ");
		sql.append("else 7 end), r.lastmodifytime desc ");
	
		List<RepositoryVO> list;
		try {
			list = tools.goPage(sql.toString(), params.toArray(),  new BeanPropertyRowMapper(RepositoryVO.class));
			System.out.println("searchPageList sql:"+sql.toString());
			System.out.println("searchPageList params"+params);
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
		
	}
	private void formatWhereSQL(RepositoryQueryVO query, StringBuffer sql, List<Object> params){
		if(query.getStatus()!=null){
			sql.append(" and r.status in( ");
			for (int state : query.getStatus()) {
				sql.append("?,");
				params.add(state);
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(") ");
		}
		if(0!=query.getReason()){
			sql.append(" and r.reason = ? ");
			params.add(query.getReason());
		}
		if(-1!=query.getSupervisorId() && 0!=query.getSupervisorId()){
			sql.append(" and r.supervisorId = ? ");
			params.add(query.getSupervisorId());
		}
		if(!StringUtil.isEmpty(query.getSupervisorName())){
			sql.append(" and s.name like ? ");
			params.add("%"+query.getSupervisorName()+"%");
		}
		if(!StringUtil.isEmpty(query.getIdNumber())){
			sql.append(" and s.idNumber like ? ");
			params.add("%"+query.getIdNumber()+"%");
		}
		if(query.getStartTime() != null){	
			sql.append(" and t.startTime >= to_date('"+DateUtil.getStringFormatByDate(query.getStartTime() , "yyyy-MM-dd")+"','yyyy-mm-dd')");
		}
		if(query.getEndTime() != null){
			sql.append(" and t.startTime <= to_date('"+DateUtil.getStringFormatByDate(query.getEndTime() , "yyyy-MM-dd")+"','yyyy-mm-dd')");
		}
		if(query.getProvince()!=null&&!query.getProvince().equals("-1")){
			sql.append(" and d.province= ? ");
			params.add(query.getProvince());
		}
		if(query.getCity()!=null&&!query.getCity().equals("-1")){
			sql.append(" and d.city= ? ");
			params.add(query.getCity());
		}
		if(query.getCounty()!=null&&!query.getCounty().equals("-1")){
			sql.append(" and d.county= ? ");
			params.add(query.getCounty());
		}
		if(query.getMsgType()==1 && query.getCreateTime()!=null){
			sql.append(" and to_char(r.createTime,'yyyyMMdd') = ?");
			params.add(DateUtil.getStringFormatByDate(query.getCreateTime(), "yyyyMMdd"));
		}
		
		if(query.getMsgType()==2 && query.getCreateTime()!=null){
			sql.append(" and r.createTime <= ?");
			params.add(query.getCreateTime());
		}
		
		if(query.getTrainEndDate()!=null){
			sql.append(" and to_char(t.endTime,'yyyyMMdd') = ?");
			params.add(DateUtil.getStringFormatByDate(query.getTrainEndDate(), "yyyyMMdd"));
		}
	}

	@Override
	public boolean updateRepositoryStatus(int id, int status,int reason) {
		StringBuffer sql= new StringBuffer();
		sql.append(" update T_REPOSITORY set status = ?,reason = ?  where id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		params.add(reason);
		params.add(id);
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	
	}
	@Override
	public RepositoryVO loadRepositoryBySupervisorId(int supervisorId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from  T_REPOSITORY  where supervisorId = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(supervisorId);
		return (RepositoryVO) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(),new BeanPropertyRowMapper(RepositoryVO.class));
	
	}

	@Override
	public RepositoryTrainVO getRepositoryTrainByRepositoryId(int repositoryId) {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from  T_REPOSITORY_TRAIN  where repositoryId = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryId);
		return (RepositoryTrainVO) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(),new BeanPropertyRowMapper(RepositoryTrainVO.class));
	
	}
	
	/**
	 * 选择框查询sql
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<RepositorySelectBean> selectRepositoryList(RepositoryQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
			"select t.id,sbc.name,tr.staffno from t_repository t " +
			"left join t_roster tr on t.id = tr.repositoryid " + 
			"left join t_supervisor_basic_information sbc on sbc.id = t.supervisorid "
		);
		sql.append(" where 1=1 ");
		sql.append(" and t.status != ?");
		params.add(RepositoryStatus.INVALID.getCode());
		if(StringUtil.isNotEmpty(query.getSupervisorName())){
			sql.append(" and sbc.name like ? ");
			params.add("%"+query.getSupervisorName()+"%");
		}
		
		if(StringUtil.isNotEmpty(query.getStaffNo())){
			sql.append(" and tr.staffNo like ? ");
			params.add("%"+query.getStaffNo()+"%");
		}
		
		List<RepositorySelectBean> list = tools.goPage(sql.toString(), params.toArray(),  new BeanPropertyRowMapper(RepositorySelectBean.class));
		return list;
	}

	@Override
	public SupervisorBaseInfoVO getSupervisorBaseInfoByRepositoryId(
			int repositoryId) {
		String sql=" select s.* from t_supervisor_basic_information s "
				+ " inner join T_REPOSITORY r on s.id=r.supervisorId "
				+ " where r.id = ? ";
		return (SupervisorBaseInfoVO) getJdbcTemplate().queryForObject(sql.toString(), new Object[]{repositoryId},new BeanPropertyRowMapper(SupervisorBaseInfoVO.class));
	}

	@Override
	public List<RepositoryDispatchCityVO> findDispatchCityList(String provinceId, String cityId, String countyId) {
		StringBuffer sql= new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select * from T_REPOSITORY_DISPATCHCITY where 1=1 ");
		if(StringUtil.isNotEmpty(provinceId)){
			if(Integer.parseInt(provinceId)>0){
				sql.append(" and province = ? ");
				params.add(provinceId);
			}
		}
		if(StringUtil.isNotEmpty(cityId)){
			if(Integer.parseInt(cityId)>0){
				sql.append(" and city = ? ");
				params.add(cityId);
			}
		}
		if(StringUtil.isNotEmpty(countyId)){
			if(Integer.parseInt(countyId)>0){
				sql.append(" and county = ? ");
				params.add(countyId);
			}
		}
		List<RepositoryDispatchCityVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RepositoryDispatchCityVO.class));
		return list;
	}
	@Override
	public List<SupMaMsgInfoVO> supAssessNotPassList(Date today) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql= new StringBuffer();
		sql.append("  select r.id as id, s.name as supervisorName ,r.createtime as interviewTime , rt.starttime as trainTime ,rt.endtime as  trainCheckDate from  t_repository_train rt "
				+ " inner join t_repository r on r.id= rt.repositoryid  "
				+ "inner join t_supervisor_basic_information s on s.id=r.supervisorid  "
				+ " where   rt.assessmentscore<70 and to_char(rt.endtime,'yyyyMMdd') = to_char(?,'yyyyMMdd') ");
		params.add(today);
		List<SupMaMsgInfoVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupMaMsgInfoVO.class));
		return list;
	}

	@Override
	public List<RepositoryVO> findRepositoryByStatus(int status) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql= new StringBuffer();
		sql.append("  select * from T_REPOSITORY where status = ? ");
		params.add(status);
		List<RepositoryVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RepositoryVO.class));
		return list;
	}
}

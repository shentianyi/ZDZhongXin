package com.zd.csms.region.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.region.dao.IRegionDAO;
import com.zd.csms.region.model.RegionQueryVO;
import com.zd.csms.region.model.RegionVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class RegionOracleDAO extends DAOSupport implements IRegionDAO {

	public RegionOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	private void formatWhereSQL(RegionQueryVO query, StringBuffer sql, List<Object> params){
		if(StringUtils.isNotEmpty(query.getName())){
			sql.append(" and name like ? ");
			params.add("%"+query.getName()+"%");
		}
		if(StringUtils.isNotEmpty(query.getParentName())){
			sql.append(" and parentId in (select id from t_region where name like ? ) ");
			params.add("%"+query.getParentName()+"%");
		}
		if(query.getStatus()!=0){
			sql.append(" and status = ? ");
			params.add(query.getStatus());
		}
		if(query.getParentId()>-1){
			sql.append(" and parentId = ? ");
			params.add(query.getParentId());
		}
		if(query.getRegionLevel()>0){
			sql.append(" and regionLevel = ? ");
			params.add(query.getRegionLevel());
		}
		sql.append("order by nlssort(name,'NLS_SORT=SCHINESE_PINYIN_M') ");
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RegionVO> findList(RegionQueryVO queryVO) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from t_region where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		formatWhereSQL(queryVO, sql, params);
		List<RegionVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RegionVO.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegionVO> searchRegionListByPage(RegionQueryVO query,
			IThumbPageTools tools) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select * from t_region where 1=1 and id>0 ");
		List<Object> params = new ArrayList<Object>();
		formatWhereSQL(query, sql, params);
		List<RegionVO> list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RegionVO.class));
		return list;
	}
	@Override
	public boolean updRegionState(int id, int status) {
		StringBuffer sql= new StringBuffer();
		sql.append(" update t_region set status = ? where id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		params.add(id);
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RegionVO> validationName(int parentId,String name) {
		StringBuffer sql= new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select * from t_region where 1=1 and name= ? and parentId = ? ");
		params.add(name);
		params.add(parentId);
		List<RegionVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RegionVO.class));
		return list;
	}

}

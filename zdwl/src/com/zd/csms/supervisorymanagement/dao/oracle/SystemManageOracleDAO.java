package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.supervisorymanagement.dao.ISystemManageDAO;
import com.zd.csms.supervisorymanagement.dao.mapper.SystemManageMapper;
import com.zd.csms.supervisorymanagement.model.SystemManageQueryVO;
import com.zd.csms.supervisorymanagement.model.SystemManageVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SystemManageOracleDAO extends DAOSupport implements ISystemManageDAO {

	public SystemManageOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_system_manage = "select * from t_system_manage";

	private boolean formatSystemManageWhereSQL(SystemManageQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(!StringUtil.isEmpty(vo.getUsername())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("username like ?");
			params.add("%"+vo.getUsername()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getStation())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("station like ?");
			params.add("%"+vo.getStation()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getLoginid())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("loginid like ?");
			params.add("%"+vo.getLoginid()+"%");
			queryFlag = true;
		}
		
		
		return !queryFlag;
	}

	@Override
	public List<SystemManageVO> searchSystemManageList(SystemManageQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(SystemManageOracleDAO.select_system_manage);
		formatSystemManageWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<SystemManageVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new SystemManageMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<SystemManageVO> searchSystemManageListByPage(SystemManageQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(SystemManageOracleDAO.select_system_manage);
		formatSystemManageWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<SystemManageVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new SystemManageMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}

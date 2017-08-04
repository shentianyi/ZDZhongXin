package com.zd.csms.rbac.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.rbac.dao.IRoleResourceDAO;
import com.zd.csms.rbac.dao.mapper.RoleResourceMapper;
import com.zd.csms.rbac.model.RoleResourceQueryVO;
import com.zd.csms.rbac.model.RoleResourceVO;
import com.zd.tools.SqlUtil;

public class RoleResourceOracleDAO extends DAOSupport implements IRoleResourceDAO {

	public RoleResourceOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	private static String select_role_resource= "select * from t_rbac_role_resource";
	
	private boolean formatRoleResourceWhereSQL(RoleResourceQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		
		if(vo.getResource_id()>-1){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("resource_id=?");
			params.add(vo.getResource_id());
			queryFlag = true;
		}
		
		if(vo.getRole_id()>-1){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("role_id=?");
			params.add(vo.getRole_id());
			queryFlag = true;
		}
		return !queryFlag;
	}

	@Override
	public List<RoleResourceVO> searchRoleResourceList(RoleResourceQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleResourceOracleDAO.select_role_resource);
		formatRoleResourceWhereSQL(query,sql,params);
		
		List<RoleResourceVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new RoleResourceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	

}

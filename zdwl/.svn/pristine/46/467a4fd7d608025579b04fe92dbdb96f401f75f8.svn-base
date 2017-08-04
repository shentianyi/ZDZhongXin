package com.zd.csms.rbac.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.rbac.dao.IResourceDAO;
import com.zd.csms.rbac.dao.mapper.ResourceMapper;
import com.zd.csms.rbac.model.ResourceQueryVO;
import com.zd.csms.rbac.model.ResourceVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ResourceOracleDAO extends DAOSupport implements IResourceDAO {

	//资源查询语句
	//private static String select_resource = "select * from t_rbac_resource_old";
	
	private static String select_resource = "select * from t_rbac_resource";
	
	public ResourceOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	private boolean formatResourceWhereSQL(ResourceQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(!StringUtil.isEmpty(vo.getResourceName())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("resource_name like ?");
			params.add("%"+vo.getResourceName()+"%");
			queryFlag = true;
		}

		if(!StringUtil.isEmpty(vo.getResourceShortname())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("resource_shortname like ?");
			params.add("%"+vo.getResourceShortname()+"%");
			queryFlag = true;
		}
		
		if(vo.getParentId()>-1){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("parent_id=?");
			params.add(vo.getParentId());
			queryFlag = true;
		}
		
		if(vo.getNodeTypes()!=null){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("(");
			for(int i=0; i<vo.getNodeTypes().length; i++){
				if(i>0){
					sql.append(" or ");
				}
				sql.append("node_type=?");
				params.add(vo.getNodeTypes()[i]);
			}
			sql.append(")");
			queryFlag = true;
		}
		
		if(vo.getStates()!=null){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("(");
			for(int i=0; i<vo.getStates().length; i++){
				if(i>0){
					sql.append(" or ");
				}
				sql.append("state=?");
				params.add(vo.getStates()[i]);
			}
			sql.append(")");
			queryFlag = true;
		}
		return !queryFlag;
	}

	@Override
	public List<ResourceVO> searchResourceList(ResourceQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(ResourceOracleDAO.select_resource);
		formatResourceWhereSQL(query,sql,params);
		sql.append(" order by resource_index asc ");
		List<ResourceVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new ResourceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<ResourceVO> searchResourceListByPage(ResourceQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(ResourceOracleDAO.select_resource);
		formatResourceWhereSQL(query,sql,params);
		
		List<ResourceVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new ResourceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<ResourceVO> searchResourceListByParent(int id) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from t_rbac_resource where parent_id = ?");
		params.add(id);
		
		List<ResourceVO> list;
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new ResourceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@Override
	public List<ResourceVO> searchResourceListByUser(int userId, int flag) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select rs.id, rs.state, rs.resource_level, rs.node_type, rs.parent_id, rs.resource_index, rs.resource_name, rs.resource_shortname, rs.resource_url, rs.des from t_rbac_resource rs,t_rbac_role_resource role_rs, t_rbac_user_role user_ro where state = ? and rs.id = role_rs.resource_id and user_ro.role_id = role_rs.role_id and user_ro.user_id = ? ");
		if(flag==1){
			//sql.append(" and ( rs.id <> (select id from t_rbac_resource where resource_name='浙商银行对接') and parent_id <> (select id from t_rbac_resource where resource_name='浙商银行对接')) ");
			sql.append(" and ( rs.id <> (select id from t_rbac_resource where resource_name='浙商银行对接') and parent_id <> (select id from t_rbac_resource where resource_name='浙商银行对接')) ");
		}
		
		sql.append(" order by rs.id asc ");
		
		params.add(StateConstants.USING.getCode());
		params.add(userId);
		
		List<ResourceVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new ResourceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@Override
	public boolean confirmDj(int repositoryid) {
		String sql = " SELECT count(t1.id) from t_distribset t1 where exists (select t2.id from MARKET_DEALER_SUPERVISOR t2 where t2.repositoryid = ? and t1.distribid=t2.dealerid) and t1.bankdocktype <> 0 ";
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryid);
		int count =  getJdbcTemplate().queryForInt(sql, params.toArray());
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<ResourceVO> searchResourceWithRole(ResourceQueryVO query, int roleId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(ResourceOracleDAO.select_resource);
		formatResourceWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id in (select resource_id from t_rbac_role_resource where role_id=?)");
		params.add(roleId);

		List<ResourceVO> list;
		try{
			list = tools.goPage(sql.toString(), params.toArray(),new ResourceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@Override
	public List<ResourceVO> searchResourceWithoutRole(ResourceQueryVO query, int roleId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(ResourceOracleDAO.select_resource);
		formatResourceWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id not in (select distinct d.resource_id from t_rbac_role_resource d where role_id=?)");
		params.add(roleId);

		List<ResourceVO> list;
		try{
			list = tools.goPage(sql.toString(), params.toArray(),new ResourceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

}

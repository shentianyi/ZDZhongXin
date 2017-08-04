package com.zd.csms.base.option.dao.mysql;

import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.base.option.dao.IOptionDAO;
import com.zd.csms.base.option.dao.mapper.OptionMapper;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.tools.StringUtil;


public class OptionMysqlDAO extends DAOSupport implements IOptionDAO {
	
	public OptionMysqlDAO(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 获得下拉列表通用方法(下拉列表用)
	 * @param String table_name		物理表名
	 * @param String code_field		代码字段
	 * @param String name_field		名称字段
	 * @param String where_sql		查询语句
	 * @param Object[] where_params	查询参数
	 * @param int[] where_params_type	查询参数类型
	 * @return List<OptionObject>
	 */
	@SuppressWarnings("unchecked")
	public List<OptionObject> options(String table_name,String code_field,String name_field,String where_sql,Object[] where_params,int[] where_params_type){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select distinct ").append(code_field).append(" code, ")
		.append(name_field).append(" name from ").append(table_name);
		
		if(!StringUtil.isBlank(where_sql)){
			sql.append(" where ").append(where_sql);
		}
		if(where_params==null){
			where_params = new Object[]{};
		}
		if(where_params_type==null){
			return this.getJdbcTemplate().query(sql.toString(), where_params, new OptionMapper());
		}
		
		
		return this.getJdbcTemplate().query(sql.toString(), where_params, where_params_type, new OptionMapper());
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionObject> selectSetStepOptionBySet(String setId) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select ").append("WORKFLOW_SET_STEP_ID code, ")
		.append("WORKFLOW_SET_STEP_NAME name from ").append("WORKFLOW_SET_STEP where workflow_set_id='"+setId+"'");
		return this.getJdbcTemplate().query(sql.toString(), new OptionMapper());
	}
	
	/**
	 * 获得下拉列表通用方法(下拉列表用)
	 * @param String table_name		物理表名
	 * @param String code_field		代码字段
	 * @param String name_field		名称字段
	 * @param String where_sql		查询语句
	 * @param Object[] where_params	查询参数
	 * @return List<OptionObject>
	 */
	public List<OptionObject> options(String table_name,String code_field,String name_field,String where_sql,Object[] where_params){
		return options(table_name, code_field, name_field, where_sql, where_params, null);
	}
	
	/**
	 * 获得下拉列表通用方法(下拉列表用)
	 * @param String table_name		物理表名
	 * @param String code_field		代码字段
	 * @param String name_field		名称字段
	 * @param String where_sql		查询语句
	 * @return List<OptionObject>
	 */
	public List<OptionObject> options(String table_name,String code_field,String name_field,String where_sql){
		return options(table_name, code_field, name_field, where_sql, null, null);
	}
	
	/**
	 * 获得下拉列表通用方法(下拉列表用)
	 * @param String table_name		物理表名
	 * @param String code_field		代码字段
	 * @param String name_field		名称字段
	 * @return List<OptionObject>
	 */
	public List<OptionObject> options(String table_name,String code_field,String name_field){
		return options(table_name, code_field, name_field, null, null, null);
	}
	
}

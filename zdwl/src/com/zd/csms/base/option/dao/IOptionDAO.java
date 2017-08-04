package com.zd.csms.base.option.dao;

import java.util.List;

import com.zd.csms.base.option.model.OptionObject;

public interface IOptionDAO {
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
	public List<OptionObject> options(String table_name,String code_field,String name_field,String where_sql,Object[] where_params,int[] where_params_type);
	
	/**
	 * 获得下拉列表通用方法(下拉列表用)
	 * @param String table_name		物理表名
	 * @param String code_field		代码字段
	 * @param String name_field		名称字段
	 * @param String where_sql		查询语句
	 * @param Object[] where_params	查询参数
	 * @return List<OptionObject>
	 */
	public List<OptionObject> options(String table_name,String code_field,String name_field,String where_sql,Object[] where_params);
	
	/**
	 * 获得下拉列表通用方法(下拉列表用)
	 * @param String table_name		物理表名
	 * @param String code_field		代码字段
	 * @param String name_field		名称字段
	 * @param String where_sql		查询语句
	 * @return List<OptionObject>
	 */
	public List<OptionObject> options(String table_name,String code_field,String name_field,String where_sql);
	
	/**
	 * 获得下拉列表通用方法(下拉列表用)
	 * @param String table_name		物理表名
	 * @param String code_field		代码字段
	 * @param String name_field		名称字段
	 * @return List<OptionObject>
	 */
	public List<OptionObject> options(String table_name,String code_field,String name_field);
	
	public List<OptionObject> selectSetStepOptionBySet(String setId);
	
	
}

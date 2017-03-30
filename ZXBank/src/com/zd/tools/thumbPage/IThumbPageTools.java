package com.zd.tools.thumbPage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.RowMapper;

/**
 * 翻页工具接口类
 * 根据数据库类型不同会自动统计全部记录数及查询当前页记录
 * 如查询sql损耗高，可通过setCountSQL设置高效的查询全部记录sql
 * */
public interface IThumbPageTools {
	
	/**
	 * 查询翻页数据
	 * @param sql
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List goPage(String sql);

	/**
	 * 查询翻页数据
	 * @param sql
	 * @param args
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List goPage(String sql,Object[] args);

	/**
	 * 查询翻页数据
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List goPage(String sql,Object[] args, int[] argTypes);

	/**
	 * 查询翻页数据
	 * @param sql
	 * @param rowMapper
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List goPage(String sql,RowMapper rowMapper);

	/**
	 * 查询翻页数据
	 * @param sql
	 * @param args
	 * @param rowMapper
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List goPage(String sql,Object[] args,RowMapper rowMapper);

	/**
	 * 查询翻页数据
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @param rowMapper
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List goPage(String sql,Object[] args, int[] argTypes,RowMapper rowMapper);

	/**
	 * 初始化
	 * @param tableName
	 * @param request
	 * @return void
	 * */
	public void init(String tableName,HttpServletRequest request);
	
	/**
	 * 记录查询条件
	 * @param queryVO
	 * @return void
	 * */
	public void saveQueryVO(Object queryVO);
	
	/**
	 * 获取上次记录的查询条件
	 * @return Object
	 * */
	public Object getQueryVO();
	
	/**
	 * 设置全部结果数查询sql
	 * 在查询数据sql查询全部结果数执行效率低时可使用其它高效sql代替
	 * @param sql 
	 * @param Object[] params
	 * @param int[] types
	 * @return void
	 * */
	public void setCountSQL(String sql,Object[] params,int[] types);
	
	/**
	 * 设置全部结果数查询sql
	 * 在查询数据sql查询全部结果数执行效率低时可使用其它高效sql代替
	 * @param sql 
	 * @param Object[] params
	 * @return void
	 * */
	public void setCountSQL(String sql,Object[] params);
	
	/**
	 * 设置全部结果数查询sql
	 * 在查询数据sql查询全部结果数执行效率低时可使用其它高效sql代替
	 * @param sql 
	 * @return void
	 * */
	public void setCountSQL(String sql);
	
	public void setPageSize(int num);
	
}

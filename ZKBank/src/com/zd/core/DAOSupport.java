package com.zd.core;


import org.springframework.jdbc.core.RowMapper;

import com.zd.core.jdbc.JdbcTemplate;
import com.zd.core.util.ObjectSQLUtil;

/**
 * dao支撑，使用框架封装后的jdbc模板
 * */
/**
 * 
 *
 * @author licheng
 * at 2016年7月12日 下午5:01:41
 * 
 * &copy;北京科码先锋软件技术有限公司 2016
 */
public class DAOSupport implements IDAO{
	
	private String dataSourceName;
	private JdbcTemplate jdbc;
	
	/**
	 * 构造函数，用于构造jdbctemplate实例
	 * @param String dataSourceName 数据源名称，配置于applicationContext.xml，为null时使用默认数据源
	 * */
	public DAOSupport(String dataSourceName){
		this.dataSourceName = dataSourceName;
		jdbc = BeanManager.getJdbcTemplate(dataSourceName);
	}

	/**
	 * 获取JdbcTemplate实例，提供给dao子类使用
	 * @return com.comall.core.jdbc.JdbcTemplate
	 * */
	public JdbcTemplate getJdbcTemplate(){
		return jdbc;
	}

	/**
	 * 获取dataSourceName名称，供开启事务及编辑分页sql等使用
	 * @return String
	 * */
	public String getDataSourceName() {
		return dataSourceName;
	}

	/**
	 * 设置dataSourceName名称
	 * @param String dataSourceName 数据源名称
	 * @return void
	 * */
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	
	
	/**
	 * 通用新增,返回新增的主键Id
	 * @author licheng at 2016年7月12日 下午5:01:43
	 * @param obj
	 * @return
	 */
	public boolean add(Object obj){
		try {
			getJdbcTemplate().add(ObjectSQLUtil.getAdd(obj),ObjectSQLUtil.getAddParameters(obj));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 通用更新方法，返回是否执行成功
	 * @author licheng at 2016年7月12日 下午5:28:34
	 * @param obj
	 * @return
	 */
	public boolean update(Object obj){
		try {
			return getJdbcTemplate().update(ObjectSQLUtil.getUpdate(obj),ObjectSQLUtil.getUpdateParameters(obj))>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	/** 
	 * 根据主键ID删除
	 * @see com.zd.core.IDAO#delete(java.lang.Class, java.io.Serializable)
	 * @author licheng at 2016年7月13日 上午11:06:35
	 * @param classType
	 * @param id
	 * @return
	 */
	@Override
	public boolean delete(Class<?> classType, int id) {
		try {
			String sql = ObjectSQLUtil.getDelete(classType);
			return update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/** 
	 * 自定义SQL
	 * @see com.zd.core.IDAO#update(java.lang.String, java.lang.Object[])
	 * @author licheng at 2016年7月13日 上午11:06:59
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public boolean update(String sql, Object... params) {
		return getJdbcTemplate().update(sql, params)>0;
	}

	
	/** 
	 * 根据主键获取ID
	 * @see com.zd.core.IDAO#get(java.lang.Class, int, org.springframework.jdbc.core.RowMapper)
	 * @author licheng at 2016年7月13日 下午1:58:31
	 * @param elementType
	 * @param id
	 * @param rowMapper
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> elementType, int id,RowMapper rowMapper) {
		String sql = null;
		try {
			sql = ObjectSQLUtil.getQuery(elementType.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return (T) getJdbcTemplate().queryForObject(sql, new Object[]{id}, rowMapper);
	}
}

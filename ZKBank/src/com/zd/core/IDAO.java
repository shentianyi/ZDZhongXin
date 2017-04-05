package com.zd.core;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 *
 * @author licheng
 * at 2016年7月13日 下午1:55:32
 * 
 * &copy;北京科码先锋软件技术有限公司 2016
 */
/**
 * 
 *
 * @author licheng
 * at 2016年7月13日 下午1:55:46
 * 
 * &copy;北京科码先锋软件技术有限公司 2016
 */
public interface IDAO {
	public String getDataSourceName();
	/**
	 * 通用新增方法
	 * @author licheng at 2016年7月13日 下午1:55:06
	 * @param obj
	 * @return
	 */
	public boolean add(Object obj);
	
	/**
	 * 通用更新方法 
	 * @author licheng at 2016年7月13日 下午1:55:17
	 * @param obj
	 * @return
	 */
	public boolean update(Object obj);
	
	/**
	 * 通用执行sql方法
	 * @author licheng at 2016年7月13日 下午1:55:36
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean update(String sql,Object... params);
	
	/**
	 * 通用删除方法
	 * @author licheng at 2016年7月13日 下午1:55:53
	 * @param classType
	 * @param id
	 * @return
	 */
	public boolean delete(Class<?> classType,int id);
	
	
	/**
	 * 根据id获取目标实体方法
	 * @author licheng at 2016年7月13日 下午1:56:11
	 * @param elementType
	 * @param id
	 * @param rowMapper
	 * @return
	 */
	public <T> T get(Class<T> elementType, int id,RowMapper rowMapper);
	
}

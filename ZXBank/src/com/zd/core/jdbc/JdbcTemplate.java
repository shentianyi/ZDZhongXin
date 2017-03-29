package com.zd.core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * 基于spring扩展后的jdbc模板
 * 重写了queryForList，queryForMap方法，避免使用到原方法返回的Map对象，该对象生成很消耗内存。
 * 增加了add方法，执行后可获得新增数据的主键对象
 * */
public class JdbcTemplate extends org.springframework.jdbc.core.JdbcTemplate {
	
	/**
	 * 构造函数
	 * */
	public JdbcTemplate(){
		super();
	}

	/**
	 * 构造函数
	 * @param dataSource 数据源
	 * */
	public JdbcTemplate(DataSource dataSource){
		super(dataSource);
	}

	/**
	 * 构造函数
	 * @param dataSource 数据源
	 * @param lazyInit
	 * */
	public JdbcTemplate(DataSource dataSource,boolean lazyInit){
		super(dataSource,lazyInit);
	}

	/**
	 * 格式化执行参数
	 * 当时间类型参数为0时按空处理
	 * @param dataSource 数据源
	 * @param lazyInit
	 * */
	private void formateParams(Object[] params){
		
		for(int i=0;i<params.length;i++){
			if(params[i] instanceof Date){
				Date date = (Date)params[i];
				if(date.getTime()==0){
					params[i] = null;
				}else{
					params[i] = new Timestamp(date.getTime());
				}
			}
		}
	}

	/**
	 * 新增数据方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @return KeyHolder 新增数据主键对象
	 * */
	public KeyHolder add(String sql,Object[] params){
		formateParams(params);
		return add(sql,params,null);
	}

	/**
	 * 新增数据方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @param types 执行参数类型
	 * @return KeyHolder 新增数据主键对象
	 * */
	public KeyHolder add(final String sql,final Object[] params,final int[] types){
		formateParams(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		super.update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				if(types==null){
					for (int i = 0; i < params.length; i++) {
						ps.setObject(i + 1, params[i]);
					}
				}else{
					for (int i = 0; i < params.length; i++) {
						ps.setObject(i + 1, params[i], types[i]);
					}
				}
				return ps;
			}
		}, keyHolder);
		return keyHolder;
	}

	/**
	 * 数据操作方法
	 * 看用于修改或删除，不建议用于自增主键的新增方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @return int 操作影响的数据条数
	 * */
	public int update(String sql,Object[] params){
		formateParams(params);
		return super.update(sql,params);
	}

	/**
	 * 数据操作方法
	 * 看用于修改或删除，不建议用于自增主键的新增方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @param types 执行参数类型
	 * @return int 操作影响的数据条数
	 * */
	public int update(String sql,Object[] params,int[] types){
		formateParams(params);
		return super.update(sql,params,types);
	}

	/**
	 * 查询单条记录方法
	 * @param sql 执行sql
	 * @param mapper 数据与类型转换对象
	 * @return Object
	 * */
	public Object queryForObject(String sql,RowMapper mapper){
		List<?> result = super.query(sql, mapper);
		if(!result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 查询单条记录方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @param mapper 数据与类型转换对象
	 * @return Object
	 * */
	public Object queryForObject(String sql,Object[] params,RowMapper mapper){
		List<?> result = super.query(sql, params, mapper);
		if(!result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 查询单条记录方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @param types 执行参数类型
	 * @param mapper 数据与类型转换对象
	 * @return Object
	 * */
	public Object queryForObject(String sql,Object[] params,int[] types,RowMapper mapper){
		List<?> result = super.query(sql, params, types, mapper);
		if(!result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 查询单条记录方法
	 * @param sql 执行sql
	 * @param clazz 查询结果对象类型（可使用Timestamp或Long等）
	 * @return Object
	 * */
	@SuppressWarnings("unchecked")
	public Object queryForObject(String sql,Class clazz){
		List<?> result = super.queryForList(sql, clazz);
		if(!result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 查询单条记录方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @param clazz 查询结果对象类型（可使用Timestamp或Long等）
	 * @return Object
	 * */
	@SuppressWarnings("unchecked")
	public Object queryForObject(String sql,Object[] params,Class clazz){
		List result = super.queryForList(sql, params, clazz);
		if(!result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 查询单条记录方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @param types 执行参数类型
	 * @param clazz 查询结果对象类型（可使用Timestamp或Long等）
	 * @return Object
	 * */
	@SuppressWarnings("unchecked")
	public Object queryForObject(String sql,Object[] params,int[] types,Class clazz){
		List result = super.queryForList(sql, params, types, clazz);
		if(!result.isEmpty()){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 以map为返回结果类型的查询方法
	 * @param sql 执行sql
	 * @return List<Map<String, Object>>
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryForList(String sql){
		return super.query(sql, new DBMapMapper());
	}

	/**
	 * 以map为返回结果类型的查询方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @return List<Map<String, Object>>
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryForList(String sql, Object[] params){
		return super.query(sql, params, new DBMapMapper());
	}

	/**
	 * 以map为返回结果类型的查询方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @param types 执行参数类型
	 * @return List<Map<String, Object>>
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryForList(String sql, Object[] params, int[] types){
		return super.query(sql, params, types, new DBMapMapper());
	}

	/**
	 * 以map为返回结果类型的查询方法
	 * @param sql 执行sql
	 * @return Map<String, Object>
	 * */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryForMap(String sql){
		return (Map<String, Object>)queryForObject(sql, new DBMapMapper());
	}

	/**
	 * 以map为返回结果类型的查询方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @return Map<String, Object>
	 * */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryForMap(String sql,Object[] params){
		return (Map<String, Object>)queryForObject(sql, params, new DBMapMapper());
	}

	/**
	 * 以map为返回结果类型的查询方法
	 * @param sql 执行sql
	 * @param params 执行参数
	 * @param types 执行参数类型
	 * @return Map<String, Object>
	 * */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryForMap(String sql, Object[] params, int[] arg2){
		return (Map<String, Object>)queryForObject(sql, params, arg2, new DBMapMapper());
	}

	/**
	 * 基于DBMap的数据影响对象
	 * 用于将查询结果转换为DBMap类型返回
	 * */
	class DBMapMapper implements RowMapper {
		
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			DBMap temp = new DBMap();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			for(int i=1;i<=metaData.getColumnCount();i++){
				String columnName = metaData.getColumnName(i);
				temp.put(columnName.toUpperCase(),rs.getObject(columnName));
			}

			return temp;

		}
	}
	
}

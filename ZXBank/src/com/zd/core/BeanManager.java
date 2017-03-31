package com.zd.core;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;

/**
 * 用于管理Spring-ioc的注入对象，并提供了与数据库连接相关对象的获取方法
 * */
public class BeanManager {

	//核心属性，用于读取配置文件创建spring组件实例
	private static ApplicationContext factory = null;
	
	/**
	 * 获取ApplicationContext
	 * @return ApplicationContext
	 * */
	public static ApplicationContext getFactory() {
		return factory;
	}
	
	/**
	 * 设置ApplicationContext，服务启动时通过ContextListener设置
	 * @param ApplicationContext factory
	 * @return void
	 * */
	public static void setFactory(ApplicationContext factory) {
		BeanManager.factory = factory;
	}
	
	/**
	 * 通过名称获取注入对象实例
	 * 依赖于Spring-ioc实现，在服务启动时通过com.comall.core.listener.ClassLoaderListener将Spring的ApplicationContext设置到BeanManager中，再通过ApplicationContext的getBean方法实现功能。
	 * @param String beanName 注入对象名称
	 * @return Object
	 * */
	public static Object getBean(String beanName) {
		return factory.getBean(beanName);
	}
	
	/**
	 * 通过名称获取数据源，用于创建jdbctemplate实例和事务实例
	 * 依赖于Spring数据库连接技术实现，使用jdbc.properties文件管理数据库连接配置，设计上支持多数据源配置。
	 * @param String name 数据源名称，与jdbc.properties配置保持一致，如name为null则默认为dataSource
	 * @return org.springframework.jdbc.datasource.DriverManagerDataSource
	 * */
	public static DataSource getDataSource(String name){
		if(name==null){
			name = "dataSource";
		}
		return (DataSource)factory.getBean(name);
	}
	
	/**
	 * 通过数据源名称获取数据库驱动名，用于判断数据库类型
	 * @param String dataSourceName 数据源名称，与jdbc.properties配置保持一致，如name为null则默认为dataSource
	 * @return String
	 * */
	public static String getDbDriver(String dataSourceName){
//		org.springframework.jdbc.datasource.DriverManagerDataSource ds = getDataSource(dataSourceName);
//		if(ds!=null){
//			return ds.getDriverClassName();
//		}
		return "oracle.jdbc.driver.OracleDriver";
	}
	
	/**
	 * 通过数据源名称获取spring原版的JDBC模板
	 * @param String dataSourceName 数据源名称，与jdbc.properties配置保持一致，如name为null则默认为dataSource
	 * @return org.springframework.jdbc.core.JdbcTemplate
	 * */
	static org.springframework.jdbc.core.JdbcTemplate getSpringJdbcTemplate(String dataSourceName) {
		return new org.springframework.jdbc.core.JdbcTemplate(getDataSource(dataSourceName));
	}
	
	/**
	 * 通过数据源名称获取JDBC模板
	 * @param String dataSourceName 数据源名称，与jdbc.properties配置保持一致，如name为null则默认为dataSource
	 * @return com.comall.core.jdbc.JdbcTemplate
	 * */
	static com.zd.core.jdbc.JdbcTemplate getJdbcTemplate(String dataSourceName) {
		return new com.zd.core.jdbc.JdbcTemplate(getDataSource(dataSourceName));
	}
	
	/**
	 * 通过数据源名称获得JTA事务模板
	 * @param String dataSourceName 数据源名称，与jdbc.properties配置保持一致，如name为null则默认为dataSource
	 * @return org.springframework.transaction.support.TransactionTemplate
	 * */
	static org.springframework.transaction.support.TransactionTemplate getTransactionTemplate(String dataSourceName) {
		 return new org.springframework.transaction.support.TransactionTemplate(getDataSourceTransactionManager(dataSourceName));
	}
	
	/**
	 * 通过数据源名称获得JTA事物管理类
	 * @param String dataSourceName 数据源名称，与jdbc.properties配置保持一致，如name为null则默认为dataSource
	 * @return org.springframework.jdbc.datasource.DataSourceTransactionManager
	 * */
	static org.springframework.jdbc.datasource.DataSourceTransactionManager getDataSourceTransactionManager(String dataSourceName) {
		return new org.springframework.jdbc.datasource.DataSourceTransactionManager(getDataSource(dataSourceName)) ;
	}
	
}

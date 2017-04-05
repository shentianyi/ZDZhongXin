package com.zd.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * service类支撑，提供事务管理
 * */
public class ServiceSupport {
	
	private Map<String,TransactionStatus> tsmap = new ConcurrentHashMap<String, TransactionStatus>();
	private Map<String,DataSourceTransactionManager> tmmap = new ConcurrentHashMap<String, DataSourceTransactionManager>();
	
	private Logger log = Logger.getLogger(ServiceSupport.class);
	
	private String executeMessage;	//用于记录service方法执行过程中错误信息，方便返回给controller消息

	/**
	 * 开启新事务
	 * @param String dataSourceName 数据源名称
	 * @return boolean	//true：成功开启事务，false：已存在事务
	 * */
	protected boolean transactionBegin(){
		return transactionBegin(null);
	}

	/**
	 * 开启新事务
	 * @param String dataSourceName 数据源名称
	 * @return boolean	//true：成功开启事务，false：已存在事务
	 * */
	protected boolean transactionBegin(String dataSourceName){
		
		TransactionStatus ts = tsmap.get(dataSourceName);
		DataSourceTransactionManager tm = tmmap.get(dataSourceName);
		
		if(ts!=null){
			log.debug("已存在事务，开启事务失败");
			return false;
		}
		
		if(tm==null){
			tm = BeanManager.getDataSourceTransactionManager(dataSourceName);
			if(tm!=null){
				tmmap.put(dataSourceName, tm);
			} else{
				log.debug(dataSourceName + " 数据源配置错误，开启事务失败");
				return false;
			}
		}

		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);

		ts = tm.getTransaction(td);
		tsmap.put(dataSourceName, ts);
		
		return true;
	}
	
	/**
	 * 提交事务
	 * @param String dataSourceName 数据源名称
	 * @return boolean	//true：成功提交事务，false：事务不存在
	 * */
	protected boolean transactionCommit(){
		return transactionCommit(null);
	}
	
	/**
	 * 提交事务
	 * @param String dataSourceName 数据源名称
	 * @return boolean	//true：成功提交事务，false：事务不存在
	 * */
	protected boolean transactionCommit(String dataSourceName){
		
		TransactionStatus ts = tsmap.get(dataSourceName);
		DataSourceTransactionManager tm = tmmap.get(dataSourceName);

		if(ts==null || tm==null){
			log.debug("不存在事务，提交事务失败");
			return false;
		}
		
		try{
			tm.commit(ts);
		}
		catch(Exception e){
			tm.rollback(ts);
			log.error("提交事务出现异常，事务回滚", e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * 回滚事务
	 * @param String dataSourceName 数据源名称
	 * @return boolean	//true：成功回滚事务，false：事务不存在
	 * */
	protected boolean transactionRollback(){
		
		return transactionRollback(null);
	}
	
	/**
	 * 回滚事务
	 * @param String dataSourceName 数据源名称
	 * @return boolean	//true：成功回滚事务，false：事务不存在
	 * */
	protected boolean transactionRollback(String dataSourceName){
		
		TransactionStatus ts = tsmap.get(dataSourceName);
		DataSourceTransactionManager tm = tmmap.get(dataSourceName);
		
		if(ts==null || tm==null){
			log.debug("不存在事务，回滚事务失败");
			return false;
		}
		
		tm.rollback(ts);
		
		return true;
	}
	
	/**
	 * 获得事务模板对象
	 * @param String dataSourceName 数据源名称
	 * @return org.springframework.transaction.support.TransactionTemplate
	 * */
	protected TransactionTemplate getTransactionTemplate(String dataSourceName) {
		return BeanManager.getTransactionTemplate(dataSourceName);
	}

	/**
	 * 获取执行结果消息
	 * @return String
	 * */
	public String getExecuteMessage() {
		return executeMessage;
	}

	/**
	 * 设置执行结果消息
	 * @param String executeMessage
	 * @return void
	 * */
	public void setExecuteMessage(String executeMessage) {
		this.executeMessage = executeMessage;
	}

}

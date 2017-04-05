package com.zd.csms.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

public class Environment {

	private static Log log = LogFactory.getLog(Environment.class);

	private DataSource dataSource;

	/**
     *
     */
	private static DataSource _dataSource;

	/**
     *
     */
	private static DataSource _underlyingDS = null;

	/**
	 * 用于各种全局的可配置的数据（比如：上传文件路径 等等）
	 */
	private Map attributes;

	public static Environment env;

	private ApplicationContext appCtx;

	public static Environment getEnv() {
		if (env == null) {
			env = new Environment();
		}
		return env;
	}

	protected static Environment createInstance() throws java.io.UnsupportedEncodingException, java.io.IOException, java.security.cert.CertificateException, Exception {
		if (getEnv() != null) {
			return getEnv();
		} else {
			env = new Environment();
			return env;
		}
	}

	public Environment() {
		attributes = new ConcurrentHashMapExt();
	}


	/**
	 * 该方法只用于获取表内部Id值的地方使用，因为getID不希望参与到其他Transaction中。 其他的方法中禁止使用该方法来获取数据库连接
	 * 
	 * @return Connection 数据库连接对象
	 * @throws SQLException
	 */
	private static Connection _getUnderlyingConnection() throws SQLException {
		return _underlyingDS.getConnection();
	}

	public Object getBean(String name) {
		try {
			if (getAppCtx() != null) {
				return getAppCtx().getBean(name);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * 获取DataSource对象
	 * 
	 * @return DataSource
	 */
	private DataSource getDataSource() {
		if (getAppCtx() != null) {
			DataSource ds = (DataSource) getBean("dataSource");
			if (ds != null) {
				return ds;
			}
		}

		return this.dataSource;
	}

	/**
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = this.getDataSource().getConnection();
		} catch (Exception e) {
			log.error("get connection error", e);
		}
		return conn;
	}

	/**
	 * 获取卡系统数据库的DataSource对象
	 */
	private DataSource cardDataSource;

	private DataSource getCardDataSource() {
		if (getAppCtx() != null) {
			DataSource ds = (DataSource) getBean("carddatasource");
			if (ds != null) {
				return ds;
			}
		}

		return this.cardDataSource;
	}

	/**
	 * 获得卡系统数据库的连接对象
	 */
	public Connection getCardConnection() {
		Connection conn = null;
		try {
			conn = this.getCardDataSource().getConnection();
		} catch (Exception e) {
			log.error("get connection error", e);
		}

		return conn;
	}

	/**
	 * 读取CRM数据源
	 * 
	 * @return
	 */
	private DataSource getCRMDataSource() {
		if (getAppCtx() != null) {
			DataSource ds = (DataSource) getBean("crmdatasource");
			if (ds != null) {
				return ds;
			}
		}

		return this.dataSource;
	}

	/**
	 * 获取CRM数据源链接
	 * 
	 * @return
	 */
	public Connection getCRMConnection() {
		Connection conn = null;
		try {
			conn = this.getCRMDataSource().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 获取商户系统中间库数据源
	 * 
	 * @return
	 */
	private DataSource getMerchantDataSource() {
		if (getAppCtx() != null) {
			DataSource ds = (DataSource) getBean("merchantdatasource");
			if (ds != null) {
				return ds;
			}
		}

		return this.dataSource;
	}

	/**
	 * 获取商户系统中间库数据库连接
	 * 
	 * @return
	 */
	public Connection getMerchantConnection() {
		Connection conn = null;
		try {
			conn = this.getMerchantDataSource().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 读取wms数据源
	 * 
	 * @return
	 */
	private DataSource getWMSDataSource() {
		if (getAppCtx() != null) {
			DataSource ds = (DataSource) getBean("wmsdatasource");
			if (ds != null) {
				return ds;
			}
		}

		return this.dataSource;
	}

	/**
	 * 获取CRM数据源链接
	 * 
	 * @return
	 */
	public Connection getWMSConnection() {
		Connection conn = null;
		try {
			conn = this.getWMSDataSource().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * @return int
	 */
	public static int getActiveConnectionCount() {
		BasicDataSource ds = (BasicDataSource) _underlyingDS;
		return ds.getNumActive();
	}



	/**
	 * 因为 appctx 的注 入是在 servlet 的init 中完成，而如果在 listener 或 filter 中想获取 applicationcontext 将获取为空，所以加一步判断，防止获取不到bean
	 * 
	 * @return
	 */
	public ApplicationContext getAppCtx() {
		return (appCtx == null) ? ContextLoader.getCurrentWebApplicationContext() : appCtx;
	}

	public void setAttribute(String key, Object value) {
		if (null == value) {
			this.attributes.remove(key);
		} else {
			this.attributes.put(key, value);
		}
	}

	/**
	 * 得到初始化属性，这些属性由初始化程序从Web.xml读取
	 * 
	 * @param key
	 *            String
	 * @return Object
	 */
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	

	

	/**
	 * 取得上传目录的实际物理路径
	 * 
	 * @return String
	 */
	public String getRealUploadPath() {
		return (String) this.getAttribute("RealUploadPath");
	}

	public String getContext() {
		String contextPath = (String) getAttribute("ContextPath");
		if (contextPath == null)
			return "";
		if (contextPath.equals("/")) {
			return "";
		}
		return contextPath;
	}

	public String getWebAppPath() {
		String webAppPath = (String) getAttribute("WebAppPath");
		return webAppPath;
	}

	public String getUploadPath() {

		String contextPath = (String) getAttribute("ContextPath");
		if (contextPath.length() > 0) {

			if (contextPath.startsWith("/")) {
				return contextPath + (String) getAttribute("UploadPath");
			} else {
				return "/" + contextPath + (String) getAttribute("UploadPath");
			}
		} else {
			return (String) getAttribute("UploadPath");
		}
	}

}

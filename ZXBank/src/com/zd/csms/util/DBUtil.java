package com.zd.csms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Description: 有关于数据库操作的工具类，提供了一些方法集合（比如：取得表的id值，取得一个查询的分页sql 。。。 ）
 * @version 1.0
 */
public class DBUtil {
	/**
	 * 启动日志
	 */
	private static Log log = LogFactory.getLog(DBUtil.class);
	/**
	 * 保存ids表中各个表的最大id数据值
	 */
	static ConcurrentHashMapExt Ids = null;

	public DBUtil() {
	}

	/**
	 * Sql片断 如：length(trim(realName)) is not null and length(trim(realName))>0
	 * 
	 * @param fieldName
	 *            String 字段名
	 * @return String
	 */
	public static String getOracleNotNullSql(String fieldName) {
		StringBuffer sql = new StringBuffer();
		if (fieldName == null || fieldName.trim().length() == 0) {
			return sql.toString();
		}
		sql.append(" length(trim(");
		sql.append(fieldName);
		sql.append(")) is not null and length(trim(");
		sql.append(fieldName);
		sql.append("))>0 ");

		return sql.toString();
	}

	/**
	 * Sql片断 如：length(trim(realName)) is null
	 * 
	 * @param fieldName
	 *            String
	 * @return String
	 */
	public static String getOracleNullSql(String fieldName) {
		StringBuffer sql = new StringBuffer();
		if (fieldName == null || fieldName.trim().length() == 0) {
			return sql.toString();
		}
		sql.append(" length(trim(");
		sql.append(fieldName);
		sql.append(")) is null ");

		return sql.toString();
	}

	/**
	 * 取得某个表的主健id值
	 * 
	 * @param Name
	 *            String
	 * @return int
	 * @throws Exception
	 */
	public static int getID(String tableName) throws Exception {
		if ("Oracle".equals(DAOManager.getDataBaseType())) {
			return getOracleId(tableName);
		} else if ("mysql".equals(DAOManager.getDataBaseType())) {
			return getMySqlId(tableName);
		}
		return 0;
	}

	/**
	 * 将某个表的所有现有的IDRanges发送出去。
	 * 
	 * @param tableName
	 * @return
	 */
	public static List<IDRange> getAllIdRanges(String tableName) throws Exception {
		Environment env = Environment.getEnv();
		Connection conn = null;
		try {
			String sql;
			if (tableName == null || "".equals(tableName)) {
				sql = "select * from t_idranges";
			} else {
				sql = "select * from t_idranges where tableName=?";
			}

			conn = env.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (tableName != null && !"".equals(tableName)) {
				stmt.setString(1, tableName);
			}

			ResultSet rs = stmt.executeQuery();
			Vector ranges = new Vector();
			while (rs.next()) {
				String dbName = rs.getString("dbName");
				String table = rs.getString("tableName");
				int min = rs.getInt("min");
				int max = rs.getInt("max");
				IDRange range = new IDRange();
				range.setBeginId(min);
				range.setEndId(max);
				range.setDbName(dbName);
				range.setTableName(table);
				ranges.add(range);

			}
			rs.close();
			stmt.close();

			return ranges;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}


	/**
	 * 功能：idsMap键值为表名小写，值为表名所使用的序列名 
	 * 作者：代大龙 
	 * 时间：2011年11月3日11:47:40
	 */
	private static Map<String, String> idsMap = new Hashtable<String, String>();
	/**
	 * 功能：当此类被调用的时候，优先执行，对idsMap集合添加元素 
	 * 作者：代大龙 
	 * 时间：2011年11月3日11:48:26
	 */
	static {
		// 只需要初始化一次即可
		if (idsMap.size() <= 0) {

			Environment env = Environment.getEnv();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				DataSource ds = (DataSource) env.getBean("dataSource");
				conn = ds.getConnection();
				// 表t_seqencedict维护使用序列的表和序列的关系
				stmt = conn.prepareStatement("select t.tablename,t.seqname from t_seqencedict t");
				rs = stmt.executeQuery();
				while (rs.next()) {
					// 表名
					String tableName = rs.getString(1);
					// 序列名
					String squenceName = rs.getString(2);
					// 保证表名小写
					idsMap.put(tableName.toLowerCase(), squenceName);
				}
				log.error("\nSequence Initialized.Total:" + idsMap.size());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}

					if (stmt != null) {
						stmt.close();
					}

					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static int getCartItemId() {
		long l = System.currentTimeMillis();
		int i = 0;
		if (l > 2147483647) {
			String s = String.valueOf(l);
			s = s.substring(s.length() - 9);
			i = Integer.parseInt(s);
		} else {
			i = Integer.parseInt(String.valueOf(l));
		}
		return i;
	}

	/**
	 * 功能：通过表名获得此表在Oracle数据库中的下一条记录ID 
	 * 修改人：代大龙 
	 * 修改时间：2011-11-4 12:49:35
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static int getOracleId(String name) throws Exception {

		if (name != null && name.equals("cartitem")) {
			return getCartItemId();
		}

		int id = 0;
		Environment env = Environment.getEnv();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			DataSource ds = (DataSource) env.getBean("dataSource");
			conn = ds.getConnection();
			// idsMap集合中Key的值为数据库中表名的小写字符串，以此来获得序列
			String seq = idsMap.get(name.toLowerCase());
			// 查询序列的SQL，例如：select SEQPID_T_ACCOUNT.nextval from dual
			String sql = "select  " + seq + ".nextval  from  dual";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				// 最大10位
				id = rs.getInt(1);
				stmt.close();
			} else {
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return id;
	}

	synchronized public static int getMySqlId(String Name) throws Exception {
		int idForThisTime;
		if (Ids == null) {
			Ids = new ConcurrentHashMapExt();
		}
		IdPair id = (IdPair) Ids.get(Name);
		if (id == null) {
			id = new IdPair();
			Ids.put(Name, id);
		}

		if (id.curVal < id.maxVal) {
			idForThisTime = id.curVal++;
			return idForThisTime;
		}

		boolean isOK = false;
		Connection _conn = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Environment env = Environment.getEnv();
		PlatformTransactionManager transactionManager = (PlatformTransactionManager) env.getBean("transactionManager");
		DefaultTransactionDefinition dtf = new DefaultTransactionDefinition();
		dtf.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus ts = transactionManager.getTransaction(dtf);
		try {
			DataSource ds = (DataSource) env.getBean("datasource");
			_conn = ds.getConnection();

			stmt = _conn.prepareStatement("Lock tables t_ids write");
			stmt.execute();
			stmt.close();

			stmt = _conn.prepareStatement("select count(*) as c from t_ids where TableName=? ");
			stmt.setString(1, Name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int c = rs.getInt(1);
				rs.close();
				stmt.close();
				if (c == 0) {
					stmt = _conn.prepareStatement("Insert into t_ids(TableName,NextValue)values(?,?)");
					stmt.setString(1, Name);
					stmt.setInt(2, 50000);
					stmt.execute();
					stmt.close();
				}
			} else {
				rs.close();
				stmt.close();
			}

			stmt = _conn.prepareStatement("select NextValue from t_ids where TableName=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, Name);
			rs = stmt.executeQuery();
			rs.next();
			int ID = rs.getInt("NextValue");

			rs.close();
			stmt.close();

			stmt = _conn.prepareStatement("Update t_ids set NextValue=NextValue+100 where TableName=?");
			stmt.setString(1, Name);
			stmt.execute();
			stmt.close();

			stmt = _conn.prepareStatement("Unlock tables");
			stmt.execute();

			Util.close(null, stmt, null);

			isOK = true;

			id.curVal = ID;
			id.maxVal = ID + 100;
			idForThisTime = id.curVal++;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (isOK) {
				transactionManager.commit(ts);
			} else {
				transactionManager.rollback(ts);
			}
			if (_conn != null && _conn.isClosed() == false) {
				_conn.close();
			}
		}
		return idForThisTime;
	}

	/**
	 * 取得一个sql查询的分页sql字符串
	 * 
	 * @param sql
	 *            String 要分页的sql字符串
	 * @param begin
	 *            int 要取得数据的开始记录
	 * @param number
	 *            int 要去的记录的数量
	 * @return String
	 */
	public static String getLimitString(String sql, int begin, int number) {
		if (begin == -1) {
			begin = 0;
		}
		if (number == -1) {
			number = 2000000000;
		}
		if (DAOManager.getDataBaseType().equals("oracle")) {
			return getOracleLimitString(sql, begin, number);
		} else if (DAOManager.getDataBaseType().equals("mysql")) {
			return getMySqlLimitString(sql, begin, number);
		}
		return null;
	}

	public static String getOracleLimitString(String sql, int begin, int number) {
		int end = begin + number;
		StringBuffer pagingSelect = new StringBuffer(100);
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_  ) where rownum_ <= " + end + "  and rownum_ > " + begin);
		return pagingSelect.toString();
	}

	public static String getMySqlLimitString(String sql, int begin, int number) {
		StringBuffer pagingSelect = new StringBuffer(100);
		pagingSelect.append(sql);
		pagingSelect.append(" limit " + begin + ", " + number);
		return pagingSelect.toString();
	}

	/**
	 * 关闭数据库操作对象
	 * 
	 * @param conn
	 *            Connection
	 * @param pstmt
	 *            PreparedStatement
	 * @param rs
	 *            ResultSet
	 */
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {

		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {

		}
		try {
			if (conn != null) {
				if (conn.isClosed() == false) {
					conn.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void close(Connection conn, Statement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {

		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {

		}
		try {
			if (conn != null) {
				if (conn.isClosed() == false) {
					conn.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 获取数据库的当前时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Timestamp getDatabaseTimestamp() throws Exception {
		if (DAOManager.getDataBaseType().equals("oracle")) {
			return getOracleTimestamp();
		} else if (DAOManager.getDataBaseType().equals("mysql")) {
			throw new Exception("not supported. currently only supports oracle");
		} else {
			throw new Exception("not supported. currently only supports oracle");
		}
	}

	public static Timestamp getOracleTimestamp() throws Exception {
		String sql = "Select sysdate from dual";
		Connection conn = null;
		Environment env = null;
		Timestamp ret = null;
		try {
			env = Environment.getEnv();
			conn = env.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ret = rs.getTimestamp(1);
			}
			rs.close();
			stmt.close();
			return ret;
		} finally {
			if (conn != null && conn.isClosed() == false) {
				conn.close();
			}
		}
	}
}

class IdPair {
	public IdPair() {
		curVal = 0;
		maxVal = 0;
	}

	public int curVal;
	public int maxVal;
}

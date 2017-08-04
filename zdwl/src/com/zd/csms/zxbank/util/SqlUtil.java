package com.zd.csms.zxbank.util;

import java.sql.*;
import java.sql.SQLException;

import com.zd.core.BeanManager;
import com.zd.core.annotation.table;

public class SqlUtil {
	private static Connection conn;
	private static PreparedStatement stmt;
	private static ResultSet rs;

	public static int getID(Class<?> clazz) {
		int id = 0;
		String seq = clazz.getAnnotation(table.class).name() + "Id";
		String sql = "select  " + seq + ".nextval  from  dual";

		try {
			conn = BeanManager.getDataSource("dataSource").getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()){
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {}
		}
		return id;
	}
}

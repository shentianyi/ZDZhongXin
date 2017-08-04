package com.zd.tools.time.dao.mysql;

import java.sql.Timestamp;

import com.zd.core.DAOSupport;
import com.zd.tools.time.dao.ITimeDAO;

public class TimeMysqlDAO extends DAOSupport implements ITimeDAO {
	
	public TimeMysqlDAO(String dataSourceName){
		super(dataSourceName);
	}
	
	public Timestamp getCurrentTimestamp(){
    	String sql = "SELECT SYSDATE() AS SYSTIME FROM DUAL";
        return (Timestamp) this.getJdbcTemplate().queryForObject(sql,java.sql.Timestamp.class);
    }

}

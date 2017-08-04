package com.zd.tools.time.dao.oracle;

import java.sql.Timestamp;

import com.zd.core.DAOSupport;
import com.zd.tools.time.dao.ITimeDAO;

public class TimeOracleDAO extends DAOSupport implements ITimeDAO {
	
	public TimeOracleDAO(String dataSourceName){
		super(dataSourceName);
	}
	
	public Timestamp getCurrentTimestamp(){
    	String sql = "SELECT SYSTIMESTAMP FROM DUAL";
        return (Timestamp) this.getJdbcTemplate().queryForObject(sql,java.sql.Timestamp.class);
    }
}

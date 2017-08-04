package com.zd.csms.base.option.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.base.option.model.OptionObject;

public class OptionMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		OptionObject temp = new OptionObject(rs.getString("code"),rs.getString("name"));
		
		return temp;
	}
}

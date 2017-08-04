package com.zd.csms.supervisorymanagement.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisorymanagement.model.SystemManageVO;

public class SystemManageMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SystemManageVO o = new SystemManageVO();
		o.setId(rs.getInt("id"));
		o.setUsername(rs.getString("username"));
		o.setStation(rs.getString("station"));
		o.setLoginid(rs.getString("loginid"));
		o.setPassword(rs.getString("password"));
		o.setDes(rs.getString("des"));
		return o;
	}

}

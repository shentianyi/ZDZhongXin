package com.zd.csms.rbac.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.rbac.model.RoleVO;

public class RoleMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		RoleVO o = new RoleVO();
		o.setId(rs.getInt("id"));
		o.setState(rs.getInt("state"));
		o.setRole_name(rs.getString("role_name"));
		o.setClient_type(rs.getInt("client_type"));
		o.setDes(rs.getString("des"));
		return o;
	}

}

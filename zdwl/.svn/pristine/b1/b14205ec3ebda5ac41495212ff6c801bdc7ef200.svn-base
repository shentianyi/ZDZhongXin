package com.zd.csms.rbac.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.rbac.model.RoleResourceVO;

public class RoleResourceMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		RoleResourceVO o = new RoleResourceVO();
		o.setId(rs.getInt("id"));
		o.setResource_id(rs.getInt("resource_id"));
		o.setRole_id(rs.getInt("role_id"));
		return o;
	}

}

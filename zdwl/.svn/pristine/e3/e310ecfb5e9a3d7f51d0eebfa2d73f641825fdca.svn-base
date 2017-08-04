package com.zd.csms.rbac.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.rbac.model.UserRoleVO;


public class UserRoleMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		UserRoleVO temp = new UserRoleVO();
		temp.setId(rs.getInt("id"));
		temp.setUser_id(rs.getInt("user_id"));	//账户id
		temp.setRole_id(rs.getInt("role_id"));	//角色id
		return temp;
	}

}

package com.zd.csms.rbac.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.rbac.model.UserVO;


public class YwBankUserMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		UserVO o = new UserVO();
		o.setId(rs.getInt("id"));
		o.setUserName(rs.getString("username"));
		o.setLoginid(rs.getString("loginid"));
		o.setPassword(rs.getString("password"));
		o.setPassword_random(rs.getString("password_random"));
		o.setMobilephone(rs.getString("mobilephone"));
		o.setEmail(rs.getString("email"));
		o.setState(rs.getInt("state"));
		o.setClient_type(rs.getInt("client_type"));
		o.setClient_id(rs.getInt("client_id"));
		o.setCreatetime(rs.getTimestamp("createtime"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getDate("upddate"));
		return o;
	}

}

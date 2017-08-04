package com.zd.csms.supervisorymanagement.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisorymanagement.model.UsernameManageVO;

public class UsernameManageMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		UsernameManageVO o = new UsernameManageVO();
		o.setId(rs.getInt("id"));
		o.setDistribid(rs.getInt("distribid"));
		o.setBankname(rs.getString("bankname"));
		o.setSupervisory_name(rs.getString("supervisory_name"));
		o.setLoginid(rs.getString("loginid"));
		o.setPassword(rs.getString("password"));
		o.setDes(rs.getString("des"));
		return o;
	}

}

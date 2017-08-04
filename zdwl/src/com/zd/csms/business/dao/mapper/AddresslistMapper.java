package com.zd.csms.business.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.business.model.AddresslistVO;

public class AddresslistMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		AddresslistVO o = new AddresslistVO();
		o.setId(rs.getInt("id"));
		o.setDepartment(rs.getString("department"));
		o.setName(rs.getString("name"));
		o.setQuarters(rs.getString("quarters"));
		o.setPhone(rs.getString("phone"));
		o.setLandline(rs.getString("landline"));
		o.setEmail(rs.getString("email"));
		o.setFax(rs.getString("fax"));
		o.setQq(rs.getString("qq"));
		o.setDuty(rs.getString("duty"));
		o.setGenre(rs.getInt("genre"));
		o.setLoginid(rs.getInt("loginid"));
		return o;
	}

}

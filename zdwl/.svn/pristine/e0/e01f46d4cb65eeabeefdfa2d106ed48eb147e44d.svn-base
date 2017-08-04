package com.zd.csms.business.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.business.model.TwoAddressVO;

public class TwoAddressMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		TwoAddressVO o = new TwoAddressVO();
		o.setId(rs.getInt("id"));
		o.setDistribid(rs.getInt("distribid"));
		o.setTwo_name(rs.getString("two_name"));
		o.setTwo_address(rs.getString("two_address"));
		o.setTwo_username(rs.getString("two_username"));
		o.setPhone(rs.getString("phone"));
		o.setDistance(rs.getString("distance"));
		o.setDes(rs.getString("des"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		o.setType(rs.getInt("type"));
		return o;
	}

}

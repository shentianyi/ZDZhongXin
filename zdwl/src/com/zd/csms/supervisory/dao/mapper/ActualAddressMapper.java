package com.zd.csms.supervisory.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisory.model.ActualAddressVO;

public class ActualAddressMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ActualAddressVO o = new ActualAddressVO();
		o.setId(rs.getInt("id"));
		o.setDistribid(rs.getInt("distribid"));
		o.setAgreement_address(rs.getString("agreement_address"));
		o.setActual_address(rs.getString("actual_address"));
		o.setRelationship(rs.getString("relationship"));
		o.setIsreport(rs.getInt("isreport"));
		o.setDistance(rs.getString("distance"));
		o.setDes(rs.getString("des"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		return o;
	}

}

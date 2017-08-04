package com.zd.csms.supervisory.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisory.model.DuedateVO;

public class DuedateMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		DuedateVO o = new DuedateVO();
		o.setId(rs.getInt("id"));
		o.setSuperviseid(rs.getInt("superviseid"));
		o.setType(rs.getInt("type"));
		o.setPicid(rs.getInt("picid"));
		o.setNextApproval(rs.getInt("nextApproval"));
		o.setApprovalState(rs.getInt("approvalState"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		return o;
	}

}

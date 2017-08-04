package com.zd.csms.supervisory.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisory.model.ExtRepairCostVO;


public class ExtRepairCostVOMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ExtRepairCostVO o = new ExtRepairCostVO();
		o.setId(rs.getInt("id"));
		o.setPromoter(rs.getInt("promoter"));
		o.setRepair_project(rs.getString("repair_project"));
		o.setProblem(rs.getString("problem"));
		o.setMoney(rs.getString("money"));
		o.setCredatetime(rs.getTimestamp("credatetime"));
		o.setNextApproval(rs.getInt("nextApproval"));
		o.setApprovalState(rs.getInt("approvalState"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		o.setDeclareUser(rs.getString("declareUser"));
		o.setCreateUser(rs.getString("createUser"));
		o.setUpdateUser(rs.getString("updateUser"));
		return o;
	}

}

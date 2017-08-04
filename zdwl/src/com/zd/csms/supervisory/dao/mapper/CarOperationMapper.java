package com.zd.csms.supervisory.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisory.model.CarOperationVO;

public class CarOperationMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		CarOperationVO o = new CarOperationVO();
		o.setId(rs.getInt("id"));
		o.setCars(rs.getString("cars"));
		o.setUserid(rs.getInt("userid"));
		o.setType(rs.getInt("type"));
		o.setCreatetime(rs.getTimestamp("createtime"));
		o.setNextApproval(rs.getInt("nextApproval"));
		o.setApprovalState(rs.getInt("approvalState"));
		o.setDistribid(rs.getInt("distribid"));
		return o;
	}

}

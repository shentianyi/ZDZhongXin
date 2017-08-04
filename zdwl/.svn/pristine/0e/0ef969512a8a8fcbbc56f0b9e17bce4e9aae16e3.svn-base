package com.zd.csms.supervisory.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisory.model.BankApproveVO;

public class BankApproveMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		BankApproveVO o = new BankApproveVO();
		o.setId(rs.getInt("id"));
		o.setSid(rs.getInt("sid"));
		o.setState(rs.getInt("state"));
		o.setCreatetime(rs.getTimestamp("createtime"));
		o.setApprovetime(rs.getTimestamp("approvetime"));
		o.setType(rs.getInt("type"));
		return o;
	}

}

package com.zd.csms.supervisory.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisory.model.CarManualVO;

public class CarManualMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		CarManualVO o = new CarManualVO();
		o.setId(rs.getInt("id"));
		o.setSid(rs.getInt("sid"));
		o.setUserid(rs.getInt("userid"));
		o.setReceiveid(rs.getInt("receiveid"));
		
		return o;
	}

}

package com.zd.csms.supervisorymanagement.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisorymanagement.model.SystemSuperviseVO;

public class SystemSuperviseMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SystemSuperviseVO o = new SystemSuperviseVO();
		o.setId(rs.getInt("id"));
		o.setTrade_name(rs.getString("trade_name"));
		o.setBankid(rs.getInt("bankid"));
		o.setBank_fen(rs.getInt("bank_fen"));
		o.setBank_zhi(rs.getInt("bank_zhi"));
		o.setProvince(rs.getInt("province"));
		o.setCity(rs.getInt("city"));
		o.setSupervise_name(rs.getString("supervise_name"));
		o.setJob_num(rs.getString("job_num"));
		o.setLoginid(rs.getString("loginid"));
		o.setPassword(rs.getString("password"));
		o.setDes(rs.getString("des"));
		
		return o;
	}

}

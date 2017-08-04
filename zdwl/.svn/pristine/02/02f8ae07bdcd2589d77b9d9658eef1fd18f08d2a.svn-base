package com.zd.csms.marketing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.marketing.model.BrandVO;

public class BrandMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		BrandVO o = new BrandVO();
		o.setId(rs.getInt("id"));
		o.setName(rs.getString("name"));
		return o;
	}

}

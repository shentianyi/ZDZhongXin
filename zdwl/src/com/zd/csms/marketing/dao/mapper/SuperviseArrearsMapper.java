package com.zd.csms.marketing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.marketing.model.SuperviseArrearsVO;

public class SuperviseArrearsMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SuperviseArrearsVO o = new SuperviseArrearsVO();
		o.setId(rs.getInt("id"));
		o.setDistribid(rs.getInt("distribid"));
		o.setFinancial_institution(rs.getString("financial_institution"));
		o.setArrears_begin(rs.getTimestamp("arrears_begin"));
		o.setArrears_end(rs.getTimestamp("arrears_end"));
		o.setArrears_money(rs.getString("arrears_money"));
		o.setArrears_user(rs.getString("arrears_user"));
		o.setArrears_phone(rs.getString("arrears_phone"));
		o.setFollow_date(rs.getTimestamp("follow_date"));
		o.setFollow_user(rs.getString("follow_user"));
		o.setFollow_result(rs.getString("follow_result"));
		return o;
	}

}

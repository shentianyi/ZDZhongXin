package com.zd.csms.attendance.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.attendance.model.SignRecordCheckSpAll;

public class SignRecordCheckSpAllMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		SignRecordCheckSpAll temp = new SignRecordCheckSpAll();
		temp.setBanksName(rs.getString("banksName"));
		temp.setDealersName(rs.getString("dealersName"));
		temp.setRespId(rs.getInt("respId"));
		temp.setName(rs.getString("name"));
		temp.setStaffNo(rs.getString("staffNo"));
		temp.setProvince(rs.getString("province"));
		temp.setProvinceName(rs.getString("provinceName"));
		temp.setCity(rs.getString("city"));
		temp.setCityName(rs.getString("cityName"));
		temp.setUpdateDate(rs.getDate("updateDate"));
		temp.setUpdateUserId(rs.getInt("updateUserId"));
		temp.setState(rs.getInt("state"));
		temp.setNextApproval(rs.getInt("nextApproval"));
		return temp;
	}

}

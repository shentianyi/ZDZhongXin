package com.zd.csms.attendance.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.attendance.model.SignRecord;

public class SignRecordRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		SignRecord sr = new SignRecord();
		sr.setId(rs.getInt("id"));
		sr.setMorningStart(rs.getTimestamp("morningStart"));
		sr.setMorningEnd(rs.getTimestamp("morningEnd"));
		sr.setAfternoonStart(rs.getTimestamp("afternoonStart"));
		sr.setAfternoonEnd(rs.getTimestamp("afternoonEnd"));
		sr.setCreateDate(rs.getTimestamp("createDate"));
		sr.setCreateUserId(rs.getInt("createUserId"));
		sr.setUpdateDate(rs.getTimestamp("updateDate"));
		sr.setUpdateUserId(rs.getInt("updateUserId"));
		sr.setRespId(rs.getInt("respId"));
		sr.setIsLate(rs.getInt("isLate"));
		sr.setIsEarly(rs.getInt("isEarly"));
		sr.setIsAbsenteeism(rs.getInt("isAbsenteeism"));
		sr.setIsNormal(rs.getInt("isNormal"));
		sr.setWorkDays(rs.getString("workDays"));
		return sr;
	}

}

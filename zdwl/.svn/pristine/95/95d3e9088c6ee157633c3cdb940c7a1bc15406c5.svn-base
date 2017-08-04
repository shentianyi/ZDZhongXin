package com.zd.csms.attendance.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.attendance.model.DealerAttendanceTime;

public class DealerAttendanceTimeMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		DealerAttendanceTime vo = new DealerAttendanceTime();
		vo.setId(rs.getInt("id"));
		vo.setDealerid(rs.getInt("dealerid"));
		vo.setDealerName(rs.getString("dealerName"));
		vo.setAfternoonEnd(rs.getString("afternoonEnd"));
		vo.setAfternoonStart(rs.getString("afternoonStart"));
		vo.setMorningEnd(rs.getString("morningEnd"));
		vo.setMorningStart(rs.getString("morningStart"));
		vo.setSystemContent(rs.getString("systemContent"));
		vo.setWorkDays(rs.getString("workDays"));
		return vo;
	}

}

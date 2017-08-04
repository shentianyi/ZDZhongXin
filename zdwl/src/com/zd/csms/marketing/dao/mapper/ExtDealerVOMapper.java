package com.zd.csms.marketing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.marketing.model.ExtDealerVO;

public class ExtDealerVOMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ExtDealerVO vo = new ExtDealerVO();
		vo.setId(rs.getInt("id"));
		vo.setDealerName(rs.getString("dealerName"));
		vo.setBrandId(rs.getInt("brandId"));
		vo.setBrandName(rs.getString("brandName"));
		vo.setBankId(rs.getString("bankId"));
		vo.setBankName(rs.getString("bankName"));
		vo.setProvinceName(rs.getString("provinceName"));
		vo.setCityName(rs.getString("cityName"));
		vo.setAddress(rs.getString("address"));
		vo.setMorningEnd(rs.getString("morningEnd"));
		vo.setMorningStart(rs.getString("morningStart"));
		vo.setAfternoonEnd(rs.getString("afternoonEnd"));
		vo.setAfternoonStart(rs.getString("afternoonStart"));
		vo.setSystemContent(rs.getString("systemContent"));
		vo.setWorkDays(rs.getString("workDays"));
		return vo;
	}

}

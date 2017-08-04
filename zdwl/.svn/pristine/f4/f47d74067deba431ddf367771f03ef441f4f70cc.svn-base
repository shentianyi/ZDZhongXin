package com.zd.csms.supervisorymanagement.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;

public class FixedAssetListMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		FixedAssetListVO o = new FixedAssetListVO();
		o.setId(rs.getInt("id"));
		o.setFid(rs.getInt("fid"));
		o.setDepartment(rs.getString("department"));
		o.setUsername(rs.getInt("username"));
		o.setTrade(rs.getString("trade"));
		o.setTrade_province(rs.getInt("trade_province"));
		o.setTrade_city(rs.getInt("trade_city"));
		o.setPassword(rs.getString("password"));
		o.setKey(rs.getString("key"));
		o.setDeposit_time(rs.getTimestamp("deposit_time"));
		o.setOut_time(rs.getTimestamp("out_time"));
		o.setExpress(rs.getString("express"));
		o.setExpress_num(rs.getString("express_num"));
		o.setExpress_money(rs.getString("express_money"));
		o.setRepair_time(rs.getTimestamp("repair_time"));
		o.setRepair_money(rs.getString("repair_money"));
		o.setRepair_des(rs.getString("repair_des"));
		o.setReceive_pic(rs.getInt("receive_pic"));
		o.setDes(rs.getString("des"));
		o.setAddress(rs.getString("address"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		return o;
	}

}

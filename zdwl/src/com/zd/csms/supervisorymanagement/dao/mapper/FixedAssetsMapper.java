package com.zd.csms.supervisorymanagement.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;

public class FixedAssetsMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		FixedAssetsVO o = new FixedAssetsVO();
		o.setId(rs.getInt("id"));
		o.setAsset_num(rs.getString("asset_num"));
		o.setAsset_type(rs.getInt("asset_type"));
		o.setAsset_name(rs.getString("asset_name"));
		o.setBrand(rs.getString("brand"));
		o.setModel(rs.getString("model"));
		o.setFactory_code(rs.getString("factory_code"));
		o.setAsset_moeny(rs.getString("asset_moeny"));
		o.setAmount(rs.getString("amount"));
		o.setPurchase_date(rs.getTimestamp("purchase_date"));
		o.setUseful_life(rs.getString("useful_life"));
		o.setLife(rs.getString("life"));
		o.setAsset_state(rs.getInt("asset_state"));
		o.setFactory_date(rs.getTimestamp("factory_date"));
		o.setExpress(rs.getString("express"));
		o.setExpress_num(rs.getString("express_num"));
		o.setTrade_name(rs.getString("trade_name"));
		o.setAddress(rs.getString("address"));
		o.setSendee(rs.getInt("sendee"));
		o.setKey(rs.getString("key"));
		o.setPassword(rs.getString("password"));
		o.setProducedate(rs.getTimestamp("producedate"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		
		return o;
	}

}

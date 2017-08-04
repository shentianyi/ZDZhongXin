package com.zd.csms.supervisory.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisory.model.SuperviseImportVO;

public class SuperviseImportMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SuperviseImportVO o = new SuperviseImportVO();
		o.setId(rs.getInt("id"));
		o.setCertificate_date(rs.getTimestamp("certificate_date"));
		o.setCertificate_num(rs.getString("certificate_num"));
		o.setCar_model(rs.getString("car_model"));
		o.setCar_structure(rs.getString("car_structure"));
		o.setDisplacement(rs.getString("displacement"));
		o.setColor(rs.getString("color"));
		o.setEngine_num(rs.getString("engine_num"));
		o.setVin(rs.getString("vin"));
		o.setKey_num(rs.getString("key_num"));
		o.setMoney(rs.getString("money"));
		o.setDes(rs.getString("des"));
		o.setDraft_num(rs.getString("draft_num"));
		o.setKey_amount(rs.getString("key_amount"));
		o.setState(rs.getInt("state"));
		o.setPrice(rs.getString("price"));
		o.setPayment_amount(rs.getString("payment_amount"));
		o.setTwo_name(rs.getString("two_name"));
		o.setCertificate_intime(rs.getTimestamp("certificate_intime"));
		o.setStoragetime(rs.getTimestamp("storagetime"));
		o.setMovetime(rs.getTimestamp("movetime"));
		o.setOuttime(rs.getTimestamp("outtime"));
		o.setBrandid(rs.getInt("brandid"));
		o.setApply(rs.getInt("apply"));
		o.setImporttime(rs.getTimestamp("importtime"));
		o.setBond(rs.getString("bond"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		o.setAddressId(rs.getInt("addressId"));
		o.setNextApproval(rs.getInt("nextapproval"));
		o.setIdentifi(rs.getInt("identifi"));
		return o;
	}

}

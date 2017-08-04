package com.zd.csms.supervisory.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisory.model.SuperviseImportOneVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;

public class CarInfoQueryBeanMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		SuperviseImportOneVO vo = new SuperviseImportOneVO();
		vo.setId(rs.getInt("id"));
		vo.setAddressId(rs.getInt("addressId"));
		vo.setApply(rs.getInt("apply"));
		vo.setBond(rs.getString("bond"));
		vo.setBrandid(rs.getInt("brandid"));
		vo.setCar_model(rs.getString("car_model"));
		vo.setCar_structure(rs.getString("car_structure"));
		vo.setCertificate_date(rs.getDate("certificate_date"));
		vo.setCertificate_intime(rs.getDate("certificate_intime"));
		vo.setCertificate_num(rs.getString("certificate_num"));
		vo.setColor(rs.getString("color"));
		vo.setCreatedate(rs.getDate("createdate"));
		vo.setCreateuserid(rs.getInt("createuserid"));
		vo.setDes(rs.getString("des"));
		vo.setDisplacement(rs.getString("displacement"));
		vo.setDraft_num(rs.getString("draft_num"));
		vo.setEngine_num(rs.getString("engine_num"));
		vo.setImporttime(rs.getDate("importtime"));
		vo.setKey_amount(rs.getString("key_amount"));
		vo.setKey_num(rs.getString("key_num"));
		vo.setMoney(rs.getString("money"));
		vo.setMovetime(rs.getDate("movetime"));
		vo.setNextApproval(rs.getInt("nextApproval"));
		vo.setOuttime(rs.getDate("outtime"));
		vo.setPayment_amount(rs.getString("payment_amount"));
		vo.setPrice(rs.getString("price"));
		vo.setState(rs.getInt("state"));
		vo.setStoragetime(rs.getDate("storagetime"));
		vo.setTwo_name(rs.getString("two_name"));
		vo.setUpddate(rs.getDate("upddate"));
		vo.setUpduserid(rs.getInt("upduserid"));
		vo.setVin(rs.getString("vin"));
		
		vo.setDealerId(rs.getInt("dealerId"));
		vo.setDealerName(rs.getString("dealerName"));
		vo.setBankFullName(rs.getString("bankFullName"));
		vo.setBrandName(rs.getString("brandName"));
		vo.setCreateUserName(rs.getString("createUserName"));
		vo.setUpdUserName(rs.getString("updUserName"));
//		vo.setYd(rs.getInt("yd"));
		/*vo.setAllCar(rs.getInt("allCar"));
		vo.setMoveCarMoney(rs.getInt("moveCarMoney"));
		vo.setInStockCarMoney(rs.getInt("inStockCarMoney"));*/
		return vo;
	}

}

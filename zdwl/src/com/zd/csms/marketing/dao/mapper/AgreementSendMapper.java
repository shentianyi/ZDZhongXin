package com.zd.csms.marketing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.marketing.model.AgreementSendVO;

public class AgreementSendMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		AgreementSendVO o = new AgreementSendVO();
		o.setId(rs.getInt("id"));
		o.setDistribid(rs.getInt("distribid"));
		o.setFinancial_institution(rs.getString("financial_institution"));
		o.setAgreement_num(rs.getString("agreement_num"));
		o.setAgreement_date(rs.getTimestamp("agreement_date"));
		o.setFinancial_user(rs.getString("financial_user"));
		o.setFinancial_phone(rs.getString("financial_phone"));
		o.setSend_address(rs.getString("send_address"));
		o.setBack_date(rs.getTimestamp("back_date"));
		o.setAgreementsigntime(rs.getTimestamp("agreementsigntime"));
		o.setAgreementexpiretime(rs.getTimestamp("agreementexpiretime"));
		o.setBrandid(rs.getInt("brandid"));
		o.setProvince(rs.getString("province"));
		o.setCity(rs.getString("city"));
		o.setCounty(rs.getString("county"));
		return o;
	}

}

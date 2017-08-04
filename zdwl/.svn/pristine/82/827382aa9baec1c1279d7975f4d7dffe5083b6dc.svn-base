package com.zd.csms.business.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.business.model.DraftVO;

public class DraftMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		DraftVO o = new DraftVO();
		o.setId(rs.getInt("id"));
		o.setAgreement(rs.getString("agreement"));
		o.setBail_num(rs.getString("bail_num"));
		o.setDistribid(rs.getInt("distribid"));
		o.setFinancial_institution(rs.getString("financial_institution"));
		o.setBrand(rs.getString("brand"));
		o.setDraft_num(rs.getString("draft_num"));
		o.setBilling_date(rs.getTimestamp("billing_date"));
		o.setDue_date(rs.getTimestamp("due_date"));
		o.setBilling_money(rs.getString("billing_money"));
		o.setMortgagecar_money(rs.getString("mortgagecar_money"));
		o.setPayment_money(rs.getString("payment_money"));
		o.setState(rs.getInt("state"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		o.setBailscale(rs.getString("bailscale"));
		return o;
	}

}

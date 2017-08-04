package com.zd.csms.finance.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.finance.model.OpenInvoiceVO;

public class OpenInvoiceMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		OpenInvoiceVO o = new OpenInvoiceVO();
		o.setId(rs.getInt("id"));
		o.setDistribid(rs.getInt("distribid"));
		o.setFinancial_institution(rs.getString("financial_institution"));
		o.setBank(rs.getString("bank"));
		o.setBrand(rs.getString("brand"));
		o.setIntime(rs.getTimestamp("intime"));
		o.setSupervisionfee_standard(rs.getString("supervisionfee_standard"));
		o.setPayment(rs.getString("payment"));
		o.setPay_standard(rs.getString("pay_standard"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		return o;
	}

}

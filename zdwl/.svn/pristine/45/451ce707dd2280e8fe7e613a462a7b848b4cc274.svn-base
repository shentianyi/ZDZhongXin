package com.zd.csms.finance.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.finance.model.OpenInvoiceListVO;

public class OpenInvoiceListMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		OpenInvoiceListVO o = new OpenInvoiceListVO();
		o.setId(rs.getInt("id"));
		o.setOpen_invoice_id(rs.getInt("open_invoice_id"));
		o.setPay_time(rs.getTimestamp("pay_time"));
		o.setPay_money(rs.getString("pay_money"));
		o.setIsinvoice(rs.getInt("isinvoice"));
		o.setInvoice_type(rs.getString("invoice_type"));
		o.setIsTransfer(rs.getInt("isTransfer"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		return o;
	}

}

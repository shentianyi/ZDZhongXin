package com.zd.csms.marketing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.marketing.model.SupervisePayVO;

public class SupervisePayMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SupervisePayVO o = new SupervisePayVO();
		o.setId(rs.getInt("id"));
		o.setDistribid(rs.getInt("distribid"));
		o.setFinancial_institution(rs.getString("financial_institution"));
		o.setSupervise_expire(rs.getTimestamp("supervise_expire"));
		o.setPayment_period(rs.getString("payment_period"));
		o.setPay_money(rs.getString("pay_money"));
		o.setAlready_pay(rs.getString("already_pay"));
		o.setNot_pay(rs.getString("not_pay"));
		o.setChange_payment(rs.getString("change_payment"));
		o.setBalance_payment(rs.getString("balance_payment"));
		return o;
	}

}

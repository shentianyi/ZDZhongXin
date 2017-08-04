package com.zd.csms.finance.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.finance.model.SupervisionfeeVO;

public class SupervisionfeeMapper implements RowMapper {

	
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SupervisionfeeVO o = new SupervisionfeeVO();
		o.setId(rs.getInt("id"));
		o.setDistribid(rs.getInt("distribid"));
		o.setType(rs.getInt("type"));
		o.setFinancial_institution(rs.getString("financial_institution"));
		o.setBank(rs.getString("bank"));
		o.setBrand(rs.getString("brand"));
		o.setIntime(rs.getTimestamp("intime"));
		o.setSupervisionfee_standard(rs.getString("supervisionfee_standard"));
		o.setPayment(rs.getString("payment"));
		o.setPay_standard(rs.getString("pay_standard"));
		o.setPay_time(rs.getTimestamp("pay_time"));
		o.setPay_money(rs.getString("pay_money"));
		o.setRefund_time(rs.getTimestamp("refund_time"));
		o.setRefund_money(rs.getString("refund_money"));
		o.setRefund_des(rs.getString("refund_des"));
		o.setDes(rs.getString("des"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		return o;
	}

}

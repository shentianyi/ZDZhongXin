package com.zd.csms.supervisorymanagement.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.supervisorymanagement.model.MailcostVO;

public class MailcostMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		MailcostVO o = new MailcostVO();
		o.setId(rs.getInt("id"));
		o.setPromoter(rs.getInt("promoter"));
		o.setFqdate(rs.getTimestamp("fqdate"));
		o.setMailingitems(rs.getString("mailingitems"));
		o.setParts(rs.getString("parts"));
		o.setOther(rs.getString("other"));
		o.setMailperson(rs.getInt("mailperson"));
		o.setExpress(rs.getString("express"));
		o.setMoney(rs.getString("money"));
		o.setTransportbegin(rs.getString("transportbegin"));
		o.setTransportend(rs.getString("transportend"));
		o.setReceiveid(rs.getInt("receiveid"));
		o.setDes(rs.getString("des"));
		o.setNextApproval(rs.getInt("nextApproval"));
		o.setApprovalState(rs.getInt("approvalState"));
		o.setCreateuserid(rs.getInt("createuserid"));
		o.setCreatedate(rs.getTimestamp("createdate"));
		o.setUpduserid(rs.getInt("upduserid"));
		o.setUpddate(rs.getTimestamp("upddate"));
		return o;
	}

}

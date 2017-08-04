package com.zd.csms.marketing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.marketing.querybean.ApprovalQueryBean;

public class RemarkMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ApprovalQueryBean bean = new ApprovalQueryBean();
		bean.setId(rs.getInt("id"));
		bean.setRemark(rs.getString("remark"));
		bean.setRemarkDate(rs.getTimestamp("remarkDate"));
		bean.setRoleName(rs.getString("roleName"));
		bean.setUserName(rs.getString("username"));
		bean.setApprovalResult(rs.getInt("approvalResult"));
		return bean;
	}

}

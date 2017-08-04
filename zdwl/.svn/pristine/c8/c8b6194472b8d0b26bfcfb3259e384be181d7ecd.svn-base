package com.zd.csms.business.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.business.model.MailingVO;

public class MailingMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		MailingVO o = new MailingVO();
		o.setId(rs.getInt("id"));
		o.setSuperviseid(rs.getInt("superviseid"));
		o.setMailnature(rs.getString("mailnature"));
		o.setMailtime(rs.getTimestamp("mailtime"));
		o.setExpress(rs.getString("express"));
		o.setExpress_num(rs.getString("express_num"));
		o.setDes(rs.getString("des"));
		return o;
	}

}

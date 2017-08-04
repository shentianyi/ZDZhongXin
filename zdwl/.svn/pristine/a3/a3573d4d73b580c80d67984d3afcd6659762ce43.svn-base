package com.zd.csms.message.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.message.model.MessageVO;

public class MessageMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		MessageVO o = new MessageVO();
		o.setId(rs.getInt("id"));
		o.setUserid(rs.getInt("userid"));
		o.setName(rs.getString("name"));
		o.setUrl(rs.getString("url"));
		o.setIsread(rs.getInt("isread"));
		o.setMsgtype(rs.getInt("msgtype"));
		o.setType(rs.getInt("type"));
		o.setDeptName(rs.getString("deptname"));
		return o;
	}

}

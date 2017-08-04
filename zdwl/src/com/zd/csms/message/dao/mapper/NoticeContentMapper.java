package com.zd.csms.message.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.message.model.NoticeContentVO;

import oracle.sql.CLOB;

public class NoticeContentMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		NoticeContentVO vo = new NoticeContentVO();
		vo.setId(rs.getInt("id"));
		vo.setTitle(rs.getString("title"));
		vo.setCreateDate(rs.getTimestamp("createDate"));
		vo.setCreateUserId(rs.getInt("createUserId"));
		
		CLOB clob = (CLOB) rs.getClob("content");
		if(clob!=null&&clob.length()>0){
			vo.setContent(clob.getSubString(1,(int)clob.length()));
		}else{
			vo.setContent("");
		}
		return vo;
	}

}

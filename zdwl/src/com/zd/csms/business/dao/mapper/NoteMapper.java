package com.zd.csms.business.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.business.model.NoteVO;

import oracle.sql.CLOB;

public class NoteMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		NoteVO note = new NoteVO();
		note.setId(rs.getInt("id"));
		note.setUserid(rs.getInt("userid"));
		note.setTitle(rs.getString("title"));
		CLOB clob = (CLOB) rs.getClob("content");
		if(clob!=null&&clob.length()>0){
			note.setContent(clob.getSubString(1,(int)clob.length()));
		}else{
			note.setContent("");
		}
		return note;
	}
	
}

package com.zd.csms.business.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.business.model.FlowVO;

import oracle.sql.CLOB;

public class FlowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		FlowVO o = new FlowVO();
		o.setId(rs.getInt("id"));
		o.setFlowname(rs.getString("flowName"));
		o.setUploadid(rs.getInt("uploadid"));
		o.setCreatetime(rs.getTimestamp("createTime"));
		CLOB clob = (CLOB) rs.getClob("content");
		if(clob!=null&&clob.length()>0){
			o.setContent(clob.getSubString(1,(int)clob.length()));
		}else{
			o.setContent("");
		}
		return o;
	}

}

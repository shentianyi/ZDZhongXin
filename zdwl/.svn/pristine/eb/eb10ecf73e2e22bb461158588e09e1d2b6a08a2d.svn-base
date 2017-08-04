package com.zd.csms.file.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.file.model.UploadfileVO;

public class UploadfileMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		UploadfileVO o = new UploadfileVO();
		o.setId(rs.getInt("id"));
		o.setFile_name(rs.getString("file_name"));
		o.setFile_type(rs.getString("file_type"));
		o.setFile_path(rs.getString("file_path"));
		o.setCreatetime(rs.getTimestamp("createtime"));
		o.setUploaduser(rs.getInt("uploaduser"));
		return o;
	}

}

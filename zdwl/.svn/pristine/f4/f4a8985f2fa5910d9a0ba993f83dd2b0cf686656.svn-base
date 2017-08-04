package com.zd.csms.region.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.region.model.RegionVO;

public class RegionMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		RegionVO region = new RegionVO();
		region.setId(rs.getInt("id"));
		region.setName(rs.getString("name"));
		region.setParentId(rs.getInt("parentId"));
		region.setCreateUser(rs.getInt("createUser"));
		region.setCreateTime(rs.getTimestamp("createTime"));
		region.setLastModifiedUser(rs.getInt("lastModifiedUser"));
		region.setLastModifiedTime(rs.getTimestamp("lastModifiedTime"));
		return region;
	}


}

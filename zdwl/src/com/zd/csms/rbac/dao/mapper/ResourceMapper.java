package com.zd.csms.rbac.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.rbac.model.ResourceVO;

public class ResourceMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ResourceVO o = new ResourceVO();
		o.setId(rs.getInt("id"));
		o.setState(rs.getInt("state"));
		o.setResource_level(rs.getInt("resource_level"));
		o.setNode_type(rs.getInt("node_type"));
		o.setParent_id(rs.getInt("parent_id"));
		o.setResource_index(rs.getString("resource_index"));
		o.setResource_name(rs.getString("resource_name"));
		o.setResource_shortname(rs.getString("resource_shortname"));
		o.setResource_url(rs.getString("resource_url"));
		o.setDes(rs.getString("des"));
		return o;
	}
}

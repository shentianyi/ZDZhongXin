package com.zd.csms.marketing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zd.csms.marketing.model.DealerSupervisorVO;

public class DealerSupervisorMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		DealerSupervisorVO o = new DealerSupervisorVO();
		o.setId(rs.getInt("id"));
		o.setDealerId(rs.getInt("dealerId"));
		o.setRepositoryId(rs.getInt("repositoryId"));
		o.setQq(rs.getString("qq"));
		o.setQqPwd(rs.getString("qqPwd"));
		o.setSupervisorAttribute(rs.getInt("supervisorAttribute"));
		return o;
	}

}

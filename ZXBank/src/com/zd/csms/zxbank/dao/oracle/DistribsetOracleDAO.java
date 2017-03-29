package com.zd.csms.zxbank.dao.oracle;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.DistribsetZX;
import com.zd.csms.zxbank.dao.IDistribsetDAO;

public class DistribsetOracleDAO extends DAOSupport implements IDistribsetDAO {

	public DistribsetOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<DistribsetZX> findAll() {
		String sql = "select * from ZX_DISTRIBSET";
		@SuppressWarnings("unchecked")
		List<DistribsetZX> list = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(DistribsetZX.class));
		System.out.println(list);
		return null;
	}
}

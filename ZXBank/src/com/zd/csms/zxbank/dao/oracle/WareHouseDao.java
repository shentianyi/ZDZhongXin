package com.zd.csms.zxbank.dao.oracle;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.IWareHouseDAO;

public class WareHouseDao extends DAOSupport implements IWareHouseDAO{

	public WareHouseDao(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<Warehouse> findAllList() {
		String sql="select * from zx_warehouse";
		@SuppressWarnings("unchecked")
		List<Warehouse> list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Warehouse.class));
		return list;
	}
	
}

package com.zd.csms.salary.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.salary.dao.ISalary;
import com.zd.csms.salary.model.BasePay;

public class SalaryDaoImpl extends DAOSupport implements ISalary{

	public SalaryDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	public BasePay getBasePay(){
		String sql="select * from T_BASE_PAY";
		return (BasePay) getJdbcTemplate().query(sql,new BeanPropertyRowMapper(BasePay.class)).get(0);
	}
	
}

package com.zd.csms.zxbank.dao.oracle;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.ZXIBankDockDao;

public class ZXBankDockDao extends DAOSupport implements ZXIBankDockDao{

	public ZXBankDockDao(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<Customer> findAllList() {
		System.out.println("++++++++++z这是sql");
		String sql="select cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate from zx_customer";
		System.out.println("---------sq2l");
		@SuppressWarnings("unchecked")
		List<Customer> list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Customer.class));
		return list;
	}

	@Override
	public String getcust(String organizationcode) {
		Object name= getJdbcTemplate().queryForObject("select cust_name from zx_customer t where t.cust_organizationcode=?",new Object[]{organizationcode},String.class);
		if(name!=null)
			return name.toString();
		else
			return "";
	}

}

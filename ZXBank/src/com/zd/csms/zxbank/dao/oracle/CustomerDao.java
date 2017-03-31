package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.ICustomerDao;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class CustomerDao extends DAOSupport implements ICustomerDao{

	public CustomerDao(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 分页 查询所有用户
	 */
	public List<Customer> findcustallList(Customer query,IThumbPageTools tools) {
		List<Customer> list=null;
		StringBuffer sql = new StringBuffer("Select a.cust_id,a.cust_no,a.cust_organizationcode,a.cust_name,a.cust_createdate as custCreateDate,a.cust_updatedate as custUpdateDate " +
				"from zx_customer a,zx_distribset b ");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query,sql, params);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Customer.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 拼接查询条件
	 * @param query
	 * @param sql
	 * @param params
	 */
	public void formatSQL(Customer query,StringBuffer sql,List<Object> params){
		sql.append(" where 1=1 ");
		sql.append(" and a.cust_organizationcode=b.organizationcode ");
		if(!StringUtil.isEmpty(query.getCustOrganizationcode())){
			sql.append(" and a.cust_organizationcode like ? ");
			params.add("%"+query.getCustOrganizationcode().trim()+"%");
		}
		if(!StringUtil.isEmpty(query.getCustName())){
			sql.append(" and a.cust_name like ? ");
			params.add("%"+query.getCustName().trim()+"%");
		}
	}

	
}

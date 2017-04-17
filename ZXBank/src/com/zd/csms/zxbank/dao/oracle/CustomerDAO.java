package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.ICustomerDAO;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 用户信息DAO实现 
 *
 */
public class CustomerDAO extends DAOSupport implements ICustomerDAO{

	public CustomerDAO(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 分页 查询所有用户
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> findcustallList(Customer query,IThumbPageTools tools) {
		List<Customer> list=null;
		StringBuffer sql = new StringBuffer("Select a.cust_id,a.cust_no,a.cust_organizationcode,a.cust_name,a.cust_createdate as custCreateDate,a.cust_updatedate as custUpdateDate " +
				"from zx_customer a,zx_distribset b ");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query,sql, params);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Customer.class));
			System.out.println(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> query() {
		String sql="Select a.cust_id,a.cust_no,a.cust_organizationcode,a.cust_name,a.cust_createdate as custCreateDate,a.cust_updatedate as custUpdateDate from zx_customer a";
		List<Customer> list=new ArrayList<Customer>();
		list=getJdbcTemplate().query(sql,new BeanPropertyRowMapper(Customer.class));
		return list;
	}
	
	public boolean add(Customer customer) {
		String sql="insert into ZX_CUSTOMER(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate) values(zx_customerId.nextval,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'))";
		getJdbcTemplate().add(sql,new Object[]{customer.getCustNo(),customer.getCustOrganizationcode(),customer.getCustName(),customer.getCustCreateDate()});
		return true;
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

	@Override
	public boolean upadat(CustomerFar cuFar,Customer customer) {
		String updateDate="to_date('"+customer.getCustUpdateDate()+"','YYYY-MM-DD HH24:MI:SS')";//拼接时间
		String sql="update zx_customer set cust_name=?,cust_updatedate=? where cust_no=?";
		getJdbcTemplate().update(sql, new Object[]{cuFar.getCustName(),updateDate,cuFar.getEcifCode()});
		System.out.println(getJdbcTemplate().update(sql));
		return true;
	}
	
	@Override
	public boolean update(String sql, Object... params) {
		CustomerFar cuFar = (CustomerFar) params[0];
		Customer customer = (Customer) params[1];
		String updateDate="to_date('"+customer.getCustUpdateDate()+"','YYYY-MM-DD HH24:MI:SS')";//拼接时间
		sql = "update zx_customer set cust_name=?,cust_updatedate=? where cust_no=?";
		return super.update(sql, cuFar.getCustName(),updateDate,cuFar.getEcifCode());
	}
	
}

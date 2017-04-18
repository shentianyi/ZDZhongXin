package com.zd.csms.zxbank.dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.BeanManager;
import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.ICustomerDAO;
import com.zd.csms.zxbank.util.DateUtil;
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
		StringBuffer sql = new StringBuffer("Select a.custId,a.custNo,a.custOrganizationcode,a.custName,a.custCreatedate as custCreateDate,a.custUpdatedate as custUpdateDate " +
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
		String sql="Select a.custId,a.custNo,a.custOrganizationcode,a.custName,a.custCreatedate as custCreateDate,a.custUpdatedate as custUpdateDate from zx_customer a";
		List<Customer> list=new ArrayList<Customer>();
		list=getJdbcTemplate().query(sql,new BeanPropertyRowMapper(Customer.class));
		return list;
	}
	
	public boolean add(Customer customer) {
//		String sql="insert into ZX_CUSTOMER(custId,custNo,custOrganizationcode,custName,custCreatedate) values(zx_customerId.nextval,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'))";
//		getJdbcTemplate().add(sql,new Object[]{customer.getCustNo(),customer.getCustOrganizationcode(),customer.getCustName(),customer.getCustCreateDate()});
		return super.add(customer);
	}
	
	/**
	 * 拼接查询条件
	 * @param query
	 * @param sql
	 * @param params
	 */
	public void formatSQL(Customer query,StringBuffer sql,List<Object> params){
		sql.append(" where 1=1 ");
		sql.append(" and a.custOrganizationcode=b.organizationcode ");
		if(!StringUtil.isEmpty(query.getCustOrganizationcode())){
			sql.append(" and a.custOrganizationcode like ? ");
			params.add("%"+query.getCustOrganizationcode().trim()+"%");
		}
		if(!StringUtil.isEmpty(query.getCustName())){
			sql.append(" and a.custName like ? ");
			params.add("%"+query.getCustName().trim()+"%");
		}
	}
	
	//更新操作
	public boolean upadat(Customer customer) {
		String sql="update zx_customer set custName=?,custUpdatedate=to_date(?,'YYYY-MM-DD HH24:MI:SS') where custNo=?";
			PreparedStatement stmt = null;
			try {
				stmt = BeanManager.getDataSource(null).getConnection().prepareStatement(sql);
				stmt.setString(1,customer.getCustName());
				stmt.setString(2,DateUtil.getStringFormatByDate(customer.getCustUpdateDate(), "yyyy-MM-dd HH:mm:ss"));
				stmt.setString(3, customer.getCustNo());
				stmt.executeUpdate();
				return true;	
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}finally{
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	
}

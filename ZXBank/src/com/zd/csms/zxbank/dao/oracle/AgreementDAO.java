package com.zd.csms.zxbank.dao.oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.BeanManager;
import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.dao.IAgreementDAO;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 监管协议DAO实现
 */
public class AgreementDAO extends DAOSupport implements IAgreementDAO{

	public AgreementDAO(String dataSourceName) {
		super(dataSourceName);
	}
	/**
	 *分页数据查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Agreement> firnAllAgList(Agreement query, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		sql.append("select agCustno,agLoncpid,agLoncpname,agProtocolno,agProtocolcode,agState,agStdate,agEnddate,agIsonline,agIsmove,agOperorg,agTotnum,agCreatedate,agUpdatedate from zx_agreement ");
		List<Object> params=new ArrayList<Object>();
		List<Agreement> list=null;
		formatSQL(sql,params,query);
		try {
			System.out.println("执行查询");
			list=tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Agreement.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	/**
	 * 根据情况格式化sql语句
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatSQL(StringBuffer sql, List<Object> params,
			Agreement query) {
		sql.append(" where 1=1");
		if(query.getAgCustno()!=null&&!query.getAgCustno().equals("")){
			params.add("%"+query.getAgCustno().trim()+"%");
			sql.append(" and agCustno like ?");
		}
		if(query.getAgLoncpname()!=null&&!query.getAgLoncpname().equals("")){
			params.add("%"+query.getAgLoncpname().trim()+"%");
			sql.append(" and agLoncpname like ?");
		}
		
	}
	/**
	 * 根据客户号查询单个协议信息。
	 */
	public Agreement getAgreement(String LonentNo){
		String sql="select agLoncpname from zx_agreement where agLoncpid='"+LonentNo+"'";
		List<Agreement> list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Agreement.class));
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	public List<Agreement> query(){
		String sql="select agProtocolno  from zx_agreement";
		List<Agreement> list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Agreement.class));
		return list;
	}
	@Override
	public boolean update(Agreement agreement) {
		String sql = "update zx_agreement set agCustno=?,agLoncpid=?,agLoncpname=?,agProtocolcode=?,agState=?,agStdate=?,agEnddate=?,agIsonline=?,agIsmove=?,agOperorg=?,agTotnum=?,agUpdatedate=to_date(?,'YYYY-MM-DD HH24:MI:SS') where agProtocolno=?";
		PreparedStatement stmt = null;
		try {
			stmt = BeanManager.getDataSource(null).getConnection().prepareStatement(sql);
			stmt.setString(1,agreement.getAgCustno());
			stmt.setString(2,agreement.getAgLoncpid());
			stmt.setString(3,agreement.getAgLoncpname());
			stmt.setString(4,agreement.getAgProtocolcode());
			stmt.setString(5,agreement.getAgState());
			stmt.setString(6,agreement.getAgStdate());
			stmt.setString(7,agreement.getAgEnddate());
			stmt.setString(8,agreement.getAgIsonline());
			stmt.setString(9,agreement.getAgIsmove());
			stmt.setString(10,agreement.getAgOperorg());
			stmt.setString(11,agreement.getAgTotnum());
			stmt.setString(12,DateUtil.getStringFormatByDate(agreement.getAgUpdatedate(),"yyyy-MM-dd HH:mm:ss"));
			stmt.setString(13,agreement.getAgProtocolno());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

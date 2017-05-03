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
		sql.append("SELECT HOSTNO,AGLONCPID,LONNM,SPVAGTID,SPVAGTNO,AGTSTT,STARTDATE,ENDDATE,ISAUTH,ISMV,OPERORG,TOTNUM,AGCREATEDATE,AGUPDATEDATE  FROM ZX_AGREEMENT");
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
		if(query.getHostno()!=null&&!query.getHostno().equals("")){
			params.add("%"+query.getHostno().trim()+"%");
			sql.append(" and HOSTNO like ?");
		}
		if(query.getLonnm()!=null&&!query.getLonnm().equals("")){
			params.add("%"+query.getLonnm().trim()+"%");
			sql.append(" and LONNM like ?");
		}
		
	}
	/**
	 * 根据客户号查询单个协议信息。
	 */
	public Agreement getAgreement(String LonentNo){
		String sql="SELECT LONNM FROM ZX_AGREEMENT WHERE AGLONCPID='"+LonentNo+"'";
		List<Agreement> list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Agreement.class));
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	public List<Agreement> query(){
		String sql="SELECT SPVAGTID FROM ZX_AGREEMENT";
		List<Agreement> list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Agreement.class));
		return list;
	}
	@Override
	public boolean update(Agreement agreement) {
		String sql = "UPDATE ZX_AGREEMENT SET HOSTNO=?,AGLONCPID=?,LONNM=?,SPVAGTNO=?,AGTSTT=?,STARTDATE=?,ENDDATE=?,ISAUTH=?,ISMV=?,OPERORG=?,TOTNUM=?,AGUPDATEDATE=TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') WHERE SPVAGTID=?";
		PreparedStatement stmt = null;
		try {
			stmt = BeanManager.getDataSource(null).getConnection().prepareStatement(sql);
			stmt.setString(1,agreement.getHostno());
			stmt.setString(2,agreement.getAgloncpid());
			stmt.setString(3,agreement.getLonnm());
			stmt.setString(4,agreement.getSpvagtno());
			stmt.setString(5,agreement.getAgtstt());
			stmt.setString(6,agreement.getStartdate());
			stmt.setString(7,agreement.getEnddate());
			stmt.setString(8,agreement.getIsauth());
			stmt.setString(9,agreement.getIsmv());
			stmt.setString(10,agreement.getOperorg());
			stmt.setString(11,agreement.getTotnum());
			stmt.setString(12,DateUtil.getStringFormatByDate(agreement.getAgupdatedate(),"yyyy-MM-dd HH:mm:ss"));
			stmt.setString(13,agreement.getSpvagtid());
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

package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.dao.IAgreementDAO;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 监管协议DAO实现
 */
public class AgreementDAO extends DAOSupport implements IAgreementDAO{

	public AgreementDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agreement> firnAllAgList(Agreement query, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zx_agreement ");
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

	private void formatSQL(StringBuffer sql, List<Object> params,
			Agreement query) {
		sql.append(" where 1=1");
		if(query.getAg_custno()!=null&&!query.getAg_custno().equals("")){
			params.add("%"+query.getAg_custno().trim()+"%");
			sql.append(" and ag_custno like ?");
		}
		if(query.getAg_loncpname()!=null&&!query.getAg_loncpname().equals("")){
			params.add("%"+query.getAg_loncpname().trim()+"%");
			sql.append(" and ag_loncpname like ?");
		}
		
	}
	
	public Agreement getAgreement(String LonentNo){
		String sql="select ag_loncpname from zx_agreement where ag_loncpid='"+LonentNo+"'";
		List<Agreement> list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Agreement.class));
		return list.get(0);
	}
}

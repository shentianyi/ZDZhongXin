package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.dao.IAgreementDAO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class AgreementDAO extends DAOSupport implements IAgreementDAO{

	public AgreementDAO(String dataSourceName) {
		super(dataSourceName);
	}

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

		/*for (Agreement agreement : list) {
			System.out.println(agreement.toString());
		}*/
		return list;
	}

	private void formatSQL(StringBuffer sql, List<Object> params,
			Agreement query) {
		/*System.out.println("这是sql拼接:");
		System.out.println("客户号："+query.getAg_custno());
		System.out.println("借款企业名称："+query.getAg_loncpname());*/
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
	
}

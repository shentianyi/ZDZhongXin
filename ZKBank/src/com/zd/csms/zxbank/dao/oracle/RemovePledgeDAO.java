package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.core.jdbc.JdbcTemplate;
import com.zd.csms.zxbank.bean.RemovePledge;
import com.zd.csms.zxbank.dao.IRemovePledgeDAO;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知DAO实现层
 * @author duyong
 *
 */
public class RemovePledgeDAO  extends DAOSupport implements IRemovePledgeDAO{

	public RemovePledgeDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_removepledge = "SELECT RP_ID,RP_NO,RP_OPERORG,RP_PLDEGEENAME,RP_LONCPID,RP_LONCPNAME,RP_SUPERVISENAME,RP_CORENAME,RP_RELIEVEPDDATE,RP_CONTENT,RP_NOTICEDATE,RP_CREATEDATE,RP_UPDATEDATE FROM ZX_REMOVEPLEDGE WHERE 1=1";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RemovePledge> findByQuery(RemovePledge query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(RemovePledgeDAO.select_removepledge);
		List<Object> params = new ArrayList<Object>();
		List<RemovePledge> list = null;
		formatSQL(sql, params,query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RemovePledge.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * sql语句拼接
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatSQL(StringBuffer sql,List<Object> params,RemovePledge query){
		if(query.getRpNo()!=null&&!query.getRpNo().equals("")){
			params.add("%"+query.getRpNo()+"%");
			sql.append(" AND RP_NO LIKE ?");
		}	
		if(query.getRpLoncpname()!=null&&!query.getRpLoncpname().equals("")){
			params.add("%"+query.getRpLoncpname()+"%");
			sql.append(" AND RP_LONCPNAME LIKE ?");
		}
	}

	@Override
	public RemovePledge fingByNo(String no) {
		RemovePledge rp = null;
		StringBuffer sql = new StringBuffer();
		sql.append(RemovePledgeDAO.select_removepledge);
		sql.append("AND RP_NO ="+no);
		rp = (RemovePledge) getJdbcTemplate().query(sql.toString(),new BeanPropertyRowMapper(RemovePledge.class)).get(0);
		return rp;
	}
}

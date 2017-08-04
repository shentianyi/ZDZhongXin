package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.RemovePledge;
import com.zd.csms.zxbank.dao.IRemovePledgeDAO;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知DAO实现层
 *
 */
public class RemovePledgeDAO extends DAOSupport implements IRemovePledgeDAO {

	public RemovePledgeDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_removepledge = "SELECT ID,RPNO,RPOPERORG,RPPLDEGEENAME,RPLONCPID,RPLONCPNAME,RPSUPERVISENAME,RPCORENAME,RPRELIEVEPDDATE,RPCONTENT,RPNOTICEDATE,RPCREATEDATE,RPUPDATEDATE FROM ZX_REMOVEPLEDGE WHERE 1=1";

	@SuppressWarnings("unchecked")
	@Override
	public List<RemovePledge> findByQuery(RemovePledge query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(RemovePledgeDAO.select_removepledge);
		List<Object> params = new ArrayList<Object>();
		List<RemovePledge> list = null;
		formatSQL(sql, params, query);
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
	private void formatSQL(StringBuffer sql, List<Object> params, RemovePledge query) {
		if (query.getRpNo() != null && !query.getRpNo().equals("")) {
			params.add("%" + query.getRpNo() + "%");
			sql.append(" AND RPNO LIKE ?");
		}
		if (query.getRpLoncpname() != null && !query.getRpLoncpname().equals("")) {
			params.add("%" + query.getRpLoncpname() + "%");
			sql.append(" AND RPLONCPNAME LIKE ?");
		}
		sql.append(" order by rpupdatedate desc nulls last");
	}

	@SuppressWarnings("unchecked")
	@Override
	public RemovePledge fingByNo(String no) {
		StringBuffer sql = new StringBuffer();
		sql.append(RemovePledgeDAO.select_removepledge);
		sql.append("AND RPNO = '" + no+"'");
		List<RemovePledge> list = null;
		list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(RemovePledge.class));
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<String> getListNotice() {
		String sql="select RPNO from ZX_REMOVEPLEDGE";
		List<String> list=null;
		list=this.getJdbcTemplate().queryForList(sql, String.class);
		return list;
	}
	
	@Override
	public boolean add(RemovePledge removePledge) {
		removePledge.setId(SqlUtil.getID(RemovePledge.class));
		return super.add(removePledge);
	}
	
	@Override
	public boolean update(RemovePledge removePledge) {
		return super.update(removePledge);
	}
	
	
}

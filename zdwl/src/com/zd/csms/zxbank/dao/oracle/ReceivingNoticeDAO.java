package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.csms.zxbank.dao.IReceivingNoticeDAO;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ReceivingNoticeDAO extends DAOSupport implements IReceivingNoticeDAO {

	public ReceivingNoticeDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceivingNotice> firnAllAgList(ReceivingNotice query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ID,NYNO,NYCORENTNM,NYSPVENTNM,NYONWSPVENM,NYTRSPTENTNM,NYLONENTNO,NYLONENTNAME,NYCSNDATE,NYETA,NYEPA,NYOFFLNSATNO,NYNTCDATE,NYTTLCMDVAL,NYEXCPLACE,NYREMARK,NYTOTNUM,NYCREATEDATE,NYUPDATEDATE from zx_notify");
		List<Object> parameters = new ArrayList<Object>();
		formatSQL(sql, parameters, query);
		List<ReceivingNotice> list = null;
		try {
			list = tools.goPage(sql.toString(), parameters.toArray(), new BeanPropertyRowMapper(ReceivingNotice.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(StringBuffer sql, List<Object> parameters, ReceivingNotice query) {
		sql.append(" where 1=1");
		if (!StringUtil.isEmpty(query.getNyNo())) {
			parameters.add("%" + query.getNyNo().trim() + "%");
			sql.append(" and nyNo like ?");
		}
		if (!StringUtil.isEmpty(query.getNyLonentname())) {
			parameters.add("%" + query.getNyLonentname().trim() + "%");
			sql.append(" and nyLonentname like ?");
		}
		sql.append(" order by nyupdatedate desc nulls last");
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReceivingNotice getNotify(String no) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ID,NYNO,NYCORENTNM,NYSPVENTNM,NYONWSPVENM,NYTRSPTENTNM,NYLONENTNO,NYLONENTNAME,NYCSNDATE,NYETA,NYEPA,NYOFFLNSATNO,NYNTCDATE,NYTTLCMDVAL,NYEXCPLACE,NYREMARK,NYTOTNUM,NYCREATEDATE,NYUPDATEDATE from zx_notify where nyno='" + no + "'");
		List<ReceivingNotice> list = null;
		list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(ReceivingNotice.class));
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<String> getListNotice() {
		String sql="select nyno from zx_notify";
		List<String> list=null;
		list=this.getJdbcTemplate().queryForList(sql, String.class);
		return list;
	}
	
	@Override
	public boolean add(ReceivingNotice receivingNotice) {
		receivingNotice.setId(SqlUtil.getID(ReceivingNotice.class));
		return super.add(receivingNotice);
	}
	
	@Override
	public boolean update(ReceivingNotice receivingNotice) {
		return super.update(receivingNotice);
	}

}

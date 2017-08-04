package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.csms.zxbank.dao.IGagerDAO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 *	质物入库
 */
public class GagerDAO extends DAOSupport implements IGagerDAO {

	public GagerDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gager> findAllList(Gager query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT GAID,GALONENTNO,GALONENTNAME,GAOPRTNAME,GAORDERNO,GAPCGRTNTNO,GACMGRTCNTNO,GAWHCODE,GAREMARK,GACONFIRMNO,GACOUNT,GASTATE,GACREATEDATE FROM ZX_GAGER");
		List<Object> parameter = new ArrayList<Object>();
		List<Gager> list = null;
		formatSQL(query, parameter, sql);
		try {
			list = tools.goPage(sql.toString(), parameter.toArray(), new BeanPropertyRowMapper(Gager.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(Gager query, List<Object> parameter, StringBuffer sql) {
		sql.append(" WHERE 1=1");
		if (!StringUtil.isEmpty(query.getGaLonentno())) {
			parameter.add("%" + query.getGaLonentno().trim() + "%");
			sql.append(" AND GALONENTNO LIKE ?");
		}
		sql.append(" order by gacreatedate desc nulls last");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Gager getGager(int gaid) {
		StringBuffer sql = new StringBuffer();
		if (!StringUtil.isEmpty(gaid + "")) {
			sql.append("SELECT GAID,GALONENTNO,GALONENTNAME,GAOPRTNAME,GAORDERNO,GAPCGRTNTNO,GACMGRTCNTNO,GAWHCODE,GAREMARK,GACONFIRMNO,GACOUNT,GASTATE,GACREATEDATE FROM ZX_GAGER WHERE GAID=" + gaid);
		}
		List<Gager> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(Gager.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

}

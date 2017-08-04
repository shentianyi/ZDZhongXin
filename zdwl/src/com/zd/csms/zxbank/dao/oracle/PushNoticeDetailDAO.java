package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.bean.PushNoticeDetail;
import com.zd.csms.zxbank.dao.IPushNoticeDetailDAO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class PushNoticeDetailDAO extends DAOSupport implements IPushNoticeDetailDAO {

	public PushNoticeDetailDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PushNoticeDetail> findNotice(PushNoticeDetail query, IThumbPageTools tools) {
		List<PushNoticeDetail> list = null;
		StringBuffer sql = new StringBuffer("SELECT PNDECIFCODE,PNDOPERORG,PNDVIN,PNDLOANCODE,STATE FROM ZX_PUSH_NOTICE_DETAIL");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query, sql, params);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PushNoticeDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void formatSQL(PushNoticeDetail query, StringBuffer sql, List<Object> params) {

		sql.append(" WHERE 1=1 ");
		if (!StringUtil.isEmpty(query.getNtcno())) {
			sql.append(" AND NTCNO = ? ");
			params.add(query.getNtcno().trim());
		}
		if (query.getPndvin()!=null) {
			sql.append(" AND PNDVIN = ? ");
			params.add(query.getPndvin().trim());
		}
		if(query.getState()!=0){
			sql.append(" AND STATE = ?");
			params.add(query.getState());
		}

	}

	@Override
	public List<String> getListNtcno() {
		String sql = "SELECT PNDVIN FROM ZX_PUSH_NOTICE_DETAIL";
		List<String> list=null;
		list=this.getJdbcTemplate().queryForList(sql, String.class);
		return list;
	}

	@Override
	public List<PushNoticeDetail> findPushNoticeDetail() {
		String sql = "SELECT * FROM ZX_PUSH_NOTICE_DETAIL";
		return this.getJdbcTemplate().queryForList(sql, PushNoticeDetail.class);
	}

}

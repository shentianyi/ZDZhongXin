package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;


import com.zd.core.DAOSupport;

import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.dao.INoticeDAO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class NoticeDAO extends DAOSupport implements INoticeDAO{

	public NoticeDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<Notice> findNotice(Notice query, IThumbPageTools tools) {
		List<Notice> list = null;
		StringBuffer sql = new StringBuffer("select n.nt_id,n.nt_type,n.nt_no,n.nt_stdate,n.nt_enddate,n.nt_failflag from zx_notice n");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query,sql, params);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Notice.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Notice> findnoticetype() {
		StringBuffer sql = new StringBuffer("select distinct nt_type from zx_notice");
		return getJdbcTemplate().query(sql.toString(),new BeanPropertyRowMapper(Notice.class));
	}
	
	public void formatSQL(Notice query,StringBuffer sql,List<Object> params){
		
		sql.append(" where 1=1 ");
		if(!StringUtil.isEmpty(query.getNtNo())){
			sql.append(" and n.nt_no like ? ");
			params.add("%"+query.getNtNo().trim()+"%");
		}
		if(query.getNtType()!=0 ){
			sql.append(" and n.nt_type = ? ");
			params.add(query.getNtType());
		}
		
	}

	
}

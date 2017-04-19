package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.csms.zxbank.dao.IReceivingNoticeDAO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ReceivingNoticeDAO extends DAOSupport implements IReceivingNoticeDAO {

	public ReceivingNoticeDAO(String dataSourceName) {
		super(dataSourceName);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceivingNotice> firnAllAgList(ReceivingNotice query, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zx_notify");
		List<Object> parameters=new ArrayList<>();
		formatSQL(sql,parameters,query);
		List<ReceivingNotice> list=null;
		try {
			list=tools.goPage(sql.toString(), parameters.toArray(), new BeanPropertyRowMapper(ReceivingNotice.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(StringBuffer sql, List<Object> parameters, ReceivingNotice query) {
		sql.append(" where 1=1");
		if(!StringUtil.isEmpty(query.getNyNo())){
			parameters.add("%"+query.getNyNo().trim()+"%");
			sql.append(" and nyNo like ?");
		}
		if(!StringUtil.isEmpty(query.getNyLonentname())){
			parameters.add("%"+query.getNyLonentname().trim()+"%");
			sql.append(" and nyLonentname like ?");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReceivingNotice getNotify(String no) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zx_notify where nyno='"+no+"'");
		List<ReceivingNotice> list=null;
		list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(ReceivingNotice.class));
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

}

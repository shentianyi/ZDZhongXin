package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.PushNoticeDetail;
import com.zd.csms.zxbank.dao.IPushNoticeDetailDAO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class PushNoticeDetailDAO extends DAOSupport implements IPushNoticeDetailDAO {

	public PushNoticeDetailDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PushNoticeDetail> findNotice(PushNoticeDetail query,
			IThumbPageTools tools) {
		List<PushNoticeDetail> list = null;
		String sql ="SELECT PNDECIFCODE,PNDOPERORG,PNDVIN,PNDLOANCODE FROM ZX_PUSH_NOTICE_DETAIL";
		try {
			list =getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PushNoticeDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

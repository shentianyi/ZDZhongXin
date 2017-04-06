package com.zd.csms.zxbank.dao.oracle;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.ReceivingDetail;
import com.zd.csms.zxbank.dao.IReceivingDetailDAO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ReceivingDetailDAO extends DAOSupport implements IReceivingDetailDAO{

	public ReceivingDetailDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceivingDetail> firnAllAgList(ReceivingDetail query, IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zx_notifydetail where nd_no='"+query.getNdNo()+"'");
		List<ReceivingDetail> list=null;
		try {
			list=tools.goPage(sql.toString(),new BeanPropertyRowMapper(ReceivingDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ReceivingDetail> firnAll(ReceivingDetail query) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from zx_notifydetail where nd_no='"+query.getNdNo()+"'");
		List<ReceivingDetail> list=null;
		try {
			list=getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(ReceivingDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

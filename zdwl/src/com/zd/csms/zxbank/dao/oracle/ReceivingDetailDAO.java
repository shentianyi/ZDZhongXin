package com.zd.csms.zxbank.dao.oracle;

import java.util.List;

import org.jfree.util.Log;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.ReceivingDetail;
import com.zd.csms.zxbank.dao.IReceivingDetailDAO;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 收货通知详情DAO实现层 
 */
public class ReceivingDetailDAO extends DAOSupport implements IReceivingDetailDAO {

	public ReceivingDetailDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceivingDetail> firnAllAgList(ReceivingDetail query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from zx_notifydetail where ndno='" + query.getNdNo() + "'");
		List<ReceivingDetail> list = null;
		try {
			list = tools.goPage(sql.toString(), new BeanPropertyRowMapper(ReceivingDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceivingDetail> findAll(String no) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from zx_notifydetail where ndno='" + no + "'");
		List<ReceivingDetail> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(ReceivingDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean add(ReceivingDetail receivingDetail) {
		receivingDetail.setId(SqlUtil.getID(ReceivingDetail.class));
		Log.info("详情:["+receivingDetail.toString()+"]");
		return super.add(receivingDetail);
	}
	
//	@Override
//	public boolean update(ReceivingDetail receivingDetail) {
//		return super.update(receivingDetail);
//	}
}

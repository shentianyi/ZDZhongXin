package com.zd.csms.supervisory.dao.oracle.outstock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.supervisory.dao.outstock.SupervisorOutStockMessageDao;
import com.zd.csms.supervisory.model.outstock.SupervisorOutStockMessageQueryVO;
import com.zd.csms.supervisory.model.outstock.SupervisorOutStockMessageVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupervisorOutStockMessageImpl extends DAOSupport implements SupervisorOutStockMessageDao {

	public SupervisorOutStockMessageImpl(String dataSourceName) {
		super(dataSourceName);
	}


	@Override
	public List<SupervisorOutStockMessageVO> searchOutStockList(
			SupervisorOutStockMessageQueryVO query) {
		return null;
	}

	@Override
	public List<SupervisorOutStockMessageVO> searchOutStockListByPage(
			SupervisorOutStockMessageQueryVO query, IThumbPageTools tools) {
		
		List<SupervisorOutStockMessageVO> list = new ArrayList<SupervisorOutStockMessageVO>();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_SUPERVISOR_OUTSTOCK where MESSAGETYPE = '" + MessageTypeContant.SUPSERVISOUTSTOCK.getName()+"'");
		sql.append(" and userid = "+ query.getUserId()+"");
		formatSQL(sql,params,query);
		sql.append(" order by isread asc ");
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(SupervisorOutStockMessageVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	
	private void formatSQL(StringBuffer sql, List<Object> params,
			SupervisorOutStockMessageQueryVO query) {

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		if (StringUtil.isNotEmpty(query.getMerchantname())) {
			sql.append(" and merchantname like ?");
			params.add("%" + query.getMerchantname() + "%");
		}
		if (StringUtil.isNotEmpty(query.getBankname())) {
			sql.append(" and bankname like ?");
			params.add("%" + query.getBankname() + "%");
		}
		if (StringUtil.isNotEmpty(query.getBrandname())) {
			sql.append(" and brandname like ?");
			params.add("%" + query.getBrandname() + "%");
		}
		if (StringUtil.isNotEmpty(query.getVin())) {
			sql.append(" and vin like ?");
			params.add("%" + query.getVin() + "%");
		}
		if (StringUtil.isNotEmpty(query.getDraft_num())) {
			sql.append(" and draft_num like ?");
			params.add("%" + query.getDraft_num() + "%");
		}
		if (query.getOutTime() != null) {
			sql.append(" and outtime  >= to_date('"+sdf.format(query.getOutTime())+"','yyyy-mm-dd')");
		}
		if (query.getOutEndDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(query.getOutEndDate());
			cal.add(Calendar.DATE, 1);
			sql.append(" and outtime < to_date('"+sdf.format(cal.getTime())+"','yyyy-mm-dd')");
		}
		
	}
	
	
	@Override
	public boolean updateReadStatus(int userId,int id) {
		String sql="update T_SUPERVISOR_OUTSTOCK set isread = 2  where userid = ? and id = ? ";
		
		boolean flag = false;
		String Mssql = "";
		flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.SUPSERVISOUTSTOCK.getName(),userId);
			if (!flag) {
				Mssql ="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.SUPSERVISOUTSTOCK.getCode() +"";
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}

	@Override
	public boolean getReadStatus(String messageType,int userId) {
		
		StringBuffer sql = new StringBuffer("select count(*) from T_SUPERVISOR_OUTSTOCK where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and userId = ?");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	}
	
	
}

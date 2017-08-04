package com.zd.csms.supervisory.dao.oracle.overtime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.supervisory.dao.costmaill.SupervisorCostMailMessageDao;
import com.zd.csms.supervisory.dao.overtime.SupervisorOverTimeMessageDao;
import com.zd.csms.supervisory.model.costmail.SupervisorCostMailMessageQueryVO;
import com.zd.csms.supervisory.model.costmail.SupervisorCostMailMessageVO;
import com.zd.csms.supervisory.model.overtime.SupervisorOverTimeMessageQueryVO;
import com.zd.csms.supervisory.model.overtime.SupervisorOverTimeMessageVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupervisorOverTimeMessageImpl extends DAOSupport implements SupervisorOverTimeMessageDao {

	public SupervisorOverTimeMessageImpl(String dataSourceName) {
		super(dataSourceName);
	}


	@Override
	public List<SupervisorOverTimeMessageVO> searchOverTimeList(
			SupervisorOverTimeMessageQueryVO query) {
		return null;
	}

	@Override
	public List<SupervisorOverTimeMessageVO> searchOverTimeListByPage(
			SupervisorOverTimeMessageQueryVO query, IThumbPageTools tools) {
		
		List<SupervisorOverTimeMessageVO> list = new ArrayList<SupervisorOverTimeMessageVO>();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_SUPERVISOR_OVERTIME where MESSAGETYPE = '" + MessageTypeContant.SUPSERVISOVERTIMEAPPLICATION.getName()+"'");
		sql.append(" and userid = "+ query.getUserId()+"");
		formatSQL(sql,params,query);
		sql.append(" order by isread asc ");
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(SupervisorOverTimeMessageVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	
	private void formatSQL(StringBuffer sql, List<Object> params,
			SupervisorOverTimeMessageQueryVO query) {

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		if (StringUtil.isNotEmpty(query.getMerchantname())) {
			sql.append(" and merchantname like ?");
			params.add("%" + query.getMerchantname() + "%");
		}
		if (StringUtil.isNotEmpty(query.getBankname())) {
			sql.append(" and bankname like ?");
			params.add("%" + query.getBankname() + "%");
		}
		if (StringUtil.isNotEmpty(query.getName())) {
			sql.append(" and name like ? ");
			params.add("%" + query.getName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getJobnumber())) {
			sql.append(" and jobnumber like ? ");
			params.add("%" + query.getJobnumber() + "%");
		}
		if (query.getBeginDate() != null) {
			sql.append(" and begindate  >= to_date('"+sdf.format(query.getBeginDate())+"','yyyy-mm-dd')");
		}
		if (query.getEndDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(query.getEndDate());
			cal.add(Calendar.DATE, 1);
			sql.append(" and endDate <= to_date('"+sdf.format(cal.getTime())+"','yyyy-mm-dd')");
		}
		
	}
	
	
	@Override
	public boolean updateReadStatus(int userId,int id) {
		String sql="update T_SUPERVISOR_OVERTIME set isread = 2  where userid = ? and id = ? ";
		
		boolean flag = false;
		String Mssql = "";
		flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.SUPSERVISOVERTIMEAPPLICATION.getName(),userId);
			if (!flag) {
				Mssql ="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.SUPSERVISOVERTIMEAPPLICATION.getCode() +"";
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}

	@Override
	public boolean getReadStatus(String messageType,int userId) {
		
		StringBuffer sql = new StringBuffer("select count(*) from T_SUPERVISOR_OVERTIME where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and userid = ? ");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	}
	
	
}

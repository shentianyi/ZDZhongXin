package com.zd.csms.supervisory.dao.oracle.movestock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.supervisory.dao.movestock.SupervisorMoveStockMessageDao;
import com.zd.csms.supervisory.model.movestock.SupervisorMoveStockMessageQueryVO;
import com.zd.csms.supervisory.model.movestock.SupervisorMoveStockMessageVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupervisorMoveStockMessageImpl extends DAOSupport implements SupervisorMoveStockMessageDao {

	public SupervisorMoveStockMessageImpl(String dataSourceName) {
		super(dataSourceName);
	}


	@Override
	public List<SupervisorMoveStockMessageVO> searchMoveStockList(
			SupervisorMoveStockMessageQueryVO query) {
		return null;
	}

	@Override
	public List<SupervisorMoveStockMessageVO> searchMoveStockListByPage(
			SupervisorMoveStockMessageQueryVO query, IThumbPageTools tools) {
		
		List<SupervisorMoveStockMessageVO> list = new ArrayList<SupervisorMoveStockMessageVO>();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_SUPERVISOR_MOVESTOCK where MESSAGETYPE = '" + MessageTypeContant.SUPSERVIMOVETSTOCK.getName()+"'");
		sql.append(" and userid = "+ query.getUserId()+"");
		formatSQL(sql,params,query);
		sql.append(" order by isread asc ");
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(SupervisorMoveStockMessageVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	
	private void formatSQL(StringBuffer sql, List<Object> params,
			SupervisorMoveStockMessageQueryVO query) {

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
		if (query.getMoveTime() != null) {
			sql.append(" and movetime  >= to_date('"+sdf.format(query.getMoveTime())+"','yyyy-mm-dd')");
		}
		if (query.getMoveTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(query.getMoveEndTime());
			cal.add(Calendar.DATE, 1);
			sql.append(" and movetime < to_date('"+sdf.format(cal.getTime())+"','yyyy-mm-dd')");
		}
		
	}
	
	
	@Override
	public boolean updateReadStatus(int userId,int id) {
		String sql="update T_SUPERVISOR_MOVESTOCK set isread = 2  where userid = ? and id = ? ";
		
		boolean flag = false;
		String Mssql = "";
		flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.SUPSERVIMOVETSTOCK.getName(),userId);
			if (!flag) {
				Mssql ="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.SUPSERVIMOVETSTOCK.getCode() +"";
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}

	@Override
	public boolean getReadStatus(String messageType,int userId) {
		
		StringBuffer sql = new StringBuffer("select count(*) from T_SUPERVISOR_MOVESTOCK where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and userId = ?");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	}
	
	
}

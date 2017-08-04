package com.zd.csms.supervisory.dao.oracle.repairecostms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.supervisory.dao.repairecostms.SupervisorRepairCostMessageDao;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageQueryVO;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupervisorRepairCostMessageImpl extends DAOSupport implements SupervisorRepairCostMessageDao {

	public SupervisorRepairCostMessageImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_repair_cost = "select * from t_repair_cost";

	@Override
	public List<SupervisorRepairCostMessageVO> searchRepairCostList(
			SupervisorRepairCostMessageQueryVO query) {
		return null;
	}

	@Override
	public List<SupervisorRepairCostMessageVO> searchRepairCostListByPage(
			SupervisorRepairCostMessageQueryVO query, IThumbPageTools tools) {
		
		List<SupervisorRepairCostMessageVO> list = new ArrayList<SupervisorRepairCostMessageVO>();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_SUPERVISOR_REPAIRCOST where MESSAGETYPE = '" + MessageTypeContant.SUPSERVISREPAIRCOST.getName()+"'");
		sql.append(" and userid = "+ query.getUserId()+"");
		formatSQL(sql,params,query);
		sql.append(" order by isread asc ");
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(SupervisorRepairCostMessageVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	
	private void formatSQL(StringBuffer sql, List<Object> params,
			SupervisorRepairCostMessageQueryVO query) {

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
		if (StringUtil.isNotEmpty(query.getMaintenanceitems())) {
			sql.append(" and maintenanceitems like ? ");
			params.add("%"+query.getMaintenanceitems()+"%");
		}
	}
	
	
	@Override
	public boolean updateReadStatus(int userId,int id) {
		String sql="update T_SUPERVISOR_REPAIRCOST set isread = 2  where userid = ? and id = ? ";
		boolean flag = false;
		String Mssql = "";
		flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.SUPSERVISREPAIRCOST.getName(),userId);
			if (!flag) {
				Mssql ="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.SUPSERVISREPAIRCOST.getCode() +"";
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}

	@Override
	public boolean getReadStatus(String messageType,int userId) {
		
		StringBuffer sql = new StringBuffer("select count(*) from t_supervisor_repaircost where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and userId = ?");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	}
	
	
}

package com.zd.csms.messagequartz.dao.oracle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messagequartz.dao.UnInspectionPlanDao;
import com.zd.csms.messagequartz.model.UnInspectionPlanQueryVO;
import com.zd.csms.messagequartz.model.UnInspectionPlanVO;
import com.zd.csms.planandreport.model.InspectionPlanVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class UnInspectionPlanDaoImpl extends DAOSupport implements UnInspectionPlanDao {

	public UnInspectionPlanDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<InspectionPlanVO> findList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select p.* from t_inspection_planinfo tip ");
		sql.append(" inner join WINDCONTROL_INSPEC_INFO win on win.id = tip.id ");
		sql.append(" inner join t_inspection_plan p on p.id = tip.id ");
		sql.append(" where to_char(tip.PREDICTFINISHDATE, 'yyyy-MM-dd') <= to_char(sysdate, 'yyyy-MM-dd')");
		
		List<InspectionPlanVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					new BeanPropertyRowMapper(InspectionPlanVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}

		return result;
	}
	@Override
	public List<UnInspectionPlanVO> searchUnInspectionPlanList(
			UnInspectionPlanQueryVO query) {
		return null;
	}

	@Override
	public List<UnInspectionPlanVO> searchUnInspectionPlanByPage(
			UnInspectionPlanQueryVO query, IThumbPageTools tools) {
		
		List<UnInspectionPlanVO> list = new ArrayList<UnInspectionPlanVO>();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_SUPERVISOR_UNINSPECTION_PLAN where MESSAGETYPE = '" + MessageTypeContant.UNINSPECTIONVID.getCode()+"'");
		sql.append(" and userid = "+ query.getUserId()+"");
		formatSQL(sql,params,query);
		sql.append(" order by isread asc ");
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(UnInspectionPlanVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	
	private void formatSQL(StringBuffer sql, List<Object> params,
			UnInspectionPlanQueryVO query) {

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
		if (StringUtil.isNotEmpty(query.getDirector())) {
			sql.append(" and director like ?");
			params.add("%" + query.getDirector() + "%");
		}
		if (query.getBeginTime() != null) {
			sql.append(" and movetime  >= to_date('"+sdf.format(query.getBeginTime())+"','yyyy-mm-dd')");
		}
		if (query.getEndTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(query.getEndTime());
			cal.add(Calendar.DATE, 1);
			sql.append(" and movetime < to_date('"+sdf.format(cal.getTime())+"','yyyy-mm-dd')");
		}
		
	}
	
	
	@Override
	public boolean updateReadStatus(int userId,int id) {
		String sql="update T_SUPERVISOR_UNINSPECTION_PLAN set isread = 2  where userid = ? and id = ? ";
		boolean flag = false;
		String Mssql = "";
		flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.UNINSPECTIONVID.getCode(),userId);
			if (!flag) {
				Mssql ="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.UNINSPECTIONVID.getCode() +"";
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}

	@Override
	public boolean getReadStatus(int messageType,int userId) {
		
		StringBuffer sql = new StringBuffer("select count(*) from T_SUPERVISOR_UNINSPECTION_PLAN where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and userid = ?");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	}
	
	
}

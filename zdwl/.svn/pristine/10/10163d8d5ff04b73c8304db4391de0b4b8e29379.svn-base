package com.zd.csms.messagequartz.dao.oracle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messagequartz.dao.UnInspectDao;
import com.zd.csms.messagequartz.model.UnInspectMes;
import com.zd.csms.messagequartz.model.UnInspectQueryVO;
import com.zd.csms.messagequartz.model.UnInspectVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class UnInspectDaoImpl extends DAOSupport implements UnInspectDao {

	public UnInspectDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<UnInspectMes> findList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select tus.userName as outControlUserName, wii.modify_time, wii.address ");
		sql.append("from t_inspection_plan tip ");
		sql.append("inner join windcontrol_inspec_info wii ");
		sql.append(" on wii.id = tip.id ");
		sql.append(" left join t_rbac_user tus ");
		sql.append(" on tus.id = tip.outcontroluserid ");
		sql.append(" where to_char(wii.modify_time +3,'yyyymmdd')<to_char(sysdate,'yyyymmdd') ");
		List<UnInspectMes> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					new BeanPropertyRowMapper(UnInspectMes.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}

		return result;
	}
	@Override
	public List<UnInspectVO> searchUnInspectList(
			UnInspectQueryVO query) {
		return null;
	}

	@Override
	public List<UnInspectVO> searchUnInspectByPage(
			UnInspectQueryVO query, IThumbPageTools tools) {
		
		List<UnInspectVO> list = new ArrayList<UnInspectVO>();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_SUPERVISOR_UNINSPECT where MESSAGETYPE = '" + MessageTypeContant.UNINSPECTI.getCode()+"'");
		sql.append(" and userid = "+ query.getUserId()+"");
		formatSQL(sql,params,query);
		sql.append(" order by isread asc ");
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(UnInspectVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	
	private void formatSQL(StringBuffer sql, List<Object> params,
			UnInspectQueryVO query) {

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		if (StringUtil.isNotEmpty(query.getAddress())) {
			sql.append(" and address like ?");
			params.add("%" + query.getAddress() + "%");
		}
		if (StringUtil.isNotEmpty(query.getDirector())) {
			sql.append(" and director like ?");
			params.add("%" + query.getDirector() + "%");
		}
		if (query.getLastModified() != null) {
			sql.append(" and lastmodified  >= to_date('"+sdf.format(query.getLastModified())+"','yyyy-mm-dd')");
		}
		if (query.getEndLastModified() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(query.getEndLastModified());
			cal.add(Calendar.DATE, 1);
			sql.append(" and lastmodified < to_date('"+sdf.format(cal.getTime())+"','yyyy-mm-dd')");
		}
		
	}
	
	
	@Override
	public boolean updateReadStatus(int userId,int id) {
		String sql="update T_SUPERVISOR_UNINSPECT set isread = 2  where userid = ? and id = ? ";
		
		boolean flag = false;
		String Mssql = "";
		flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.UNINSPECTI.getCode(),userId);
			if (!flag) {
				Mssql ="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.UNINSPECTI.getCode() +"";
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}

	@Override
	public boolean getReadStatus(int messageType,int userId) {
		
		StringBuffer sql = new StringBuffer("select count(*) from T_SUPERVISOR_UNINSPECT where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and userId = ?");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	}
	
	
}

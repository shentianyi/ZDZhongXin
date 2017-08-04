package com.zd.csms.messagequartz.dao.oracle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.DAOSupport;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messagequartz.dao.InspectRemindDao;
import com.zd.csms.messagequartz.model.InspectRemindInfoVO;
import com.zd.csms.messagequartz.model.InspectRemindQueryVO;
import com.zd.csms.messagequartz.model.InspectRemindVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class InspectRemindDaoImpl extends DAOSupport implements InspectRemindDao {

	public InspectRemindDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<InspectRemindVO> searchInspectRemindList(
			InspectRemindQueryVO query) {
		return null;
	}

	@Override
	public List<InspectRemindVO> searchInspectRemindByPage(
			InspectRemindQueryVO query, IThumbPageTools tools) {
		
		List<InspectRemindVO> list = new ArrayList<InspectRemindVO>();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_SUPERVISOR_INSPECT where MESSAGETYPE = '" + MessageTypeContant.COINSPECTI.getCode()+"'");
		sql.append(" and userid = "+ query.getUserId()+"");
		formatSQL(sql,params,query);
		sql.append(" order by isread asc ");
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(InspectRemindVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	
	private void formatSQL(StringBuffer sql, List<Object> params,
			InspectRemindQueryVO query) {

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
		/*if (query.getBeginTime() != null) {
			sql.append(" and movetime  >= to_date('"+sdf.format(query.getBeginTime())+"','yyyy-mm-dd')");
		}
		if (query.getEndTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(query.getEndTime());
			cal.add(Calendar.DATE, 1);
			sql.append(" and movetime < to_date('"+sdf.format(cal.getTime())+"','yyyy-mm-dd')");
		}*/
		
	}
	
	
	@Override
	public boolean updateReadStatus(int userId,int id) {
		String sql="update T_SUPERVISOR_INSPECT set isread = 2  where userid = ? and id = ? ";
		
		boolean flag = false;
		String Mssql = "";
		flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.COINSPECTI.getCode(),userId);
			if (!flag) {
				Mssql ="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.COINSPECTI.getCode() +"";
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}

	@Override
	public boolean getReadStatus(int messageType,int userId) {
		
		StringBuffer sql = new StringBuffer("select count(*) from T_SUPERVISOR_INSPECT where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and userId = ?");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public InspectRemindInfoVO getInfoVO(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select tus.userName as outControlUserName,md.dealername,");
		sql.append(" (select tr.name from t_region tr where tr.id = md.province) as provincename,");
		sql.append( " (select tr.name from t_region tr where tr.id = md.city) as cityname,");
		sql.append(" tb.bankfullname,tba.name as brandname from t_inspection_plan tip ");
		sql.append("   left join t_rbac_user tus on tus.id = tip.outcontroluserid ");
		sql.append(" inner join market_dealer md on md.id = tip.dealerid ");
		sql.append(" inner join market_dealer_supervisor mds on md.id = mds.dealerid");
		sql.append(" inner join t_bank tb on tb.id = mds.bankid inner join t_brand tba on tba.id = md.brandid ");
		sql.append("  inner join windcontrol_inspec_info wii on wii.id = tip.id where wii.id = " + id);
		List<InspectRemindInfoVO> result = this.getJdbcTemplate().query(sql.toString(),
				new BeanPropertyRowMapper(InspectRemindInfoVO.class));
		if (result != null) {
			return result.get(0);
		}
		return null;
	}
	
	
}

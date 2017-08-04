package com.zd.csms.messagequartz.dao.oracle;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.attendance.dao.mapper.SignRecordRowMapper;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messagequartz.dao.DealerSupervisorDao;
import com.zd.csms.messagequartz.model.MsgBillNoCarVO;
import com.zd.csms.messagequartz.model.SupervisorVO;
import com.zd.csms.messagequartz.querybean.MsgOutStorageQueryBean;
import com.zd.csms.planandreport.model.InspectionPlanVO;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 监管员定时任务监管员生日提醒
 *
 */
public class DealerSupervisorDaoImpl extends DAOSupport implements DealerSupervisorDao{

	public DealerSupervisorDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	@Override
	public List<SupervisorVO> findList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select u.id,s.name from");
		sql.append(" T_SUPERVISOR_BASIC_INFORMATION s ");
		sql.append(" inner join T_REPOSITORY r on r.supervisorid=s.id");
		sql.append(" inner join t_rbac_user u on r.id=u.client_id");
		sql.append(" where to_char(sysdate,'mmdd') = to_char(s.BIRTHDAY,'mmdd')");
		sql.append(" and u.state = 1");
		List<SupervisorVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					new BeanPropertyRowMapper(SupervisorVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	@Override
	public List<SupervisorVO> findListSupervisors(SupervisorVO query,
			IThumbPageTools tools,int userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_SUPERVISOR_MESSAGE where MESSAGETYPE = "+ MessageTypeContant.SUPSERVISORBIRTHDAY.getCode() +" and SUPERVISORID ="+userId);
		sql.append(" order by isread asc ");
		List<SupervisorVO> list = null;
		try {
			list = tools.goPage(sql.toString(),
					new BeanPropertyRowMapper(SupervisorVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean updateReadStatus(int userId,int id) {
		String Mssql="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.SUPSERVISORBIRTHDAY.getCode() +"";
		String sql="update T_SUPERVISOR_MESSAGE set isread = 2  where SUPERVISORID = ? and id = ? ";
		boolean flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.SUPSERVISORBIRTHDAY.getCode(),userId);
			if (!flag) {
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}

	@Override
    public boolean getReadStatus(int messageType,int userId) {
		StringBuffer sql = new StringBuffer("select count(*) from T_SUPERVISOR_MESSAGE where messagetype='"+messageType+"'");
		sql.append(" and isread = ? and SUPERVISORID = ?");
		int count = getJdbcTemplate().queryForInt(sql.toString(), new Object[]{1,userId});
		boolean flag = false;
		if (count>0) {
			flag = true;
		}
		return flag;
	 }
	@Override
	public List<SupervisorVO> findListinYear() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.id from ");
		sql.append(" T_TRANSFER_REPOSITORY s ");
		sql.append(" inner join T_REPOSITORY r on r.id =s.REPOSITORYID ");
		sql.append(" inner join t_rbac_user u on r.id=u.client_id ");
		sql.append(" inner join ");
		sql.append(" (select u.id as id,min(s.entrytime)as entrytime from");
		sql.append("  T_TRANSFER_REPOSITORY s ");
		sql.append(" inner join T_REPOSITORY r on r.id =s.REPOSITORYID ");
		sql.append(" inner join t_rbac_user u on r.id=u.client_id ");
		sql.append(" group by u.id) ss on ss.id = u.id ");
		sql.append(" where to_char(sysdate,'yyyymmdd') = to_char(add_months(ss.ENTRYTIME,12),'yyyymmdd')");
		sql.append(" and u.state = 1");
		sql.append(" group by u.id");
		List<SupervisorVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					new BeanPropertyRowMapper(SupervisorVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
		
	}
	@Override
	public List<SupervisorVO> findListSupervisorsYear(SupervisorVO query,
			IThumbPageTools tools,int userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_SUPERVISOR_MESSAGE where MESSAGETYPE = "+ MessageTypeContant.SUPSERVISORBIRTHDAYYEARANDDAY.getCode() +" and SUPERVISORID ="+userId);
		sql.append(" order by isread asc ");
		List<SupervisorVO> list = null;
		try {
			list = tools.goPage(sql.toString(),
					new BeanPropertyRowMapper(SupervisorVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean updateYearReadStatus(int userId,int id) {
		String Mssql="update T_MESSAGE set isread = 2  where USERID = ? and type = "+ MessageTypeContant.SUPSERVISORBIRTHDAYYEARANDDAY.getCode() +"";
		String sql="update T_SUPERVISOR_MESSAGE set isread = 2  where SUPERVISORID = ? and id = ? ";
		boolean flag = getJdbcTemplate().update(sql, new Object[]{userId,id})>=0;
		if (flag) {
			flag = getReadStatus( MessageTypeContant.SUPSERVISORBIRTHDAYYEARANDDAY.getCode(),userId);
			if (!flag) {
				flag = getJdbcTemplate().update(Mssql, new Object[]{userId})>=0;
			}
		}
		return flag;
	}
	
	@Override
	public MsgBillNoCarVO loadDraftByNum(String draftNum) {
			MsgBillNoCarVO vo = null;
			String sql = " select t.* from t_msg_billnocar t where t.draft_num = ? ";
			try {
				vo = (MsgBillNoCarVO) getJdbcTemplate().queryForObject(sql, new Object[]{draftNum},new BeanPropertyRowMapper(MsgBillNoCarVO.class));
			} catch (Exception e) {
				e.printStackTrace();
				vo = null;
			}
			return vo;
	}
	
	@Override
	public String findUserIdByDraftNum(String draftNum) {
		String sql="select wm_concat(tyw.userid) from t_draft td "
				+ "inner join market_dealer_supervisor mds on td.distribid = mds.dealerid "
				+ "inner join t_yw_bank tyw on tyw.bankid = mds.bankid where td.draft_num = ? ";
		return (String) getJdbcTemplate().queryForObject(sql,new Object[]{draftNum},String.class);
	}
	
	@Override
	public String findSuperviseIdByDraftNum(String draftNum) {
		String sql="select wm_concat(ru.id) from t_draft td "
				+ "inner join market_dealer_supervisor mds on td.distribid = mds.dealerid "
				+ "inner join t_rbac_user ru on ru.client_id = mds.repositoryid "
				+ "where td.draft_num = ? and ru.client_type = " + ClientTypeConstants.SUPERVISORY.getCode();
		return (String) getJdbcTemplate().queryForObject(sql,new Object[]{draftNum},String.class);
	}
	/**
	 * 根据经销商id获取业务专员id
	 */
	@Override
	public String findUserIdByDealerId(int dealerId) {
		String sql="select wm_concat(tyw.userid) from market_dealer md "
				+ "inner join market_dealer_supervisor mds on md.id = mds.dealerid "
				+ "inner join t_yw_bank tyw on tyw.bankid = mds.bankid where md.id = ? ";
		return (String) getJdbcTemplate().queryForObject(sql,new Object[]{dealerId},String.class);
	}
	/**
	 * 根据经销商id获取监管员id
	 */
	@Override
	public String findSuperviseIdByDealerId(int dealerId) {
		String sql="select wm_concat(ru.id) from market_dealer md "
				+ "inner join market_dealer_supervisor mds on md.id = mds.dealerid "
				+ "inner join t_rbac_user ru on ru.client_id = mds.repositoryid "
				+ "where md.id = ? and ru.client_type = " + ClientTypeConstants.SUPERVISORY.getCode();
		return (String) getJdbcTemplate().queryForObject(sql,new Object[]{dealerId},String.class);
	}
	
	/**
	 * 根据经销商id获取市场专员id
	 */
	@Override
	public String findMarketIdByDealerId(int dealerId) {
		String sql="select wm_concat(md.createuserid) from market_dealer md where md.id = ? ";
		return (String) getJdbcTemplate().queryForObject(sql,new Object[]{dealerId},String.class);
	}
	
	/**
	 * 根据经销商id获取市场专员id
	 */
	@Override
	public String findMarketIdByDraftNum(String draft_num) {
		String sql=" select wm_concat(md.createuserid) from market_dealer md " + 
				   " inner join t_draft td on td.distribid = md.id where td.draft_num = ? ";
		return (String) getJdbcTemplate().queryForObject(sql,new Object[]{draft_num},String.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

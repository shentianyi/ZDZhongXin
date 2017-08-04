package com.zd.csms.attendance.dao.oralce;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.zd.core.DAOSupport;
import com.zd.csms.attendance.dao.IAttendanceDao;
import com.zd.csms.attendance.dao.mapper.DealerAttendanceTimeMapper;
import com.zd.csms.attendance.dao.mapper.SignRecordCheckSpAllMapper;
import com.zd.csms.attendance.dao.mapper.SignRecordRowMapper;
import com.zd.csms.attendance.model.AttendanceBean;
import com.zd.csms.attendance.model.AttendanceInfo;
import com.zd.csms.attendance.model.AttendanceQueryVO;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.DealerAttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.model.SignRecordBean;
import com.zd.csms.attendance.model.SignRecordCheckSp;
import com.zd.csms.attendance.model.SignRecordCheckSpAll;
import com.zd.csms.attendance.querybean.AttendanceTimeQueryBean;
import com.zd.csms.attendance.querybean.LedgerQueryBean;
import com.zd.csms.attendance.querybean.LedgerQueryVO;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

@Repository
public class AttendanceDaoImpl extends DAOSupport implements IAttendanceDao{
	public AttendanceDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<AttendanceTimeQueryBean> getAttendanceTimeByUserId(int id) {
		
		StringBuffer sql = new StringBuffer();

		sql.append("select tat.* from t_attendance_time tat "
				+ "where tat.id in ( "
				+ " select mds.dealerid from market_dealer_supervisor mds "
				+ "where mds.repositoryid = "
				+ "(select tru.client_id from t_rbac_user tru where tru.id = ? ) ) ");

		List<AttendanceTimeQueryBean> list = null;
		sql.append(" order by tat.id desc ");
		
		try {
			list = getJdbcTemplate().query(sql.toString(), new Object[] { id },
					new BeanPropertyRowMapper(AttendanceTimeQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<AttendanceTime> getAttendanceTimeByRepId(int repositoryid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select tat.* from t_attendance_time tat where tat.id in "
				+ " ( select mds.dealerid from market_dealer_supervisor mds "
				+ "where mds.repositoryid = ? ) order by tat.id desc ");
		List<AttendanceTime> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), new Object[] {repositoryid},
					new BeanPropertyRowMapper(AttendanceTime.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public SignRecord getSignRecordByUserId(Date date,int respId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select tsr.* from t_sign_record tsr where "
				+ " to_char(tsr.createdate,'yyyyMMdd') = "+DateUtil.getStringFormatByDate(date,"yyyyMMdd")+" and tsr.respid = ?");
		return (SignRecord) getJdbcTemplate().queryForObject(
				sql.toString(), new Object[] { respId },
				new SignRecordRowMapper());
	}
	
	
	/**
	 * 根据用户id查询这个月的签到记录
	 * @param userId
	 * @return
	 */
	public List<SignRecord> findListByUserId(int userId,int year,int month){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_SIGN_RECORD t where t.createuserid = ?"
				+ " and to_char(t.createDate,'yyyymm')=?"
				+ " order by t.createDate asc");
		return getJdbcTemplate().query(sql.toString(),new Object[]{userId,year+""+(month<10?"0"+month:""+month)},new SignRecordRowMapper());
	}
	
    @Override
	public List<LedgerQueryBean> findLedgerList(LedgerQueryVO ledgerQuery,
			IThumbPageTools tools) {
		 StringBuffer sql = new StringBuffer();
		sql.append(" select tinfo.* ,att.year , attr.month  from t_attendance_info tinfo ");
		sql.append(" inner join t_attendance att ") ;
		sql.append(" on tinfo.attendanceId=att.id") ;
	    sql.append(" where att.year=?");
	    sql.append(" and att.month=?");	
		return null;
	}

	@Override
	public List<AttendanceBean> findAttendanceList(AttendanceQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select a.id as \"attendanceId\",a.year ,a.month ,a.state as \"attendanceState\",i.* from T_ATTENDANCE_INFO i  "
				+ "  inner join T_ATTENDANCE a on i.attendanceId=a.id where 1=1 ");
	
		List<Object> params = new ArrayList<Object>();
		List<AttendanceBean> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by a.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(AttendanceBean.class));
			System.out.println("findAttendanceList sql:"+sql.toString());
			System.out.println("findAttendanceList params:"+params);
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	public List<AttendanceBean> findAttendanceList(AttendanceQueryVO query){
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select a.id as \"attendanceId\",a.year ,a.month ,a.state as \"attendanceState\",i.* from T_ATTENDANCE_INFO i  "
				+ "  inner join T_ATTENDANCE a on i.attendanceId=a.id where 1=1 ");
	
		List<Object> params = new ArrayList<Object>();
		List<AttendanceBean> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by a.id desc ");
		try {
			list = getJdbcTemplate().query(sql.toString(),params.toArray(), new BeanPropertyRowMapper(AttendanceBean.class));
			System.out.println("findAttendanceList sql:"+sql.toString());
			System.out.println("findAttendanceList params:"+params);
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	private void formatSQL(StringBuffer sql, List<Object> params,
			AttendanceQueryVO query) {
		int currRole = query.getCurrentRole();
		
		if(StringUtil.isNotEmpty(query.getStaffNo())){
			sql.append(" and i.staffNo like ? ");
			params.add("%"+query.getStaffNo()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankNames())){
			sql.append(" and i.bankNames like ? ");
			params.add("%"+query.getBankNames()+"%");
		}
		if(query.getBankId()>0){
			sql.append(" and i.bankIds like ? ");
			params.add("%,"+query.getBankIds()+",%");
		}
		if(query.getDealerId()>0){
			sql.append(" and i.dealerIds like ? ");
			params.add("%,"+query.getDealerId()+",%");
		}
		if(StringUtil.isNotEmpty(query.getDealerNames())){
			sql.append(" and i.dealerNames = ? ");
			params.add("%"+query.getDealerNames()+"%");
		}
		if(StringUtil.isNotEmpty(query.getName())){
			sql.append(" and i.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getProvinceName())){
			sql.append(" and i.provinceName like ? ");
			params.add("%"+query.getProvinceName()+"%");
		}
		if(query.getProvinceId()>0){
			sql.append(" and i.provinceId = ? ");
			params.add(query.getProvinceId());
		}
		if(StringUtil.isNotEmpty(query.getCityName())){
			sql.append(" and i.cityName like ? ");
			params.add("%"+query.getCityName()+"%");
		}
		if(query.getCityId()>0){
			sql.append(" and i.cityId = ? ");
			params.add(query.getCityId());
		}
		if(query.getApprovalState()>=0){
			sql.append(" and i.state = ? ");
			params.add(query.getApprovalState());
		}
		if(currRole>0){
			//管理列表
			if(query.getYear()>0){
				sql.append(" and a.year = ? ");
				params.add(query.getYear());
			}
			if(query.getMonth()>0){
				sql.append(" and a.month = ? ");
				params.add(query.getMonth());
			}
			/*if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()){
				sql.append(" and a.state != ?  ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}else if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
				sql.append(" and a.state = ?  ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}*/
		}else{
			//台账列表
			//拼时间
			if(query.getStartTime()!=null){
				sql.append(" and to_char(a.currentMonth,'yyyymm') >= ? ");
				params.add(DateUtil.getStringFormatByDate(query.getStartTime(), "yyyyMM"));
			}
			if(query.getEndTime()!=null){
				sql.append(" and to_char(a.currentMonth,'yyyymm') <= ? ");
				params.add(DateUtil.getStringFormatByDate(query.getEndTime(), "yyyyMM"));
			}
			/*sql.append("and (a.state = ? or a.state = ?)");
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			params.add(ApprovalContant.APPROVAL_NOPASS.getCode());*/
		}
	}

	@Override
	public boolean updateAttendanceInfoState(int id, int state) {
		String sql=" update T_ATTENDANCE_INFO set state = ? where id = ?  ";
		return getJdbcTemplate().update(sql, new Object[]{state,id})>=0;
	}

	@Override
	public boolean updateAttendanceState(int id, int approvalState,
			int nextApproval) {
		String sql=" update T_ATTENDANCE set state = ?  where id = ? ";
		return getJdbcTemplate().update(sql, new Object[]{approvalState,id})>=0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceInfo> getAttendanceInfoByState(int attendanceId,
			int approvalState) {
		String sql="select i.* from T_ATTENDANCE_INFO i inner join T_ATTENDANCE a on i.attendanceId=a.id "
				+ " where i.attendanceId= ? and i.state=?  ";
		return getJdbcTemplate().query(sql, new Object[]{attendanceId,approvalState}, new BeanPropertyRowMapper(AttendanceInfo.class));
	}

	@Override
	public boolean updateAttendanceInfoStateByAttendanceId(int attendanceId,
			int state) {
		String sql=" update T_ATTENDANCE_INFO set state = ? where attendanceId = ?  and state != 1 ";
		return getJdbcTemplate().update(sql, new Object[]{state,attendanceId})>=0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SignRecord> findAttendanceSignRecordByRepositoryId(
			int repositoryId, Date monthStartDay, Date monthEndDay) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_SIGN_RECORD t where t.respId = ? and t.createDate>= ? and t.createDate<= ? "
				+ " order by t.createDate asc");
		System.out.println("findAttendanceSignRecordByRepositoryId sql:"+sql);
		System.out.println("findAttendanceSignRecordByRepositoryId params:"+repositoryId+"/"+monthStartDay+"/"+monthEndDay);
		return getJdbcTemplate().query(sql.toString(),new Object[]{repositoryId,monthStartDay,monthEndDay},new SignRecordRowMapper());
	}

	@Override
	public boolean updateAttendanceSignRecord(int id, int isLate, int isEarly,
			int isAbsenteeism, int isNormal) {
		String sql=" update T_SIGN_RECORD set isLate = ?,isEarly =? , isAbsenteeism = ? ,isNormal =? where id = ?  ";
		return getJdbcTemplate().update(sql, new Object[]{isLate,isEarly,isAbsenteeism,isNormal,id})>=0;
	}
	
	

	/**
	 * 根据储备库id查询某个月的签到记录
	 */
	@Override
	public AttendanceInfo getAttendanceInfo(int respId, int  year,int month) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select info.* from t_attendance_info info"
				+"   inner join t_attendance a on info.attendanceId=a.id"
				+"   where 1=1 and info.respId =?"
				+ "  and a.year=?  and  a.month=? "
			    );
		return (AttendanceInfo)getJdbcTemplate().queryForObject(sql.toString(), new Object[] {respId,year,month}, new BeanPropertyRowMapper(AttendanceInfo.class));
	}

	 /**
     * 更新迟到，早退,旷工，实际出勤，是否全勤
     * @param info
     * @return
     */
	@Override
	public boolean updateAttendanceInfo(AttendanceInfo info) {
		String sql=" update t_attendance_info set  absenteeism=? , lateDay=? ,earlyDay=?  where id = ?  " ;
		return getJdbcTemplate().update(sql, new Object[]{info.getAbsenteeism(),info.getLateDay(),info.getEarlyDay(),info.getId()})>=0;
	}

	/**
	 * 考勤列表修改事假,病假,倒休，正休，加班
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateAttendance(int attendanceInfoId, String name,
			double value) {
		String sql=" update t_attendance_info set "+name+" =?  where id = ?  " ;
		System.out.println("updateAttendance sql:"+sql);
		return getJdbcTemplate().update(sql, new Object[]{value,attendanceInfoId})>=0;
		
	}

	@Override
	public List<SignRecord> findSignRecordListDateAndStatus(Date date, int isLate, int isEarly, int isAbsenteeism, int isNormal,int respId) {
		StringBuffer sql=new StringBuffer();
		List<Object> params=new ArrayList<Object>();
		sql.append(" select * from T_SIGN_RECORD t where 1=1 and to_char(t.createDate,'yyyyMMdd') = ? ");
		params.add(DateUtil.getStringFormatByDate(date, "yyyyMMdd"));
		if(isLate>0){
			sql.append(" and t.isLate = ? ");
			params.add(isLate);
		}
		if(isEarly>0){
			sql.append(" and t.isEarly = ? ");
			params.add(isEarly);
		}
		if(isAbsenteeism>0){
			sql.append(" and t.isAbsenteeism = ? ");
			params.add(isAbsenteeism);
		}
		if(isNormal>0){
			sql.append(" and t.isNormal = ? ");
			params.add(isNormal);
		}
		//是否正常传-1时为不正常
		if(isNormal<0){
			sql.append(" and t.isNormal = ? ");
			params.add(0);
		}
		if(respId>0){
			sql.append(" and t.respId = ? ");
			params.add(respId);
		}
		
		List<SignRecord> list;
		try {
			list=getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SignRecord.class));
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	@Override
	public List<SignRecord> findAbnormalSignRecordList(Date date,int respId) {
		StringBuffer sql=new StringBuffer();
		List<Object> params=new ArrayList<Object>();
		sql.append(" select * from T_SIGN_RECORD t where 1=1 and to_char(t.createDate,'yyyyMMdd') = ? ");
		params.add(DateUtil.getStringFormatByDate(date, "yyyyMMdd"));
		sql.append(" and (t.isLate = 1 or t.isEarly = 1 or t.isAbsenteeism = 1 or t.isNormal = 0) ");
		if(respId>0){
			sql.append(" and t.respId = ? ");
			params.add(respId);
		}
		List<SignRecord> list;
		try {
			list=getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SignRecord.class));
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	/**						
	 * 经销商考勤台账					
	 * @param query					
	 * @return					
	 */					
	public List<AttendanceTime> getAttendanceTimes(int dealerId,IThumbPageTools tools){					
		StringBuffer sql=new StringBuffer();				
		List<Object> params = new ArrayList<Object>();				
		/*sql.append("select b.dealername, a.* from t_attendance_time a join market_dealer b on a.id=b.id");				
		if (dealerId>0) {
			sql.append(" where b.id = ? ");
			
			params.add(dealerId);
		}
			
		sql.append(" order by a.id desc ");*/
		sql.append("select t.dealername,t.id as dealerid,a.* ");
		sql.append(" from market_dealer t ");
		sql.append(" left join t_brand tb on t.brandid = tb.id ");
		sql.append(" left join t_extend_distribset ed on ed.id = t.id ");
		sql.append(" left join t_distribset d on d.distribid = t.id ");
		sql.append(" left join market_dealer_supervisor mds on mds.dealerId = t.id ");
		sql.append(" left join t_bank b on mds.bankId = b.id ");
		sql.append(" left join t_attendance_time a on a.id=t.id ");
		sql.append(" where 1 = 1  ");
		if (dealerId>0) {
			sql.append(" and t.id = ? ");
			params.add(dealerId);
		}
		sql.append(" and t.cooperationstate = 1 ");
		List<AttendanceTime> list;				
		try {				
			//list=tools.goPage(sql.toString(),params.toArray(),new BeanPropertyRowMapper(DealerAttendanceTime.class));
			list=tools.goPage(sql.toString(),params.toArray(),new DealerAttendanceTimeMapper());
			System.out.println("SQL................"+dealerId);
		SqlUtil.debug(getDataSourceName(), sql.toString());		
		} catch (Exception e) {				
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题			
			SqlUtil.debug(getDataSourceName(), sql.toString());			
			e.printStackTrace();			
			list = null;			
		}				
		return list;				
	}

	/*
	 * 需求86
	*/

	@SuppressWarnings("unchecked")
	@Override
	public List<SignRecordCheckSpAll> findRespIdCS(SignRecordCheckSpAll query,
			IThumbPageTools tools) {
		StringBuffer sql=new StringBuffer();
		int currRole = query.getCurrentRole();
		List<Object> params = new ArrayList<Object>();	
		//sql.append("select distinct t.respId from t_sign_record t ");
		sql.append(" select distinct t.respid from t_sign_record t ");
		sql.append(" left join t_repository tr on t.respid = tr.id ");
		sql.append(" left join t_supervisor_basic_information tsb on tr.supervisorid = tsb.id  ");
		sql.append(" left join t_roster tro on t.respid = tro.repositoryid ");
		sql.append(" left join market_dealer_supervisor mds on t.respid  = mds.repositoryid ");
		sql.append(" left join market_dealer md on md.id = mds.dealerid ");
		sql.append(" left join t_region tre on tre.id = md.province ");
		sql.append(" left join t_region trg on trg.id = md.city ");
		sql.append(" left join t_bank tb on mds.bankid = tb.id ");
		sql.append(" where (select count(respid) from t_sign_record where respid in(select distinct t.respid from t_sign_record ) ");
		sql.append(" and to_char(createDate,'mm') = "+query.getMonth());
		if (currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()) {
			sql.append(" and state=3 or state=1 or state=2 ");
		}
		sql.append(") >0 ");
		
		if (query.getState()>0) {
			sql.append(" and t.state = ? and to_char(t.createDate,'mm') = ? ");
			params.add(query.getState());
			params.add(query.getMonth());
		}
		if (StringUtil.isNotEmpty(query.getStaffNo())) {
			sql.append(" and tro.staffno = ? ");
			params.add(query.getStaffNo());
		}
		if (StringUtil.isNotEmpty(query.getName())) {
			sql.append(" and tsb.name = ? ");
			params.add(query.getName());
		}
		if (StringUtil.isNotEmpty(query.getProvinceName())) {
			sql.append(" and tre.name like '%" + query.getProvinceName() + "%'  ");
		}
		if (StringUtil.isNotEmpty(query.getCityName())) {
			sql.append(" and trg.name like '%" + query.getCityName() + "%'  ");
		}
		if (StringUtil.isNotEmpty(query.getDealersName())) {
			sql.append(" and md.dealername like '%" + query.getDealersName() + "%' " );
		}
		if (StringUtil.isNotEmpty(query.getBanksName())) {
			sql.append(" and tb.bankfullname like '%" + query.getBanksName() + "%' ");
		}
		
		List<SignRecordCheckSpAll> list;				
		try {				
			list=tools.goPage(sql.toString(),params.toArray(),new BeanPropertyRowMapper(SignRecordCheckSpAll.class));
			System.out.println("findRespIdCS sql:"+sql.toString());
			SqlUtil.debug(getDataSourceName(), sql.toString());		
		} catch (Exception e) {				
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题			
			SqlUtil.debug(getDataSourceName(), sql.toString());			
			e.printStackTrace();			
			list = null;			
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SignRecordCheckSpAll> findSignRecordRespIdAndDayCheckCS(
			SignRecordCheckSpAll query) {
		StringBuffer sql=new StringBuffer();
		List<Object> params = new ArrayList<Object>();	
		sql.append("select t.respId,t.islate,t.isearly,t.isabsenteeism,t.isnormal,to_char(t.createdate,'yyyy') as year, ");
		sql.append(" to_char(t.createdate,'mm') as month,to_char(t.createdate,'dd') as time ");
		sql.append(" from t_sign_record t ");
		sql.append(" where 1=1 and t.respId = ? ");
		params.add(query.getRespId());
		if (query.getYear() >0) {
			sql.append(" and to_char(t.createdate,'yyyy') = ? ");
			params.add(query.getYear());
		}
		if (query.getMonth() >0 ) {
			sql.append("  and to_char(t.createdate,'mm')= ? ");
			params.add(query.getMonth());
		}
		System.out.println("==========="+query.getRespId()+"/"+query.getYear()+"/"+query.getMonth());
		List<SignRecordCheckSpAll> list;				
		try {				
			list=this.getJdbcTemplate().query(sql.toString(),params.toArray(),new BeanPropertyRowMapper(SignRecordCheckSpAll.class));
			System.out.println("findSignRecordRespIdAndDayCheckCS sql:"+sql.toString());
			SqlUtil.debug(getDataSourceName(), sql.toString());		
		} catch (Exception e) {				
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题			
			SqlUtil.debug(getDataSourceName(), sql.toString());			
			e.printStackTrace();			
			list = null;			
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SignRecordCheckSpAll> findSignRecordDealerByRespIdCS(
			SignRecordCheckSpAll query) {
		StringBuffer sql=new StringBuffer();
		List<Object> params = new ArrayList<Object>();	
		sql.append(" select b1.* ,tsr.updatedate,tsr.updateuserid,tsr.state,tsr.nextapproval  from ");
		sql.append(" (select tr.id as respId, tsb.name as name,tro.staffno as staffNo,md.province,md.city,tre.name as provinceName,trg.name as cityName, ");
		//sql.append(" ,md.dealername as dealersName,tb.bankfullname as banksName ");
		sql.append(" (select wm_concat(md.dealername) from market_dealer md " +
				" left join market_dealer_supervisor mds on mds.dealerid = md.id " +
				" where mds.repositoryid = ? )as dealersName, ");
		params.add(query.getRespId());
		sql.append(" (select wm_concat(tb.bankfullname) from t_bank tb " +
				" left join market_dealer_bank mdb on mdb.bankid = tb.id " +
				" left join market_dealer md on md.id = mdb.dealerid" +
				" left join market_dealer_supervisor mds on mds.dealerid = md.id" +
				" where mds.repositoryid = ? ) as banksName ");
		params.add(query.getRespId());
		sql.append(" from t_repository tr ");
		sql.append(" left join t_supervisor_basic_information tsb on tr.supervisorid = tsb.id ");
		sql.append(" left join t_roster tro on tr.id = tro.repositoryid ");
		sql.append(" left join market_dealer_supervisor mds on tr.id  = mds.repositoryid ");
		sql.append(" left join market_dealer md on md.id = mds.dealerid ");
		sql.append(" left join t_region tre on tre.id = md.province ");
		sql.append(" left join t_region trg on trg.id = md.city ");
		sql.append(" left join t_bank tb on mds.bankid = tb.id ");
		sql.append(" where tr.id =? " +
				" group by  tr.id ,tsb.name,tro.staffno, md.province, md.city, tre.name  , trg.name) b1 ");
		params.add(query.getRespId());
		sql.append(" left join (select tsr1.* from t_sign_record tsr1 where tsr1.respid = ? and to_char(tsr1.createdate,'mm')=?) tsr ");
		params.add(query.getRespId());
		params.add(query.getMonth());
		
		sql.append(" on tsr.respId = b1.respid ");
		sql.append(" group by b1.respId ,b1.name,b1.staffno, b1.province, b1.city, b1.provinceName  , b1.cityName, b1.dealersName , b1.banksName," +
				" tsr.updatedate,tsr.updateuserid,tsr.state,tsr.nextapproval ");
		
		
		List<SignRecordCheckSpAll> list;				
		try {				
			list=this.getJdbcTemplate().query(sql.toString(), params.toArray(),new SignRecordCheckSpAllMapper());
			System.out.println("findSignRecordDealerByRespIdCS sql:"+sql.toString());
			SqlUtil.debug(getDataSourceName(), sql.toString());		
		} catch (Exception e) {				
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题			
			SqlUtil.debug(getDataSourceName(), sql.toString());			
			e.printStackTrace();			
			list = null;			
		}
		return list;
	}

	
	@SuppressWarnings("unchecked")
	public List<SignRecordCheckSpAll> searchSignRecordCheckSpListDetails(
			int repositoryId, int year, int month){
		StringBuffer sql=new StringBuffer();
		List<Object> params = new ArrayList<Object>();	
		sql.append("select t.* from t_sign_record t where t.respId = ? and to_char(t.createDate,'yyyy')= ? and to_char(t.createDate,'mm')= ? ");
		params.add(repositoryId);
		params.add(year);
		params.add(month);
		sql.append(" order by t.createDate ");
		List<SignRecordCheckSpAll> list;
		try {				
			list=this.getJdbcTemplate().query(sql.toString(),params.toArray(),new BeanPropertyRowMapper(SignRecordCheckSpAll.class));
			System.out.println("searchSignRecordCheckSpListDetails sql:"+sql.toString());
			SqlUtil.debug(getDataSourceName(), sql.toString());		
		} catch (Exception e) {				
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题			
			SqlUtil.debug(getDataSourceName(), sql.toString());			
			e.printStackTrace();			
			list = null;			
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SignRecordCheckSpAll> ExtfindRespId(SignRecordCheckSpAll query) {
		StringBuffer sql=new StringBuffer();
		int currRole = query.getCurrentRole();
		List<Object> params = new ArrayList<Object>();	
		//sql.append("select distinct t.respId from t_sign_record t ");
		sql.append(" select distinct t.respid from t_sign_record t ");
		sql.append(" left join t_repository tr on t.respid = tr.id ");
		sql.append(" left join t_supervisor_basic_information tsb on tr.supervisorid = tsb.id  ");
		sql.append(" left join t_roster tro on t.respid = tro.repositoryid ");
		sql.append(" left join market_dealer_supervisor mds on t.respid  = mds.repositoryid ");
		sql.append(" left join market_dealer md on md.id = mds.dealerid ");
		sql.append(" left join t_region tre on tre.id = md.province ");
		sql.append(" left join t_region trg on trg.id = md.city ");
		sql.append(" left join t_bank tb on mds.bankid = tb.id ");
		sql.append(" where (select count(respid) from t_sign_record where respid in(select distinct t.respid from t_sign_record ) ");
		sql.append(" and to_char(createDate,'mm') = "+query.getMonth());
		if (currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()) {
			sql.append(" and state=3 or state=1 or state=2 ");
		}
		sql.append(") >0 ");
		
		if (query.getState()>0) {
			sql.append(" and t.state = ? and to_char(t.createDate,'mm') = ? ");
			params.add(query.getState());
			params.add(query.getMonth());
		}
		if (StringUtil.isNotEmpty(query.getStaffNo())) {
			sql.append(" and tro.staffno = ? ");
			params.add(query.getStaffNo());
		}
		if (StringUtil.isNotEmpty(query.getName())) {
			sql.append(" and tsb.name = ? ");
			params.add(query.getName());
		}
		if (StringUtil.isNotEmpty(query.getProvinceName())) {
			sql.append(" and tre.name like '%" + query.getProvinceName() + "%'  ");
		}
		if (StringUtil.isNotEmpty(query.getCityName())) {
			sql.append(" and trg.name like '%" + query.getCityName() + "%'  ");
		}
		if (StringUtil.isNotEmpty(query.getDealersName())) {
			sql.append(" and md.dealername like '%" + query.getDealersName() + "%' " );
		}
		if (StringUtil.isNotEmpty(query.getBanksName())) {
			sql.append(" and tb.bankfullname like '%" + query.getBanksName() + "%' ");
		}
		
		List<SignRecordCheckSpAll> list;				
		try {				
			list=this.getJdbcTemplate().query(sql.toString(),params.toArray(),new BeanPropertyRowMapper(SignRecordCheckSpAll.class));
			System.out.println("findRespIdCS sql:"+sql.toString());
			SqlUtil.debug(getDataSourceName(), sql.toString());		
		} catch (Exception e) {				
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题			
			SqlUtil.debug(getDataSourceName(), sql.toString());			
			e.printStackTrace();			
			list = null;			
		}
		return list;
	}

	@Override
	public boolean SubmitSignRecordCheckSpToApprove(int repositoryId, int year,
			int month) {
		String sql = "update t_sign_record t set t.nextapproval =?, t.state=? where t.id in" +
				"(select sr.id from t_sign_record sr where sr.respid=? and to_char(sr.createdate,'yyyy')=? and to_char(sr.createdate,'mm')=?)";
		System.out.println("SubmitSignRecordCheckSpToApprove sql:"+sql);
		System.out.println("SubmitSignRecordCheckSpToApprove params:"+repositoryId+"/"+year+"/"+month);
		return getJdbcTemplate().update(sql, new Object[]{9,3,repositoryId,year,month})>=0;
	}

	public boolean ApproveSignRecordCheckSpList(SignRecordCheckSpAll query){
		String sql = "update t_sign_record t set t.state=?, t.approvedate=?,t.approveopinion=?  where t.id in "+
				"(select sr.id from t_sign_record sr where sr.respid=? and to_char(sr.createdate,'yyyy')=? and to_char(sr.createdate,'mm')=?)";
		System.out.println("ApproveSignRecordCheckSpList sql:"+sql);
		System.out.println("ApproveSignRecordCheckSpList params:"+query.getState()+"/"+new Date()+"/"+query.getApproveOpinion()
				+"/"+query.getRespId()+"/"+query.getYear()+"/"+query.getMonth());
		return getJdbcTemplate().update(sql, new Object[]{query.getState(),new Date(),query.getApproveOpinion(),query.getRespId(),query.getYear(),query.getMonth()})>=0;
	}

	@Override
	public boolean updateSignRecord(SignRecord sr) {
		String sql = "update t_sign_record set isLate = ?,isEarly = ?,isAbsenteeism=?,isNormal=?,updateUserId=?,updateDate=?,state=? where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(sr.getIsLate());
		params.add(sr.getIsEarly());
		params.add(sr.getIsAbsenteeism());
		params.add(sr.getIsNormal());
		params.add(sr.getUpdateUserId());
		params.add(sr.getUpdateDate());
		params.add(sr.getState());
		params.add(sr.getId());
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}

	
}
